package com.selfspring.gimi.cc.after;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 顾客
 *
 * @author:dufy
 * @version:1.0.0
 * @date 2019/1/21
 */
public class Coustomer {

    private String _name;

    private Vector _rentals = new Vector();

    public Coustomer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental rental) {
        _rentals.addElement(rental);
    }

    public String getName() {
        return _name;
    }

    public String statement() {

        Enumeration<Rental> rentals = _rentals.elements();

        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";

        }

        result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points ";

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        Enumeration<Rental> rentals = _rentals.elements();
        int frequentRenterPoints = 0;
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            frequentRenterPoints += each.getMovie().getFrequentRenterPoints(each.getDaysRented());
        }
        return frequentRenterPoints;
    }

    private double getTotalAmount() {
        Enumeration<Rental> rentals =_rentals.elements();
        double totalAmount = 0;
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            totalAmount += each.getCharge();
        }
        return totalAmount;
    }


}

