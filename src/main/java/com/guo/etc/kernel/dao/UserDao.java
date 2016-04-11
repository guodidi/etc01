package com.guo.etc.kernel.dao;

import com.guo.etc.kernel.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public interface UserDao {

    public void addUser(User user);

    public void deleteUser(User user, Long id);

    public void mergeUser(User user,Long id);

    public List<User>  getAllUser();

    public User getUser();

}
