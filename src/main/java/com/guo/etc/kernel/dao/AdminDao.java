package com.guo.etc.kernel.dao;

/**
 * Created by Administrator on 2016/5/16.
 */
public interface AdminDao {
    //根据登录框传入的字符串判断是不是管理员
    public boolean adjustAdmin(String[] hql);
}
