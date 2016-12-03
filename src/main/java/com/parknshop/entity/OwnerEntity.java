package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "owner", schema = "parknshop", catalog = "")
public class OwnerEntity {
    private Integer ownerId;
    private String username="";
    private String userImage="";
    private String realname="";
    private String phone="";
    private String password="";
    private String email="";
    private String address="";
    private String identityId="";
    private String picture="";
    private Double balance=0.0;
    private String confirm="";
    private Integer state;
    private RoleEntity roleByRoleId;
    private Collection<ShopEntity> shopsByOwnerId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ownerId", nullable = false)
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
    @Basic
    @Column(name = "userImage", length = 50)
    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
    @Basic
    @Column(name = "realname", length = 50)
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 50)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "identityId", nullable = true, length = 50)
    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    @Basic
    @Column(name = "picture", nullable = true, length = 100)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 2)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "confirm", nullable = true, length = 50)
    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerEntity that = (OwnerEntity) o;

        if (ownerId != null ? !ownerId.equals(that.ownerId) : that.ownerId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (identityId != null ? !identityId.equals(that.identityId) : that.identityId != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (confirm != null ? !confirm.equals(that.confirm) : that.confirm != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (userImage != null ? !userImage.equals(that.userImage) : that.userImage != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = ownerId != null ? ownerId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (identityId != null ? identityId.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (confirm != null ? confirm.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (userImage != null ? userImage.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", nullable = false)
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @OneToMany(mappedBy = "ownerByOwnerId")
    public Collection<ShopEntity> getShopsByOwnerId() {
        return shopsByOwnerId;
    }

    public void setShopsByOwnerId(Collection<ShopEntity> shopsByOwnerId) {
        this.shopsByOwnerId = shopsByOwnerId;
    }
}
