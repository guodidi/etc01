package com.guo.etc.kernel.dao;


import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.model.VehicleType;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface TypeDao {

    //增删改查
    public boolean addVehicleType(VehicleType vehicleType);

    public boolean deleteVehicleType(Long id);

    public boolean updateVehicleType(VehicleType vehicleType);

    public VehicleType findVehicleTypeById(Long id);

    public List<VehicleType> findAllVehicleType();

    public VehicleType findTypeByHql(String vehicleType);

}
