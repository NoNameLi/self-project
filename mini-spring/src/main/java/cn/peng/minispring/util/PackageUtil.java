package cn.peng.minispring.util;

import java.io.File;

/**
 * 包相关工具类
 */
public class PackageUtil {

    public static final String point = ".";

    public static final String slant = "/";

    public static String packageName2Path(String packageName) {
        return packageName.replace(".", slant);
    }

    public static String path2PackageName(String path) {
        return path.replace(slant, ".");
    }

    public static String packageNameJoin(String basePackage, String... joinNames) {
        if (joinNames == null || joinNames.length == 0) {
            return basePackage;
        }
        if (basePackage.endsWith(point)) {
            basePackage = basePackage.substring(0, basePackage.length() - 1);
        }
        StringBuilder sb = new StringBuilder(basePackage);
        for (String name : joinNames) {
            sb.append(point).append(name);
        }
        return sb.toString();
    }

}
