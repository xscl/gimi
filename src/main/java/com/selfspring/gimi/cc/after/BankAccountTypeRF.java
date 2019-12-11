package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class BankAccountTypeRF {

    private double interestRate;

    public boolean isPremium() {
        return false;
    }

    double overdraftCharge(int daysOverdrawn) {
        if(isPremium()){
            double result = 10;
            if(daysOverdrawn > 7){
                result += (daysOverdrawn - 7) * 0.85;
                return result;
            }
        }
        return daysOverdrawn * 1.75;
    }


    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
