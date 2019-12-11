package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class NewReleasePrice extends Price{
    @Override
    public int getPriceCode(){
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return daysRented > 1 ? 2 : 1;
    }

}
