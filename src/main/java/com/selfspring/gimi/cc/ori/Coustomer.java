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

    private Vector rentals = new Vector();

    public Coustomer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        //总费用
        double totalAmount = 0;
        // 积分数
        int integralNum = 0;
        Enumeration erts = rentals.elements();
        String result = "租用的顾客名字为： " + getName() + "\n";
        result += "=========================================\n";
        while (erts.hasMoreElements()) {
            // 每个影片的费用
            double thisAmount = 0;
            Rental rental = (Rental) erts.nextElement();

            int daysRented = rental.getDaysRented();
            Integer type = rental.getMovie().getType();
            switch (type) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (daysRented > 2) {
                        thisAmount += (daysRented - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += daysRented * 3;
                    break;

                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (daysRented > 3) {
                        thisAmount += (daysRented - 3) * 1.5;
                    }
                    break;
            }

            integralNum++;
            // 如果是租用新片则累积多加1积分
            if (type == Movie.NEW_RELEASE && daysRented > 1) {
                integralNum++;
            }

            //打印租用影片详情
            result += "影片名称：" + rental.getMovie().getTitle() + "\t 影片类型：" + rental.getMovie().getMovieTypeName(type)
                    + "\n";
            result += "租期：" + daysRented + "天\t 费用：" + thisAmount + "元\n";
            result += "----------------------------------------------------\n";
            totalAmount += thisAmount;
        }

        result += ">>>>>>>>>>>>>>>>>>总费用：" + totalAmount + "元<<<<<<<<<<<<<<<<<<<< \n";
        result += ">>>>>>>>>>>>>>>>>>本次积分：" + integralNum + "<<<<<<<<<<<<<<<<<<<<";
        return result;
    }

}

