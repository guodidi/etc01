package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.baseComponent.impl.BaseDaoImpl;
import com.guo.etc.kernel.dao.UserDao;
import com.guo.etc.kernel.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
@Repository(value = "userDao")
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public void addUser(User user) {
        super.save(user);
    }

    @Override
    public void deleteUser(User user, Long id) {
        User user1 = super.findById(User.class, id);
        super.delete(user1);
    }

    @Override
    public void mergeUser(User user, Long id) {
        User user1 = super.findById(User.class, id);
        if(user1 == null) {
            System.out.println("get in");
            user1.setId(id);
        }
        user1.setUserName(user.getUserName());
        user1.setUserPassword(user.getUserPassword());
        user1.setYesNo(user.getYesNo());
        user1.setUserAge(user.getUserAge());
        super.merge(user1);
    }

    @Override
    public List<User> getAllUser() {

        return super.findAllEntity(User.class);
    }

    @Override
    public User getUser() {
        return super.findById(User.class,1L);
    }
}
