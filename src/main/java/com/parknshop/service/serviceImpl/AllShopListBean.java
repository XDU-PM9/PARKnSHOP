package com.parknshop.service.serviceImpl;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2016/12/11.
 */
@Component
public class AllShopListBean extends AbstractListBean<ShopAndOwnerDbBean> {
    //管理员查看所有没有删除的商店
    final
    IBaseDao<ShopAndOwnerDbBean> mDao;

    @Autowired
    public AllShopListBean(IBaseDao<ShopAndOwnerDbBean> mDao) {
        this.mDao = mDao;
    }

    @Override
    public void init(int page, int lines) {
        super.init(page,lines);
    }

    @Override
    public void init(Object object, int page, int lines) {
        super.init(object,page,lines);
    }

    @Override
    protected long count(){
        String hql ="select count(*) from ShopEntity where state !=?";
        Object[] param = {IOwnerService.SHOP_STATE_DELETE};
        return mDao.count(hql,param);
    }
    @Override
    protected List<ShopAndOwnerDbBean> initList(int page, int lines){
        String hql =PersonShopListBean.hql+
                " and s.state != ?"+//
                " order by s.shopId desc ";
        Object[] param = {IOwnerService.SHOP_STATE_DELETE};

        return  mDao.find(hql,param,page,lines);
    }

}
