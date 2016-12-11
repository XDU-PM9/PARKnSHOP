package com.parknshop.bean;

import com.parknshop.dao.IBaseDao;
import com.parknshop.dao.IPictureDao;
import com.parknshop.dao.daoImpl.BaseDao;
import com.parknshop.dao.daoImpl.PitureDao;
import com.parknshop.entity.PhotoEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by weina on 2016/12/2.
 */
public class ShopAndOwnerDbBean {
    /**
     * 角色
     */
    private int ownerId;
    private String username;
    private String realname;
    private String userImage;
    private String phone;
    private String email;
    private String address;
    private String identityId;
    private String picture;
    private Double balance;
    private Integer ownerState;
    /**
     * 商店
     */
    private int shopId;
    private String shopName;
    private String introduction;
    private String photoGroup;
    private Integer views;
    private String logo;
    private Date timestamp;
    private int shopState;

    private List<PhotoEntity> picturePath;//图片组地址

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public void setOwnerState(Integer ownerState) {
        this.ownerState = ownerState;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ShopAndOwnerDbBean(int ownerId, String username,String realname,String userImage, String phone, String email, String address, String identityId, String picture, Double balance, int ownerState, int shopId, String shopName, String introduction, String photoGroup, Integer views, String logo, int shopState, Date timestamp) {
        this.ownerId = ownerId;
        this.username = username;
        this.realname=realname;
        this.userImage=userImage;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.identityId = identityId;
        this.picture = picture;
        this.balance = balance;
        this.ownerState = ownerState;
        //商店
        this.shopId = shopId;

        this.shopName = shopName;
        this.introduction = introduction;
        this.photoGroup = photoGroup;
        this.views = views;
        this.logo = logo;
        this.timestamp =timestamp;
        this.shopState = shopState;
        //初始化 图片列表
        initPhotoList();
    }
    private void initPhotoList(){
        IBaseDao<PhotoEntity> pDao = new BaseDao<>();
        IPictureDao dao = new PitureDao( pDao);
        this.picturePath = dao.getPictures(this.photoGroup);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getOwnerState() {
        return ownerState;
    }

    public void setOwnerState(int ownerState) {
        this.ownerState = ownerState;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getPhotoGroup() {
        return photoGroup;
    }

    public void setPhotoGroup(String photoGroup) {
        this.photoGroup = photoGroup;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getCreateTime() {
        return timestamp;
    }

    public void setCreateTime(Date createTime) {
        this.timestamp = createTime;
    }

    public int getShopState() {
        return shopState;
    }

    public void setShopState(int shopState) {
        this.shopState = shopState;
    }

    public List<PhotoEntity> getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(List<PhotoEntity> picturePath) {
        this.picturePath = picturePath;
    }



    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
