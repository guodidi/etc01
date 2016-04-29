package com.guo.etc.kernel.a_launch;

import com.guo.etc.kernel.app.client.base.TopButtonPanel;
import com.guo.etc.kernel.util.Constant;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/8.
 */
public class StartClient extends JFrame {

    //这个contentPanel用来承载所有的内容组件，包括JScrollPanel,Buttons
    public static JPanel contentPanel = new JPanel();

    private ApplicationContext context = null;

    public StartClient(ApplicationContext context) {
        this.context = context;
        this.setVisible(true);
        initWindows();
        initContainer();
        //更新显示面板
       contentPanel.setLayout(new BorderLayout());
       SwingUtilities.updateComponentTreeUI(contentPanel);
    }

    /*
    * 初始化这个窗体布局
    * */
    private void initWindows() {
        this.setMinimumSize(new Dimension(Constant.WindowSize.width, Constant.WindowSize.height));
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        //设置窗口居中
        this.setLocation(((int) dimension.getWidth()- Constant.WindowSize.width)/2,((int) dimension.getHeight()- Constant.WindowSize.height)/2);
    }

    /*
    * 初始化整个容器布局
    * */
    private void initContainer() {
        final Container container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(new TopButtonPanel(context),BorderLayout.NORTH);
        container.add(contentPanel,BorderLayout.CENTER);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
            new StartClient(context);
    }

}

