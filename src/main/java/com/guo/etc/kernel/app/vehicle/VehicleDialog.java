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

    /*
    * 这个Dialog要完成的功能----增加，修改，删除
    * 界面要求：做好JLabel，JTextField，JButton等控件
    * 增加：
    * 删除：
    * 修改：
    * */

    private static final String addName = "确认";
    private static final String deleteName = "删除";
    private static final String updateName = "更新";
    private static final String cancelName = "取消";

    private JLabel nameLabel = new JLabel();
    private JTextField nameTF = new JTextField(32);
    private JLabel passwordLabel = new JLabel();
    private JTextField passwordTF = new JTextField(32);
    private JLabel ageLabel = new JLabel();
    private JTextField ageTF = new JTextField(32);
    private JLabel typeLabel = new JLabel();
    private JComboBox typeBox = new JComboBox(new Object[]{"郭","垚","辉","四","月","了"
    });

    private JButton confirmButton = new JButton();
    private JButton cancelButton = new JButton();

    //由于增删改采用的是相同的一套机制，所以传入buttonFlag来判断要怎么进行初始化
    private ApplicationContext context = null;
    private Long[] selectId ;

    private static VehicleDialog vehicleDialog = null;

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
        vehicleDialog.nameTF.setText("guodaye");
        vehicleDialog.passwordTF.setText("123456");
        vehicleDialog.ageTF.setText("12");
        vehicleDialog.confirmButton.setText("添加");
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
        vehicleDialog.nameTF.setText(String.valueOf(selectValues.get(1)));
        vehicleDialog.passwordTF.setText(String.valueOf(selectValues.get(2)));
        vehicleDialog.ageTF.setText(String.valueOf(selectValues.get(3)));
        vehicleDialog.confirmButton.setText("修改");
        return vehicleDialog;
    }

    public static VehicleDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (vehicleDialog != null) {
            vehicleDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在删除中");
        } else {
            vehicleDialog = new VehicleDialog(context,selectId);
            vehicleDialog.nameTF.setText(String.valueOf(selectValues.get(1)));
            vehicleDialog.passwordTF.setText(String.valueOf(selectValues.get(2)));
            vehicleDialog.ageTF.setText(String.valueOf(selectValues.get(3)));
            vehicleDialog.nameTF.setEnabled(false);
            vehicleDialog.ageTF.setEnabled(false);
            vehicleDialog.passwordTF.setEnabled(false);
            vehicleDialog.confirmButton.setText("删除");
        }

        return vehicleDialog;

    }



    /*
    * Dialog的界面设计
    * */
    private void init() {
        super.dialogInit();
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        //姓名
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        nameLabel.setText("姓名 : ");
        namePanel.add(nameLabel);
        namePanel.add(nameTF);
        contentPanel.add(namePanel,BorderLayout.NORTH);

        //密码
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout());
        passwordLabel.setText("密码 : ");
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTF);
        contentPanel.add(passwordPanel,BorderLayout.CENTER);

        //年龄
        JPanel agePanel = new JPanel();
        agePanel.setLayout(new FlowLayout());
        ageLabel.setText("年龄 : ");
        agePanel.add(ageLabel);
        agePanel.add(ageTF);
        contentPanel.add(agePanel, BorderLayout.SOUTH);

        JPanel typePanel = new JPanel();
        typePanel.setLayout(new FlowLayout());
        typeLabel.setText("类型");
        typePanel.add(typeLabel);
        typePanel.add(typeBox);
        contentPanel.add(typePanel,BorderLayout.EAST);


        //按钮
        JPanel buttonPanel = new JPanel(new BorderLayout());
        confirmButton.setText("确定");
        cancelButton.setText("取消");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        buttonPanel.add(confirmButton,BorderLayout.WEST);
        buttonPanel.add(cancelButton,BorderLayout.EAST);

        this.add(contentPanel,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);


        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600,400));
        setLocation(600 / 2 - getWidth() / 2, 400 / 2 - getHeight() / 2);
    }

    private void addUser() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        Vehicle vehicle = new Vehicle();

        vehicleService.addVehicle(vehicle);
    }

    private void updateVehicle() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        Vehicle vehicle = vehicleService.findVehicleById(selectId[0]);
        vehicle.setId();
        vehicleService.updateVehicle()
        User user = vehicleService.findById(selectId[0]);
        user.setUserName(nameTF.getText());
        user.setUserPassword(passwordTF.getText());
        user.setUserAge(Integer.valueOf(ageTF.getText()));
        vehicleService.mergeUser(user,selectId[0]);
    }

    private void delete() {
        VehicleService vehicleService = (VehicleService)context.getBean(VehicleService.class);
        vehicleService.deleteUser(selectId[0]);
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
}
