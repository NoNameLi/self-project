package cn.peng.studygodpath.java8.overLoad;

import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by remote on 2018/1/9.
 */
public class TestMethod {


    @Test
    public void abstractMethod() {
        ExtendsChildren children = new ExtendsChildren();
        children.hello();
    }

    @Test
    public void childrenCallParentStatic() {
        Children ch = new Children();
        ch.add();
    }

    @Test
    public void testParentChildrenMehtod() {
        Children children = new Children();
        Parent parent = children;
        System.out.println(children.getName());
        System.out.println(parent.getName());
        System.out.println(children.equals(parent));
    }


    @Test
    public void OverLoadTest() {
        System.out.println("string" + null);
//        Map<String, String> map = new HashMap<>();
//        map.put("a","a");
//        map.put("b","b");
//        System.out.println(getUrlParamsByMap(null));
    }

    public static String getUrlParamsByMap(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        if (null != params && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
            // 减去最后一个&
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
