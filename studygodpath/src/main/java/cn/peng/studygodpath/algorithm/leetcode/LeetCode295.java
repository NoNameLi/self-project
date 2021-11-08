package cn.peng.studygodpath.algorithm.leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-07 10:31
 * QDescription:
 */
public class LeetCode295 {
    /**
     *数据流的中位数。中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * 例如，
     *      [2,3,4] 的中位数是 3，
     *      [2,3] 的中位数是 (2 + 3) / 2 = 2.5。
     * 设计一个支持以下两种操作的数据结构：
     *      void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     *      double findMedian() - 返回目前所有元素的中位数。
     * 示例：
     *      addNum(1)，addNum(2)，findMedian() -> 1.5，addNum(3) ，findMedian() -> 2。
     * 进阶:
     *      如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     *          用长度未100的数组存整数的个数，并又两个指针指向中位数，每次计算两个指针
     *      如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法
     *          同上，超过100的数 存在另外的数据中
     */
    @Test
    public void testMedinaNum() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(2);
        finder.addNum(3);
        finder.addNum(3);
        System.out.println(finder.fidnMedian());
    }

    /**
     * 把数据流分成连个序列： 分成 奇偶 or 偶偶 or 奇奇
     * 利用两个堆，大顶堆（存小序列的值）、小顶堆（存大序列的值）,并保证两个堆的个数相差不超过一，这样两个对顶就是数据流的中位数
     */
    class MedianFinder {
        PriorityQueue<Integer> maxh;
        PriorityQueue<Integer> minh;

        public MedianFinder() {
            maxh = new PriorityQueue<>((x, y) -> y - x);
            minh = new PriorityQueue<>((x, y) -> x - y);
        }

        public void addNum(int num) {
            if (minh.isEmpty() || minh.peek() > num) {
                minh.add(num);
            } else {
                maxh.add(num);
            }
            balance();
        }

        public double fidnMedian() {
            if (maxh.size() == minh.size()) {
                return (maxh.peek() + minh.peek()) / 2.0;
            } else if (maxh.size() > minh.size()) {
                return maxh.peek();
            } else {
                return minh.peek();
            }
        }

        /**
         * 确保两个堆的size 相差不超过1
         */
        private void balance() {
            int min = minh.size(), max = maxh.size();
            if (min > max && min - max > 1) {
                maxh.add(minh.poll());
            } else if (min < max && max - min > 1) {
                minh.add(maxh.poll());
            }
        }

    }

}
