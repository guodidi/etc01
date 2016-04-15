package com.guo.etc.kernel.app.type;

import com.guo.etc.kernel.app.base.BasePanel;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.TypeService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.List;

/**
 * Created by Administrator on 2016/4/8.
 */
public class TypePanel extends BasePanel {

    private static TypePanel typePanel = null;
    private ApplicationContext context;

    private TypePanel (ApplicationContext context) {
        super();
        this.context = context;
        this.setVisible(true);
    }

    public static TypePanel getInstance(ApplicationContext context) {
            typePanel = new TypePanel(context);
        return typePanel;
    }




    @Override
    public String[] tableHeader() {
        return new String[] {"序号","收费类型","收费金额"};
    }

    @Override
    public String[][] tableData() {
        TypeService vehicleTypeService =context.getBean(TypeService.class);
        List<VehicleType> typeList = vehicleTypeService.findAllVehicleType();
        int i= 0;
        if (typeList != null) {
            String[][] types = new String[typeList.size()][3];
            for (VehicleType vehicleType : typeList) {
                String[] temp = new String[] {
                        String.valueOf(vehicleType.getId()),vehicleType.getType(),String.valueOf(vehicleType.getFee())
                };
                types[i++] = temp;
            }
            return types;
        }
         return null;
    }

    @Override
    public JPanel setButtonJPanel() {
        return new TypeMiddlePanel(context);
    }
}
