package com.guo.etc.kernel.app.rsu;

import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/4/12.
 */
public class RsuMiddlePanel extends JPanel implements ActionListener {

    private ApplicationContext context = null;

    private static int middleButtonTopLine = 0;
    private static int middleButtonHeight = 22;
    private static int middleButtonWidth = 60;

    private static String addButtonName = "增加RSU";
    private static String deleteButtonName = "删除RSU";
    private static String updateButtonName = "修改RSU";

    public RsuMiddlePanel(ApplicationContext context) {
        this.context = context;
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBackground(new Color(232, 232, 232));
        this.setButton();
    }

    public void setButton() {
        //增加addButton
        addButton();
        //增加deleteButton
        addDeleteButton();
        //增加updateButton
        addUpdateButton();
    }

    private void addUpdateButton() {
        final JButton update_button = new JButton(updateButtonName);
        update_button.setBounds(135,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        update_button.addActionListener(this);
        this.add(update_button);
    }

    private void addDeleteButton() {
        final JButton delete_button = new JButton(deleteButtonName);
        delete_button.setBounds(155,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        delete_button.addActionListener(this);
        this.add(delete_button);
    }

    private void addButton() {
        final JButton add_button = new JButton(addButtonName);
        add_button.setBounds(115,middleButtonTopLine,middleButtonWidth,middleButtonHeight);
        add_button.addActionListener(this);
        this.add(add_button);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
