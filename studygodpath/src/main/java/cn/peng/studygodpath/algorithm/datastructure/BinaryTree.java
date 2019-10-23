package cn.peng.studygodpath.algorithm.datastructure;

import lombok.Getter;
import lombok.Setter;

/**
 * 二叉树 实现
 */
@Getter
@Setter
public class BinaryTree {
    private Node root;
    private int count;
    private Comparable[] refArr;
    private int offrset;

    public void addNode(Comparable data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
        } else {
            root.addNode(newNode);
        }
        count++;
    }

    public Comparable[] toArray() {
        if (null != root) {
            refArr = new Comparable[this.count];
            offrset = 0;
            root.toMidArray();
            return refArr;
        } else {
            return null;
        }
    }


    @Getter
    @Setter
    public class Node {
        private Comparable data;
        private Node left;
        private Node right;


        public Node(Comparable data) {
            this.data = data;
        }

        public void addNode(Node newNode) {
            if (this.data.compareTo(newNode.data) > 0) { // 左子樹
                if (this.left == null) {
                    this.left = newNode;
                } else {
                    this.left.addNode(newNode);
                }
            } else { // 右子樹
                if (this.right == null) {
                    this.right = newNode;
                } else {
                    this.right.addNode(newNode);
                }
            }
        }

        public void toMidArray() {
            if (this.left != null) {
                this.left.toMidArray();
            }
            refArr[offrset++] = this.data;
            if (this.right != null) {
                this.right.toMidArray();
            }
        }
    }


}
