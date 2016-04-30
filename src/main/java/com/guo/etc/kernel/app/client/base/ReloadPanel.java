package com.guo.etc.kernel.app.client.base;

import com.guo.etc.kernel.a_launch.Start;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 * 重载组件，每次进行操作后都更新一下全部的组件
 */
public class ReloadPanel {

    public static void reloadPanel(BasePanel basePanel) {
        basePanel.viewJPanel();
        Start.contentPanel.setLayout(new BorderLayout());
        Start.contentPanel.removeAll();
        Start.contentPanel.add(basePanel);
        SwingUtilities.updateComponentTreeUI(Start.contentPanel);
    }

    public static void reloadSimulatePanel(JPanel panel) {
        Start.contentPanel.setLayout(new BorderLayout());
        Start.contentPanel.removeAll();
        Start.contentPanel.add(panel);
        SwingUtilities.updateComponentTreeUI(Start.contentPanel);
    }
}
