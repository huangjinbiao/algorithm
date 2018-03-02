package cn.com.algorithm;

import java.util.List;

/**
 * 找到一个峰值点
 * 一维版本
 * 二维版本
 * @author: JinBiaoHuang
 * @description:
 * @Date: 3/2/2018
 * @modify by:
 */
public class FindPeak {

    /**
     * 基于一维版本的峰值点查找(仅需找到一个)
     * @param arr : 不为空，且有值
     * @return
     */
    public int findAPeakBaseOneDimensional(List<Integer> arr) {
        if (arr.size() == 1) {
            return arr.get(0);
        }
        if (arr.size() == 2) {
            return arr.get(0) > arr.get(1) ? arr.get(0) : arr.get(1);
        }
        int median = arr.size() / 2;
        if (arr.get(median) < arr.get(median - 1)) {
            return findAPeakBaseOneDimensional(arr.subList(0, median));
        }
        if (arr.get(median) < arr.get(median + 1)) {
            return findAPeakBaseOneDimensional(arr.subList(median + 1, arr.size()));
        }
        return arr.get(median);
    }

    /**
     * 找到二维数组中的一个峰值
     * @param arr
     * @param minColumn 列的中间找起
     * @return
     */
    public int findAPeakBaseTwoDimensional(int[][] arr,int minColumn) {
        int column=arr[0].length;
        int maxRowIndex = getMaxRowIndex(arr, minColumn);
        int target = arr[maxRowIndex][minColumn];
        //与左边对比，小则向左找峰值
        if(minColumn > 0 && target< arr[maxRowIndex][minColumn-1]){
          return  findAPeakBaseTwoDimensional(arr,minColumn -1);
        }
        //与右边对比，小则向右找峰值
        if(minColumn+1 < column  && target< arr[maxRowIndex][minColumn+1]){
           return findAPeakBaseTwoDimensional(arr,minColumn + 1);
        }
        //垂直方向上它是最大，水平方向上也是最大，所以它就是peak
        return target;
    }

    /**
     * 获取指定列上最大值的行下标
     * @param arr 二维数组
     * @param minColumn 指定列
     * @return
     */
    private int getMaxRowIndex(int[][] arr, int minColumn) {
        int max = -1,maxRowIndex = 0;
        for(int i=0,rowLen=arr.length;i<rowLen;i++){
             if( max < arr[i][minColumn] ){
                 max = arr[i][minColumn];
                 maxRowIndex = i;
             }
        }
        return maxRowIndex;
    }

}
