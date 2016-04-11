package com.guo.etc.kernel.app.base;

import com.guo.etc.kernel.app.StartFrame;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 * 重载组件，每次进行操作后都更新一下全部的组件
 */
public class ReloadPanel {

    public static void reloadPanel(BasePanel basePanel) {
        basePanel.viewJPanel();
        StartFrame.contentPanel.setLayout(new BorderLayout());
        StartFrame.contentPanel.removeAll();
        StartFrame.contentPanel.add(basePanel);
        SwingUtilities.updateComponentTreeUI(StartFrame.contentPanel);
    }
}
