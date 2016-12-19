package com.parknshop.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/12/19.
 */
public class HqlBean{
    private String innerHql;
    private List<Object> innerParam = new ArrayList<>();

    public String getInnerHql() {
        return innerHql;
    }

    public void setInnerHql(String innerHql) {
        this.innerHql = innerHql;
    }

    public List<Object> getInnerParam() {
        return innerParam;
    }

    public void setInnerParam(List<Object> innerParam) {
        this.innerParam = innerParam;
    }
}
