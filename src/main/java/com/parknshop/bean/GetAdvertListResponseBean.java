package com.parknshop.bean;

import java.util.List;
import java.util.Date;

/**
 * Created by Cong on 2016/12/20.
 */
public class GetAdvertListResponseBean {

    /**
     * error : true/false
     * total : 2
     * realSize : 5
     * data : [{"advertId":12,"startTime":"Date","price":12.5,"state":"state","detail":{"id":12,"name":"name","introduction":"intro"},"detailOfOwner":{"ownerid":12,"realname":"realname","phone":"029-8083028"}}]
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
         * advertId : 12
         * startTime : Date
         * price : 12.5
         * state : state
         * detail : {"id":12,"name":"name","introduction":"intro"}
         * detailOfOwner : {"ownerid":12,"realname":"realname","phone":"029-8083028"}
         */

        private int advertId;
        private Date startTime;
        private double price;
        private int state;
        private DetailBean detail;
        private DetailOfOwnerBean detailOfOwner;

        public int getAdvertId() {
            return advertId;
        }

        public void setAdvertId(int advertId) {
            this.advertId = advertId;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
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

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public DetailOfOwnerBean getDetailOfOwner() {
            return detailOfOwner;
        }

        public void setDetailOfOwner(DetailOfOwnerBean detailOfOwner) {
            this.detailOfOwner = detailOfOwner;
        }

        public static class DetailBean {
            /**
             * id : 12
             * name : name
             * introduction : intro
             */

            private int id;
            private String name;
            private String introduction;

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

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }
        }

        public static class DetailOfOwnerBean {
            /**
             * ownerid : 12
             * realname : realname
             * phone : 029-8083028
             */

            private int ownerid;
            private String realname;
            private String phone;

            public int getOwnerid() {
                return ownerid;
            }

            public void setOwnerid(int ownerid) {
                this.ownerid = ownerid;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
