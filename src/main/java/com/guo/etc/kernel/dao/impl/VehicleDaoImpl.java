package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.dao.VehicleDao;
import com.guo.etc.kernel.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Repository(value = "vehicleDao")
public class VehicleDaoImpl extends BaseDaoImpl implements VehicleDao {

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        super.save(vehicle);
        return true;
    }

    @Override
    public boolean deleteVehicle(Long id) {
        Vehicle vehicle = super.findById(Vehicle.class, id);
        if (vehicle == null) {
            System.out.println("在Vehicle中没有查找到相关的id的信息---> "+"id : "+id);
            return false;
        } else {
            super.delete(vehicle);
        }
        return true;
    }

    @Override
    public boolean updateVehicle(Vehicle vehicle) {
        super.merge(vehicle);
        return false;
    }

    @Override
    public Vehicle findVehicleById(Long id) {
        Vehicle vehicle = super.findById(Vehicle.class, id);
        return vehicle;
    }

    @Override
    public List<Vehicle> findAllVehicle() {
        return super.findAllEntity(Vehicle.class);
    }
}
