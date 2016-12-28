package com.parknshop.bean.owner;

/**
 * Created by SONG on 2016/12/28.
 */
public class UncheckedOrderRequest {
    int page;
    int lines;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "page="+page+",lines:"+lines;
    }
}
