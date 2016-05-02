package com.guo.etc.kernel.app.client.base;

import com.guo.etc.kernel.app.client.record.RecordPanel;
import com.guo.etc.kernel.app.client.rsu.RsuPanel;
import com.guo.etc.kernel.app.client.simulate.SimulatePanel;
import com.guo.etc.kernel.app.client.type.TypePanel;
import com.guo.etc.kernel.app.client.vehicle.VehiclePanel;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

/**
 * Created by Administrator on 2016/5/2.
 */
public class TopButtonPanel extends JPanel {
    private JPanel contentPanel;
    private JButton vehicleButton;
    private JButton typeButton;
    private JButton roadButton;
    private JButton feeButton;
    private JButton simulateButton;

    private static TopButtonPanel buttonPanel = null;

    public static TopButtonPanel getInstance(ApplicationContext context){
        if (buttonPanel == null) {
            buttonPanel = new TopButtonPanel(context);
        }
        return buttonPanel;
    }

    private TopButtonPanel(ApplicationContext context) {
        this.add(contentPanel);
        vehicleButton.addActionListener(e -> {ReloadPanel.reloadPanel(VehiclePanel.getInstance(context));});
        typeButton.addActionListener(e -> {ReloadPanel.reloadPanel(TypePanel.getInstance(context));});
        roadButton.addActionListener(e -> {ReloadPanel.reloadPanel(RsuPanel.getInstance(context));});
        feeButton.addActionListener(e -> {ReloadPanel.reloadPanel(RecordPanel.getInstance(context));});
        simulateButton.addActionListener(e -> {ReloadPanel.reloadSimulatePanel(SimulatePanel.getInstance(context));});
    }


}
