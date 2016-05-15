package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.AdminDao;
import com.guo.etc.kernel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/5/16.
 */
@Transactional
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    @Override
    public boolean adjustAdmin(String[] names) {
        return adminDao.adjustAdmin(names);
    }
}
