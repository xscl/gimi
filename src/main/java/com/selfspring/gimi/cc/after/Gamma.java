package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class Gamma {


    private int inputVal;
    private int quality;
    private int yearToDate;

    public Gamma(int inputVal, int quality, int yearToDate) {
        this.inputVal = inputVal;
        this.quality = quality;
        this.yearToDate = yearToDate;
    }


    public int compute(int delta) {
        int importantVal1 = inputVal * quality + delta;
        int importantVal2 = inputVal * yearToDate + 100;
        if(yearToDate - importantVal1 > 100){
            importantVal2 -= 20;
        }
        int importantVal3 = importantVal2 * 7;
        return importantVal3 - 2 * importantVal1;
    }
}
