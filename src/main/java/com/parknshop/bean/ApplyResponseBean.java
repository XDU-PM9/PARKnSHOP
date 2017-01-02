package com.parknshop.bean;

import java.util.List;

/**
 * Created by song on 16-12-2.
 */
public class ApplyResponseBean {


    /**
     * error : false
     * total : 9
     * realSize : 5
     * data : [{"ownerName":"注册名","ownerImg":"头像url","ownerEmail":"注册邮箱","realName":"真实名字","realImg":"正面带身份证照片的url","shopId":"shopId","shopName":"店名","shopImg":"店的logo url","shopDesc":"店的描述","shopDate":"date"}]
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
         * ownerName : 注册名
         * ownerImg : 头像url
         * ownerEmail : 注册邮箱
         * realName : 真实名字
         * realImg : 正面带身份证照片的url
         * shopId : shopId
         * shopName : 店名
         * shopImg : 店的logo url
         * shopDesc : 店的描述
         * shopDate : date
         */

        private String ownerName;
        private String ownerImg;
        private String ownerEmail;
        private String realName;
        private String realImg;
        private int shopId;
        private String shopName;
        private String shopImg;
        private String shopDesc;
        private String shopDate;

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getOwnerImg() {
            return ownerImg;
        }

        public void setOwnerImg(String ownerImg) {
            this.ownerImg = ownerImg;
        }

        public String getOwnerEmail() {
            return ownerEmail;
        }

        public void setOwnerEmail(String ownerEmail) {
            this.ownerEmail = ownerEmail;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getRealImg() {
            return realImg;
        }

        public void setRealImg(String realImg) {
            this.realImg = realImg;
        }

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getShopImg() {
            return shopImg;
        }

        public void setShopImg(String shopImg) {
            this.shopImg = shopImg;
        }

        public String getShopDesc() {
            return shopDesc;
        }

        public void setShopDesc(String shopDesc) {
            this.shopDesc = shopDesc;
        }

        public String getShopDate() {
            return shopDate;
        }

        public void setShopDate(String shopDate) {
            this.shopDate = shopDate;
        }
    }
}
