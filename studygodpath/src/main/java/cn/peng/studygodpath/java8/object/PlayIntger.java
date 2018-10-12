package cn.peng.studygodpath.java8.object;

import org.testng.annotations.Test;

/**
 * Created by remote on 2018/1/31.
 *
 * Integer 有 最大值（2^31-1） 和最小值(2^31)
 * 类型（Type）为  int 但Class 还是为Integer
 *
 *静态变量：digits 为2进制到36进制的 转换数组 进制出现的最大值
 *静态变量：sizeTable 为某些位数的最大值（9,99,999。。。。。）
 *
 *静态方法 toString(i，radix) 将Integer 对象转换成 某进制的字符串表示，
 *      转换成10进制的字符串 使用toString(i);
 *      转换成其他进制 先将正数转为负数，然后循环对进制取余 ，余数取负再通过 digits 找到进制的等价值 复制到char数组 最后一个值直接复制的char数组中 最后在将负号符号加上
 *静态方法 toString(i) 将int 转换成10进制字符串 int 最小值 直接返回固定值
 *      先计算数值的长度，使用stringSize(i),这个方法只能计算正数的长度，因此需要将负值取正，再加一 即为负值的长度
 *
 *
 *静态方法 stringSize(x) 计算数值的长度 在此方法中 使用静态常量 sizeTable，小于数组的下标加1即为正数的位数
 *
 *静态方法 getChars(i,size,buff) 将size长度中 i 放在buff 中
 *
 *
 *
 *
 *
 *
 *
 */
public class PlayIntger {

    @Test
    public void MaxMin(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE) +":" + Integer.toBinaryString(Integer.MAX_VALUE).length());
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE + 1) + ":" + Integer.toBinaryString(Integer.MAX_VALUE + 1).length());
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.TYPE);
        System.out.println(Integer.class);
        System.out.println(Math.abs(Integer.MAX_VALUE));
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Integer.valueOf(Integer.MAX_VALUE).BYTES);
        System.out.println(Integer.valueOf(Integer.MAX_VALUE).toString());
        System.out.println(Integer.toString(Integer.MAX_VALUE,16));
        System.out.print(Integer.toHexString(Integer.MAX_VALUE));
    }
}
