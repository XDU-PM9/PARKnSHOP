package com.parknshop.bean.owner;

import java.util.Date;
import java.util.List;

/**
 * Created by fallb on 2016/12/11.
 */
public class GoodsListBean {

    /**
     * current : 1
     * total : 5
     * count : 10
     * data : [{"id":"goods id","name":"name","desc":"desc","price":12.5,"discount":0.75,"createTime":"time","views":100,"state":1,"photos":["a","b","c"]}]
     */

    private boolean success;
    private int current;
    private int total;
    private int count;

    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : goods id
         * name : name
         * desc : desc
         * price : 12.5
         * discount : 0.75
         * createTime : time
         * views : 100
         * state : 1
         * photos : ["a","b","c"]
         */

        private int id;
        private String name;
        private String desc;
        private double price;
        private double discount;
        private Date createTime;
        private int views;
        private int state;
        private String type;
        private String postWay;
        private String[] photos;
        private boolean ad;

        public boolean isAd() {
            return ad;
        }

        public void setAd(boolean ad) {
            this.ad = ad;
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
