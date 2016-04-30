package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */

public class BaseDaoImpl implements BaseDao {

    @Autowired
    SessionFactory sessionFactory;

    //获取Session
    public Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> void save(T entity) {
        this.getSession(sessionFactory).save(entity);
    }

    @Override
    public <T> void delete(T entity) {
        if(entity != null) {
            this.getSession(sessionFactory).delete(entity);
        } else {
            System.out.println("the entity no find !");
        }
    }

    @Override
    public <T> void merge(T entity) {
        this.getSession(sessionFactory).update(entity);
    }

    @Override
    public <T> T findById(Class<T> clazz, Long id) {
        return (T) this.getSession(sessionFactory).get(clazz, id);
    }

    @Override
    public <T> List<T>  findAllEntity(Class<T> clazz) {
        String hql = "from " + clazz.getName() + " as entity order by entity.id asc";
        System.out.println("hql : "+hql);
        Query query = this.getSession(sessionFactory).createQuery(hql);
        return query.list();
    }

    //根据传入的HQL语言来查找记录
    @Override
    public <T> T findBySql(String sourceHQL) {
        String hql = sourceHQL;
        System.out.println("sourceHQL : "+sourceHQL);
        try {
            Query query = this.getSession(sessionFactory).createQuery(hql);
            List<T> list = query.list();
            T t = null;
            if (list != null&&list.size()>0) {
                t = list.get(0);
            }
            return (t);
        }catch (Exception e) {
            System.out.println("麻痹，出错了");
            return null;
        }


    }

}
