package com.guo.etc.kernel.app.vehicle;

import com.guo.etc.kernel.app.base.data.DataTable;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/9.
 *
 * 这一行的Button是中间的那一行的Button的，包括的是增删改查
 *
 */
public class VehicleMiddlePanel extends JPanel implements ActionListener {

    private ApplicationContext context = null;

    private static int middleButtonTopLine = 0;
    private static int middleButtonHeight = 22;
    private static int middleButtonWidth = 60;

    private static String addButtonName = "增加车辆";
    private static String deleteButtonName = "删除车辆";
    private static String updateButtonName = "修改车辆";

    public VehicleMiddlePanel( ApplicationContext context) {
        this.context = context;
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBackground(new Color(232, 232, 232));
        this.setButton();
    }

    public void setButton() {
        //增加addButton
        addButton();
        //增加deleteButton
        addDeleteButton();
        //增加updateButton
        addUpdateButton();
    }

    private void addUpdateButton() {
        final JButton update_button = new JButton(updateButtonName);
        update_button.setBounds(135,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        update_button.addActionListener(this);
        this.add(update_button);
    }

    private void addDeleteButton() {
        final JButton delete_button = new JButton(deleteButtonName);
        delete_button.setBounds(155,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        delete_button.addActionListener(this);
        this.add(delete_button);
    }

    private void addButton() {
        final JButton add_button = new JButton(addButtonName);
        add_button.setBounds(115,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        add_button.addActionListener(this);
        this.add(add_button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
        * 当触动一个按钮时，有以下操作步骤
        * 首先要获取当前表格的实例，并且得到此时点击的行的id值
        * 其次，根据我们的点击命令，得到要进行的操作是什么
        * 最后，创建一个Dialog来进行下一步的操作
        * */
        //获取点击的命令是什么
        String command = e.getActionCommand();
        //获得JTable中选中的行数
        DataTable dataTable = DataTable.getInstance();
        if(dataTable == null) {
            return;
        }
        ArrayList<Object> selectValues = dataTable.getSelectValues();
        Long[] selectIds = dataTable.getSelectIds();
        //System.out.println("现在选中的行数是第"+selectIds[0]+"行");


        if (command.equals(addButtonName)) {
            //增加信息弹出窗口
            VehicleDialog vehicleDialog = VehicleDialog.getAddInstance(context);
            vehicleDialog.setVisible(true);
        }else if(command.equals(deleteButtonName)) {
            //删除信息弹出窗口
            VehicleDialog vehicleDialog = VehicleDialog.getDeleteInstance(context,selectIds,selectValues);
            vehicleDialog.setVisible(true);
            System.out.println("1"+deleteButtonName);
        }else if (command.equals(updateButtonName)) {
            //修改信息弹出窗口
            VehicleDialog vehicleDialog = VehicleDialog.getUpdateInstance(context,selectIds,selectValues);
            vehicleDialog.setVisible(true);
            System.out.println("2"+updateButtonName);
        } else {
            System.out.println("VehicleMiddlePanel Error now!!!");
        }

    }
}

/*
class V {
    public void deptAction(String command) {
        DataJTable dataJTable = DataJTable.getInstance();
        if (dataJTable == null) {
            return;
        }
        Long[] selectIds = dataJTable.getSelectIds();
        if (command.equals(ButtoName.addDept)) {
            DeptDialog deptDialog = DeptDialog.getInstance();
            deptDialog.getButtonAdd().setName(ButtonName.addDept);
            deptDialog.getButtonAdd().setText("添加");
            deptDialog.setTitle(ButtonName.addDept);
            deptDialog.setDept(new TbDept());
            deptDialog.tbDeptToDialog();
            deptDialog.setVisible(true);
        } else if (command.equals(ButtonName.updateDept)) {
            // 获取选中id
            if (selectIds == null) {
                JOptionPane.showMessageDialog(null, "请选择要操作的一行?", "提示",
                        JOptionPane.YES_OPTION);
                return;
            }
            TbDeptManager tbDeptManager = ManagerFactoryUtil.getTbDeptManager();
            Long id = selectIds[0];
            // 把选中的Appliances id从数据库中取出来，显示到dialog中
            TbDept dept = tbDeptManager.getById(TbDept.class, id);
            DeptDialog deptDialog = DeptDialog.getInstance();
            deptDialog.getButtonAdd().setName(ButtonCommonName.updateDept);
            deptDialog.getButtonAdd().setText("修改");
            deptDialog.setTitle(ButtonCommonName.updateDept);
            deptDialog.setDept(dept);
            deptDialog.tbDeptToDialog();
            deptDialog.setVisible(true);
        } else if (command.equals(ButtonCommonName.deleteDept)) {
            // 获取选中id
            if (selectIds == null) {
                JOptionPane.showMessageDialog(null, "请选择要操作的一行?", "提示",
                        JOptionPane.YES_OPTION);
                return;
            }
            // 获得TbDeptManager实例，该实例是通过配置文件appcontext.xml获得的
            TbDeptManager tbDeptManager = ManagerFactoryUtil.getTbDeptManager();
            tbDeptManager.deleteList(TbDept.class, selectIds);// 删除选择的部门信息
            // 删除数据库中数据以后删除表格中的数据
            dataJTable.removeSelectRow();
        }
    }

}*/
