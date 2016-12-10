package com.parknshop.dao;

import com.parknshop.entity.PhotoEntity;

import java.util.List;

/**
 * Created by weina on 2016/12/10.
 */
public interface IPictureDao {
    //用于图片 的dao 层

    /**
     * 保存图片
     * @param list  图片的地址
     * @param photoGroup 图片组的id 需要保证唯一性 最好是代号+ 时间戳
     * @return 成功 true ，失败false
     */
    boolean savePicture(List<String> list , String photoGroup);

    /**
     *  获取图片
     * @param photoGroup 图片组的id
     * @return 成功返回列表失败 null
     */
    List<PhotoEntity> getPictures(String photoGroup);
}
