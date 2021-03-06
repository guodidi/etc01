package com.guo.etc.kernel.app.client.type;

import com.guo.etc.kernel.app.client.base.ReloadPanel;
import com.guo.etc.kernel.app.client.base.data.DataTable;
import com.guo.etc.kernel.app.client.record.RecordDialog;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/2.
 */
public class TypeMiddlePanel extends JPanel {
    private JPanel contentPanel;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private DataTable dataTable = null;
    private ArrayList<Object> selectValues = new ArrayList<>();
    private Long[] selectIds = null;

    public TypeMiddlePanel(ApplicationContext context) {
        this.add(contentPanel);

        addButton.addActionListener(e -> {
            initActionListener();
            TypeDialog typeDialog = TypeDialog.getAddInstance(context);
            typeDialog.setVisible(true);
            ReloadPanel.reloadPanel(TypePanel.getInstance(context));
        });

        updateButton.addActionListener(e -> {
            initActionListener();
            if (selectIds == null){
                JOptionPane.showMessageDialog(null, "请选择一行进行修改！", "提示",
                        JOptionPane.YES_OPTION);
                return;
            } else {
                TypeDialog typeDialog = TypeDialog.getUpdateInstance(context, selectIds, selectValues);
                typeDialog.setVisible(true);
                ReloadPanel.reloadPanel(TypePanel.getInstance(context));
            }
        });

        deleteButton.addActionListener(e -> {
            initActionListener();
            if (selectIds == null){
                JOptionPane.showMessageDialog(null, "请选择一行进行删除！", "提示",
                        JOptionPane.YES_OPTION);
                return;
            } else {
                TypeDialog typeDialog = TypeDialog.getDeleteInstance(context, selectIds, selectValues);
                typeDialog.setVisible(true);
                ReloadPanel.reloadPanel(TypePanel.getInstance(context));
            }
        });
    }

    private void initActionListener(){
        dataTable = DataTable.getInstance();
        if (dataTable == null) {
            return;
        }
        selectValues = dataTable.getSelectValues();
        selectIds = dataTable.getSelectIds();
    }

}
