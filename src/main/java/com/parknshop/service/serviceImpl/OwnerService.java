package com.parknshop.service.serviceImpl;

import com.parknshop.bean.GoodsDbBean;
import com.parknshop.bean.ShopAndOwnerDbBean;
import com.parknshop.bean.ShopBean;
import com.parknshop.bean.ShopDbBean;
import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.IPictureDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.dao.daoImpl.PitureDao;
import com.parknshop.entity.*;
import com.parknshop.service.IAdvertisement;
import com.parknshop.service.IGoodsBuilder;
import com.parknshop.service.IListBean;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.baseImpl.IUploadPictures;
import com.parknshop.service.serviceImpl.listBean.GoodsListBean;
import com.parknshop.service.serviceImpl.listBean.PersonShopListBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
@Scope(value = "prototype")
@Service
public class OwnerService implements IOwnerService {
    private final IPictureDao photoEntityIBaseDao;
    final
    IBaseDao<OwnerEntity> mDao;
    final
    IBaseDao<ShopEntity> mDaoShop;
    final
    IPictureDao mDaoPhoto;
    final
    IListBean listBean;
    final
    IBaseDao<GoodsEntity> goodDao;
    private final IListBean goodsList;
    private final IBaseDao<GoodsDbBean> goodsDbBeanIBaseDao;
    private final IAdvertisement advertisement;
    @Autowired
    public OwnerService(IBaseDao<OwnerEntity> mDao, IBaseDao<ShopEntity> mDaoShop, PitureDao mDaoPhoto, PersonShopListBean listBean, IBaseDao<GoodsEntity> goodDao, GoodsListBean goodsList, IBaseDao<GoodsDbBean> goodsDbBeanIBaseDao, IAdvertisement advertisement, IPictureDao photoEntityIBaseDao) {
        this.mDao = mDao;
        this.mDaoShop = mDaoShop;
        this.mDaoPhoto = mDaoPhoto;
        this.listBean = listBean;
        this.goodDao = goodDao;
        this.goodsList = goodsList;
        this.goodsDbBeanIBaseDao = goodsDbBeanIBaseDao;
        this.advertisement = advertisement;
        this.photoEntityIBaseDao = photoEntityIBaseDao;
    }

    //更新用户
    @Override
    public int updateOwner(OwnerEntity ownerEntity) {
        try{
            mDao.update(ownerEntity);
            return UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return UPDATE_FAIL;
        }
    }
    //创建新的商店
    @Override
    public int newShop(OwnerEntity ownerEntity, ShopEntity shopEntity, IUploadPictures pictures) {
        //把商店和  用户绑定在一起
        shopEntity.setOwnerByOwnerId(ownerEntity);
        //回调
        List<String> listPath= pictures.getPicturePaths();
        //生成 photoGroup
        String photoGroup = String.valueOf(ownerEntity.getOwnerId())+String.valueOf(System.currentTimeMillis());

        if(!mDaoPhoto.savePicture(listPath,photoGroup)){
            //保存图片错误
            return NEW_ERROPICTURE;
        }else {//保存图片
            shopEntity.setPhotoGroup(photoGroup);
        }
        shopEntity.setState(SHOP_STATE_CHECKING);//正在申请状态
        shopEntity.setViews(0);//访问量0
        try{
            mDao.update(ownerEntity);
            mDaoShop.save(shopEntity);
            return NEW_SUCCESS;

        }catch (Exception e){
            e.printStackTrace();
            return NEW_FAIL;
        }
    }

    @Override
    public IListBean<ShopAndOwnerDbBean> getMyShop(OwnerEntity entity,int page, int lines) {
        listBean.init(entity,page,lines);
        return listBean;
    }

    @Override
    public IListBean<GoodsDbBean> getMyGoods(ShopEntity entity, int page, int lines) {
        goodsList.init(entity,page,lines);
        return goodsList;
    }

    @Override
    public int newGoods(IGoodsBuilder goodsBuilder, IUploadPictures pictures) {
        try {
            GoodsEntity goodsEntity = goodsBuilder.builder();
            //回调
            List<String> listPath= pictures.getPicturePaths();
            //生成 photoGroup
            String photoGroup ="Good"+String.valueOf(goodsEntity.getCreateTime())+String.valueOf(System.currentTimeMillis());

            if(!mDaoPhoto.savePicture(listPath,photoGroup)){
                //保存图片错误
                return NEW_ERROPICTURE;
            }else {//保存图片
                goodsEntity.setPhotoGroup(photoGroup);
            }
            //保存
            goodDao.save(goodsEntity);
            return  NEW_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return NEW_FAIL;
        }
    }

    @Override
    public boolean deletGoods(int goodId) {
        advertisement.cancelGoods(goodId);//同时取消广告位置
        return updateGoodState(IGoodsBuilder.GOOD_SATE_DELETE,goodId);
    }

