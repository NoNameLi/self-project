package cn.peng.nacos.example.spring;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    /**
     * 设置spring容器启动的入口
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * 设置DispatcherServlet的拦截路径
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
