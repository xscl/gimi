package com.selfspring.gimi.cc.ori;

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

        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration<Rental> rentals = _rentals.elements();

        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {

            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            switch (each.getMovie().getType()) {
                case Movie.CHILDRENS:

                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3) {
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:

                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.REGULAR:

                    thisAmount += 2;
                    if (each.getDaysRented() > 2) {
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    }
                    break;

                default:
                    break;
            }

            frequentRenterPoints++;

            if (each.getMovie().getType() == Movie.NEW_RELEASE && each.getDaysRented() > 1) frequentRenterPoints++;

            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;

        }

        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";

        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points ";

        return result;
    }


}

