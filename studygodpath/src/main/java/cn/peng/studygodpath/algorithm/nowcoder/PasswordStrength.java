package cn.peng.studygodpath.algorithm.nowcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 密码按如下规则进行计分，并根据不同的得分为密码进行安全等级划分。
 * <p>
 * 一、密码长度:
 * 5 分: 小于等于4 个字符
 * 10 分: 5 到7 字符
 * 25 分: 大于等于8 个字符
 * <p>
 * 二、字母:
 * 0 分: 没有字母
 * 10 分: 密码里的字母全都是小（大）写字母
 * 20 分: 密码里的字母符合”大小写混合“
 * <p>
 * 三、数字:
 * 0 分: 没有数字
 * 10 分: 1 个数字
 * 20 分: 大于1 个数字
 * <p>
 * 四、符号:
 * 0 分: 没有符号
 * 10 分: 1 个符号
 * 25 分: 大于1 个符号
 * <p>
 * 五、奖励（只能选符合最多的那一种奖励）:
 * 2 分: 字母和数字
 * 3 分: 字母、数字和符号
 * 5 分: 大小写字母、数字和符号
 * <p>
 * 最后的评分标准:
 * >= 90: 非常安全
 * >= 80: 安全（）
 * >= 70: 非常强
 * >= 60: 强（）
 * >= 50: 一般（Average）
 * >= 25: 弱（）
 * >= 0:  非常弱（）
 */
public class PasswordStrength {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String password = br.readLine();
        System.out.println(passwordStrength(password));
    }

    public static String passwordStrength(String password) {
        int[] num = charNum(password);
        int score = lengthScore(num[0]) + charScore(num[0], num[2], num[3]) + numScore(num[0], num[1]) + symbolScore(num[0], num[4])
                + rewardScore(num);
        if (score >= 90) {
            return "VERY_SECURE";
        } else if (score >= 80) {
            return "SECURE";
        } else if (score >= 70) {
            return "VERY_STRONG";
        } else if (score >= 60) {
            return "STRONG";
        } else if (score >= 50) {
            return "AVERAGE";
        } else if (score >= 25) {
            return "WEAK";
        } else {
            return "VERY_WEAK";
        }
    }

    /**
     *
     */
    public static int[] charNum(String password) {
        int smallCharCount = 0, bigCharCount = 0, numCount = 0, symbolCount = 0;
        char[] chars = password.toCharArray();
        for (int i = 0, j = chars.length; i < j; i++) {
            if (chars[i] >= '0' && chars[i] < '9') {
                numCount++;
            } else if (chars[i] >= 'a' && chars[i] < 'z') {
                smallCharCount++;
            } else if (chars[i] >= 'A' && chars[i] < 'Z') {
                bigCharCount++;
            } else {
                symbolCount++;
            }
        }
        return new int[]{password.length(), numCount, smallCharCount, bigCharCount, symbolCount};
    }

    public static int rewardScore(int[] num) {
        if (num[1] > 0 && num[2] > 0 && num[3] > 0 && num[4] > 0) {
            return 5;
        } else if (num[1] > 0 && num[2] + num[3] > 0 && num[4] > 0) {
            return 3;
        } else if (num[1] > 0 && num[2] + num[3] > 0) {
            return 2;
        } else {
            return 0;
        }
    }

    public static int symbolScore(int length, int symbolCount) {
        if (symbolCount == 0) {
            return 0;
        } else if (symbolCount == 1) {
            return 10;
        } else {
            return 25;
        }

    }

    public static int numScore(int length, int numCount) {
        if (numCount == 0) {
            return 0;
        } else if (numCount == 1) {
            return 10;
        } else {
            return 20;
        }
    }

    public static int charScore(int length, int smallChar, int bigChar) {
        int sum = smallChar + bigChar;
        if (smallChar == 0 && bigChar == 0) {
            return 0;
        } else if (smallChar == sum || bigChar == sum) {
            return 10;
        } else if (smallChar > 0 && bigChar > 0) {
            return 20;
        }
        return 0;
    }

    public static int lengthScore(int length) {
        if (length <= 4) {
            return 5;
        } else if (length <= 7) {
            return 10;
        } else {
            return 25;
        }
    }


}
