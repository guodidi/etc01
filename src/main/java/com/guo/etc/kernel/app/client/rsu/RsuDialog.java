package com.guo.etc.kernel.app.client.rsu;

import com.guo.etc.kernel.model.Rsu;
import com.guo.etc.kernel.service.RsuService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 *
 * finish in 2015/4/15
 */
public class RsuDialog extends JDialog implements ActionListener {

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static RsuDialog rsuDialog = null;

    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";
    private static final String cancelName = "取消";
    private static final String confirmName = "确定";

    private JButton confirmButton = new JButton(confirmName);
    private JButton cancelButton = new JButton(cancelName);
    private JLabel describeLabel = new JLabel();

    JPanel describePanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    private JLabel rsuIdLabel = new JLabel("Rsu编号");
    private JTextField rsuIdTF = new JTextField(30);
    private JLabel rsuSiteLabel = new JLabel("Rsu位置");
    private JTextField rsuSiteTF = new JTextField(30);
    private JLabel rsuNameLabel = new JLabel("Rsu名称");
    private JTextField rsuNameTF = new JTextField(30);

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
        describeLabel.setText("这是Rsu管理Dialog");
        GridBagLayout layout =new GridBagLayout();
        buttonPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        layout.setConstraints(describeLabel,constraints);
        describePanel.add(describeLabel);
    }

    private void setInputPanel() {
        GridBagLayout layout = new GridBagLayout();
        inputPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20,20,20,20);
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(rsuIdLabel, constraints);
        inputPanel.add(rsuIdLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(rsuIdTF, constraints);
        inputPanel.add(rsuIdTF);

        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(rsuNameLabel, constraints);
        inputPanel.add(rsuNameLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(rsuNameTF, constraints);
        inputPanel.add(rsuNameTF);

        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(rsuSiteLabel, constraints);
        inputPanel.add(rsuSiteLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(rsuSiteTF, constraints);
        inputPanel.add(rsuSiteTF);
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

    private RsuDialog (ApplicationContext context,Long[] selectId) {
        this.context = context;
        this.selectId = selectId;
        init();
    }

    private RsuDialog(ApplicationContext context) {
        this.context = context;
        init();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == addName) {
            addRsu();
        } else if (command == updateName) {
            updateRsu();
        } else if (command == deleteName) {
            deleteRsu();
        } else if (command == cancelName) {
            closeDialog();
        } else {
            System.out.println("关闭窗口");
        }

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
