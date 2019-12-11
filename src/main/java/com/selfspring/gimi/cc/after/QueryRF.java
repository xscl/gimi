package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class QueryRF {
    private int quality;
    private int itemPrice;
    private int balance;

    private double primaryForce;
    private double mass;
    private int delay;
    private double secondForce;

    public double getPrice() {
        return getBasePrice() * getDiscountFor();
    }

    private double getDiscountFor() {
        double discountFor;
        if (getBasePrice() > 1000) {
            discountFor = 0.95;
        } else {
            discountFor = 0.98;
        }
        return discountFor;
    }

    private int getBasePrice() {
        return quality * itemPrice;
    }

    public double price() {
        final int basePrice = quality * itemPrice;
        final double qualityDiscount = Math.max(0, quality - 500) * itemPrice * 0.05;
        final double shipping = Math.min(basePrice * 0.1, 100);
        return basePrice - qualityDiscount + shipping;
    }


    public double getDistanceTravellad(int time) {
        double result;
        final double primaryAcc = primaryForce / mass;
        int primaryTime = Math.min(time, delay);
        result = 0.5 * primaryAcc * primaryTime * primaryTime;
        int secondTime = time - delay;
        if (secondTime > 0) {
            double primaryVel = primaryAcc * delay;
            final double secondAcc = (primaryForce + secondForce) / mass;
            result += primaryVel * secondTime +
                    0.5 * secondAcc * secondTime * secondTime;
        }
        return result;
    }
}
