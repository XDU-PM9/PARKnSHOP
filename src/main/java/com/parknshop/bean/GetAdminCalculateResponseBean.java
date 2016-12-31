package com.parknshop.bean;

import java.util.List;
import java.util.Date;

/**
 * Created by Cong on 2016/12/31.
 */
public class GetAdminCalculateResponseBean {

    /**
     * error : true
     * data : [{"date":"Date","earn":"double"}]
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
         * date : Date
         * earn : double
         */

        private String date;
        private Double earn;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Double getEarn() {
            return earn;
        }

        public void setEarn(Double earn) {
            this.earn = earn;
        }
    }
}
