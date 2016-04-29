package com.guo.etc.kernel.dao;

import com.guo.etc.kernel.model.Vehicle;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface VehicleDao {



    //增删改查
    public boolean addVehicle(Vehicle vehicle);

    public boolean deleteVehicle(Long id);

    public boolean updateVehicle(Vehicle vehicle);

    public Vehicle findVehicleById(Long id);

    public List<Vehicle> findAllVehicle();

    //根据车辆的车牌查找该车所在的记录
    public Vehicle findByVehicleID(String vehicleID);
}


