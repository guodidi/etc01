package com.guo.etc.kernel.app.rsu;

import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/4/13.
 */
public class RsuDialog extends JDialog {

    private ApplicationContext context = null;
    private Long[] selectId = null;
    private static RsuDialog rsuDialog = null;

    private static final String addName = "确认";
    private static final String deleteName = "删除";
    private static final String updateName = "更新";
    private static final String cancelName = "取消";

    private JButton confirmButton = new JButton();
    private JButton cancelButton = new JButton();
    JPanel describePanel = new JPanel();
    JPanel inputPanel = new JPanel();
    JPanel buttonPanel = new JPanel();





    private void init() {
        super.dialogInit();
        this.setLayout(new BorderLayout());
        //顶层介绍JPanel
        this.add(setDescribePanel(),BorderLayout.NORTH);
        //中间层输入JPanel
        this.add(setInputPanel(),BorderLayout.CENTER);
        //底层按钮JPanel
        this.add(setButtonPanel(),BorderLayout.SOUTH);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(600,400));
        setLocation(600 / 2 - getWidth() / 2, 400 / 2 - getHeight() / 2);
    }

    private JPanel setButtonPanel() {
        return null;
    }

    private JPanel setInputPanel() {
        return null;
    }

    private JPanel setDescribePanel() {
        return null;
    }

    private RsuDialog (ApplicationContext context,Long[] selectId) {
        this.context = context;
        this.selectId = selectId;
        init();
    }

    private RsuDialog(ApplicationContext context) {
        this.context = context;
        init();
    }

    public static RsuDialog getAddInstance(ApplicationContext context) {
        if (rsuDialog != null) {
            rsuDialog.dispose();
        }
        rsuDialog = new RsuDialog(context);

        return rsuDialog;
    }

    public static RsuDialog getUpdateInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues) {
        if (rsuDialog != null) {
            rsuDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在刷新中");
            return null;
        }
        rsuDialog = new RsuDialog(context,selectId);

        return rsuDialog;
    }

    public static RsuDialog getDeleteInstance(ApplicationContext context,Long selectId[], ArrayList<Object> selectValues){
        if (rsuDialog != null) {
            rsuDialog.dispose();
        }
        if(selectId == null){
            System.out.println("你大爷，这个什么都没有，我在删除中");
        } else {

        }

        return rsuDialog;

    }



}
