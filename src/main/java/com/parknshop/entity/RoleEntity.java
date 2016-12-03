package com.parknshop.entity;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by weina on 2016/11/30.
 */
@Entity
@Repository
@Table(name = "role", schema = "parknshop", catalog = "")
public class RoleEntity {
    private Integer roleId;
    private String describes;
    private Collection<AdminEntity> adminsByRoleId;
    private Collection<OwnerEntity> ownersByRoleId;
    private Collection<UserEntity> usersByRoleId;

    @Id
    @Column(name = "roleId", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "describes", nullable = true, length = 50)
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (describes != null ? !describes.equals(that.describes) : that.describes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (describes != null ? describes.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<AdminEntity> getAdminsByRoleId() {
        return adminsByRoleId;
    }

    public void setAdminsByRoleId(Collection<AdminEntity> adminsByRoleId) {
        this.adminsByRoleId = adminsByRoleId;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<OwnerEntity> getOwnersByRoleId() {
        return ownersByRoleId;
    }

    public void setOwnersByRoleId(Collection<OwnerEntity> ownersByRoleId) {
        this.ownersByRoleId = ownersByRoleId;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<UserEntity> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Collection<UserEntity> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }
}
