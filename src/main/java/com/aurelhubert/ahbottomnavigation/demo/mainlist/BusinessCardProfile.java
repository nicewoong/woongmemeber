package com.aurelhubert.ahbottomnavigation.demo.mainlist;

/**
 * Created by nicewoong on 2017. 10. 28..
 */

public class BusinessCardProfile {


    public String name;
    public String role;
    public String company;
    public String image;
    public int imageDrawableID;
    public BusinessCardProfile(String name, String role, String company, int imageDrawableID) {
        this.name = name;
        this.role = role;
        this.company = company;
        this.imageDrawableID = imageDrawableID;
    }
}
