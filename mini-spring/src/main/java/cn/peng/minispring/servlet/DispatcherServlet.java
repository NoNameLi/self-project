package cn.peng.minispring.servlet;

import cn.peng.minispring.annotation.*;
import cn.peng.minispring.util.PackageUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@WebServlet(name = "dispatcherServlet", urlPatterns = "/", loadOnStartup = 1, initParams = {@WebInitParam(name = "base-package", value = "cn.peng.minispring")})
public class DispatcherServlet extends HttpServlet {

    private String basePackage = "";

    // 指定包含的包名
    private List<String> packageNames = new ArrayList<>();

    // 注解的值:类实例
    private Map<String, Object> instacnceMap = new HashMap<>();

    // 全限定名 注解上的名
    private Map<String, String> nameMap = new HashMap<>();

    // url : 方法
    private Map<String, Method> urlMethodMap = new HashMap<>();

    // 方面 和限定类名的映射 根据方法找到对象
    private Map<Method, String> methodPackageMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        StringBuffer url = req.getRequestURL();// 返回整个URL的请求路径
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();// 返回项目上下文
        String path = url.replace(contextPath, "");

        Method method = urlMethodMap.get(path);
        String pachageName = methodPackageMap.get(method);
        Object obj = instacnceMap.get(nameMap.get(pachageName));
        try {
            // 参数 ？ 参数应该还需要 一个map 保存方法的参数 以及参数上的注解数据
            method.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        basePackage = config.getInitParameter("base-package");

        try {
            // 指定扫描包
            this.scanBasePackage(this.basePackage);

            // 实例化扫描到的类（有 Controller Repository Service 注解的类）
            this.instance();

            // ioc 依赖注入
            this.ioc();

            // url 方法映射
            handdlerUrlMethodMap();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void handdlerUrlMethodMap() {
        Set<String> nameSet = instacnceMap.keySet();
        Object instance;
        Class<?> clazz = null;
        String baseUrl = "";
        for (String name : nameSet) {
            instance = instacnceMap.get(name);
            clazz = instance.getClass();
            if (clazz.isAnnotationPresent(Controller.class)) {
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    baseUrl = clazz.getAnnotation(RequestMapping.class).value();
                }
                List<Method> methodList = MethodUtils.getMethodsListWithAnnotation(clazz, RequestMapping.class, true, true);
                for (Method method : methodList) {
                    urlMethodMap.put(baseUrl + method.getAnnotation(RequestMapping.class).value(), method);
                    methodPackageMap.put(method, clazz.getName());
                }

            }
        }

    }

    public void ioc() throws IllegalAccessException {
        Set<String> nameSet = instacnceMap.keySet();
        for (String name : nameSet) {
            Object instance = instacnceMap.get(name);
            List<Field> fieldList = FieldUtils.getAllFieldsList(instance.getClass());
            for (Field field : fieldList) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    String value = field.getAnnotation(Qualifier.class).value();
                    if (StringUtils.isBlank(value)) {
                        value = nameMap.get(field.getType().getName());
                    }
                    field.setAccessible(true);
                    field.set(instance, instacnceMap.get(value));
                }
            }
        }
    }

    /**
     * 实例化 对象 是判读 component 和是 都判读？spring 如何实现的？
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void instance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String name : packageNames) {
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(name.replace(".class", ""));

            String instanceName = getInstanceAnnotationValue(clazz);
            if (instanceName != null) {
                if (StringUtils.isBlank(instanceName)) {
                    String clazzName = clazz.getName().substring(clazz.getName().lastIndexOf(PackageUtil.point) + 1);
                    instanceName = new StringBuilder().append(Character.toLowerCase(clazzName.charAt(0))).append(clazzName.substring(1)).toString();
                }
                nameMap.put(name, instanceName);
                instacnceMap.put(instanceName, clazz.newInstance());
            }
        }
    }

    private String getInstanceAnnotationValue(Class<?> clazz) {
        if (!clazz.isAnnotation()) {
            if (clazz.isAnnotationPresent(Service.class)) {
                return clazz.getAnnotation(Service.class).value();
            } else if (clazz.isAnnotationPresent(Controller.class)) {
                return clazz.getAnnotation(Controller.class).value();
            } else if (clazz.isAnnotationPresent(Repository.class)) {
                return clazz.getAnnotation(Repository.class).value();
            } else if (clazz.isAnnotationPresent(Component.class)) {
                return clazz.getAnnotation(Component.class).value();
            }
        }
        return null;
    }


    //扫描基包下的类，得到信息。PathMatchingResourcePatternResolver
    public void scanBasePackage(String basePackage) throws IOException {
        String path = PackageUtil.packageName2Path(basePackage);
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);

        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            if ("jar".equals(url.getProtocol())) {
                // jar 包
                scanJarFile(basePackage, ((JarURLConnection) url.openConnection()).getJarFile());
            } else if ("file".equals(url.getProtocol())) {
                scanClassFile(basePackage, URLDecoder.decode(url.getPath(), "UTF-8"));
            }
        }
    }

    private void scanClassFile(String packageName, String filePath) {
        File base = new File(filePath);
        if (!base.exists() || !base.isDirectory()) {// 不存在 不是文件夹 直接返回
            return;
        }
        File[] file = base.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));
        String pn = null;
        for (File f : file) {
            pn = PackageUtil.packageNameJoin(packageName, f.getName().replace(".class", ""));
            if (f.isDirectory()) {
                scanClassFile(pn, f.getPath());
            } else {
                packageNames.add(pn);
            }
        }
    }

    private void scanJarFile(String basePackage, JarFile jar) {
        String path = PackageUtil.packageName2Path(basePackage);
        Enumeration<JarEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            JarEntry element = entries.nextElement();
            if (!element.isDirectory() && element.getName().startsWith(path) && element.getName().endsWith(".class")) {
                packageNames.add(element.getName().substring(0, element.getName().length() - 6));
            }
        }
    }


    //@Controller/@Service/@Repository注解而言，我们需要拿到对应的名称，并初始化它们修饰的类，形成映射关系B。

    //发现有@Qualifier的话，我们需要完成注入。

    //扫描@RequestMapping，完成URL到某一个Controller的某一个方法上的映射关系C。


    public List<String> getPackageNames() {
        return this.packageNames;
    }

}
