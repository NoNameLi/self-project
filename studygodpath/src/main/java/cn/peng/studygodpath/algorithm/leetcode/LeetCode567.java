package cn.peng.studygodpath.algorithm.leetcode;

import java.util.HashMap;

/**
 * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 */
public class LeetCode567 {

    public boolean checkInclusion(String s1, String s2) {
        // s1 短 s2 长
        int length1 = s1.length(), length2 = s2.length();

        HashMap<Character, Integer> str2Char = new HashMap<>(), str1Char = new HashMap<>();
        for (int i = 0; i < length1; i++) {
            putMap(str1Char, s1.charAt(i));
            putMap(str2Char, s2.charAt(i));
        }
        for (int i = length1; i < length2; i++) {
            putMap(str2Char, s2.charAt(i));
            removeMap(str2Char, s2.charAt(i - length1));
            if (mapContextEqual(str1Char, str2Char)) {
                return true;
            }
        }

        return false;
    }

    public static boolean mapContextEqual(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        if (map1.size() == map2.size()) {
            for (Character key : map1.keySet()) {
                if (map1.get(key).equals(map2.get(key))) {
                    continue;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public static void removeMap(HashMap<Character, Integer> map, Character character) {
        if (map.containsKey(character)) {
            map.put(character, map.get(character) - 1);
        }
    }

    public static void putMap(HashMap<Character, Integer> map, Character character) {
        if (map.containsKey(character)) {
            map.put(character, map.get(character) + 1);
        } else {
            map.put(character, 1);
        }
    }
}
