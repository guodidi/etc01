package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.TypeDao;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Transactional
@Service(value = "typeService")
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;


    @Override
    public boolean addVehicleType(VehicleType vehicleType) {
        typeDao.addVehicleType(vehicleType);
        return false;
    }

    @Override
    public boolean deleteVehicleType(Long id) {
        typeDao.deleteVehicleType(id);
        return false;
    }

    @Override
    public boolean updateVehicleType(VehicleType vehicleType) {
        typeDao.updateVehicleType(vehicleType);
        return false;
    }

    @Override
    public VehicleType findVehicleTypeById(Long id) {

        return typeDao.findVehicleTypeById(id);
    }

    @Override
    public List<VehicleType> findAllVehicleType() {
        return typeDao.findAllVehicleType();
    }
}
