package com.parknshop.service.serviceAbstract;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.service.IListBean;

import java.util.List;

/**
 * Created by weina on 2016/12/4.
 */
public abstract class  AbstractListBean implements IListBean<ShopAndOwnerDbBean> {
    private List<ShopAndOwnerDbBean> listBean;
    private long currentPage;
    private long maxPage;
    private long currentLines;

    private Object object;

    @Override
    public void init(int page, int lines) {
        this.init(null,page,lines);
    }

    @Override
    public void init(Object object, int page, int lines) {
        long maxNumber;
        this.object =object;
        //获取数量
        maxNumber =count();
        listBean=null;
        currentLines =0;
        maxPage=0;
        currentPage=page;
        //测试是否超过
        if((page-1)*lines>maxNumber){//如果超了就退出
            return;
        }
        //计算最大页面
        if (maxNumber % lines == 0) {
            maxPage = maxNumber/lines;
        } else {
            maxPage = maxNumber/lines+1;
        }
        listBean = initList(page,lines);
        if(null != listBean) {
            currentLines = listBean.size();
        }else {
            currentLines =0;
        }


    }

    protected abstract List<ShopAndOwnerDbBean> initList(int page,int lines);
    protected abstract long count();

    @Override
    public List<ShopAndOwnerDbBean> getShopList() {
        return this.listBean;
    }

    @Override
    public long getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public long getMaxPages() {
        return this.maxPage;
    }

    @Override
    public long getNumer() {
        return this.currentLines;
    }

    public Object getObject() {
        return object;
    }
}
