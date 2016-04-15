package com.guo.etc.kernel.service;

import com.guo.etc.kernel.model.VehicleType;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface TypeService {

    //增删改查
    public boolean addVehicleType(VehicleType vehicleType);

    public boolean deleteVehicleType(Long id);

    public boolean updateVehicleType(VehicleType vehicleType);

    public VehicleType findVehicleTypeById(Long id);

    public List<VehicleType> findAllVehicleType();

}
