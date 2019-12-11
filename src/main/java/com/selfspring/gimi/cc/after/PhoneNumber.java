package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/12.
 */
public class PhoneNumber {
    private String officeAreaCode;
    private String officeNumber;

    public String getOfficeAreaCode() {
        return officeAreaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.officeAreaCode = officeAreaCode;
    }


    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getTelephoneNumber(){
        return "(" + officeAreaCode + ")" + officeNumber;
    }
}
