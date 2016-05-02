package com.guo.etc.kernel.app.client.rsu;

import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.service.RsuService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.util.ArrayList;

public class RsuDialog extends JDialog {
    private JPanel contentPanel;
    private JTextField rsuIdTF;
    private JTextField rsuNameTF;
    private JTextField rsuSiteTF;
    private JPanel inputPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel desPanel;
    private JLabel rsuIdLabel;
    private JLabel rsuNameLabel;
    private JLabel rsuSiteLabel;
    private JLabel describeLabel;

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static RsuDialog rsuDialog = null;

    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";

    private RsuDialog (ApplicationContext context,Long[] selectId) {
        super.dialogInit();
        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.context = context;
        this.selectId = selectId;
        confirmButton.addActionListener(e -> {
            String command = e.getActionCommand();
            if (command == addName) {
                addRsu();
            } else if (command == updateName) {
                updateRsu();
            } else if (command == deleteName) {
                deleteRsu();
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

    private RsuDialog(ApplicationContext context) {
        super.dialogInit();
        this.context = context;
        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        confirmButton.addActionListener(e -> {
            String command = e.getActionCommand();
            if (command == addName) {
                addRsu();
            } else if (command == updateName) {
                updateRsu();
            } else if (command == deleteName) {
                deleteRsu();
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

    public static RsuDialog getAddInstance(ApplicationContext context) {
        if (rsuDialog != null) {
            rsuDialog.dispose();
        }
        rsuDialog = new RsuDialog(context);
        rsuDialog.confirmButton.setText(addName);
        return rsuDialog;
    }

    public static RsuDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (rsuDialog != null) {
            rsuDialog.dispose();
        }
        rsuDialog = new RsuDialog(context,selectId);
        rsuDialog.confirmButton.setText(updateName);
        rsuDialog.rsuIdTF.setText(String.valueOf(selectValues.get(1)));
        rsuDialog.rsuNameTF.setText(String.valueOf(selectValues.get(2)));
        rsuDialog.rsuSiteTF.setText(String.valueOf(selectValues.get(3)));
        return rsuDialog;
    }

    public static RsuDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (rsuDialog != null) {
            rsuDialog.dispose();
        }
        rsuDialog = new RsuDialog(context,selectId);
        rsuDialog.confirmButton.setText(deleteName);
        rsuDialog.rsuIdTF.setText(String.valueOf(selectValues.get(1)));
        rsuDialog.rsuNameTF.setText(String.valueOf(selectValues.get(2)));
        rsuDialog.rsuSiteTF.setText(String.valueOf(selectValues.get(3)));
        rsuDialog.rsuIdTF.setEnabled(false);
        rsuDialog.rsuNameTF.setEnabled(false);
        rsuDialog.rsuSiteTF.setEnabled(false);
        return rsuDialog;
    }


    private void deleteRsu() {
        System.out.println("这是删除操作");
        RsuService rsuService = (RsuService)context.getBean(RsuService.class);
        rsuService.deleteRsu(selectId[0]);
        closeDialog();
    }

    private void updateRsu() {
        System.out.println("这是修改操作");
        RsuService rsuService = (RsuService)context.getBean(RsuService.class);
        Rsu rsu = rsuService.findRsuById(selectId[0]);
        rsu.setRsuId(rsuIdTF.getText());
        rsu.setRsuName(rsuNameTF.getText());
        rsu.setRsuSite(rsuSiteTF.getText());
        rsuService.updateRsu(rsu);
        closeDialog();
    }

    private void addRsu() {
        RsuService rsuService = (RsuService)context.getBean(RsuService.class);
        Rsu rsu = new Rsu();
        rsu.setRsuId(rsuIdTF.getText());
        rsu.setRsuName(rsuNameTF.getText());
        rsu.setRsuSite(rsuSiteTF.getText());
        rsuService.addRsu(rsu);
        closeDialog();
    }

    private void closeDialog (){
        this.dispose();
    }

}
