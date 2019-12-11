package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class AccountRF {


    public int gamma(int inputVal, int quality, int yearToDate) {

        return new Gamma(inputVal, quality, yearToDate).compute(delta());
    }


    public int delta() {
        return 0;
    }
}
