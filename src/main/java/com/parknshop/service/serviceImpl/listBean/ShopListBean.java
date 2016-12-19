package com.parknshop.service.serviceImpl.listBean;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
@Scope(value = "prototype")
@Component
public class ShopListBean  extends AbstractListBean<ShopAndOwnerDbBean>{
    //管理员查看所有请求商铺
    final
    IBaseDao<ShopAndOwnerDbBean> mDao;

    @Autowired
    public ShopListBean(IBaseDao<ShopAndOwnerDbBean> mDao) {
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
        String hql ="select count(*) from ShopEntity where state =?";
        Object[] param = {IOwnerService.SHOP_STATE_CHECKING};
        return mDao.count(hql,param);
    }
    @Override
    protected List<ShopAndOwnerDbBean> initList(int page, int lines){
        String hql =PersonShopListBean.hql+
                " and s.state = ?"+//
                 " order by s.shopId desc ";
        Object[] param = {IOwnerService.SHOP_STATE_CHECKING};

        return  mDao.find(hql,param,page,lines);
    }



    public static void main(String[] args){
        ShopListBean shopListBean = new ShopListBean(new BaseDao<>());
        try{
            shopListBean.init(1,4);
//            shopListBean.initList(1,1);
            System.out.print("成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("失败");
        }
    }
}
