package com.guo.etc.kernel.app.vehicle;

import com.guo.etc.kernel.app.base.ReloadPanel;
import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.service.VehicleService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/9.
 */
public class VehicleDialog extends JDialog implements ActionListener {

    private ApplicationContext context = null;
    private Long[] selectId ;

    private static VehicleDialog vehicleDialog = null;
    private static final String addName = "确认";
    private static final String deleteName = "删除";
    private static final String updateName = "更新";
    private static final String cancelName = "取消";

    private JButton confirmButton = new JButton();
    private JButton cancelButton = new JButton();
    JPanel describePanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    /*
    * 界面设计
    * */
    private void init() {
        super.dialogInit();
        this.setLayout(new BorderLayout());
        //顶层介绍JPanel
        this.add(setDescribePanel(),BorderLayout.NORTH);
        //中间层输入JPanel
        this.add(setInputPanel(),BorderLayout.CENTER);
        //底层按钮JPanel
        this.add(setButtonPanel(),BorderLayout.SOUTH);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600,400));
        setLocation(600 / 2 - getWidth() / 2, 400 / 2 - getHeight() / 2);
    }

    private JPanel setButtonPanel() {
        return null;
    }

    private JPanel setInputPanel() {
        return null;
    }

    private JPanel setDescribePanel() {
        return null;
    }








    private VehicleDialog (ApplicationContext context,Long[] selectId) {
        this.context = context;
        this.selectId = selectId;
        init();
    }

    private VehicleDialog(ApplicationContext context) {
        this.context = context;
        init();
    }

    public static VehicleDialog getAddInstance(ApplicationContext context) {
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        vehicleDialog = new VehicleDialog(context);

        return vehicleDialog;
    }

    public static VehicleDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在刷新中");
            return null;
        }
        vehicleDialog = new VehicleDialog(context,selectId);

        return vehicleDialog;
    }

    public static VehicleDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在删除中");
        } else {

            vehicleDialog.confirmButton.setText("删除");
        }

        return vehicleDialog;

    }







    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("添加")) {
            addUser();
            this.dispose();
            ReloadPanel.reloadPanel(VehiclePanel.getInstance(context));
            System.out.println("这是添加的符号呢！");
        } else if (command.equals("修改")) {
            updateVehicle();
            this.dispose();
            ReloadPanel.reloadPanel(VehiclePanel.getInstance(context));
            System.out.println("这是修改的符号，我靠真的可以呢");
        } else if (command.equals("删除")) {
            delete();
            this.dispose();
            ReloadPanel.reloadPanel(VehiclePanel.getInstance(context));
            System.out.println("这里是删除，对表和数据库进行删除操作");
        }
    }

    private void addUser() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        Vehicle vehicle = new Vehicle();

        vehicleService.addVehicle(vehicle);
    }

    private void updateVehicle() {

    }

    private void delete() {

    }



}
