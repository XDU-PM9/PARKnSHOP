package com.parknshop.service.serviceImpl.listBean;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.bean.HqlBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.OrdersEntity;
import com.parknshop.service.serviceAbstract.AbstractListBean;

import java.util.List;

/**
 * Created by weina on 2016/12/27.
 */
public class SortGoodsListBean extends AbstractListBean {
    @Override
    protected List<GoodsDbBean> initList(int page, int lines) {
        String hqlHead = GoodsDbBean.hql;
        if(null != getObject()){
            HqlBean hqlBean = (HqlBean) getObject();
            String hql =hqlHead + hqlBean.getInnerHql();
            List<Object> param = hqlBean.getInnerParam();
            IBaseDao<GoodsDbBean> ordersEntityIBaseDao = new BaseDao<>();
            return ordersEntityIBaseDao.find(hql,param,page,lines);

        }else {
            return null;
        }
    }

    @Override
    protected long count() {
        String hqlHead = "select count(*) from GoodsEntity as g where 1=1 ";
        if(null != getObject()){
            HqlBean hqlBean = (HqlBean) getObject();
            String hql =hqlHead + hqlBean.getInnerHql();//拼接
            List<Object> param = hqlBean.getInnerParam();
            IBaseDao<GoodsEntity> ordersEntityIBaseDao = new BaseDao<>();
            return ordersEntityIBaseDao.count(hql,param);

        }else {
            return 0;
        }
    }
}
