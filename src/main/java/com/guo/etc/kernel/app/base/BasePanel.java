package com.guo.etc.kernel.app.base;

import com.guo.etc.kernel.app.base.data.DataTableJScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 *
 * 描述：这个是所有带表格的面板组件的基类
 *
 */
public abstract class BasePanel extends JPanel {

    //车辆管理面板
    public final static String vehiclePanel = "vehiclePanelName";
    //类型管理面板
    public final static String typePanel = "typePanelName";
    //道路管理面板
    public final static String rsuPanel = "rsuPanelName";
    //总的收费管理面板
    public final static String feePanel = "feePanelName";
    //模拟收费面板
    public final static String simulatePanel = "simulatePanelName";

    public abstract String[] tableHeader();

    public abstract String[][] tableData();

    public abstract JPanel setButtonJPanel();

    /*
    * 显示面板
    * */
    public void viewJPanel() {
        this.setLayout(new BorderLayout());
        DataTableJScrollPane dataTableJScrollPane = new DataTableJScrollPane(tableData(),tableHeader());
        this.add(dataTableJScrollPane,BorderLayout.CENTER);
        this.add(setButtonJPanel(), BorderLayout.NORTH);
    }

    public BasePanel(){
        super();
    }

}
