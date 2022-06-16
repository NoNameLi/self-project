package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 输入一个只包含小写英文字母和数字的字符串，按照不同字符统计个数由多到少输出统计结果，如果统计的个数相同，则按照ASCII码由小到大排序输出。
 * 数据范围：字符串长度满足 1 \le len(str) \le 1000 \1≤len(str)≤1000
 * <p>
 * 输入描述：
 * 一个只包含小写英文字母和数字的字符串。
 * <p>
 * 输出描述：
 * 一个字符串，为不同字母出现次数的降序表示。若出现次数相同，则按ASCII码的升序输出。
 */
public class CharNum {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] chars = br.readLine().toCharArray();
        TreeMap<Integer, Set<Character>> sortMap = new TreeMap<>(Comparator.reverseOrder());
        Map<Character, Integer> charNum = new HashMap<>();

        for (char aChar : chars) {
            if (charNum.containsKey(aChar)) {
                charNum.put(aChar, charNum.get(aChar) + 1);
            } else {
                charNum.put(aChar, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : charNum.entrySet()) {
            Set<Character> set = sortMap.computeIfAbsent(entry.getValue(), k -> new TreeSet<>(Comparator.naturalOrder()));
            set.add(entry.getKey());
        }
        Iterator<Map.Entry<Integer, Set<Character>>> entryIterator = sortMap.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (entryIterator.hasNext()) {
            Map.Entry<Integer, Set<Character>> entry = entryIterator.next();
            entry.getValue().forEach(t -> sb.append(t));
        }
        System.out.println(sb);
    }
}
