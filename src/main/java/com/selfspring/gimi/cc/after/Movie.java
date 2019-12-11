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
    private Price price;

    public Movie() {
    }

    public Movie(String title, Integer type) {
        this.title = title;
        this.setPrice(type);
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
    public Price getPrice() {
        return price;
    }

    public void setPrice(Integer type) {
        switch (type) {
            case CHILDRENS:
                price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                price = new ChildrensPrice();
                break;
            case REGULAR:
                price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("不支持的类型") ;
        }
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

    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}