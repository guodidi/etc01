package com.guo.etc.kernel.app.client.base;

import com.guo.etc.kernel.app.client.base.data.DataTableJScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/5/2.
 */
public abstract class BasePanel extends JPanel {
    private JPanel contentPanel;

    public abstract String[] tableHeader();

    public abstract String[][] tableData();

    public abstract JPanel setButtonJPanel();


    /*
* 显示面板
* 显示带有表格的Janel
* */
    public void viewJPanel() {
        this.setLayout(new BorderLayout());
        DataTableJScrollPane dataTableJScrollPane = new DataTableJScrollPane(tableData(),tableHeader());
        this.add(dataTableJScrollPane,BorderLayout.CENTER);
        this.add(setButtonJPanel(), BorderLayout.SOUTH);
    }
    /*
    * 显示不带有表格的JPanel
    * */
    public void viewSimulatePanel() {
        this.setLayout(new BorderLayout());
    }

    public BasePanel() {
        this.add(contentPanel);
    }
}
