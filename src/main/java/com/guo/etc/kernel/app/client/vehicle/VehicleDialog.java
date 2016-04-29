package com.guo.etc.kernel.app.client.vehicle;

import com.guo.etc.kernel.model.Vehicle;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.TypeService;
import com.guo.etc.kernel.service.VehicleService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Administrator on 2016/4/9.
 */
public class VehicleDialog extends JDialog implements ActionListener {

    private ApplicationContext context = null;
    private Long[] selectId ;

    private static VehicleDialog vehicleDialog = null;
    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";
    private static final String cancelName = "取消";

    private JButton confirmButton = new JButton("确定");
    private JButton cancelButton = new JButton(cancelName);
    private JLabel describeLabel = new JLabel();

    private JPanel describePanel = new JPanel();
    private JPanel inputPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private JLabel vehicleIdLabel = new JLabel("车牌号码");
    private JTextField vehicleIdTF = new JTextField(30);
    private JLabel vehicleTypeLabel = new JLabel("车牌类型");
    private JComboBox vehicleTypeTF = null;
    private JLabel vehicleOwnerLabel = new JLabel("车主");
    private JTextField vehicleOwnerTF = new JTextField(30);
    private JLabel obuMacLabel = new JLabel("OBU的MAC");
    private JTextField obuMacTF = new JTextField(30);

    private VehicleDialog (ApplicationContext context,Long[] selectId) {
        this.context = context;
        this.selectId = selectId;
        init();
    }

    private VehicleDialog(ApplicationContext context) {
        this.context = context;
        init();
    }


    private void init() {
        super.dialogInit();
        this.setLayout(new BorderLayout());
        this.add(describePanel,BorderLayout.NORTH);
        setDescribePanel();
        this.add(inputPanel,BorderLayout.CENTER);
        setInputPanel();
        this.add(buttonPanel,BorderLayout.SOUTH);
        setButtonPanel();
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600,400));
        setLocation(600 / 2 - getWidth() / 2, 400 / 2 - getHeight() / 2);
    }

    private void setDescribePanel() {
        describeLabel.setText("这是车辆管理Dialog");
        GridBagLayout layout =new GridBagLayout();
        buttonPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        layout.setConstraints(describeLabel,constraints);
        describePanel.add(describeLabel);
    }

    private void setInputPanel() {
        TypeService typeService = (TypeService)context.getBean(TypeService.class);
        java.util.List<VehicleType> VehicleTypes = typeService.findAllVehicleType();
        ArrayList<String> typeList = new ArrayList<String>();
        for(VehicleType type : VehicleTypes) {
            typeList.add(type.getType());
        }
        vehicleTypeTF = new JComboBox(typeList.toArray());
        GridBagLayout layout = new GridBagLayout();
        inputPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10,20,10,20);
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(vehicleIdLabel, constraints);
        inputPanel.add(vehicleIdLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(vehicleIdTF, constraints);
        inputPanel.add(vehicleIdTF);

        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(vehicleTypeLabel, constraints);
        inputPanel.add(vehicleTypeLabel);
        constraints.gridwidth = 0;
        vehicleTypeTF.setPreferredSize(new Dimension(330,28));//设置JBox的大小
        layout.setConstraints(vehicleTypeTF, constraints);
        inputPanel.add(vehicleTypeTF);

        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(vehicleOwnerLabel, constraints);
        inputPanel.add(vehicleOwnerLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(vehicleOwnerTF, constraints);
        inputPanel.add(vehicleOwnerTF);

        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(obuMacLabel, constraints);
        inputPanel.add(obuMacLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(obuMacTF, constraints);
        inputPanel.add(obuMacTF);
    }

    private void setButtonPanel() {
        GridBagLayout layout =new GridBagLayout();
        buttonPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20,20,20,20);
        confirmButton.addActionListener(this);
        layout.setConstraints(confirmButton,constraints);
        buttonPanel.add(confirmButton);
        constraints.anchor = GridBagConstraints.EAST;
        cancelButton.addActionListener(this);
        layout.setConstraints(cancelButton,constraints);
        buttonPanel.add(cancelButton);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals(addName)) {
            addUser();
            this.dispose();
        } else if (command.equals(updateName)) {
            updateVehicle();
            this.dispose();
        } else if (command.equals(deleteName)) {
            delete();
            this.dispose();
        } else if(command.equals(cancelName)) {
            this.dispose();
        } else {
            System.out.println("Error has happen in VehicleDialog");
            this.dispose();
        }
    }

    private void addUser() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(vehicleIdTF.getText());
        vehicle.setVehicleType(String.valueOf(vehicleTypeTF.getSelectedItem()));
        vehicle.setVehicleOwner(vehicleOwnerTF.getText());
        vehicle.setObuMac(obuMacTF.getText());
        vehicleService.addVehicle(vehicle);
    }

    private void updateVehicle() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        Vehicle vehicle = vehicleService.findVehicleById(selectId[0]);
        vehicle.setVehicleId(vehicleIdTF.getText());
        vehicle.setVehicleType(String.valueOf(vehicleTypeTF.getSelectedItem()));
        vehicle.setVehicleOwner(vehicleOwnerTF.getText());
        vehicle.setObuMac(obuMacTF.getText());
        vehicleService.updateVehicle(vehicle);
    }

    private void delete() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        vehicleService.deleteVehicle(selectId[0]);
    }

}
