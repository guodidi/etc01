package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.dao.RsuDao;
import com.guo.etc.kernel.model.Rsu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Repository(value = "rsuDao")
public class RsuDaoImpl extends BaseDaoImpl implements RsuDao {
    @Override
    public boolean addRsu(Rsu rsu) {
        super.save(rsu);
        return false;
    }

    @Override
    public boolean deleteRsu(Long id) {
        Rsu rsu = super.findById(Rsu.class,id);
        if (rsu == null) {
            System.out.println("你查询的Rsu在数据库中不存在： "+ id);
            return false;
        }else {
            super.delete(rsu);
        }
        return false;
    }

    @Override
    public boolean updateRsu(Rsu rsu) {
        super.merge(rsu);
        return true;
    }

    @Override
    public Rsu findRsuById(Long id) {
        return super.findById(Rsu.class, id);
    }

    @Override
    public List<Rsu> findAllRsu() {
        return super.findAllEntity(Rsu.class);
    }
}
