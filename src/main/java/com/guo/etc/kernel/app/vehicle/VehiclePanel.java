package com.guo.etc.kernel.app.vehicle;

import com.guo.etc.kernel.app.base.BasePanel;
import com.guo.etc.kernel.model.User;
import com.guo.etc.kernel.service.UserService;
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
        return new String[] {"序号","用户名","密码","年龄"};
    }

    @Override
    public String[][] tableData() {
        UserService userService = context.getBean(UserService.class);
        List<User> userList = userService.getAllUser();
        int i=0;
        if(userList != null) {
            String[][] users = new String[userList.size()][4];
            for(User user: userList) {
                String[] temp = new String[] {String.valueOf(user.getId()),user.getUserName(),user.getUserPassword(),String.valueOf(user.getUserAge())};
                users[i++] = temp;
            }
            return users;
        }
        return null;
    }

    @Override
    public JPanel setButtonJPanel() {
        return new VehicleMiddlePanel(context);
        //return new MiddleButtonPanel(BasePanel.vehiclePanel);
    }
}
