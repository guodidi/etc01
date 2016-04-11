package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.VehicleTypeDao;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Transactional
@Service(value = "vehicleTypeService")
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    VehicleTypeDao vehicleTypeDao;


    @Override
    public boolean addVehicleType(VehicleType vehicleType) {
        vehicleTypeDao.addVehicleType(vehicleType);
        return false;
    }

    @Override
    public boolean deleteVehicleType(Long id) {
        vehicleTypeDao.deleteVehicleType(id);
        return false;
    }

    @Override
    public boolean updateVehicleType(VehicleType vehicleType) {
        vehicleTypeDao.updateVehicleType(vehicleType);
        return false;
    }

    @Override
    public VehicleType findVehicleTypeById(Long id) {

        return vehicleTypeDao.findVehicleTypeById(id);
    }

    @Override
    public List<VehicleType> findAllVehicleType() {
        return vehicleTypeDao.findAllVehicleType();
    }
}
