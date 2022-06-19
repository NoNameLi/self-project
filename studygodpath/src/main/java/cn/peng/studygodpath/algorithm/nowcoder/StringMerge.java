package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 按照指定规则对输入的字符串进行处理。
 * <p>
 * 详细描述：
 * <p>
 * 第一步：将输入的两个字符串str1和str2进行前后合并。如给定字符串 "dec" 和字符串 "fab" ， 合并后生成的字符串为 "decfab"
 * <p>
 * 第二步：对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。这里的下标的意思是字符在字符串中的位置。注意排序后在新串中仍需要保持原来的奇偶性。例如刚刚得到的字符串“decfab”，分别对下标为偶数的字符'd'、'c'、'a'和下标为奇数的字符'e'、'f'、'b'进行排序（生成 'a'、'c'、'd' 和 'b' 、'e' 、'f'），再依次分别放回原串中的偶数位和奇数位，新字符串变为“abcedf”
 * <p>
 * 第三步：对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作。
 * 转换规则如下：
 * 对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符（注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
 * 如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
 * 如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
 * 如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是 '3'。
 * 根据这个转换规则，由第二步生成的字符串 “abcedf” 转换后会生成字符串 "5D37BF"。
 * <p>
 * <p>
 * 数据范围：输入的字符串长度满足\1≤n≤100
 */
public class StringMerge {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        char[] result = merge(s[0], s[1]);
        System.out.println(trans(result));
    }

    public static char[] merge(String str1, String str2) {
        char[] chars = (str1 + str2).toCharArray();
        int mid = chars.length / 2;//0 2 4 6   1 3 5 7
        char[] sort1 = new char[chars.length - mid], sort2 = new char[mid];
        for (int i = 0, m = 0, n = 0, j = chars.length; i < j; i++) {
            if (i % 2 == 0) {
                sort1[m] = chars[i];
                m++;
            } else {
                sort2[n] = chars[i];
                n++;
            }
        }
        Arrays.sort(sort1);
        Arrays.sort(sort2);
        int i = 0, n = 0;
        for (int j = sort2.length; i < j; i++) {
            chars[n++] = sort1[i];
            chars[n++] = sort2[i];
        }
        if (sort1.length > sort2.length) {
            chars[n] = sort1[i];
        }
        return chars;
    }

    public static String trans(char[] input) {
        for (int i = 0, j = input.length; i < j; i++) {
            if ((input[i] >= '0' && input[i] <= '9') || (input[i] >= 'A' && input[i] <= 'F') || (input[i] >= 'a' && input[i] <= 'f')) {
                input[i] = transCode(input[i]);
            }
        }
        return new String(input);
    }

    public static char transCode(char hex) {
        //字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，转换为十六进制的大写字母 3
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(Integer.parseInt(String.valueOf(hex), 16)));
        while (builder.length() < 4) {
            builder.insert(0, '0');
        }
        return Integer.toHexString(Integer.parseInt(builder.reverse().toString(), 2)).toUpperCase().charAt(0);
    }

}
