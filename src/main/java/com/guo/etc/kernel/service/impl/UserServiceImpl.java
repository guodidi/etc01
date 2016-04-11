package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.UserDao;
import com.guo.etc.kernel.model.User;
import com.guo.etc.kernel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(new User(),id);
    }

    @Override
    public void mergeUser(User user, Long id) {
        userDao.mergeUser(user, id);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User findById(Long id) {
        return userDao.getUser();
    }
}
