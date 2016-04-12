package com.guo.etc.kernel.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public interface BaseDao {

    //添加一条记录
    public <T> void save(T entity);
    //删除一条记录
    public <T> void delete(T entity);
    //更新一条记录
    public <T> void merge(T entity);
    //根据id找到一条记录
    public <T> T findById(Class<T> clazz, Long id);
    //根据一个类名找到该类的全部数据
    public <T> List<T> findAllEntity(Class<T> clazz);



}
