package com.guo.etc.kernel.service;

import com.guo.etc.kernel.model.Rsu;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface RsuService {

    //增删改查
    public boolean addRsu(Rsu rsu);

    public boolean deleteRsu(Long id);

    public boolean updateRsu(Rsu rsu);

    public Rsu findRsuById(Long id);

    public List<Rsu> findAllRsu();
}
