package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.dao.VehicleDao;
import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Transactional
@Service(value = "vehicleService")
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        vehicleDao.addVehicle(vehicle);
        return false;
    }

    @Override
    public boolean deleteVehicle(Long id) {
        vehicleDao.deleteVehicle(id);
        return false;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        vehicleDao.updateVehicle(vehicle);
        return false;
    }

    @Override
    public Vehicle findVehicleById(Long id) {
        return vehicleDao.findVehicleById(id);
    }

    @Override
    public List<Vehicle> findAllVehicle() {
        return vehicleDao.findAllVehicle();
    }

    @Override
    public boolean findByHql(String vehicleID, String vehicleType, String obuMac) {
        Vehicle vehicle = vehicleDao.findByVehicleID(vehicleID, vehicleType,obuMac);
        if (vehicle != null) {
            return true;
        }
        System.out.println("vehicleID和vehicleType的类型不匹配！");
        return false;
    }
}
