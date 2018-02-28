package cn.com.algorithm;

import org.junit.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到第k个数
 * 寻找最大的数： kMaxTh = arrayList.size()
 * 寻找最小的数： kMaxTh = 1
 *
 * @author: JinBiaoHuang
 * @description:
 * @Date: 2/26/2018
 * @modify by:
 */
public class FindKth {


    /**
     * 从数组中查找第k大的数
     *
     * @param arrayList
     * @param kTh 第k个元素
     * @return
     */
    public int select(List<Integer> arrayList, int kTh) {
        Assert.assertTrue("数组不包含任何元素", !CollectionUtils.isEmpty(arrayList));
        Assert.assertTrue(String.format("当前数组不存在第%s小的数",kTh),kTh <= arrayList.size());
        int median = getMedianOfMedian(arrayList);
        int pivotIndex = partition(arrayList, median);
        if (pivotIndex + 1 == kTh) {
            return median;
        } else if (kTh < pivotIndex + 1) {
            return select(arrayList.subList(0, pivotIndex), kTh);
        } else {
            return select(arrayList.subList(pivotIndex + 1, arrayList.size()), kTh - pivotIndex - 1);
        }
    }

    /**
     *
     * 以k为pivot，进行分区
     *
     * @param arrayList
     * @param pivot 枢轴
     */
    private int partition(List<Integer> arrayList, int pivot) {
        int i = -1, j = 0;
        for (; j < arrayList.size(); j++) {
            if (arrayList.get(j) < pivot) {
                i++;
                swap(arrayList, i, j);
            }
        }
        int pivotIndex = arrayList.indexOf(pivot);
        swap(arrayList, i + 1, pivotIndex);
        return i + 1;
    }

    /**
     * 1. 切分n个数。切分的结果是每个数组都只包含5个数。(不足的为n%5个数)
     * 2. 求中位数。为步骤1的每个数组求中位数。
     * 3. 返回中位数中的中位数。若步骤2中的结果只有一个，那么返回。否则重复步骤1。
     * @param arrayList
     * @return
     */
    private int getMedianOfMedian(List<Integer> arrayList) {
        int groupSize = (int) Math.ceil(arrayList.size() / 5.0);
        List<Integer> medianList = new ArrayList<>(groupSize);
        int j = 0;
        for (; j < groupSize - 1; j++) {
            medianList.add(getMedianNum(arrayList.subList(j * 5, j * 5 + 5)));
        }
        medianList.add(getMedianNum(arrayList.subList(j * 5, arrayList.size())));
        if (groupSize > 1) {
            return getMedianOfMedian(medianList);
        } else {
            return medianList.get(0);
        }
    }

    /**
     * 取中位数
     * @param array
     * @return
     */
    private Integer getMedianNum(List<Integer> array) {
        List<Integer> arrayList = new ArrayList<>(array.size());
        arrayList.addAll(array);
        Integer medianNum;
        switch (arrayList.size()) {
            case 0:
            case 1:
            case 2:
                medianNum = arrayList.get(0);
                break;
            case 3:
                medianNum = getMedianFrom3Num(arrayList);
                break;
            case 4:
                medianNum = getMedianFrom4Num(arrayList);
                break;
            case 5:
                medianNum = getMedianFrom5Num(arrayList);
                break;
            default:
                throw new RuntimeException("参数错误");
        }
        return medianNum;
    }

    /**
     * 求5个数中的中位数
     *
     * @param arrayList
     * @return
     */
    private Integer getMedianFrom5Num(List<Integer> arrayList) {
        boolean retBool;
        Integer medianNum;
        swapIfLowThan(arrayList, 0, 1);
        swapIfLowThan(arrayList, 2, 3);
        retBool = swapIfLowThan(arrayList, 0, 2);
        if (retBool) {
            swap(arrayList, 1, 3);
        }
        swapIfLowThan(arrayList, 1, 4);
        swapIfLowThan(arrayList, 1, 2);
        swapIfLowThan(arrayList, 2, 4);
        medianNum = arrayList.get(1);
        return medianNum;
    }

    /**
     * 求4个数中的中位数
     *
     * @param arrayList
     * @return
     */
    private Integer getMedianFrom4Num(List<Integer> arrayList) {
        boolean retBool;
        Integer medianNum;
        swapIfLowThan(arrayList, 0, 1);
        swapIfLowThan(arrayList, 2, 3);
        retBool = swapIfLowThan(arrayList, 0, 2);
        if (retBool) {
            swap(arrayList, 1, 3);
        }
        retBool = swapIfLowThan(arrayList, 1, 2);
        if (retBool) {
            swapIfLowThan(arrayList, 1, 3);
        }
        medianNum = arrayList.get(1);
        return medianNum;
    }

    /**
     * 从三个数中找出中位数
     *
     * @param arrayList
     * @return
     */
    private Integer getMedianFrom3Num(List<Integer> arrayList) {
        boolean retBool;
        Integer medianNum;
        swapIfLowThan(arrayList, 0, 1);
        retBool = swapIfLowThan(arrayList, 0, 2);
        if (retBool) {
            swap(arrayList, 1, 2);
        } else {
            swapIfLowThan(arrayList, 1, 2);
        }
        medianNum = arrayList.get(1);
        return medianNum;
    }


    /**
     * 如果数组内targetIndex位置的数 > 数组内compareIndex位置的数
     *
     * @param arrayList
     * @param targetIndex
     * @param compareIndex
     * @return
     */
    private boolean swapIfLowThan(List<Integer> arrayList, int targetIndex, int compareIndex) {
        if (arrayList.get(targetIndex) > arrayList.get(compareIndex)) {
            swap(arrayList, targetIndex, compareIndex);
            return true;
        }
        return false;
    }

    /**
     * 交换数据内两个数据
     *
     * @param arrayList
     * @param i         待交换数据的位置
     * @param j         待交换数据的位置
     */
    private void swap(List<Integer> arrayList, int i, int j) {
        int temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }

}



