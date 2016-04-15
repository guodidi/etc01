package com.guo.etc.kernel.app.type;

import com.guo.etc.kernel.model.VehicleType;
import com.guo.etc.kernel.service.TypeService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 *
 * finish in 2016/4/15.
 *
 */
public class TypeDialog extends JDialog implements ActionListener {
    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static TypeDialog typeDialog = null;

    private static TypeDialog vehicleDialog = null;
    private static final String addName = "添加";
    private static final String deleteName = "删除";
    private static final String updateName = "修改";
    private static final String cancelName = "取消";
    private static final String confirmName = "确定";

    JPanel describePanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    private JLabel describeLabel = new JLabel();

    private JButton confirmButton = new JButton(confirmName);
    private JButton cancelButton = new JButton(cancelName);


    private JLabel typeLabel = new JLabel("类型");
    private JLabel feeLabel = new JLabel("费用");

    private JTextField typeTF  = new JTextField(30);
    private JTextField feeTF = new JTextField(30);



    //初始化GUI界面
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
    //底层的按钮的位置设置
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

    //输入区域的位置设置
    private void setInputPanel() {
        GridBagLayout layout = new GridBagLayout();
        inputPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(20,20,20,20);
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(typeLabel, constraints);
        inputPanel.add(typeLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(typeTF, constraints);
        inputPanel.add(typeTF);
        constraints.gridheight = 1;
        constraints.gridwidth = 2;
        layout.setConstraints(feeLabel, constraints);
        inputPanel.add(feeLabel);
        constraints.gridwidth = 0;
        layout.setConstraints(feeTF, constraints);
        inputPanel.add(feeTF);
    }

    //描述区域的位置设置
    private void setDescribePanel() {
        describeLabel.setText("这是类型管理Dialog");
        GridBagLayout layout =new GridBagLayout();
        buttonPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        layout.setConstraints(describeLabel,constraints);
        describePanel.add(describeLabel);
    }


    //TypeDialog的私有构造函数
    private TypeDialog (ApplicationContext context,Long[] selectId) {
        this.context = context;
        this.selectId = selectId;
        init();
    }

    private TypeDialog(ApplicationContext context) {
        this.context = context;
        init();
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
        typeDialog = new TypeDialog(context,selectId);
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


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == addName) {
            addType();
        } else if (command == updateName) {
            updateType();
        } else if (command == deleteName) {
            deleteType();
        } else if (command == cancelName) {
            closeDialog();
        } else {
            System.out.println("关闭窗口");
        }
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
