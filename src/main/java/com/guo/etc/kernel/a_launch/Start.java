package com.guo.etc.kernel.a_launch;

import com.guo.etc.kernel.app.client.base.FirstPage;
import com.guo.etc.kernel.app.client.base.TopButtonPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

/**
 * Created by Administrator on 2016/4/8.
 */
public class Start extends JFrame {

    //这个contentPanel用来承载所有的内容组件，包括JScrollPanel,Buttons
    public static JPanel contentPanel = new FirstPage();

    private ApplicationContext context = null;

    public Start(ApplicationContext context) {
        //setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\Desktop\\blue16_006.gif"));
        setIconImage(Toolkit.getDefaultToolkit().getImage(Start.class.getResource("/image/First/1185783.png")));
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



    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
            //UIManager.setLookAndFeel(new MotifLookAndFeel());
            //UIManager.setLookAndFeel(new MetalLookAndFeel());//这个相当于没有变
            //UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e) {}
        new Start(context);
    }

}

