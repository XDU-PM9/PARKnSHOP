package com.parknshop.bean;

import java.util.List;

/**
 * Created by niewenzhi on 2016/12/28.
 */
public class GetBackupResponseBean {

    /**
     * error : false
     * lastbackuptime : filename
     * data : [{"filename":"name"}]
     */

    private boolean error;
    private String lastbackuptime;
    private List<DataBean> data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getLastbackuptime() {
        return lastbackuptime;
    }

    public void setLastbackuptime(String lastbackuptime) {
        this.lastbackuptime = lastbackuptime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * filename : name
         */

        private String filename;

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }
    }
}
