package com.selfspring.gimi.cc.after;

public class Rental {

    /**
     * 影片
     */
    Movie movie;

    /**
     * 租用的天数
     */
    private int daysRented;

    public Rental() {
    }

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }

}