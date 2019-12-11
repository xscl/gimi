package com.selfspring.gimi.cc.after;

/**
 * Created by ckyang on 2019/12/12.
 */
public class PersonRF {
    private String name;
    private PhoneNumber phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeAreaCode() {
        return phoneNumber.getOfficeAreaCode();
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.phoneNumber.setOfficeAreaCode(officeAreaCode);
    }

    public String getOfficeNumber() {
        return this.phoneNumber.getOfficeNumber();
    }

    public void setOfficeNumber(String officeNumber) {
        this.phoneNumber.setOfficeNumber(officeNumber);
    }
}
