package com.parknshop.dao.daoImpl;

import com.parknshop.dao.IBaseDao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weina on 2016/11/25.
 */
@Repository
public class BaseDao<T> implements IBaseDao<T> {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public Serializable save(T o) throws Exception {
        Session session = null;
        try {
            // 创建session
            // 此处的session并不是web中的session
            // session只有在用时，才建立concation,session还管理缓存。
            // session用完后，必须关闭。
            // session是非线程安全，一般是一个请求一个session.
            session = this.getCurrentSession();

            // 手动开启事务(可以在hibernate.cfg.xml配置文件中配置自动开启事务)
            session.beginTransaction();
			/*
			 * 保存数据，此处的数据是保存对象，这就是hibernate操作对象的好处，
			 * 我们不用写那么多的JDBC代码，只要利用session操作对象，至于hibernat如何存在对象，这不需要我们去关心它，
			 * 这些都有hibernate来完成。我们只要将对象创建完后，交给hibernate就可以了。
			 */
            session.save(o);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            // 回滚事务
            session.getTransaction().rollback();
            throw new Exception("HWN:erro when save object");
        } finally {
            // 关闭session
            session.close();
        }
        return session;
    }
    public Serializable save(List<T> o) throws Exception {
        Session session = null;
        try {
            session = this.getCurrentSession();
            session.beginTransaction();
            for(T t:o){
                session.save(t);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            // 回滚事务
            session.getTransaction().rollback();
            throw new Exception("HWN:erro when save object");
        } finally {
            // 关闭session
            session.close();
        }
        return session;
    }
    /*
        此功能尚未实现完全
         */
    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    @Override
    public boolean insert(String sql, Object[] params) {
        Session session=this.getCurrentSession();
        Query query=session.createSQLQuery(sql);

        for(int i=0;i<params.length;i++)
        {
            query.setParameter(i,params[i]);
        }
        if(query.executeUpdate()>0) {
            session.close();
            return true;
        }
        else {
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(String sql,Integer id) {
         Session session=this.getCurrentSession();
        Query query=session.createSQLQuery(sql);
        query.setInteger(0,id);
        if(query.executeUpdate()==1) {
            session.close();
            return true;
        }
        else
        {
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(String sql, Object[] params) {
       return insert(sql,params);
    }

    public void update(T o) throws Exception {
        Session session = null;
        try {
            session = this.getCurrentSession();

            session.beginTransaction();

            session.update(o);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            // 回滚事务
            session.getTransaction().rollback();
            throw new Exception("HWN:erro when save update");
        } finally {
            // 关闭session
            session.close();
        }
        return;
    }
    @Override
    public Serializable update(List<T> o) throws Exception {
        Session session = null;
        try {
            session = this.getCurrentSession();
            session.beginTransaction();
            for(T t:o){
                session.update(t);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            // 回滚事务
            session.getTransaction().rollback();
            throw new Exception("HWN:erro when save object");
        } finally {
            // 关闭session
            session.close();
        }
        return session;
    }

    /*
    此功能尚未实现完全
     */
    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

    public List<T> find(String hql) {
        return this.getCurrentSession().createQuery(hql).list();
    }

    public List<T> find(String hql, Object[] param) {
        Session session = this.getCurrentSession();
        Query q = session.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        List<T> ts = q.list();
        session.close();
        return ts;
    }

    public List<T> find(String hql, List<Object> param) {
        Session session = this.getCurrentSession();
        Query q = session.createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        List<T> ts = q.list();
        session.close();
        return ts;
    }

    public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
        Session session = this.getCurrentSession();
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = session.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        List<T> ts = q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
        session.close();
        return ts;
    }

    @Override
    public List<T> findNumberRows(String hql, Object[] param, Integer first, Integer max) {
        Session session=this.getCurrentSession();
        Query query=session.createQuery(hql);
        if(param!=null){
            for(int i=0;i<param.length;i++){
                query.setParameter(i,param[i]);
            }
        }
        List<T> result=query.setFirstResult(first).setMaxResults(max).list();
        session.close();
        return result;
    }

    @Override
    public List<T> findNumberRows(String hql, List param, Integer first, Integer max) {
        Session session=this.getCurrentSession();
        Query query=session.createQuery(hql);
        for(int i=0;i<param.size();i++){
            query.setParameter(i, param.get(i));
        }
        List<T> result=query.setFirstResult(first).setMaxResults(max).list();
        session.close();
        return result;
    }

    public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
        Session session = this.getCurrentSession();
        if (page == null || page < 1) {
            page = 1;
        }
        if (rows == null || rows < 1) {
            rows = 10;
        }
        Query q = session.createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        List<T> ts = q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
        session.close();
        return ts;
    }

    public T get(Class<T> c, Serializable id) {
        Session session = this.getCurrentSession();
        T t = session.get(c, id);
        session.close();
        return t;
    }

    public T get(String hql, Object[] param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public T get(String hql, List<Object> param) {
        List<T> l = this.find(hql, param);
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    public Long count(String hql) {
        Session session = this.getCurrentSession();
        Long rs = (Long) session.createQuery(hql).uniqueResult();
        return rs;
    }

    public Long count(String hql, Object[] param) {
        Session session = this.getCurrentSession();
        Query q = session.createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        T t = (T) q.uniqueResult();
        session.close();
        return (Long) t;
    }

    public Long count(String hql, List<Object> param) {
        Session session = this.getCurrentSession();
        Query q = session.createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        T t = (T) q.uniqueResult();
        session.close();
        return (Long) t;
    }

    public Integer executeHql(String hql) {
        return this.getCurrentSession().createQuery(hql).executeUpdate();
    }

    public Integer executeHql(String hql, Object[] param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                q.setParameter(i, param[i]);
            }
        }
        return q.executeUpdate();
    }

    public Integer executeHql(String hql, List<Object> param) {
        Query q = this.getCurrentSession().createQuery(hql);
        if (param != null && param.size() > 0) {
            for (int i = 0; i < param.size(); i++) {
                q.setParameter(i, param.get(i));
            }
        }
        return q.executeUpdate();
    }
}

