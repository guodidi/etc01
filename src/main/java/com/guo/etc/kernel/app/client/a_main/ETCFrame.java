package com.guo.etc.kernel.app.client.a_main;

import com.guo.etc.kernel.app.client.base.FirstPage;
import com.guo.etc.kernel.app.client.base.TopButtonPanel;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 */
public class ETCFrame extends JFrame {

    //这个contentPanel用来承载所有的内容组件，包括JScrollPanel,Buttons
    public static JPanel contentPanel = new FirstPage();

    private ApplicationContext context = null;
    private static ETCFrame etcFrame;

    public static ETCFrame getInstance(ApplicationContext context) {
        if (etcFrame == null) {
            etcFrame = new ETCFrame(context);
        }
        return etcFrame;
    }

    private ETCFrame(ApplicationContext context) {
        //setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Desktop\\blue16_006.gif"));
        setIconImage(Toolkit.getDefaultToolkit().getImage(ETCFrame.class.getResource("/image/First/etcIcon.png")));
        this.context = context;
        this.setVisible(true);
        this.setTitle("自由流电子收费软件设计");
        initWindows();
        initContainer();
        //更新显示面板
        //image/First/2.png
       SwingUtilities.updateComponentTreeUI(contentPanel);
    }


    /*
    * 初始化这个窗体布局
    * */
    private void initWindows() {
        this.setMinimumSize(new Dimension(1100, 650));
        //设置窗口居中
        this.setLocationRelativeTo(null);
    }

    /*
    * 初始化整个容器布局
    * */
    private void initContainer() {
        final Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(TopButtonPanel.getInstance(context),BorderLayout.NORTH);
        container.add(contentPanel,BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

/*
* Error集合：
* 1  NoClassDefFoundError: org/aopalliance/intercept/MethodInterceptor  在Spring中缺少了AspectJ的Jar包
* 2  NoClassDefFoundError: org/springframework/aop/TargetSource  在Spring中缺少了spring-aop的Jar包
* 3  NoClassDefFoundError: org/apache/commons/logging/LogFactory 在pom.xml中缺少了commons-logging的Jar包
* */

/*    public static void a_main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {}
        new ETCFrame(context);
        System.out.println("hello world");

    }*/

}

