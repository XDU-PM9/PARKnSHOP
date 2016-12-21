package com.parknshop.service.serviceImpl.listBean;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IGoodsBuilder;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2016/12/11.
 */
@Component
public class GoodsListBean extends AbstractListBean<GoodsDbBean> {
    final
    IBaseDao<GoodsDbBean> mDao;

    @Autowired
    public GoodsListBean(IBaseDao<GoodsDbBean> mDao) {
        this.mDao = mDao;
    }

    @Override
    protected List<GoodsDbBean> initList(int page, int lines) {
        if(null == getObject()){
            return  null;
        }
        try {
            ShopEntity entity = (ShopEntity) getObject();
            // 使用 常量 hql 语句 根据名字搜索
            String hql = GoodsDbBean.hql + //
                    " and s.shopId = ?" +//
                    " and g.state > ? "+//
                    " order by g.goodsId desc ";
            Object[] param = {entity.getShopId(), IGoodsBuilder.GOOD_SATE_DELETE};
            return mDao.find(hql,param,page,lines);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected long count() {
        if(null == getObject()){
            return  0;
        }
        try {
            ShopEntity entity = (ShopEntity) getObject();
            String hql = "select count(*) from GoodsEntity as g,ShopEntity as s where g.shopByShopId.shopId = s.shopId "+//
                    " and s.shopId = ?"+//
                    " and g.state > ? ";
            Object[] param = {entity.getShopId(), IGoodsBuilder.GOOD_SATE_DELETE};
            return mDao.count(hql,param);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static void main(String[] args){
        GoodsListBean shopListBean = new GoodsListBean(new BaseDao<>());
        try{
            ShopEntity entity = new ShopEntity();
            entity.setShopId(1);
            shopListBean.init(entity,1,4);
            System.out.println(shopListBean.getNumer());
            System.out.print("成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("失败");
        }
    }
}
