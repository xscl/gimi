package com.selfspring.gimi.algo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * Created by ckyang on 2019/7/23.
 */
public class AlgorithmTest {

    @Test
    public void testTwoSum() throws Exception {
        System.out.println(JSON.toJSONString(Algorithm.twoSum(new int[]{2,7,11,13},9)));

    }
}