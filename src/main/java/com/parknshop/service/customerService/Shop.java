package com.parknshop.service.customerService;

/**
 * Created by wei on 16-12-11.
 */
public class Shop {

    public int getShopId() {
        return shopId;
    }

    public Shop setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

    public String getShopName() {
        return shopName;
    }

    public Shop setShopName(String shopName) {
        this.shopName = shopName;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Shop setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public Shop setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public int getViews() {
        return views;
    }

    public Shop setViews(int views) {
        this.views = views;
        return this;
    }

    public String getTips() {
        return tips;
    }

    public Shop setTips(String tips) {
        this.tips = tips;
        return this;
    }


    public Shop(int shopId, String shopName, String introduction, String logo, int views) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.introduction = introduction;
        this.logo = logo;
        this.views = views;
    }

    public Shop(int shopId, String shopName, String introduction, String logo, int views, String tips) {
        this.shopId = shopId;
        this.shopName = shopName;
        this.introduction = introduction;
        this.logo = logo;
        this.views = views;
        this.tips = tips;
    }


    int shopId;
    String shopName;
    String introduction;
    String logo;
    int views;
    String tips="";
}
