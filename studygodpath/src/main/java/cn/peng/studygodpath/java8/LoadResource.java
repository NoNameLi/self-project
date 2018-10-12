package cn.peng.studygodpath.java8;


import java.util.ResourceBundle;

/**
 * Created by remote on 2018/4/14.
 */
public class LoadResource {

    protected ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public String getValue(String key) {
        System.out.println(resourceBundle.getBaseBundleName());
        return resourceBundle.getString(key);
    }


    public static void main(String[] args) {
        LoadResource resource = new LoadResource();
        System.out.println(resource.getValue("server.port"));
    }


}
