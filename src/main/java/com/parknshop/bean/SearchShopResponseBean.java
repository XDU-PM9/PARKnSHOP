package com.parknshop.bean;

/**
 * Created by cong on 2016/12/10.
 */
public class SearchShopResponseBean {

    /**
     * error : boolean
     * data : {"shopId":"int","shopName":"String","introduction":"String","photoGroup":"String","views":"int","logo":"String","state":"int","ownerId":"int"}
     */

    private boolean error;
    private DataBean data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shopId : int
         * shopName : String
         * introduction : String
         * photoGroup : String
         * views : int
         * logo : String
         * state : int
         * ownerId : int
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
