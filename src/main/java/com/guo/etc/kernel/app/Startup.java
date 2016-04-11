/*
package com.guo.etc.kernel.app;

import com.guo.etc.kernel.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

*/
/**
 * Created by Administrator on 2016/4/5.
 *//*

public class Startup {



    public Startup(UserService userService) {

        //新建一个主窗口
        JFrame mainFrame = new JFrame("自由流电子收费软件");
        //新建一个JTabbedPane进行操作选择
        JTabbedPane mainTab = new JTabbedPane();
        //指定JTabbedPanel的大小
        mainTab.setPreferredSize(new Dimension(500,300));

        */
/**
         * 车辆信息管理的功能有2：
         * 1：可以显示当前所有车辆的信息(JTable形式实现)
         * 2：点击当前行可以修改该车辆的一切的信息（JTableModel形式实现）
         * 3：统计所有车辆的类型的总数
         *//*

        mainTab.add("车辆信息",new JPanel());

        */
/**
         * 车道信息管理的功能有2：
         * 1：显示当前所有车道的信息
         * 2：点击当前行可以修改该车道的信息
         * *//*

        JPanel laneMangerPanel = new JPanel();

        mainTab.add("车道信息",laneMangerPanel);

        */
/**
         * 统计信息有2：
         * 1：根据车辆类型来统计当月的收费总费用
         * 2：根据时间来统计当月的各经过类型的车辆
         * *//*

        JPanel statisticsPanel = new JPanel();
        mainTab.add("统计信息", statisticsPanel);


        mainTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane temp = (JTabbedPane)e.getSource();
                int selectIndex = temp.getSelectedIndex();
                switch (selectIndex) {
                    case 0:
                        System.out.println("case 0");
                        break;
                    case 1:
                        System.out.println("case 1");
                        break;
                    case 2:
                        System.out.println("case 2");
                        break;
                }
            }
        });

        mainFrame.getContentPane().add(mainTab, BorderLayout.CENTER);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setBackground(Color.getColor("#77B33F"));
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

 */
/*   public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        UserService userService = (UserService)context.getBean(UserService.class);
        new Startup(userService);

    }*//*


}
*/
