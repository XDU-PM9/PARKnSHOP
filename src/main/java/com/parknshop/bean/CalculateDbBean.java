package com.parknshop.bean;

import java.util.Date;

/**
 * Created by weina on 2016/12/29.
 */
public class CalculateDbBean {

    Date date;
    Double price;
    public CalculateDbBean(){

    }

    public CalculateDbBean(Date date, Double price) {
        this.date = date;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }



    public void setPrice(Double price) {
        this.price = price;
    }
}
