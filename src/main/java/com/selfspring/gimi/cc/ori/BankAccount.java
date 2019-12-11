package com.selfspring.gimi.cc.ori;

/**
 * Created by ckyang on 2019/12/11.
 */
public class BankAccount {
    private BankAccountType type;
    private int daysOverdrawn;
    private double interestRate;

    public double overdraftCharge(){
        if(type.isPremium()){
            double result = 10;
            if(daysOverdrawn > 7){
                result += (daysOverdrawn - 7) * 0.85;
                return result;
            }
        }
        return daysOverdrawn * 1.75;

    }

    private double bankCharge() {
        double result = 4.5;
        if(daysOverdrawn > 0){
            result += overdraftCharge();
        }
        return result;
    }

    public double interestForAmount_days(double amount,int days){
       return interestRate * amount * days / 365;
    }
}
