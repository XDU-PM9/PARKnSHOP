package com.parknshop.bean;

import java.util.List;

/**
 * Created by cong on 2016/12/11.
 */
public class ApplyAllShopResponseBean {

    /**
     * error : boolean
     * total : 9
     * realSize : 5
     * data : [{"shopId":"int","shopName":"String","introduction":"String","photoGroup":"String","views":"int","logo":"String","state":"int","ownerId":"int"}]
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
        private String ownerId;

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

        public String getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(String ownerId) {
            this.ownerId = ownerId;
        }
    }
}
