package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "photo", schema = "parknshop", catalog = "")
public class PhotoEntity {
    private Integer photoId;
    private String photoGroup="";
    private String photoName="";
    private String address="";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "photoId", nullable = false)
    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    @Basic
    @Column(name = "photoGroup", nullable = false, length = 50)
    public String getPhotoGroup() {
        return photoGroup;
    }

    public void setPhotoGroup(String photoGroup) {
        this.photoGroup = photoGroup;
    }

    @Basic
    @Column(name = "photoName", nullable = true, length = 100)
    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 200)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoEntity that = (PhotoEntity) o;

        if (photoId != null ? !photoId.equals(that.photoId) : that.photoId != null) return false;
        if (photoGroup != null ? !photoGroup.equals(that.photoGroup) : that.photoGroup != null) return false;
        if (photoName != null ? !photoName.equals(that.photoName) : that.photoName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = photoId != null ? photoId.hashCode() : 0;
        result = 31 * result + (photoGroup != null ? photoGroup.hashCode() : 0);
        result = 31 * result + (photoName != null ? photoName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
