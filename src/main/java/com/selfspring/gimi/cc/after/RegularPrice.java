package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class RegularPrice extends Price{
    @Override
    public int getPriceCode(){
        return Movie.REGULAR;
    }

    @Override
    double getCharge(int daysRented) {
        double result = 2.0;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
