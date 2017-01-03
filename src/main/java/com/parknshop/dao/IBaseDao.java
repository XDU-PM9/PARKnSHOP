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
    public  boolean insert(String sql,Object[] params);
    public boolean  delete(String sql,Integer id);
    public  boolean update(String sql,Object[] params);
    void update(T o) throws Exception;
    Serializable update(List<T> o) throws Exception;
    void saveOrUpdate(T o);//此功能尚未完成
    List<Object> executeSQL(String sql,Object[] param);
    List<T> find(String hql);
    public int countNum(String hql,Object[] param);
    List<T> find(String hql, Object[] param);

    List<T> find(String hql, List<Object> param);

    List<T> find(String hql, Object[] param, Integer page, Integer rows);

    List<T> findNumberRows(String hql, Object[] param, Integer first, Integer max);

    List<T> findNumberRows(String hql, List param, Integer first, Integer max);

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
