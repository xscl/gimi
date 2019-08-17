package com.selfspring.gimi.algo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ckyang on 2019/7/23.
 */
public class Algorithm {
    public static int[] twoSum(int[] nums, int target) {
        Map map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int dev = target - nums[i];
            if(map.containsKey(dev)){
                return new int[]{i, (int) map.get(dev)};
            }
        }
        throw new IllegalArgumentException("不存在两个数之后等于"+target);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        ListNode r = null;
        int out = 0;
        while(p != null && q != null){
            int sum = p.getValue() + q.getValue() + out;
            out = sum - 10 >= 0 ? 1 : 0;
            int rValue = out == 0 ? sum : sum - 10;
            if(r == null){
                r = new ListNode(rValue,null);
            }else{
                r.setNext(new ListNode(rValue,null));
            }
        }
        if(p != null){

        }
        return null;
    }

    @Data
    @AllArgsConstructor
    static class ListNode{
        private int value;
        private ListNode next;


    }
}
