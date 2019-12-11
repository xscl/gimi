package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public abstract class Price {
    abstract int getPriceCode();
    abstract double getCharge(int daysRented);
    abstract int getFrequentRenterPoints(int daysRented);
}
