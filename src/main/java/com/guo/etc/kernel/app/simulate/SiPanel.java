package com.guo.etc.kernel.app.simulate;

import com.guo.etc.kernel.app.base.BasePanel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2016/4/24.
 */
public class SiPanel extends JPanel implements Runnable{

    private JLabel ipLabel = new JLabel("IP");
    private JLabel portLabel = new JLabel("Port");
    private JTextField ipTF = new JTextField(20);
    private JTextField portTF = new JTextField(20);
    private JLabel desLabel = new JLabel("连接状态");
    private JButton connectButton = new JButton("连接");
    private JButton disConnectButton = new JButton("断开");

    private JButton sendButton = new JButton("发送");
    private JTextField sendTF = new JTextField(30);
    private JTextArea showMessageTA = new JTextArea(25,20);
    private JTextField sx = new JTextField(20);

    //与文本显示相关的变量
    public static StringBuffer toAppend = new StringBuffer("");
    public static StringBuffer toSend = new StringBuffer("");

    //与网络相关的变量
    public static Socket socket = null;
    public static BufferedReader in = null;
    public static PrintWriter out = null;

    //与网络状态相关的变量
    private static int connectionStatus = 0;
    private final int DISCONNECTED = 1;
    private final int DISCONNECTING = 2;
    private final int BEGIN_CONNECT = 3;
    private final int CONNECTED = 4;
    private final String END_FLAG = "END_FLAG_SESSION";
    private Thread t = null;










    private static SiPanel siPanel = null;

    public static SiPanel getInstance(){
        if (siPanel == null) {
            siPanel = new SiPanel();
        }
        return siPanel;
    }

    private SiPanel(){
        initGUI();
        initCom();
        initActionListener();
    }
    //设置组件的基本行为
    private void initCom() {
        disConnectButton.setEnabled(false);
        showMessageTA.setEnabled(false);
        sx.setEnabled(false);
        sendButton.setEnabled(false);
        sendTF.setEnabled(false);
        ipTF.setText("127.0.0.1");
        portTF.setText("1234");
    }


    //界面初始化
    private void initGUI() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 10;
        c.ipady = 15;
        c.weightx = 1;

        c.insets = new Insets(5,15,5,5);
        this.addCom(ipLabel,c,0,0,1,1);
        this.addCom(ipTF,c,1,0,3,1);

        this.addCom(portLabel,c,0,1,1,1);
        this.addCom(portTF,c,1,1,3,1);
        this.addCom(sx,c,1,3,3,1);
        this.addCom(desLabel,c,0,3,1,1);
        this.addCom(connectButton,c,1,7,1,0);
        this.addCom(disConnectButton,c,3,7,1,0);

        this.addCom(sendTF,c,4,7,5,1);
        c.weighty = 0.5;
        JScrollPane showJS = new JScrollPane(showMessageTA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        c.weighty = 0;
        this.addCom(showJS,c,4,0,0,6);
        this.addCom(sendButton,c,9,7,0,1);
    }

    //编写一个方法用来添加约束到Panel
    private void addCom(Component c, GridBagConstraints gc,int x,int y,int w,int h) {
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = w;
        gc.gridheight = h;
        add(c,gc);
    }


    //添加事件
    private void initActionListener() {
        //功能完成
        connectButton.addActionListener((n)->{
            if(!ipTF.getText().equals("")&&!portTF.getText().equals("")){
                connectionStatus = BEGIN_CONNECT;
                t = new Thread(this);
                t.start();
            }
        });

        disConnectButton.addActionListener((n)->{
            connectionStatus = DISCONNECTING;
        });

        sendButton.addActionListener((n)->{
            String sendMessage = sendTF.getText();
            if (!sendMessage.equals("")) {
                sendButton.setEnabled(false);
                appendContent("客户端 ： "+sendMessage);
                sendString(sendMessage);
            }
            sendTF.setText("");
            sendButton.setEnabled(true);
        });

    }

    //将接收到的文本添加到TextArea中
    private void appendContent(String s) {
        showMessageTA.append("\n"+s);
    }

    // 发送数据到服务端
    private static void sendString(String s) {
        synchronized (toSend) {
            toSend.append(s + "\n");
        }
    }

    @Override
    public void run() {
        while (true) {

            switch (connectionStatus){
                case BEGIN_CONNECT:
                    initClientSocket();
                    break;
                case CONNECTED:
                    ReceiveOrSend();
                    break;
                case DISCONNECTING:
                    breakConnection();
                    break;
                default:
                    break;
            }
        }
    }
    //初始化Socket，非阻塞的方法
    private void initClientSocket() {
        try {
            socket = new Socket(ipTF.getText(),Integer.parseInt(portTF.getText()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            connectButton.setEnabled(false);
            disConnectButton.setEnabled(true);
            sendButton.setEnabled(true);
            sendTF.setEnabled(true);
            sx.setText("");
            sx.setText("连接成功");

            //new Thread(repaintGUI).start();
            connectionStatus = CONNECTED;
        } catch (IOException e) {
            sx.setText("");
            sx.setText("连接失败，可能是因为服务端尚未开启");
            connectionStatus = DISCONNECTED;
            cleanUp();
        }
    }

    //发送或者是接收数据
    private void ReceiveOrSend() {
        //发送数据到服务端
        if (toSend.length() != 0) {
            out.print(toSend);
            out.flush();
            toSend.setLength(0);
        }

        // 接收来自服务端的数据
        try {
            if (in.ready()) {
                String s = in.readLine();
                if (s.equals(END_FLAG)){
                    breakConnection();
                }
                else {
                    System.out.print("接收来自服务端的数据是：");
                    System.out.println(s);
                    appendContent("服务端 ： "+s);
                }
            }
        } catch (IOException e) {
            System.out.println("接收来自服务端数据出错，请及时解决");
            e.printStackTrace();
        }
    }

    //断开连接之前的操作
    private void breakConnection() {
        out.print(END_FLAG);
        out.flush();
        sx.setText("");
        sx.setText("尚未连接");
        connectButton.setEnabled(true);
        disConnectButton.setEnabled(false);
        sendButton.setEnabled(false);
        sendTF.setEnabled(false);
        connectionStatus = DISCONNECTED;
        cleanUp();
    }

    //关闭资源
    private void cleanUp() {
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
        }
        catch (IOException e) { socket = null; }

        try {
            if (in != null) {
                in.close();
                in = null;
            }
        }
        catch (IOException e) { in = null; }

        if (out != null) {
            out.close();
            out = null;
        }

        if (t != null) {
            try {
                t.join();
                t = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}
