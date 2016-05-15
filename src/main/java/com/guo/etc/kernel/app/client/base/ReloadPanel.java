package com.guo.etc.kernel.app.client.base;

import com.guo.etc.kernel.app.client.a_main.ETCFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 * 重载组件，每次进行操作后都更新一下全部的组件
 */
public class ReloadPanel {

    public static void reloadPanel(BasePanel basePanel) {
        basePanel.viewJPanel();
        ETCFrame.contentPanel.setLayout(new BorderLayout());
        ETCFrame.contentPanel.removeAll();
        ETCFrame.contentPanel.add(basePanel);
        SwingUtilities.updateComponentTreeUI(ETCFrame.contentPanel);
    }

    public static void reloadSimulatePanel(JPanel panel) {
        ETCFrame.contentPanel.setLayout(new BorderLayout());
        ETCFrame.contentPanel.removeAll();
        ETCFrame.contentPanel.add(panel);
        SwingUtilities.updateComponentTreeUI(ETCFrame.contentPanel);
    }
}