    @Override
    public boolean updateGoods(GoodsEntity goodsEntity, IUploadPictures uploadPictures) {
        try {
            if (null == uploadPictures) {
                goodDao.update(goodsEntity);
                return true;
            }else {
                List<String> listPath= uploadPictures.getPicturePaths();
                //生成 photoGroup
                String photoGroup ="Good"+String.valueOf(goodsEntity.getCreateTime())+String.valueOf(System.currentTimeMillis());

                if(!mDaoPhoto.savePicture(listPath,photoGroup)){
                    //保存图片错误
                    return false;
                }else {//保存图片
                    goodsEntity.setPhotoGroup(photoGroup);
                }
                //保存
                goodDao.update(goodsEntity);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public GoodsDbBean getGoods(int goodsId) {
        String hql = GoodsDbBean.hql + //
                " and g.goodsId = ?";
        Object[] param = {goodsId};
        GoodsDbBean entity = goodsDbBeanIBaseDao.get(hql,param);
        //数量加一
        synchronized (this){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    GoodsEntity entity = goodDao.get(GoodsEntity.class,goodsId);
                    entity.setViews(entity.getViews()+1);
                    try {
                        goodDao.update(entity);
                        System.out.println("view add ");
                    } catch (Exception e) {
                        System.out.println("view add fail");
                    }
                }
            }).start();
        }
        return entity;
    }

    @Override
    public ShopDbBean getShop(int shopId) {
        ShopEntity shopEntity = mDaoShop.get(ShopEntity.class,shopId);
        if(null == shopEntity){
            if(shopEntity.getState() != IOwnerService.SHOP_STATE_USING){
                return null;//商店不在使用；
            }
            return null;
        }
        List<PhotoEntity> photoEntities = photoEntityIBaseDao.getPictures(shopEntity.getPhotoGroup());
        ShopDbBean shopDbBean = new ShopDbBean();
        shopDbBean.setShopEntity(shopEntity);
        shopDbBean.setPhotoEntityList(photoEntities);


        synchronized (this){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ShopEntity entity = mDaoShop.get(ShopEntity.class,shopId);
                    entity.setViews(entity.getViews()+1);
                    try {
                        mDaoShop.update(entity);
                        System.out.println("view add ");
                    } catch (Exception e) {
                        System.out.println("view add fail");
                    }
                }
            }).start();
        }
        return shopDbBean;
    }


    @Override
    public int isHasShop(OwnerEntity ownerEntity){
        String hql = "from ShopEntity where 1=1 and ownerByOwnerId.ownerId=? and state > ?";
        //搜索 商店表单，id 相同 但是状态不是 和 拒绝的 删除的 商店 > 1
        Object[] param = {ownerEntity.getOwnerId(),IOwnerService.SHOP_STATE_REJECT};
        try {
            List<ShopEntity> shopEntityList = mDaoShop.find(hql, param);
            if(null == shopEntityList){
                return SHOP_STATE_NOSHOP;//商店不存在
            }else {
                for(ShopEntity e:shopEntityList) {
                    switch (e.getState()) {
                        case SHOP_STATE_DELETE:
                           break;//已经删除
                        case SHOP_STATE_REJECT:
                            break;//拒绝
                        case SHOP_STATE_CHECKING:
                            return SHOP_STATE_CHECKING;//查看
                        case SHOP_STATE_BLAKE:
                            return SHOP_STATE_BLAKE;//黑名单
                        case SHOP_STATE_USING:
                            return SHOP_STATE_USING;//正在使用
                        default:
                            return SHOP_STATE_ELSE;
                    }
                }
                return SHOP_STATE_NOSHOP;
            }
        }catch (Exception e){
            e.printStackTrace();
            return SHOP_STATE_ELSE;//商店错误
        }

    }
    //更新商品状态
    private  boolean updateGoodState(int type,int goodsId){
        try {
            System.out.println(goodsId);
            GoodsEntity entity = goodDao.get(GoodsEntity.class, goodsId);
            if(null != entity) {
                entity.setState(type);//可以使用
                goodDao.update(entity);
                int h;//没用的，不要提醒代码重复
                return true;
            }else {

                return false;
            }

        }catch (Exception e){

            e.printStackTrace();
            return false;
        }
    }

    public static  void main(String[] args){
//        OwnerService ownerService = new OwnerService(new BaseDao<>(),new BaseDao<>(),new BaseDao<>());
//        OwnerEntity entity = new OwnerEntity();
//        RoleEntity roleEntity = new RoleEntity();
//        roleEntity.setRoleId(2);
//        entity.setRoleByRoleId(roleEntity);
//        System.out.println( ownerService.newShop(entity, new ShopEntity(), new IUploadPictures() {
//            @Override
//            public List<String> getPicturePaths() {
//                List<String >  list = new ArrayList<>();
//                list.add("www");
//                list.add("pp");
//                return list;
//            }
//        }));
//        OwnerEntity entity = new OwnerEntity();
//        entity.setOwnerId(5);
//        System.out.println(ownerService.isHasShop(entity));
        IBaseDao<GoodsEntity> dao = new BaseDao<>();
        GoodsEntity entity = dao.get(GoodsEntity.class,11);
        System.out.print(entity.getInventory());
    }
}
