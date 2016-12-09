package com.parknshop.service.customerService;

/**
 * Created by wei on 16-12-9.
 */
public class Product {
    public int getGoodsId() {
        return goodsId;
    }

    public Product setGoodsId(int goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Product setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public Product setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public String getGoodsIntroduction() {
        return goodsIntroduction;
    }

    public Product setGoodsIntroduction(String goodsIntroduction) {
        this.goodsIntroduction = goodsIntroduction;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Product setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public Product setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public int getShopId() {
        return shopId;
    }

    public Product setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public Product setTips(String tips) {
        this.tips = tips;
        return this;
    }

    int goodsId;
    String goodsName;
    double price;
    double discount;
    String goodsIntroduction;
    String picture;
    String shopName;
    int shopId;
    String tips = "";
}
