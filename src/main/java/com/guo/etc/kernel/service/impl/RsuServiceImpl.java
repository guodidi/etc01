package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.RsuDao;
import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.service.RsuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Transactional
@Service(value = "rsuService")
public class RsuServiceImpl implements RsuService {

    @Autowired
    RsuDao rsuDao;

    @Override
    public boolean addRsu(Rsu rsu) {
        rsuDao.addRsu(rsu);
        return false;
    }

    @Override
    public boolean deleteRsu(Long id) {
        rsuDao.deleteRsu(id);
        return false;
    }

    @Override
    public boolean updateRsu(Rsu rsu) {
        rsuDao.updateRsu(rsu);
        return false;
    }

    @Override
    public Rsu findRsuById(Long id) {
        return rsuDao.findRsuById(id);
    }

    @Override
    public List<Rsu> findAllRsu() {
        return rsuDao.findAllRsu();
    }
}
