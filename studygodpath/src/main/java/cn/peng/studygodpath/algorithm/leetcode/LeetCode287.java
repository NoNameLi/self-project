package cn.peng.studygodpath.algorithm.leetcode;

import org.junit.Test;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-01 23:08
 * QDescription:
 */
public class LeetCode287 {

    /**
     * 寻找重复数。给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。力扣287。
     */
    public int findDuplicate(int arr[]) {
        /**
         * 再此问题中，将数组类比成链表，arr[x]  下一个节点：arr[arr[x]] 因为数组内容从1~n
         *
         * 求环形链表的入环点
         *      快慢指针方案
         *          慢指针从0开始每次自增1，快指针从0开始每次自增2，当快慢指针相遇，另一个指针（cur）从0开始自增1，当指针和慢指针相遇时，就是如如环点
         *          证明：
         *          慢指针距离：L + X
         *          快指针距离：L + X + nR
         *          2(L + X) = L + X + nR
         *          L = nR -x
         *          L = (n - 1)R + (R - X)
         *          即：让cur再从0开始自增，走了L，此时慢指针走了 (n-1)圈，加上剩余的圈，再入环点和 cur指针相遇
         */
        int slow = arr[0], fast = arr[arr[0]];
        while (slow != fast) {
            slow = arr[slow];// 移动下一个节点
            fast = arr[arr[fast]];// 移动下下一个节点
        }
        fast = arr[0];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return fast;
    }

    static class Node<T> {
        private T value;
        private Node next;

        public static <T> Node instance(T value) {
            return new Node(value);
        }

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this(value);
            this.next = next;
        }

        public Node nextN(Node next) {
            this.next = next;
            return next;
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value + '}';
        }
    }

    /**
     * 通用版
     */
    public Node findDuplicateCommon(Node head) {
        if (null == head || head.next == null) {
            return null;
        }
        Node slow = head.next, fast = head.next.next;
        while (fast != null && slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast != null){
            fast = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return fast;
    }

    @Test
    public void test() {
        int[] arr = new int[]{2, 1, 1};
        System.out.println(findDuplicate(arr));
        Node head = Node.instance("a");
        Node point = Node.instance("b");
        head.nextN(point).nextN(Node.instance("c")).nextN(Node.instance("d")).nextN(head);
        System.out.println(findDuplicateCommon(head));
    }
}
