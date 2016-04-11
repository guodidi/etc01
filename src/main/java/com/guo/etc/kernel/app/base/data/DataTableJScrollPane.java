package com.guo.etc.kernel.app.base.data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by Administrator on 2016/4/8.
 */
public class DataTableJScrollPane extends JScrollPane {

    /*
    * 设置表格的数据和表头
    *
    * @param data
    * @param column
    * */

    public DataTableJScrollPane(final String[][] data ,final String[] column) {
        DataTable dataTable = DataTable.getNewInstance();
        System.out.println("Table"+data);
        dataTable.setModel(new DefaultTableModel(data, column));
        this.setViewportView(dataTable);
    }

}
