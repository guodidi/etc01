package com.guo.etc.kernel.service;

import com.guo.etc.kernel.model.Vehicle;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface VehicleService {

    //增删改查
    public boolean addVehicle(Vehicle vehicle);

    public boolean deleteVehicle(Long id);

    public boolean updateVehicle(Vehicle vehicle);

    public Vehicle findVehicleById(Long id);

    public List<Vehicle> findAllVehicle();

    public boolean findByHql(String vehicleID, String vehicleType, String obu_mac);
}
