package com.selfspring.gimi.cc.ori;

/**
 * Created by ckyang on 2019/12/11.
 */
public class Query {
    private int quality;
    private int itemPrice;
    private int balance;
    private double primaryForce;
    private double mass;
    private int delay;
    private double secondForce;

    public double getPrice(){
        int basePrice = quality * itemPrice;
        double discountFor;
        if (basePrice > 1000) {
            discountFor = 0.95;
        }else{
            discountFor = 0.98;
        }
        return basePrice * discountFor;
    }

    public double price(){
        return quality * itemPrice -
                Math.max(0,quality - 500) * itemPrice * 0.05
                + Math.min(quality * itemPrice * 0.1,100);
    }

    public double getDistanceTravellad(int time){
        double result;
        double acc = primaryForce / mass;
        int primaryTime = Math.min(time,delay);
        result = 0.5 *acc * primaryTime * primaryTime;
        int secondTime = time - delay;
        if(secondTime > 0){
            double primaryVel = acc * delay;
            acc = (primaryForce + secondForce) / mass;
            result += primaryVel * secondTime +
                    0.5 * acc * secondTime* secondTime;
        }
        return result;
    }
}
