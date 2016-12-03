package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "admin", schema = "parknshop", catalog = "")
public class AdminEntity {
    private Integer adminId;
    private String username;
    private String userImage;
    private String password;
    private RoleEntity roleByRoleId;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "adminId", nullable = false)
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
    @Column(name = "username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (adminId != null ? !adminId.equals(that.adminId) : that.adminId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (userImage != null ? !userImage.equals(that.userImage) : that.userImage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminId != null ? adminId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userImage != null ? userImage.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }
}
