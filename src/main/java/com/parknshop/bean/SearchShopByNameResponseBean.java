package com.parknshop.bean;

import java.util.List;

/**
 * Created by Cong on 2017/1/2.
 */
public class SearchShopByNameResponseBean {


    /**
     * error : true
     * data : [{"shopId":1,"shopName":"String","introduction":"String","photoGroup":"String","views":1,"logo":"String","state":1,"ownerId":1}]
     */

    private boolean error;
    private List<DataBean> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopId : 1
         * shopName : String
         * introduction : String
         * photoGroup : String
         * views : 1
         * logo : String
         * state : 1
         * ownerId : 1
         */

        private int shopId;
        private String shopName;
        private String introduction;
        private String photoGroup;
        private int views;
        private String logo;
        private int state;
        private int ownerId;

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

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getPhotoGroup() {
            return photoGroup;
        }

        public void setPhotoGroup(String photoGroup) {
            this.photoGroup = photoGroup;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(int ownerId) {
            this.ownerId = ownerId;
        }
    }
}
