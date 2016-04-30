package com.guo.etc.kernel.app.client.simulate;

import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/30.
 */
public class SiPanel extends JPanel{
    private static SiPanel siMuPanel = null;

    public static SiPanel getInstance(ApplicationContext context) {
        if (siMuPanel == null) {
            siMuPanel = new SiPanel(context);
        }
        return siMuPanel;
    }

    private SiPanel(ApplicationContext context){
        this.setVisible(true);
        this.setLayout(new GridLayout(1,2));
        this.add(SimulateServerPanel.getInstance());
        this.add(SimulateClientPanel.getInstance(context));
        //this.add(SimulatePanel.getInstance(context));
        //this.add(ServerGui.getInstance());

    }

}
