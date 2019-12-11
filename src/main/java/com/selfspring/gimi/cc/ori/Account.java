package com.selfspring.gimi.cc.ori;

/**
 * Created by ckyang on 2019/12/11.
 */
public class Account {
    private int quality;
    private int itemPrice;
    private int balance;
    private double primaryForce;
    private double mass;
    private int delay;
    private double secondForce;

    public int gamma(int inputVal,int quality,int yearToDate){
        int importantVal1 = inputVal * quality + delta();
        int importantVal2 = inputVal * yearToDate + 100;
        if(yearToDate - importantVal1 > 100){
            importantVal2 -= 20;
        }
        int importantVal3 = importantVal2 * 7;
        return importantVal3 - 2 * importantVal1;
    }

    private int delta() {
        return 0;
    }
}
