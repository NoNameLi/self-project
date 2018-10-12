package cn.peng.studygodpath.algorithm.sort;

import org.junit.Test;

/**
 * 堆排序 建立 数组和 堆之间的映射
 * 
 * @author remote
 *
 */
public class HeapSort implements Sort {

    @Override
    public void sort(Integer[] data) {
        // 1. 建立大顶堆
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            ajustHeap(data, i, data.length);
        }
        for (int i = data.length - 1; i > 0; i--) {
            // 2. 交换堆顶和堆尾
            swap(data, 0, i);
            // 3. 去除堆尾 重新建立大顶堆
            ajustHeap(data, 0, i);
        }
    }

    /**
     * @param data
     *            树
     * @param item
     *            要调整的节点 的位置
     * @param length
     *            树的总个数
     */
    public void ajustHeap(Integer[] data, int item, int length) {
        Integer temp = data[item];
        for (int k = 2 * item + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && data[k] < data[k + 1]) {
                k++;
            }
            if (temp < data[k]) {
                data[item] = data[k];
                item = k;
            } else {
                // 没有调整 可以直接退出循环 没有调整子树也就没有改变
                break;
            }
        }
        // 调整完将 temp 放在最终值上
        data[item] = temp;
    }

    @Test
    public void test() {
        Integer[] arr = { 40, 41, 11, 26, 80, 36, 43, 17, 34, 5 };
        HeapSort heap = new HeapSort();
        heap.execSort();
        heap.sort(arr);
    }
}
