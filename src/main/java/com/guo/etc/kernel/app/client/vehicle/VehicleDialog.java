package com.guo.etc.kernel.app.client.vehicle;

import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.RsuService;
import com.guo.etc.kernel.service.TypeService;
import com.guo.etc.kernel.service.VehicleService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.ArrayList;

public class VehicleDialog extends JDialog {
    private JPanel contentPanel;
    private JTextField vehicleIdTF;
    private JTextField vehicleOwnerTF;
    private JTextField obuMacTF;
    private JPanel inputPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel desPanel;
    private JLabel vehicleIdLabel;
    private JLabel vehicleOwnerLabel;
    private JLabel obuMacLabel;
    private JLabel describeLabel;
    private JComboBox vehicleTypeTF;
    private JLabel vehicleTypeLabel;

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static VehicleDialog vehicleDialog = null;

    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";

    private VehicleDialog(ApplicationContext context, Long[] selectId) {

        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.context = context;
        this.selectId = selectId;
        confirmButton.addActionListener(e -> {
            String command = e.getActionCommand();
            if (command == addName) {
                addVehicle();
            } else if (command == updateName) {
                updateVehicle();
            } else if (command == deleteName) {
                deleteVehicle();
            } else {
                System.out.println("关闭窗口");
            }
        });
        cancelButton.addActionListener(e -> {
            closeDialog();
        });
        setComBoxContent();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private VehicleDialog(ApplicationContext context) {
        super.dialogInit();
        this.context = context;
        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        confirmButton.addActionListener(e -> {
            String command = e.getActionCommand();
            if (command == addName) {
                addVehicle();
            } else if (command == updateName) {
                updateVehicle();
            } else if (command == deleteName) {
                deleteVehicle();
            } else {
                System.out.println("关闭窗口");
            }
        });
        cancelButton.addActionListener(e -> {
            closeDialog();
        });
        setComBoxContent();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void setComBoxContent(){
        TypeService typeService = (TypeService)context.getBean(TypeService.class);
        java.util.List<VehicleType> VehicleTypes = typeService.findAllVehicleType();
        ArrayList<String> typeList = new ArrayList<String>();
        for(VehicleType type : VehicleTypes) {
            typeList.add(type.getType());
        }
        vehicleTypeTF.setModel(new DefaultComboBoxModel<>(typeList.toArray()));
    }

    public static VehicleDialog getAddInstance(ApplicationContext context) {
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        vehicleDialog = new VehicleDialog(context);
        vehicleDialog.confirmButton.setText(addName);
        return vehicleDialog;
    }

    public static VehicleDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        vehicleDialog = new VehicleDialog(context,selectId);
        vehicleDialog.confirmButton.setText(updateName);
        vehicleDialog.vehicleIdTF.setText(String.valueOf(selectValues.get(1)));
        vehicleDialog.vehicleOwnerTF.setText(String.valueOf(selectValues.get(2)));
        vehicleDialog.vehicleTypeTF.setSelectedItem(selectValues.get(3));
        vehicleDialog.obuMacTF.setText(String.valueOf(selectValues.get(4)));
        return vehicleDialog;
    }

    public static VehicleDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        vehicleDialog = new VehicleDialog(context,selectId);
        vehicleDialog.confirmButton.setText(deleteName);
        vehicleDialog.vehicleIdTF.setText(String.valueOf(selectValues.get(1)));
        vehicleDialog.vehicleOwnerTF.setText(String.valueOf(selectValues.get(2)));
        vehicleDialog.vehicleTypeTF.setSelectedItem(selectValues.get(3));
        vehicleDialog.obuMacTF.setText(String.valueOf(selectValues.get(4)));
        vehicleDialog.vehicleIdTF.setEnabled(false);
        vehicleDialog.vehicleOwnerTF.setEnabled(false);
        vehicleDialog.vehicleTypeTF.setEnabled(false);
        vehicleDialog.obuMacTF.setEnabled(false);
        return vehicleDialog;
    }


    private void addVehicle() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleIdTF.getText());
        vehicle.setVehicleType(String.valueOf(vehicleTypeTF.getSelectedItem()));
        vehicle.setVehicleOwner(vehicleOwnerTF.getText());
        vehicle.setObuMac(obuMacTF.getText());
        vehicleService.addVehicle(vehicle);
        closeDialog();
    }

    private void updateVehicle() {
        VehicleService vehicleService = (VehicleService) context.getBean(VehicleService.class);
        Vehicle vehicle = vehicleService.findVehicleById(selectId[0]);
        vehicle.setVehicleId(vehicleIdTF.getText());
        vehicle.setVehicleType(String.valueOf(vehicleTypeTF.getSelectedItem()));
        vehicle.setVehicleOwner(vehicleOwnerTF.getText());
        vehicle.setObuMac(obuMacTF.getText());
        vehicleService.updateVehicle(vehicle);
        closeDialog();
    }

    private void deleteVehicle() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        vehicleService.deleteVehicle(selectId[0]);
        closeDialog();

    }

    private void closeDialog (){
        this.dispose();
    }

}
