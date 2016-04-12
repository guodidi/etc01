package com.guo.etc.kernel.app.simulate;

import com.guo.etc.kernel.app.base.BasePanel;

import javax.swing.*;

/**
 * Created by Administrator on 2016/4/8.
 */
public class SimulatePanel extends BasePanel {
    @Override
    public String[] tableHeader() {
        return new String[0];
    }

    @Override
    public String[][] tableData() {
        return new String[0][];
    }

    @Override
    public JPanel setButtonJPanel() {
        return null;
    }
}
