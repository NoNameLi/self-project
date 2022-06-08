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
//        LoadResource resource = new LoadResource();
//        System.out.println(resource.getValue("server.port"));
//        Scanner scanner = new Scanner(System.in);

//        char b = scanner.next().charAt(0);
//        char c = (char) (b >= 97 ? b - 32 : b + 32);
//        char d = b >= 'A' && b < 'z' ? (char) (b >= 'a' ? b - 32 : b + 32) : b;

//        System.out.println((int) '1');
//        System.out.println((int) 'A');
        String a = "0xAA";
        int num = 0;
        for (int i = a.length() - 1, j = 0; i >= 2; i--, j++) {
            char item = a.charAt(i);
            num += (item < 58 ? item - 48 : item - 55) * (16 ^ j);
            System.out.println(num);
            int test = 5;
            double sqrt = Math.sqrt(test);

        }

        System.out.println(num);
    }


}
