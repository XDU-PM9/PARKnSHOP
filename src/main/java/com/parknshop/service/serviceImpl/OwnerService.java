package com.parknshop.service.serviceImpl;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.entity.OwnerEntity;
import com.parknshop.entity.PhotoEntity;
import com.parknshop.entity.RoleEntity;
import com.parknshop.entity.ShopEntity;
import com.parknshop.service.IOwnerService;
import com.parknshop.service.baseImpl.IUploadPictures;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
@Scope(value = "prototype")
@Service
public class OwnerService implements IOwnerService {
    final
    IBaseDao<OwnerEntity> mDao;
    final
    IBaseDao<ShopEntity> mDaoShop;
    final
    IBaseDao<PhotoEntity> mDaoPhoto;

    @Autowired
    public OwnerService(IBaseDao<OwnerEntity> mDao, IBaseDao<ShopEntity> mDaoShop, IBaseDao<PhotoEntity> mDaoPhoto) {
        this.mDao = mDao;
        this.mDaoShop = mDaoShop;
        this.mDaoPhoto = mDaoPhoto;
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

        if(!savePicture(listPath,photoGroup)){
            //保存图片错误
            return NEW_ERROPICTURE;
        }else {//保存图片
            shopEntity.setPhotoGroup(photoGroup);
        }
        shopEntity.setState(SHOP_STATE_CHECKING);//正在申请状态
        shopEntity.setViews(0);//访问量0
        try{
            mDao.save(ownerEntity);
            mDaoShop.save(shopEntity);
            return NEW_SUCCESS;

        }catch (Exception e){
            e.printStackTrace();
            return NEW_FAIL;
        }
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
    private boolean savePicture(List<String> list ,String photoGroup){
        try{
            //这里使用new 防止出问题
            List<PhotoEntity> photoList = new ArrayList<>();
            for(String path:list){//添加数据
                PhotoEntity photoEntity = new PhotoEntity();
                photoEntity.setPhotoGroup(photoGroup);
                photoEntity.setAddress(path);
                photoList.add(photoEntity);
            }
            mDaoPhoto.save(photoList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static  void main(String[] args){
        OwnerService ownerService = new OwnerService(new BaseDao<>(),new BaseDao<>(),new BaseDao<>());
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
        OwnerEntity entity = new OwnerEntity();
        entity.setOwnerId(5);
        System.out.println(ownerService.isHasShop(entity));
    }
}
