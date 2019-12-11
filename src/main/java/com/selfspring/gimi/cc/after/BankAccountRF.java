package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/11.
 */
public class BankAccountRF {
    private BankAccountTypeRF type;
    private int daysOverdrawn;
    private double interestRate;

    public double overdraftCharge(){
        return type.overdraftCharge(daysOverdrawn);

    }

    private double bankCharge() {
        double result = 4.5;
        if(daysOverdrawn > 0){
            result += type.overdraftCharge(daysOverdrawn);
        }
        return result;
    }


    public double interestForAmount_days(double amount,int days){
        return type.getInterestRate() * amount * days / 365;
    }

}
