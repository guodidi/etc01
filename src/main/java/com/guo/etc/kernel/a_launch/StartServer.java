package com.guo.etc.kernel.a_launch;

import com.guo.etc.kernel.app.server.gui.ServerGui;

import javax.swing.*;
import java.awt.*;

/*
*
* Created by Administrator on 2016/4/28.
* 除了Encode还没有开始做，其他的部分已经做好了，下午主要做编解码部分，然后第一版就出来了。4-29
*/


public class StartServer {

    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        JFrame frame = new JFrame("ServerGui");
        ServerGui clientGUI = ServerGui.getInstance();
        frame.add(clientGUI);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(880,580));
        frame.setVisible(true);
    }
}
