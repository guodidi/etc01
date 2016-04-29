package com.guo.etc.kernel.app.client.base.data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/8.
 */
public class DataTable extends JTable {

    private Long[] selectIds;
    private int[] selectRows;
    private ArrayList<Object> selectValues = new ArrayList<Object>();

    private static DataTable dataTable = null;

    private DataTable() {
        //设置行高
        this.setRowHeight(27);
        //设置表格颜色
        this.setGridColor(new Color(20,20,20));
        //设置表头
        JTableHeader header = this.getTableHeader();
        header.setPreferredSize(new Dimension(800,25));

        this.selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //选中表格的行时，保存当前行和行的id值
                selectRows = DataTable.this.getSelectedRows();
                selectIds =  DataTable.rowToids(selectRows, dataModel);
                System.out.println("选中的行数是：  "+selectRows[0]);
               System.out.println("选中的行数所对应的数据库中的ID是："+selectIds[0]);
                int columns = getColumnCount();
                if(selectValues != null) {
                    selectValues.clear();
                }
                for (int i = 0; i < columns; i++) {
                    selectValues.add(dataModel.getValueAt(selectRows[0],i));
                }
            }
        });
    }

    public static DataTable getNewInstance() {
        dataTable = new DataTable();
        return dataTable;
    }

    public static DataTable getInstance() {
        return  dataTable;
    }

    public ArrayList<Object> getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(ArrayList<Object> selectValues) {
        this.selectValues = selectValues;
    }

    public Long[] getSelectIds() {
        return selectIds;
    }

    public void setSelectIds(Long[] selectIds) {
        this.selectIds = selectIds;
    }

    public int[] getSelectRows() {
        return selectRows;
    }

    public void setSelectRows(int[] selectRows) {
        this.selectRows = selectRows;
    }

    /*
        * 根据在表格中选择的索引，获取该索引的id值
        * @Param selectRows
        * @param model
        * */
    public static Long[] rowToids(int[] selectRows, TableModel model) {
        Long[] selectRowIsds = new Long[selectRows.length];
        for (int i = 0; i < selectRows.length; i++) {
            selectRowIsds[i] = new Long(model.getValueAt(selectRows[i], 0)
                    .toString());
        }
        return selectRowIsds;
    }
}
