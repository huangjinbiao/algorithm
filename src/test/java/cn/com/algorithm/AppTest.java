package cn.com.algorithm;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest  extends TestCase {

    /**二维数组中找峰值*/
    @Test
    public void testFindPeakBaseTwoDimensional(){

        int[][] arr = new int[][]{
                {10,8,10,10},
                {14,13,12,11},
                {15,9,11,21},
                {16,17,19,20}
        };
        findPeakBase2DimensionalInternal(arr,21);

        arr = new int[][]{
                {10,8,10,10}
        };
        findPeakBase2DimensionalInternal(arr,10);

        arr = new int[][]{
                {10},
                {20},
                {100},
        };
        findPeakBase2DimensionalInternal(arr,100);
    }

    private void findPeakBase2DimensionalInternal(int[][] arr,int expectPeakValue) {
        FindPeak fp = new FindPeak();
        int peak = fp.findAPeakBaseTwoDimensional(arr,arr[0].length/2);
        Assert.assertTrue(peak == expectPeakValue );
    }

    /**一维数组中找峰值*/
    @Test
    public void testFindPeakBaseOneDimensional(){
        testFindPeakBase1DimensionalInternal(getCustomerArray(1),1);
        testFindPeakBase1DimensionalInternal(getCustomerArray(1,2),2);
        testFindPeakBase1DimensionalInternal(getCustomerArray(6,7,4,3,2,1,4,5),7);
    }

    /**中位数的中位数*/
    @Test
    public void  testFindMedianOfMedian(){
        //1个数
        testMedianOfMedianInternal(getCustomerArray(1),1);
        //2个数
        testMedianOfMedianInternal(getCustomerArray(1,2),1);
        testMedianOfMedianInternal(getCustomerArray(2,1),1);
        //3个数
        testMedianOfMedianInternal(getCustomerArray(1,2,3),2);
        testMedianOfMedianInternal(getCustomerArray(3,2,1),2);
        testMedianOfMedianInternal(getCustomerArray(2,1,3),2);
        testMedianOfMedianInternal(getCustomerArray(1,3,2),2);
        //4个数
        testMedianOfMedianInternal(getCustomerArray(1,2,3,4),2);
        testMedianOfMedianInternal(getCustomerArray(4,3,2,1),2);
        testMedianOfMedianInternal(getCustomerArray(2,1,3,4),2);
        testMedianOfMedianInternal(getCustomerArray(1,3,4,2),2);
        //5个数
        testMedianOfMedianInternal(getCustomerArray(1,2,3,4,5),3);
        testMedianOfMedianInternal(getCustomerArray(5,4,3,2,1),3);
        testMedianOfMedianInternal(getCustomerArray(3,1,2,4,5),3);
        testMedianOfMedianInternal(getCustomerArray(1,2,4,5,3),3);
        testMedianOfMedianInternal(getCustomerArray(10,5,9,11,2),9);
    }
    /**
     * 从n个数中找到第k小的数
     */
    @Test
    public void testFindKTh() {
        findKthInternal(1,1,1);
        findKthInternal(5,3,3);
        findKthInternal(5,1,1);
        findKthInternal(100,10,10);
    }

    private void findKthInternal(int arrayLen,int kth,int median) {
        FindKth findKth = new FindKth();
        int ret = findKth.select(getArray(arrayLen), kth);
        Assert.assertTrue(String.format("找第%s小的数错误",kth),ret == median);
    }

    private List<Integer> getCustomerArray(Integer... arr) {
        List<Integer> arrayList = new ArrayList<>(arr.length);
        arrayList.addAll(Arrays.asList(arr));
        return arrayList;
    }

    /**
     * 生成测试数据
     * @param len
     * @return
     */
    private static List<Integer> getArray(int len) {
        List<Integer> arrayList = new ArrayList<>(16);
        for(int i=1;i<=len;i++){
            arrayList.add(i);
        }
        return arrayList;
    }

    private void testMedianOfMedianInternal(List<Integer> arr,int median){
        FindKth findKth = new FindKth();
        int ret = findKth.getMedianNum(arr);
        Assert.assertTrue(String.format("median of median of %s member of array wrong",arr.size()),ret == median);
    }

    private void testFindPeakBase1DimensionalInternal(List<Integer> customerArray, int peak) {
        FindPeak findPeak = new FindPeak();
        int ret = findPeak.findAPeakBaseOneDimensional(customerArray);
        Assert.assertTrue("find peak wrong",ret == peak);
    }
}
