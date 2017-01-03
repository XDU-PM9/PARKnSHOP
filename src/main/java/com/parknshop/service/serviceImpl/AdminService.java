package com.parknshop.service.serviceImpl;

import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.*;
import com.parknshop.service.*;
import com.parknshop.service.serviceImpl.listBean.*;
import com.parknshop.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
@Scope(value = "prototype")
@Service
public class AdminService  implements IAdminService{

    private final IBaseDao<ShopEntity> mDao;
    private final IBaseDao<Object> objectDao;
    private final IBaseDao<ShopAndOwnerDbBean> shopDao;
    /**
     *
     * @param page  请求第几页
     * @param  lines  请求多少条
     * @return
     */
    final private IListBean listBean;
    private final IListBean userList;
    private final IListBean ownerList;
    private final IListBean allShopList;
    private final IAdvertisement advertisement;
    private final IOwnerService ownerService;
    private final IBaseDao<CommissionEntity> commissionEntityIBaseDao;
    @Autowired
    public AdminService(ShopListBean listBean, IBaseDao<ShopEntity> mDao, IBaseDao<ShopAndOwnerDbBean> shopDao, IBaseDao<Object> objectDao, UserListBean userList, OwnerListBean ownerList, AllShopListBean allShopList, IAdvertisement advertisement, IOwnerService ownerService, IBaseDao<CommissionEntity> commissionEntityIBaseDao) {
        this.listBean = listBean;
        this.mDao = mDao;
        this.shopDao =shopDao;
        this.objectDao =objectDao;
        this.userList = userList;
        this.ownerList = ownerList;
        this.allShopList = allShopList;
        this.advertisement = advertisement;
        this.ownerService = ownerService;
        this.commissionEntityIBaseDao = commissionEntityIBaseDao;
    }

    @Override
    public IListBean<ShopAndOwnerDbBean> getApplyShop(int page, int lines) {
        listBean.init(page,lines);
        return listBean;
    }

    @Override
    public IListBean<ShopAndOwnerDbBean> getAllShop(int page, int lines) {
        allShopList.init(page,lines);
        return allShopList;
    }

    @Override
    public boolean suggestShop(int shopId) {
       return updateShopState(IOwnerService.SHOP_STATE_USING,shopId);
    }

    @Override
    public boolean rejectShop(int shopId) {
        return updateShopState(IOwnerService.SHOP_STATE_REJECT,shopId);
    }

    @Override
    public boolean blackShop(int shopId) {
        boolean res = advertisement.cancelAdvertByName(IAdvertisement.AD_TYPE_SHOP,shopId);//同时取消商店的广告位置
        Log.debug(" 取消 广告 ："+shopId + "  " +res);
        return updateShopState(IOwnerService.SHOP_STATE_BLAKE,shopId);
    }

    @Override
    public boolean whiteShop(int shopId) {
        return updateShopState(IOwnerService.SHOP_STATE_USING,shopId);
    }

    @Override
    public boolean deleteShop(int shopId) {
        advertisement.cancelAdvertByName(IAdvertisement.AD_TYPE_SHOP,shopId);//同时取消商店的广告位置
        String hql = "from GoodsEntity where shopId =?";
        Object[] param ={shopId};
        IBaseDao<GoodsEntity> goodsEntityIBaseDao = new BaseDao<>();
        List<GoodsEntity> list = goodsEntityIBaseDao.find(hql,param);
        for(GoodsEntity entity:list){
            ownerService.deletGoods(entity.getGoodsId());//遍历商铺删除商品
            advertisement.cancelAdvertByName(IAdvertisement.AD_TYOE_GOODS,entity.getGoodsId());//取消商品广告位置
        }
        return updateShopState(IOwnerService.SHOP_STATE_DELETE,shopId);
    }

