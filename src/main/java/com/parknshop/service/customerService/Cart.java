package com.parknshop.service.customerService;

/**
 * Cart类为POJO，用于购物车中一条记录中需要的所有具体数据（不要直接使用该类，请通过调用IGetProductList中相应函数完成）
 * Created by wei on 16-12-9.
 */
public class Cart {
    int cartId;
    int goodsId;
    int amount;
    double price;
    double discount;
    String goodsName;
    String goodsIntroduction;
    String picture;
    String shopName;
    int shopId;

    public int getGoodsAmount() {
        return goodsAmount;
    }

    public Cart setGoodsAmount(int goodsAmount) {
        this.goodsAmount = goodsAmount;
        return this;
    }

    int goodsAmount;
    String tips = "";

    public int getCartId() {
        return cartId;
    }

    public Cart setCartId(int cartId) {
        this.cartId = cartId;
        return this;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public Cart setGoodsId(int goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public Cart setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Cart setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public String getGoodsIntroduction() {
        return goodsIntroduction;
    }

    public Cart setGoodsIntroduction(String goodsIntroduction) {
        this.goodsIntroduction = goodsIntroduction;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Cart setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public Cart setTips(String tips) {
        this.tips = tips;
        return this;
    }


    public double getPrice() {
        return price;
    }

    public Cart setPrice(double price) {
        this.price = price;
        return this;
    }

    public double getDiscount() {
        return discount;
    }

    public Cart setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public Cart setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public int getShopId() {
        return shopId;
    }

    public Cart setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

}
