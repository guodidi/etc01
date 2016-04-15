package com.guo.etc.kernel.dao.impl;

import com.guo.etc.kernel.dao.TypeDao;
import com.guo.etc.kernel.model.VehicleType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/4/11.
 */
@Repository(value = "typeDao")
public class TypeDaoImpl extends BaseDaoImpl implements TypeDao {


    @Override
    public boolean addVehicleType(VehicleType vehicleType) {
        super.save(vehicleType);
        return false;
    }

    @Override
    public boolean deleteVehicleType(Long id) {
        VehicleType vehicleType = super.findById(VehicleType.class, id);
        if(vehicleType == null) {
            System.out.println("在VehicleType中尚未查找到该id对应的实体--->"+"id: "+id);
            return false;
        } else {
            super.delete(vehicleType);
        }
        return true;
    }

    @Override
    public boolean updateVehicleType(VehicleType vehicleType) {
        super.merge(vehicleType);
        return false;
    }

    @Override
    public VehicleType findVehicleTypeById(Long id) {
        return super.findById(VehicleType.class, id);
    }

    @Override
    public List<VehicleType> findAllVehicleType() {
        return super.findAllEntity(VehicleType.class);
    }
}
