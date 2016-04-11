package com.guo.etc.kernel.app.base.button;

import com.guo.etc.kernel.app.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/4/8.
 */
public abstract class MiddleButtonPanel extends JPanel implements ActionListener {

    private static int middleButtonTopLine = 0;
    private static int middleButtonHeight = 22;
    private static int middleButtonWidth = 60;

    public MiddleButtonPanel() {

    }

    /*    public MiddleButtonPanel(String panelName) {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBackground(new Color(232, 232, 232));
        setMiddleButton(panelName);
    }*/

    /*
    * 设置中间按钮
    * */
/*    private void setMiddleButton(String panelName) {
        switch (panelName) {
            case (BasePanel.vehiclePanel):
                createVehicleButtonAdd();
                createVehicleButtonDelete();
                createVehicleButtonUpdate();
                System.out.println();
                break;
            case(BasePanel.typePanel):
                createTypeButtonAdd();
                createTypeButtonDelete();
                createTypeButtonUpdate();
                break;
            case(BasePanel.feePanel):
                break;
            case(BasePanel.roadPanel):
                createRoadButtonAdd();
                createRoadButtonDelete();
                createRoadButtonUpdate();
                break;
            case(BasePanel.simulatePanel):
                break;
            default:
                System.out.println("设置中间按钮这儿出问题了");
        }
    }*/

    /*
    * 道路管理
    * */
/*    private void createRoadButtonUpdate() {

    }

    private void createRoadButtonDelete() {

    }

    private void createRoadButtonAdd() {

    }*/

    /*
    * 类型管理
    * */
/*    private void createTypeButtonUpdate() {

    }

    private void createTypeButtonDelete() {


    }

    private void createTypeButtonAdd() {


    }*/


    /*
    * 车辆管理
    * */
/*    private void createVehicleButtonUpdate() {
        final JButton button_update = new JButton(updateVehicle);
        button_update.setBounds(115,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        button_update.addActionListener(this);
        this.add(button_update);
    }

    private void createVehicleButtonDelete() {
        final JButton button_delete = new JButton(deleteVehicle);
        button_delete.setBounds(135,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        button_delete.addActionListener(this);
        this.add(button_delete);
    }

    private void createVehicleButtonAdd() {
        final JButton button_add = new JButton(addVehicle);
        button_add.setBounds(155,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        button_add.addActionListener(this);
        this.add(button_add);
    }*/


}
