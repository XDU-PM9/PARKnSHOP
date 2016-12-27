package com.parknshop.service.serviceImpl;

import com.parknshop.bean.AdvertisementDbBean;
import com.parknshop.bean.HqlBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.AdvertEntity;
import com.parknshop.entity.CommissionEntity;
import com.parknshop.entity.GoodsEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IAdvertisement;
import com.parknshop.service.IGoodsBuilder;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.serviceImpl.listBean.AdvertisementListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by weina on 2016/12/19.
 */
@Scope(value = "prototype")
@Service
public class AdvertisementService implements IAdvertisement{
    private final IListBean<AdvertisementDbBean> advertisementDbBeanIListBean;
    private final IBaseDao<AdvertEntity> advertEntityIBaseDao ;
    final
    IBaseDao<CommissionEntity> commissionEntityIBaseDao;
    @Autowired
    public AdvertisementService(AdvertisementListBean advertisementDbBeanIListBean, IBaseDao<AdvertEntity> advertEntityIBaseDao, IBaseDao<CommissionEntity> commissionEntityIBaseDao) {
        this.advertisementDbBeanIListBean = advertisementDbBeanIListBean;
        this.advertEntityIBaseDao = advertEntityIBaseDao;
        this.commissionEntityIBaseDao = commissionEntityIBaseDao;
    }

    @Override
    public int addAdvertisementShop(int id){
        CommissionEntity commissionEntity = commissionEntityIBaseDao.get(CommissionEntity.class,1);
        return  addAdvertisementShop(id,commissionEntity.getShopPrice());

    }

    private int addAdvertisementShop(int id, double price) {
        IBaseDao<ShopEntity> dao = new BaseDao<>();
        ShopEntity entity = dao.get(ShopEntity.class,id);
        if(null == entity ||entity.getState() != IOwnerService.SHOP_STATE_USING){//商店正在使用才能申请
            return ILLEGAL_SHOP;
        }else {
            return addAdert(IAdvertisement.AD_TYPE_SHOP,id,price,entity.getOwnerByOwnerId().getOwnerId());
        }
    }

    @Override
    public int addAdvertisementGoods(int id){
        CommissionEntity commissionEntity = commissionEntityIBaseDao.get(CommissionEntity.class,1);
        return  addAdvertisementGoods(id,commissionEntity.getGoodsPrice());
    }
    private int addAdvertisementGoods(int id, double price) {
        IBaseDao<GoodsEntity> dao = new BaseDao<>();
        IBaseDao<ShopEntity> shopDao = new BaseDao<>();
        GoodsEntity goodsEntity = dao.get(GoodsEntity.class,id);
        if(null == goodsEntity || goodsEntity.getState() != IGoodsBuilder.GOOD_STATE_USING){
            return ILLEGAl_GOODS;
        }else {
            ShopEntity entity = shopDao.get(ShopEntity.class,id);
            if(null == entity ||entity.getState() != IOwnerService.SHOP_STATE_USING){//商店正在使用才能申请
                return ILLEGAL_SHOP;
            }else {
                return addAdert(IAdvertisement.AD_TYOE_GOODS,id,price,entity.getOwnerByOwnerId().getOwnerId());
            }
        }
    }

    @Override
    public boolean acceptShop(int id) {
        return updateAdert(id,IAdvertisement.AD_STATUS_EFFECT);
    }

    @Override
    public boolean acceptGoods(int id) {
        return updateAdert(id,IAdvertisement.AD_STATUS_EFFECT);
    }

    @Override
    public boolean rejectShop(int id) {
        return updateAdert(id,IAdvertisement.AD_SATUS_REJECT);
    }

    @Override
    public boolean rejectGoods(int id) {
        return updateAdert(id,IAdvertisement.AD_SATUS_REJECT);
    }

    @Override
    public boolean cancelShop(int id) {
        return updateAdert(id,IAdvertisement.AD_STATUS_CANCEL);
    }

    @Override
    public boolean cancelGoods(int id) {
        return updateAdert(id,IAdvertisement.AD_STATUS_CANCEL);
    }

