package com.parknshop.bean;

/**
 * Created by Cong on 2016/12/21.
 */
public class GetUserAdvertListRequestBean {

    /**
     * userId : 12
     * index : 12345
     * size : 5
     */

    private int userId;
    private int index;
    private int size;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
