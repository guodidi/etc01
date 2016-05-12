package com.guo.etc.kernel.service.impl;

import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class VehicleServiceImplTest {


    @Autowired
    VehicleService vehicleService;

    @Test
    public void testFindByHql() throws Exception {
/*        Vehicle vehicle = vehicleService.findVehicleById(1L);
        System.out.println("vehicle ID is ： "+vehicle.getId());
        System.out.println("vehicle Type is ： "+vehicle.getVehicleType());

        System.out.println("-------------------------------------------------------------");
        List<Vehicle> vehicles = vehicleService.findAllVehicle();
        System.out.println("分割线");*/
        System.out.println("分割线");
        System.out.println("--------------------------------------------------------------");
/*        //Vehicle vehicle = vehicleService.findByHql("闽B","小型车","951");
        if (vehicle != null) {
                        System.out.println("id : "+vehicle.getId());
            System.out.println("type : "+vehicle.getVehicleType());
            System.out.println("obu : "+vehicle.getObuMac());
            System.out.println("owner : "+vehicle.getVehicleOwner());
        }*/
        System.out.println("--------------------------------------------------------------");
        System.out.println("分割线");
    }
}
