package cn.com.algorithm;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest  extends TestCase {

    /**
     * 从n个数中找到第k小的数
     */
    @Test
    public void  testFindKTh(){
         FindKth findKth = new FindKth();
         int ret = findKth.select(getArray(5),3);
         Assert.assertTrue(ret == 3);

        ret = findKth.select(getArray(1),1);
        Assert.assertTrue(ret == 1);

        ret = findKth.select(getArray(100),50);
        Assert.assertTrue(ret == 50);
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



}
