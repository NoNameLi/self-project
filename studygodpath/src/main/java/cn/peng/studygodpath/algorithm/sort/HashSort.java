package cn.peng.studygodpath.algorithm.sort;

import org.junit.Test;

public class HashSort implements Sort {

    @Override
    public void sort(Integer[] data) {
        for (int gap = data.length / 2; gap > 0; gap = gap / 2) {
            // 所有分组的第二个元素 开始插入排序
            for (int i = gap; i < data.length; i++) {
                Integer temp = data[i];
                int k = i;
                for (; k - gap >= 0 && temp < data[k - gap]; k = k - gap) {
                    data[k] = data[k - gap];
                }
                data[k] = temp;
            }
        }
    }

    @Test
    public void test() {
        HashSort hash = new HashSort();
        hash.execSort();
    }

}
