package com.guo.etc.kernel.app.client.type;

import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.RsuService;
import com.guo.etc.kernel.service.TypeService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.ArrayList;

public class TypeDialog extends JDialog {
    private JPanel contentPanel;
    private JTextField typeTF;
    private JTextField feeTF;
    private JTextField rsuSiteTF;
    private JPanel inputPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel desPanel;
    private JLabel typeLabel;
    private JLabel feeLabel;
    private JLabel describeLabel;

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static TypeDialog typeDialog = null;

    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";

    private TypeDialog(ApplicationContext context, Long[] selectId) {
        super.dialogInit();

        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.context = context;
        this.selectId = selectId;
        confirmButton.addActionListener(e -> {
            String command = e.getActionCommand();
            if (command == addName) {
                addType();
            } else if (command == updateName) {
                updateType();
            } else if (command == deleteName) {
                deleteType();
            } else {
                System.out.println("关闭窗口");
            }
        });
        cancelButton.addActionListener(e -> {
            closeDialog();
        });
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private TypeDialog(ApplicationContext context) {
        super.dialogInit();

        this.context = context;
        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        confirmButton.addActionListener(e -> {
            String command = e.getActionCommand();
            if (command == addName) {
                addType();
            } else if (command == updateName) {
                updateType();
            } else if (command == deleteName) {
                deleteType();
            } else {
                System.out.println("关闭窗口");
            }
        });
        cancelButton.addActionListener(e -> {
            closeDialog();
        });
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static TypeDialog getAddInstance(ApplicationContext context) {
        if (typeDialog != null) {
            typeDialog.dispose();
        }
        typeDialog = new TypeDialog(context);
        typeDialog.confirmButton.setText(addName);
        return typeDialog;
    }

    public static TypeDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (typeDialog != null) {
            typeDialog.dispose();
        }
        typeDialog = new TypeDialog(context, selectId);
        typeDialog.confirmButton.setText(updateName);
        typeDialog.typeTF.setText(String.valueOf(selectValues.get(1)));
        typeDialog.feeTF.setText(String.valueOf(selectValues.get(2)));
        return typeDialog;
    }

    public static TypeDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (typeDialog != null) {
            typeDialog.dispose();
        }
        typeDialog = new TypeDialog(context,selectId);
        typeDialog.confirmButton.setText(deleteName);
        typeDialog.typeTF.setText(String.valueOf(selectValues.get(1)));
        typeDialog.feeTF.setText(String.valueOf(selectValues.get(2)));
        typeDialog.typeTF.setEnabled(false);
        typeDialog.feeTF.setEnabled(false);
        return typeDialog;
    }
    private void deleteType() {
        TypeService typeService = (TypeService)context.getBean(TypeService.class);
        typeService.deleteVehicleType(selectId[0]);
        closeDialog();
    }

    private void updateType() {
        TypeService typeService = (TypeService)context.getBean(TypeService.class);
        VehicleType type = typeService.findVehicleTypeById(selectId[0]);
        type.setType(typeTF.getText());
        type.setFee(Integer.valueOf(feeTF.getText()));
        typeService.updateVehicleType(type);

        closeDialog();
    }

    private void addType() {
        TypeService typeService = (TypeService)context.getBean(TypeService.class);
        VehicleType type = new VehicleType();
        type.setType(typeTF.getText());
        type.setFee(Integer.valueOf(feeTF.getText()));
        typeService.addVehicleType(type);
        closeDialog();
    }

    private void closeDialog (){
        this.dispose();
    }

}
