package com.parknshop.bean;

import java.util.List;

/**
 * Created by Cong on 2017/1/3.
 */
public class GetFinishOrderResponseBean {


    /**
     * error : true
     * total : 9
     * realSize : 5
     * data : [{"ordersId":1,"ownerId":1,"userByUserId":1,"goodsByGoodsId":1,"goodsName":" String","createTime":"String","reciver":"String","price":12.5,"state":4}]
     */

    private boolean error;
    private int total;
    private int realSize;
    private List<DataBean> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRealSize() {
        return realSize;
    }

    public void setRealSize(int realSize) {
        this.realSize = realSize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ordersId : 1
         * ownerId : 1
         * userByUserId : 1
         * goodsByGoodsId : 1
         * goodsName :  String
         * createTime : String
         * reciver : String
         * price : 12.5
         * state : 4
         */

        private int ordersId;
        private int ownerId;
        private int userByUserId;
        private int goodsByGoodsId;
        private String goodsName;
        private String createTime;
        private String reciver;
        private double price;
        private int state;

        public int getOrdersId() {
            return ordersId;
        }

        public void setOrdersId(int ordersId) {
            this.ordersId = ordersId;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public int getUserByUserId() {
            return userByUserId;
        }

        public void setUserByUserId(int userByUserId) {
            this.userByUserId = userByUserId;
        }

        public int getGoodsByGoodsId() {
            return goodsByGoodsId;
        }

        public void setGoodsByGoodsId(int goodsByGoodsId) {
            this.goodsByGoodsId = goodsByGoodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getReciver() {
            return reciver;
        }

        public void setReciver(String reciver) {
            this.reciver = reciver;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
