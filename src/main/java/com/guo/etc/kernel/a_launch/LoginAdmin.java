package com.guo.etc.kernel.a_launch;

import com.guo.etc.kernel.app.client.a_main.ETCFrame;
import com.guo.etc.kernel.service.AdminService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/5/16.
 * 这个类同样作为整个程序的入口。
 * 启动这个程序，首先弹出来的是：一个登陆界面
 * 如果拥有正确的密码，那么就可以顺利的登录进入ETCFrame
 */
public class LoginAdmin extends JFrame {
    private JPanel contentPanel;
    private JPanel desPanel;
    private JPanel inputPanel;
    private JPanel statusPanel;
    private JLabel desLabel;
    private JPanel loginInfoPanel;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private JTextField nameTF;
    private JTextField passwordTF;
    private JLabel nameLabel;
    private JLabel statusLabel;
    private ApplicationContext context;
    private static LoginAdmin loginAdmin;
    private int count = 0;

    public static LoginAdmin getInstance(ApplicationContext context) {
        if (null == loginAdmin) {
            loginAdmin = new LoginAdmin(context);
        }
        return loginAdmin;
    }
    private LoginAdmin(ApplicationContext context)  {
        this.context = context;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginAdmin.class.getResource("/login/adminLogin.png")));
        this.add(contentPanel);
        this.pack();
        this.setTitle("管理员登录");
        this.initListener();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setAllTF();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void initListener() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTF();
                statusLabel.setText("登录状态： 请重新输入账号和密码进行登录 . . . ");
            }
        });
        //如果登录框中的名字和密码是正确的，则点击确认会进入ETCFrame，否则就会让用户重新输入。
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ++count;
                if (count <= 3) {
                    if (LoginAdmin.this.adjustAdmin()) {
                        SwingUtilities.invokeLater(()->{
                            ETCFrame.getInstance(context);
                        });
                        LoginAdmin.this.dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "这是第"+ count+"次密码输入错误，请重新输入！", "提示",
                                JOptionPane.YES_OPTION);
                        clearTF();
                        statusLabel.setText("登录状态： 请输入正确的账号和密码重新登录 . . . ");
                        return;
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "密码输入错误超过"+ (--count) +"次，程序即将关闭！", "提示",
                            JOptionPane.YES_OPTION);
                    System.exit(0);
                }
            }
        });
    }


    //判断登录的用户是否在数据库中
    private boolean adjustAdmin(){
        AdminService adminService = (AdminService)context.getBean(AdminService.class);
        String[] names = new String[]{nameTF.getText(),passwordTF.getText()};
        return adminService.adjustAdmin(names);
    }

    //清除登录框，让用户重新输入
    private void clearTF(){
        nameTF.setText("");
        passwordTF.setText("");
        statusLabel.setText("登录状态： 请登录. . . ");
    }

    //初始化登录框
    private void setAllTF() {
        nameTF.setText("郭垚辉");
        passwordTF.setText("123");
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {}
        SwingUtilities.invokeLater(()->{
           LoginAdmin.getInstance(context);
        });
    }

}

