package com.guo.etc.kernel.app.client.record;

import com.guo.etc.kernel.model.Record;
import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.service.RecordService;
import com.guo.etc.kernel.service.RsuService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RecordDialog extends JDialog {
    private JPanel contentPanel;
    private JTextField vehicleIdTF;
    private JTextField chargeStatusTF;
    private JTextField feeTF;
    private JPanel inputPanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JPanel desPanel;
    private JLabel vehicleIdLabel;
    private JLabel chargeStatusLabel;
    private JLabel feeLabel;
    private JLabel describeLabel;
    private JLabel rsuIdLabel;
    private JLabel laneIdLabel;
    private JLabel chargeTimeLabel;
    private JTextField rsuIdTF;
    private JTextField laneIdTF;
    private JTextField chargeTimeTF;

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static RecordDialog recordDialog = null;

    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";

    private RecordDialog(ApplicationContext context, Long[] selectId) {
        super.dialogInit();
        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(RecordDialog.class.getResource("/image/fee2.png")));
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
        //居中显示
        this.setLocationRelativeTo(null);
    }

    private RecordDialog(ApplicationContext context) {
        super.dialogInit();
        this.context = context;
        setContentPane(contentPanel);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(RecordDialog.class.getResource("/image/fee2.png")));
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

    public static RecordDialog getAddInstance(ApplicationContext context) {
        if (recordDialog != null) {
            recordDialog.dispose();
        }
        recordDialog = new RecordDialog(context);
        recordDialog.confirmButton.setText(addName);
        return recordDialog;
    }

    public static RecordDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (recordDialog != null) {
            recordDialog.dispose();
        }
        recordDialog = new RecordDialog(context,selectId);
        recordDialog.vehicleIdTF.setText(String.valueOf(selectValues.get(1)));
        recordDialog.rsuIdTF.setText(String.valueOf(selectValues.get(3)));
        recordDialog.laneIdTF.setText(String.valueOf(selectValues.get(4)));
        recordDialog.chargeTimeTF.setText(String.valueOf(selectValues.get(5)));
        recordDialog.chargeStatusTF.setText(String.valueOf(selectValues.get(6)));
        recordDialog.feeTF.setText(String.valueOf(selectValues.get(7)));
        recordDialog.chargeTimeTF.setEnabled(false);
        recordDialog.vehicleIdTF.setEnabled(false);
        recordDialog.confirmButton.setText(updateName);
        return recordDialog;
    }

    public static RecordDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (recordDialog != null) {
            recordDialog.dispose();
        }
        recordDialog = new RecordDialog(context,selectId);
        recordDialog.vehicleIdTF.setText(String.valueOf(selectValues.get(1)));
        recordDialog.rsuIdTF.setText(String.valueOf(selectValues.get(3)));
        recordDialog.laneIdTF.setText(String.valueOf(selectValues.get(4)));
        recordDialog.chargeTimeTF.setText(String.valueOf(selectValues.get(5)));
        recordDialog.chargeStatusTF.setText(String.valueOf(selectValues.get(6)));
        recordDialog.feeTF.setText(String.valueOf(selectValues.get(7)));
        recordDialog.vehicleIdTF.setEnabled(false);
        recordDialog.rsuIdTF.setEnabled(false);
        recordDialog.laneIdTF.setEnabled(false);
        recordDialog.chargeTimeTF.setEnabled(false);
        recordDialog.chargeStatusTF.setEnabled(false);
        recordDialog.feeTF.setEnabled(false);
        recordDialog.confirmButton.setText(deleteName);
        return recordDialog;
    }


    private void deleteRsu() {
        System.out.println("这是删除操作");
        RecordService rsuService = (RecordService)context.getBean(RecordService.class);
        rsuService.deleteRecord(selectId[0]);
        closeDialog();
    }

    private void updateRsu() {
        System.out.println("这是修改操作");
        RecordService recordService = (RecordService)context.getBean(RecordService.class);
        Record record = recordService.findRecordById(selectId[0]);
        record.setRsuId(rsuIdTF.getText());
        record.setRoadId(laneIdTF.getText());
        record.setTradeStatus(chargeStatusTF.getText());
        if (feeTF.getText() == null) {
            record.setFee(0L);
        }
        record.setFee(Long.valueOf(feeTF.getText()));
        recordService.updateRecord(record);
        closeDialog();
    }

    private void addRsu() {

    }

    private void closeDialog (){
        this.dispose();
    }

}
