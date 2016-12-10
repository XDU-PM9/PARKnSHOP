package com.parknshop.dao.daoImpl;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.IPictureDao;
import com.parknshop.entity.PhotoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weina on 2016/12/10.
 */
@Repository
public class PitureDao implements IPictureDao {
    final
    IBaseDao<PhotoEntity> mDaoPhoto;

    @Autowired
    public PitureDao(IBaseDao<PhotoEntity> mDaoPhoto) {
        this.mDaoPhoto = mDaoPhoto;
    }

    @Override
    public boolean savePicture(List<String> list, String photoGroup) {
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

    @Override
    public List<PhotoEntity> getPictures(String photoGroup) {
        try {
            String hql = "from PhotoEntity where photoGroup = ? ";
            Object[] param = {photoGroup};
            return mDaoPhoto.find(hql,param);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
