package com.guo.etc.kernel.app.client.simulate;

import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/30.
 */
public class SimulatePanel extends JPanel{
    private static SimulatePanel siMuPanel = null;

    public static SimulatePanel getInstance(ApplicationContext context) {
        if (siMuPanel == null) {
            siMuPanel = new SimulatePanel(context);
        }
        return siMuPanel;
    }

    private SimulatePanel(ApplicationContext context){
        this.setVisible(true);
        this.setLayout(new GridLayout(1,2));
        this.add(SimulateServerPanel.getInstance());
        this.add(SimulateClientPanel.getInstance(context));
    }

}
