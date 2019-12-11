package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class ChildrensPrice extends Price{

    @Override
    public int getPriceCode(){
        return Movie.CHILDRENS;
    }


    @Override
    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

    @Override
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
