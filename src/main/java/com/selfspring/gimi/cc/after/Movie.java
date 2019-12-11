package com.selfspring.gimi.cc.after;

public class Movie {

    /**
     * 普通片
     */
    public static final int REGULAR = 0;
    /**
     *新片
     */
    public static final int NEW_RELEASE = 1;
    /**
     * 儿童片
     */
    public static final int CHILDRENS = 2;

    /**
     * 影片的名称
     */
    private String title;
    private Integer type;

    public Movie() {
    }

    public Movie(String title, Integer type) {
        this.title = title;
        this.setType(type);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 影片的类型
     */
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMovieTypeName(int type){
        String name = "";
        if(REGULAR == type){
            name = "普通片";
        }else if(NEW_RELEASE == type){
            name = "新片";
        }else if(CHILDRENS == type){
            name = "儿童片";
        }else{
            name = "未知";
        }
        return name;
    }

    double getCharge(int daysRented) {
        double result = 0.0;
        switch (getType()) {
            case CHILDRENS:

                result += 1.5;
                if (daysRented > 3) {
                    result += (daysRented - 3) * 1.5;
                }
                break;
            case NEW_RELEASE:

                result += daysRented * 3;
                break;
            case REGULAR:

                result += 2;
                if (daysRented > 2) {
                    result += (daysRented - 2) * 1.5;
                }
                break;

            default:
                break;
        }
        return result;
    }

    public int getFrequentRenterPoints(int daysRented) {
        int result = 1;

        if (getType() == NEW_RELEASE && daysRented > 1) {
            result++;
        }
        return result;
    }
}