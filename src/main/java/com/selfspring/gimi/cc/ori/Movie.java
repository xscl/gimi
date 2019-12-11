package com.selfspring.gimi.cc.ori;

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
    /**
     * 影片的类型
     */
    private Integer type;

    public Movie() {
    }

    public Movie(String title, Integer type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

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
}