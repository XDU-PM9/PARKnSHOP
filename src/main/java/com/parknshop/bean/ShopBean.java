package com.parknshop.bean;

import java.util.List;

/**
 * Created by fallb on 2016/12/9.
 */
public class ShopBean {

    /**
     * count : 1
     * shops : [{"name":"anta","desc":"desc","logo":"a.png","state":1}]
     */

    private int count;
    private List<Shop> shops;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    public static class Shop {
        /**
         * name : anta
         * desc : desc
         * logo : a.png
         * state : 1
         */

        private String name;
        private String desc;
        private String logo;
        private int state;

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
    }
}
