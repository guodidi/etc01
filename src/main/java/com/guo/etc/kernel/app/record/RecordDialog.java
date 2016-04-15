package com.guo.etc.kernel.app.record;

import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class RecordDialog extends JDialog implements ActionListener {

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static RecordDialog recordDialog = null;

    private static final String addName = "确认";
    private static final String deleteName = "删除";
    private static final String updateName = "更新";
    private static final String cancelName = "取消";

    private JButton confirmButton = new JButton("确定");
    private JButton cancelButton = new JButton("取消");
    private JLabel describeLabel = new JLabel();

    JPanel describePanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();





    private void init() {
        super.dialogInit();
        this.setLayout(new BorderLayout());
        this.add(describePanel,BorderLayout.NORTH);
        setDescribePanel();
        this.add(inputPanel,BorderLayout.CENTER);
        setInputPanel();
        this.add(buttonPanel,BorderLayout.SOUTH);
        setButtonPanel();
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600,400));
        setLocation(600 / 2 - getWidth() / 2, 400 / 2 - getHeight() / 2);
    }

    private void setDescribePanel() {
        describeLabel.setText("这是记录管理Dialog");
        GridBagLayout layout =new GridBagLayout();
        buttonPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        layout.setConstraints(describeLabel,constraints);
        describePanel.add(describeLabel);
    }

    private void setInputPanel() {

    }

    private void setButtonPanel() {
        GridBagLayout layout =new GridBagLayout();
        buttonPanel.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(20,20,20,20);
        confirmButton.addActionListener(this);
        layout.setConstraints(confirmButton,constraints);
        buttonPanel.add(confirmButton);
        constraints.anchor = GridBagConstraints.EAST;
        cancelButton.addActionListener(this);
        layout.setConstraints(cancelButton,constraints);
        buttonPanel.add(cancelButton);
    }
    private RecordDialog (ApplicationContext context,Long[] selectId) {
        this.context = context;
        this.selectId = selectId;
        init();
    }

    private RecordDialog(ApplicationContext context) {
        this.context = context;
        init();
    }

    public static RecordDialog getAddInstance(ApplicationContext context) {
        if (recordDialog != null) {
            recordDialog.dispose();
        }
        recordDialog = new RecordDialog(context);

        return recordDialog;
    }

    public static RecordDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (recordDialog != null) {
            recordDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在刷新中");
            return null;
        }
        recordDialog = new RecordDialog(context,selectId);

        return recordDialog;
    }

    public static RecordDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (recordDialog != null) {
            recordDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在删除中");
        } else {

        }

        return recordDialog;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
