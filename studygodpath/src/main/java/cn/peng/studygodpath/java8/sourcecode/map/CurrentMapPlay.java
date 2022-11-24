package cn.peng.studygodpath.java8.sourcecode.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-27 11:18
 * QDescription:
 */
public class CurrentMapPlay {

    public static void main(String[] args) {
        Map<String, Integer> map = new ConcurrentHashMap<>();

        map.computeIfAbsent("aa", key -> map.computeIfAbsent("bb", key2 -> 10));

        System.out.print(map.toString());
    }
}
