package com.parknshop.service.serviceImpl;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.bean.HqlBean;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.service.IHomeService;
import com.parknshop.service.IListBean;
import com.parknshop.service.serviceImpl.builder.GoodsBuilder;
import com.parknshop.service.serviceImpl.listBean.GoodsListBean;
import com.parknshop.service.serviceImpl.listBean.SortGoodsListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by weina on 2016/12/27.
 */
@Service
public class HomeService implements IHomeService {
    private final IListBean<GoodsDbBean> sortGoodsListBeanIListBean;

    @Autowired
    public HomeService(SortGoodsListBean sortGoodsListBeanIListBean) {
        this.sortGoodsListBeanIListBean = sortGoodsListBeanIListBean;
    }
    @Override
    public IListBean<GoodsDbBean> getMostSales(int page, int lines) {
        return getOrderList("and g.state = ? order by g.sales desc",new Object[]{GoodsBuilder.GOOD_STATE_USING},page,lines);
    }

    @Override
    public IListBean<GoodsDbBean> getMostView(int page, int lines) {
        return getOrderList("and g.state = ? order by g.views desc",new Object[]{GoodsBuilder.GOOD_STATE_USING},page,lines);
    }

    @Override
    public IListBean<GoodsDbBean> getMostValue(int page, int lines) {
        return getOrderList("and g.state = ? order by g.price desc",new Object[]{GoodsBuilder.GOOD_STATE_USING},page,lines);
    }


    private IListBean<GoodsDbBean> getOrderList(String hql, List<Object> param,int page, int lines) {
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(hql);
        hqlBean.setInnerParam(param);
        int i;
        sortGoodsListBeanIListBean.init(hqlBean,page,lines);
        return sortGoodsListBeanIListBean;
    }
    private IListBean<GoodsDbBean> getOrderList(String hql, Object[] param, int pages, int lines){
        List<Object> list = Arrays.asList(param);
        return getOrderList(hql,list,pages,lines);
    }

}
