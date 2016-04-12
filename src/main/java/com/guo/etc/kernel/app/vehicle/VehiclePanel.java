package com.guo.etc.kernel.app.vehicle;

import com.guo.etc.kernel.app.base.BasePanel;
import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.service.VehicleService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */

public class VehiclePanel extends BasePanel {

    private ApplicationContext context = null;

    private static VehiclePanel vehiclePanel;

    private VehiclePanel(ApplicationContext context) {
        super();
        this.setVisible(true);
        this.context = context;
    }

    public static VehiclePanel getInstance(ApplicationContext context) {
        vehiclePanel = new VehiclePanel(context);
        return vehiclePanel;
    }

    @Override
    public String[] tableHeader() {
        return new String[] {"序号","车牌号码","车主","收费车型"};
    }

    @Override
    public String[][] tableData() {
        VehicleService vehicleService = context.getBean(VehicleService.class);
        List<Vehicle> vehicleList = vehicleService.findAllVehicle();
        int i=0;
        if(vehicleList != null) {
            String[][] vehicles = new String[vehicleList.size()][4];
            for(Vehicle vehicle: vehicleList) {
                String[] temp = new String[] {String.valueOf(vehicle.getId()),vehicle.getVehicleId(),vehicle.getVehicleOwner(),vehicle.getVehicleType()};
                vehicles[i++] = temp;
            }
            return vehicles;
        }
        return null;
    }

    @Override
    public JPanel setButtonJPanel() {
        return new VehicleMiddlePanel(context);
    }
}
