package com.guo.etc.kernel.app.client.base;

import javax.swing.*;

/**
 * Created by Administrator on 2016/5/3.
 */
public class FirstPage extends JPanel {
    private JPanel contentPanel;
    private JLabel imageLabel;

    public FirstPage() {
        this.add(contentPanel);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("FirstPage");
        frame.setContentPane(new FirstPage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
