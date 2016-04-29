package com.guo.etc.kernel.app.client.base;

import com.guo.etc.kernel.a_launch.StartClient;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 * 重载组件，每次进行操作后都更新一下全部的组件
 */
public class ReloadPanel {

    public static void reloadPanel(BasePanel basePanel) {
        basePanel.viewJPanel();
        StartClient.contentPanel.setLayout(new BorderLayout());
        StartClient.contentPanel.removeAll();
        StartClient.contentPanel.add(basePanel);
        SwingUtilities.updateComponentTreeUI(StartClient.contentPanel);
    }

    public static void reloadSimulatePanel(JPanel panel) {
        StartClient.contentPanel.setLayout(new BorderLayout());
        StartClient.contentPanel.removeAll();
        StartClient.contentPanel.add(panel);
        SwingUtilities.updateComponentTreeUI(StartClient.contentPanel);
    }
}