    @Override
    public List<AdvertisementDbBean> getTopShop() {
        return getTopList(IAdvertisement.AD_TYPE_SHOP,1,5).getShopList();
    }

    @Override
    public List<AdvertisementDbBean> getTopGoods() {
        return getTopList(IAdvertisement.AD_TYOE_GOODS,1,10).getShopList();
    }


    @Override
    public IListBean<AdvertisementDbBean> getAllShop(int page,int lines) {
        return getAllList(IAdvertisement.AD_TYPE_SHOP,page,lines);
    }

    @Override
    public IListBean<AdvertisementDbBean> getAllGoods(int page,int lines) {
        return getAllList(IAdvertisement.AD_TYOE_GOODS,page,lines);
    }

    @Override
    public IListBean<AdvertisementDbBean> getMyShop(int userId,int page,int lines) {
        return getMyList(IAdvertisement.AD_TYPE_SHOP,userId,page,lines);
    }

    @Override
    public IListBean<AdvertisementDbBean> getMyGoods(int userId,int page,int lines) {
        return getMyList(IAdvertisement.AD_TYOE_GOODS,userId,page,lines);
    }

    private IListBean<AdvertisementDbBean> getMyList(int type,int userId,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and type =? and userId =? ");
        hqlBean.getInnerParam().add(type);
        hqlBean.getInnerParam().add(userId);
        advertisementDbBeanIListBean.init(hqlBean,page,lines);
        return  advertisementDbBeanIListBean;
    }
    private IListBean<AdvertisementDbBean> getAllList(int type,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and type =? ");
        hqlBean.getInnerParam().add(type);
        advertisementDbBeanIListBean.init(hqlBean,page,lines);
        return  advertisementDbBeanIListBean;
    }
    private IListBean<AdvertisementDbBean> getTopList(int type,int page,int lines){
        HqlBean hqlBean = new HqlBean();
        hqlBean.setInnerHql(" and type =? and state = ?");
        hqlBean.getInnerParam().add(type);
        hqlBean.getInnerParam().add(IAdvertisement.AD_STATUS_EFFECT);
        advertisementDbBeanIListBean.init(hqlBean,page,lines);
        return  advertisementDbBeanIListBean;
    }
    //更新商店状态
    private boolean updateAdert(int id,int state){
        IBaseDao<AdvertEntity> dao = new BaseDao<>();
        AdvertEntity entity = dao.get(AdvertEntity.class,id);
        if(null == entity){
            return false;
        }
        entity.setState(state);

        if(state == IAdvertisement.AD_STATUS_EFFECT){//生效时间
            entity.setStartDate(new Date());
            entity.setStartTime(new Date());
        }
        entity.setEndDate(new Date());
        entity.setEndTime(new Date());
        try {
            dao.update(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private int addAdert(int type,int id,double price,int userId){
        if(checkAdvertExist(type,id)){//如果为真则已经存在
            return ALREADY_EXISTS;
        }
        AdvertEntity advertEntity = new AdvertEntity();
        advertEntity.setState(IAdvertisement.AD_STATUS_APPLYING);
        advertEntity.setPrice(price);
        advertEntity.setType(type);
        advertEntity.setTypeId(id);
        advertEntity.setUserId(userId);
        try {
            advertEntityIBaseDao.save(advertEntity);
            return ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ELSE_ERRO;
        }
    }

    /**
     * 判断是否存在
     * @param type
     * @param typeId
     * @return 存在 true 不存在false
     */
    private boolean checkAdvertExist(int type,int typeId){
        String hql = "from AdvertEntity where type =? and typeId = ? and (state = ? or state = ?)";
        Object[] param ={type,typeId,IAdvertisement.AD_STATUS_APPLYING,IAdvertisement.AD_STATUS_EFFECT};
        AdvertEntity entity = advertEntityIBaseDao.get(hql,param);
        if(null == entity){
            return false;
        }else {
            return true;
        }
    }

    public static void main(String[] args){
//        AdvertisementService advertisementService = new AdvertisementService(new AdvertisementListBean(),new BaseDao<>());
//        //advertisementService.addAdvertisementGoods(2,12);c
//        System.out.println(advertisementService.addAdvertisementGoods(2,12));
    }


}
