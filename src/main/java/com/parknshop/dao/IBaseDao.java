package com.parknshop.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weina on 2016/11/25.
 */
public interface IBaseDao<T> {

    Serializable save(T o) throws Exception;
    public Serializable save(List<T> o)throws  Exception;
    void delete(T o);//此功能尚未完成

    void update(T o) throws Exception;

    void saveOrUpdate(T o);//此功能尚未完成

    List<T> find(String hql);

    List<T> find(String hql, Object[] param);

    List<T> find(String hql, List<Object> param);

    List<T> find(String hql, Object[] param, Integer page, Integer rows);

    List<T> find(String hql, List<Object> param, Integer page, Integer rows);

    T get(Class<T> c, Serializable id);

    T get(String hql, Object[] param);

    T get(String hql, List<Object> param);

    Long count(String hql);

    Long count(String hql, Object[] param);

    Long count(String hql, List<Object> param);

    Integer executeHql(String hql);//此功能尚未完成

    Integer executeHql(String hql, Object[] param);//此功能尚未完成

    Integer executeHql(String hql, List<Object> param);//此功能尚未完成
}