    @Override
    public ShopAndOwnerDbBean getShopById(int shopId) {
        try {
            String hql = PersonShopListBean.hql +//
                        "  and s.shopId = ? ";
            Object[] param = {shopId};
            return shopDao.get(hql,param);

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public List<ShopAndOwnerDbBean> getShopById(String shopName) {
        try {
            String hql = PersonShopListBean.hql +//
                    "  and s.shopName like ?";
            Object[] param = {'%'+shopName+'%'};
            return shopDao.find(hql,param);

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public OwnerEntity getOwnerById(int ownerId) {
        return (OwnerEntity) getPeppleById(OwnerEntity.class,ownerId);
    }

    @Override
    public List<OwnerEntity> getOwnerById(String ownerName) {
        String hql = " from OwnerEntity where username like ? ";
        Object[] param = new Object[]{'%'+ownerName+'%'};
        IBaseDao<OwnerEntity> baseDao = new BaseDao<>();
        List<OwnerEntity> entity = baseDao.find(hql,param);
        return entity;
    }

    @Override
    public UserEntity getUserById(int userId) {
        return (UserEntity) getPeppleById(UserEntity.class,userId);
    }

    @Override
    public List<UserEntity> getUserById(String userName) {
        String hql = " from UserEntity where username like ? ";
        Object[] param = new Object[]{'%'+userName+'%'};
        IBaseDao<UserEntity> baseDao = new BaseDao<>();
        List<UserEntity> entity = baseDao.find(hql,param);
        return entity;
    }

    @Override
    public IListBean<UserEntity> getUserList(int page, int lines) {
        userList.init(page,lines);
        return userList;
    }

    @Override
    public boolean blackUser(int userId) {
        return updateUserSate(IUserService.STATE_BLAKENAME,userId);
    }

    @Override
    public boolean whiteUser(int userId) {
        return updateUserSate(IUserService.STATE_USING,userId);
    }

    @Override
    public boolean deleteUser(int userId) {
        return updateUserSate(IUserService.STATE_DELETE,userId);
    }

    @Override
    public IListBean<OwnerEntity> getOwnerList(int page, int lines) {
        ownerList.init(page,lines);
        return ownerList;
    }

    @Override
    public boolean blackOwner(int ownerId) {
        String hql ="from ShopEntity where ownerId = ?";
        Object[] param = {ownerId};
        IBaseDao<ShopEntity> shopEntityIBaseDao = new BaseDao<>();
        List<ShopEntity> list = shopEntityIBaseDao.find(hql,param);
        for(ShopEntity entity:list){
            blackShop(entity.getShopId());//拉黑所有商铺
        }
        return updateOwnerState(IUserService.STATE_BLAKENAME,ownerId);
    }

    @Override
    public boolean whiteOwner(int ownerId) {
        return updateOwnerState(IUserService.STATE_USING,ownerId);
    }

    @Override
    public boolean deleteOwner(int ownerId) {
        String hql ="from ShopEntity where ownerId = ?";
        Object[] param = {ownerId};
        IBaseDao<ShopEntity> shopEntityIBaseDao = new BaseDao<>();
        List<ShopEntity> list = shopEntityIBaseDao.find(hql,param);
        for(ShopEntity entity:list){
            deleteShop(entity.getShopId());//删除所有商铺
        }
        return updateOwnerState(IUserService.STATE_DELETE,ownerId);
    }

    @Override
    public CommissionEntity getCommission() {
        return commissionEntityIBaseDao.get(CommissionEntity.class,1);
    }

    @Override
    public boolean updateCommission(CommissionEntity entity) {
        try{
            commissionEntityIBaseDao.update(entity);
            double i;
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Object getPeppleById(Class mClass, int id) {
        return objectDao.get(mClass,id);
    }

    //更新商店状态
    private  boolean updateShopState(int type,int shopId){
        try {
            ShopEntity entity = mDao.get(ShopEntity.class, shopId);
            if(null != entity) {
                entity.setState(type);//可以使用
                mDao.update(entity);
                return true;
            }else {
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //更新商家信息
    private boolean updateOwnerState(int type,int ownerId){
        try{
            OwnerEntity entity = getOwnerById(ownerId);
            if(null != entity) {
                entity.setState(type);//可以使用
                objectDao.update(entity);
                int i =0;//tm 的提示我 代码重复，暂时不想管
                i++;
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //更新用户信息
    private boolean updateUserSate(int type,int userId){
        try{
            UserEntity entity = getUserById(userId);
            if(null != entity) {
                entity.setState(type);//可以使用
                objectDao.update(entity);
                String ss ="";//tm 的提示我 代码重复，暂时不想管
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
