package cn.peng.studygodpath.algorithm.leetcode;

import cn.peng.studygodpath.algorithm.leetcode.help.ListNode;

/*
 * You are given two non-empty linked lists representing two non-negative integers.
 * 你会被给两个非空的链接的列表去代表两个非负的int
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * 数字以颠倒的顺序存储，并且 每一个结点包含一个数字
 * Add the two numbers and return it as a linked list.
 * 两个数字相加 并且 以连接的列表返回结果
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * 你可以假定 两个数字包含任何首位0，除非这个数字本身就是0
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *  2 -> 4 -> 0
 *  5 -> 6 -> 4
 *  7 -> 0 -> 5
 * 42 + 465 = 507
 *
 *  这实际上是大数加法
 * */
public class AddTwoNumbers {

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode c1 = l1, c2 = l2;
        int l1Size = 0, l2Size = 0;
        for (; c1 != null; c1 = c1.next) {
            l1Size++;
        }
        for (; c2 != null; c2 = c2.next) {
            l2Size++;
        }


        return l1;
    }

    public void dealAdd(ListNode i, ListNode j) {
        i.val = i.val + j.val;
        carry(i);
    }

    public void carry(ListNode i) {
        if (i != null) {
            int ten = i.val / 10;
            i.val = i.val % 10;
            if (ten != 0) {
                if (null == i.next) i.next = new ListNode(ten);
                else i.next.val = i.next.val + ten;
            }
            carry(i.next);
        }
    }

    /**
     * 最优
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode c1 = l1, c2 = l2;
        ListNode resutl = new ListNode(0), r = resutl;
        int remainder = 0, carry = 0;
        for (; c1 != null || c2 != null || carry != 0; r = r.next) {
            remainder = 0;
            if (c1 != null) {
                remainder += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                remainder += c2.val;
                c2 = c2.next;
            }
            remainder += carry;
            r.next = new ListNode(remainder % 10);
            carry = remainder / 10;
        }
        return resutl.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode c1 = l1, c2 = l2;
        int l1Size = 0, l2Size = 0;
        for (; c1 != null; c1 = c1.next) {
            l1Size++;
        }
        for (; c2 != null; c2 = c2.next) {
            l2Size++;
        }
        if (l1Size >= l2Size) {
            c1 = l1;
            c2 = l2;
        } else {
            c1 = l2;
            c2 = l1;
        }
        int ten;
        for (; c2 != null; c1 = c1.next, c2 = c2.next) {
            c1.val = c1.val + c2.val;
            ten = c1.val / 10;
            c1.val = c1.val % 10;
            if (ten != 0) {
                if (null == c1.next) c1.next = new ListNode(ten);
                else c1.next.val = c1.next.val + ten;
            }
        }
        for (; c1 != null; c1 = c1.next) {
            ten = c1.val / 10;
            c1.val = c1.val % 10;
            if (ten != 0) {
                if (null == c1.next) c1.next = new ListNode(ten);
                else c1.next.val = c1.next.val + ten;
            }
        }
        return l1Size >= l2Size ? l1 : l2;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        l1.next.next.next = new ListNode(9);
//        l1.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next.next.next.next = new ListNode(9);
//        l1.next.next.next.next.next.next.next.next.next = new ListNode(9);

//        System.out.print(new AddTwoNumbers().nodeToInt(l1));

        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);
        print(new AddTwoNumbers().addTwoNumbers(l1, l2));
//        print(new AddTwoNumbers().addTwoNumbers2(l1, l2));
//        print(new AddTwoNumbers().addTwoNumbers3(l2, l1));
//        print(new AddTwoNumbers().intToNode(256));
    }

    public static void print(ListNode header) {
        for (ListNode node = header; node != null; node = node.next) {
            System.out.print(node.val);
        }
    }


}
