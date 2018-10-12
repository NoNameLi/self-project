package cn.peng.studygodpath.design.build.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

/**
 * Created by remote on 2017/7/11.
 */
public class Test {

    // @org.junit.Test
    public void testSerializable() throws Exception {
        URL resource = this.getClass().getResource("object.txt");
        Singleton7 singleton = Singleton7.getInstance();
        ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream(resource.getFile()));
        oops.writeObject(singleton);
        oops.close();
        System.out.println(singleton.toString());
        ObjectInputStream oips = new ObjectInputStream(new FileInputStream(resource.getFile()));
        Singleton7 singleton2 = (Singleton7) oips.readObject();
        oips.close();
        System.out.println(singleton2.toString());
        System.out.println(singleton.equals(singleton2));
        Singleton6.install.otherMethod();
        
    }
}
