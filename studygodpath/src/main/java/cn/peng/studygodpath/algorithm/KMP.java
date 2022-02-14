package cn.peng.studygodpath.algorithm;

/**
 * KMP 字符串匹配算法
 */
public class KMP {

    public static void main(String[] args) {
        System.out.println(violenceFor("123", "2311231"));
    }

    /**
     * 暴力破解
     */
    public static int violenceFor(String pat, String txt) {
        int n = pat.length(), m = txt.length();
        for (int i = 0; i < m - n; i++) {
            int j = 0;
            for (; j < n; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }
            if (j == n)
                return i;
        }
        return -1;
    }


}
