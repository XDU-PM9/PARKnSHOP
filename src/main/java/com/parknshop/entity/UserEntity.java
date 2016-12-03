package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "user", schema = "parknshop", catalog = "")
public class UserEntity {
    private Integer userId;
    private String username;
    private String userImage;
    private String phone;
    private String password;
    private String email;
    private Double balance;
    private String confirm;
    private Integer state;
    private Collection<AddressEntity> addressesByUserId;
    private Collection<CartEntity> cartsByUserId;
    private Collection<CollectionEntity> collectionsByUserId;
    private Collection<CollectshopEntity> collectshopsByUserId;
    private Collection<OrdersEntity> orderssByUserId;
    private RoleEntity roleByRoleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
    @Column(name = "userImage", length = 50)
    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
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

        UserEntity that = (UserEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (balance != null ? !balance.equals(that.balance) : that.balance != null) return false;
        if (confirm != null ? !confirm.equals(that.confirm) : that.confirm != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (userImage != null ? !userImage.equals(that.state) : that.userImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (confirm != null ? confirm.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (userImage != null ? userImage.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<AddressEntity> getAddressesByUserId() {
        return addressesByUserId;
    }

    public void setAddressesByUserId(Collection<AddressEntity> addressesByUserId) {
        this.addressesByUserId = addressesByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CartEntity> getCartsByUserId() {
        return cartsByUserId;
    }

    public void setCartsByUserId(Collection<CartEntity> cartsByUserId) {
        this.cartsByUserId = cartsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CollectionEntity> getCollectionsByUserId() {
        return collectionsByUserId;
    }

    public void setCollectionsByUserId(Collection<CollectionEntity> collectionsByUserId) {
        this.collectionsByUserId = collectionsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CollectshopEntity> getCollectshopsByUserId() {
        return collectshopsByUserId;
    }

    public void setCollectshopsByUserId(Collection<CollectshopEntity> collectshopsByUserId) {
        this.collectshopsByUserId = collectshopsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<OrdersEntity> getOrderssByUserId() {
        return orderssByUserId;
    }

    public void setOrderssByUserId(Collection<OrdersEntity> orderssByUserId) {
        this.orderssByUserId = orderssByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId", nullable = false)
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
