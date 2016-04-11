package com.guo.etc.kernel.baseComponent.impl;

import com.guo.etc.kernel.baseComponent.BaseDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Query query = this.getSession(sessionFactory).createQuery(hql);
        return query.list();
    }
}
