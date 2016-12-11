package com.parknshop.service.serviceImpl;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.serviceAbstract.AbstractListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weina on 2016/12/4.
 */
@Scope(value = "prototype")
@Component
public class PersonShopListBean extends AbstractListBean<ShopAndOwnerDbBean>{
    //根据用户名字搜索商铺
    final static public String hql ="select new com.parknshop.bean.ShopAndOwnerDbBean " +//
            "(o.ownerId,o.username,o.realname,o.userImage,o.phone,o.email,o.address,o.identityId,o.picture,o.balance,o.state," +//
            "s.shopId,s.shopName,s.introduction,s.photoGroup,s.views,s.logo,s.state,s.createTime)" +//
            " from OwnerEntity as o,ShopEntity as s where o.ownerId = s.ownerByOwnerId.ownerId ";
    //商家查看自己的 商铺
    final
    IBaseDao<ShopAndOwnerDbBean> mDao;

    @Autowired
    public PersonShopListBean(IBaseDao<ShopAndOwnerDbBean> mDao) {
        this.mDao = mDao;
    }
    @Override
    protected List<ShopAndOwnerDbBean> initList(int page, int lines) {
        if(null == getObject()){
            return  null;
        }
        try {
            OwnerEntity entity = (OwnerEntity) getObject();
            // 使用 常量 hql 语句 根据名字搜索
            String hql = this.hql + //
                    " and o.username = ?" +//
                    " order by s.shopId desc ";
            Object[] param = {entity.getUsername()};
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
            OwnerEntity entity = (OwnerEntity) getObject();
            String hql = "select count(*) from ShopEntity as s,OwnerEntity as o where o.ownerId = s.ownerByOwnerId.ownerId "+//
                     " and  o.username = ?";
            Object[] param = {entity.getUsername()};
            return mDao.count(hql,param);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static void main(String[] args){
        PersonShopListBean shopListBean = new PersonShopListBean(new BaseDao<>());
        try{
            OwnerEntity ownerEntity = new OwnerEntity();
            ownerEntity.setUsername("wei");
           shopListBean.init(ownerEntity,1,4);
            System.out.println(shopListBean.getMaxPages());
            System.out.print("成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.print("失败");
        }
    }
}
