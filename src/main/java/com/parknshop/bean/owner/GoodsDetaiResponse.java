package com.parknshop.bean.owner;

import java.util.Date;

/**
 * Created by SONG on 2016/12/20.
 */
public class GoodsDetaiResponse {
    boolean success;

    DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        private int id;
        private String name;
        private String desc;
        private double price;
        private double discount;
        private Date createTime;
        private int inventory;
        private int views;
        private int state;
        private String type;
        private String postWay;
        private String[] photos;


        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public String getPostWay() {
            return postWay;
        }

        public void setPostWay(String postWay) {
            this.postWay = postWay;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getDiscount() {
            return discount;
        }

        public void setDiscount(double discount) {
            this.discount = discount;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String[] getPhotos() {
            return photos;
        }

        public void setPhotos(String[] photos) {
            this.photos = photos;
        }
    }
}
