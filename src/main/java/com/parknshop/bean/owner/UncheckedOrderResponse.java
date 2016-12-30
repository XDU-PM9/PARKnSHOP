package com.parknshop.bean.owner;

import java.util.Date;
import java.util.List;

/**
 * Created by SONG on 2016/12/28.
 */
public class UncheckedOrderResponse {

    /**
     * success : true
     * data : [{"ordersId":123,"orderNumber":"123","singleGoodId":123,"photo":"123.jpg","goodsName":"123","goodsDescribe":"123","amount":123,"createTime":"123","paidTime":"123","price":12.3,"commission":12.3,"commissionRate":12.3,"comment":"123","commentTime":"123","ownerId":123,"state":123,"address":"123","reciverPhone":"123","reciver":"123","postWay":"123"}]
     */

    private boolean success;
    private int current;
    private int total;
    private int count;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ordersId : 123
         * orderNumber : 123
         * singleGoodId : 123
         * photo : 123.jpg
         * goodsName : 123
         * goodsDescribe : 123
         * amount : 123
         * createTime : 123
         * paidTime : 123
         * price : 12.3
         * commission : 12.3
         * commissionRate : 12.3
         * comment : 123
         * commentTime : 123
         * ownerId : 123
         * state : 123
         * address : 123
         * reciverPhone : 123
         * reciver : 123
         * postWay : 123
         */

        private int ordersId;
        private String orderNumber;
        private int singleGoodId;
        private String photo;
        private String goodsName;
        private String goodsDescribe;
        private int amount;
        private Date createTime;
        private Date paidTime;
        private double price;
        private double commission;
        private double commissionRate;
        private String comment;
        private Date commentTime;
        private int ownerId;
        private int state;
        private String address;
        private String reciverPhone;
        private String reciver;
        private String postWay;

        public int getOrdersId() {
            return ordersId;
        }

        public void setOrdersId(int ordersId) {
            this.ordersId = ordersId;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getSingleGoodId() {
            return singleGoodId;
        }

        public void setSingleGoodId(int singleGoodId) {
            this.singleGoodId = singleGoodId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getGoodsDescribe() {
            return goodsDescribe;
        }

        public void setGoodsDescribe(String goodsDescribe) {
            this.goodsDescribe = goodsDescribe;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Date getPaidTime() {
            return paidTime;
        }

        public void setPaidTime(Date paidTime) {
            this.paidTime = paidTime;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public double getCommission() {
            return commission;
        }

        public void setCommission(double commission) {
            this.commission = commission;
        }

        public double getCommissionRate() {
            return commissionRate;
        }

        public void setCommissionRate(double commissionRate) {
            this.commissionRate = commissionRate;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Date getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(Date commentTime) {
            this.commentTime = commentTime;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getReciverPhone() {
            return reciverPhone;
        }

        public void setReciverPhone(String reciverPhone) {
            this.reciverPhone = reciverPhone;
        }

        public String getReciver() {
            return reciver;
        }

        public void setReciver(String reciver) {
            this.reciver = reciver;
        }

        public String getPostWay() {
            return postWay;
        }

        public void setPostWay(String postWay) {
            this.postWay = postWay;
        }
    }
}
