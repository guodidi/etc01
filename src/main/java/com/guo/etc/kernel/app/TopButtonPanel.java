package com.guo.etc.kernel.app;

import com.guo.etc.kernel.app.base.ReloadPanel;
import com.guo.etc.kernel.app.record.RecordPanel;
import com.guo.etc.kernel.app.rsu.RsuPanel;
import com.guo.etc.kernel.app.type.TypePanel;
import com.guo.etc.kernel.app.vehicle.VehiclePanel;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/4/8.
 */
public class TopButtonPanel extends JPanel implements ActionListener  {

    private static int topButtonHeight = 35;
    private static int topButtonWidth = 120;
    private static int topButtonTopLine = 0;

    private static String vehicleButtonName = "车辆管理";
    private static String typeButtonName = "类型管理";
    private static String roadButtonName = "道路管理";
    private static String feeButtonName = "费用管理";
    private static String simulateFeeName = "模拟收费";

    private ApplicationContext context = null;


    public TopButtonPanel(ApplicationContext context) {
        this.context = context;
        this.setLayout(new FlowLayout());
        setPreferredSize(new Dimension(800,35));
        this.setTopCommon();
    }

    private void setTopCommon() {
        addVehicleButton(this);
        addTypeButton(this);
        addRoadButton(this);
        addFeeButton(this);
        addSimulataFeeButton(this);
    }

    private void addSimulataFeeButton(TopButtonPanel topButtonPanel) {
        final JButton simulateFeeButton = new JButton("模拟收费");
        simulateFeeButton.setBounds(115,topButtonTopLine,topButtonWidth,topButtonHeight);
        simulateFeeButton.addActionListener(this);
        topButtonPanel.add(simulateFeeButton);
    }

    private void addFeeButton(TopButtonPanel topButtonPanel) {
        final JButton feeButton = new JButton("费用管理");
        feeButton.setBounds(215,topButtonTopLine,topButtonWidth,topButtonHeight);
        feeButton.addActionListener(this);
        topButtonPanel.add(feeButton);
    }

    private void addRoadButton(TopButtonPanel topButtonPanel) {
        final JButton roadButton = new JButton("道路管理");
        roadButton.setBounds(315,topButtonTopLine,topButtonWidth,topButtonHeight);
        roadButton.addActionListener(this);
        topButtonPanel.add(roadButton);
    }

    private void addTypeButton(TopButtonPanel topButtonPanel) {
        final JButton typeButton = new JButton("类型管理");
        typeButton.setBounds(415,topButtonTopLine,topButtonWidth,topButtonHeight);
        typeButton.addActionListener(this);
        topButtonPanel.add(typeButton);
    }

    private void addVehicleButton(TopButtonPanel topButtonPanel) {
        final JButton vehicleButton = new JButton("车辆管理");
        vehicleButton.setBounds(515,topButtonTopLine,topButtonWidth,topButtonHeight);
        vehicleButton.addActionListener(this);
        topButtonPanel.add(vehicleButton);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals(vehicleButtonName)){
            System.out.println("车辆管理");
            ReloadPanel.reloadPanel(VehiclePanel.getInstance(context));
        }else if(command.equals(typeButtonName)) {
            System.out.println("类型管理");
            ReloadPanel.reloadPanel(TypePanel.getInstance(context));
        }else if (command.equals(feeButtonName)) {
            System.out.println("费用管理");
            ReloadPanel.reloadPanel(RecordPanel.getInstance(context));
        }else if (command.equals(roadButtonName)) {
            System.out.println("道路管理");
            ReloadPanel.reloadPanel(RsuPanel.getInstance(context));

        }else if (command.equals(simulateFeeName)) {
            //ReloadPanel.reloadPanel(SimulatePanel.getInstance(context));
            System.out.println("模拟收费");
        }else {
            System.out.println("啥JB玩意儿啊");
        }

    }
}
