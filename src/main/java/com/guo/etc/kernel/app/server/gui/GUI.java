package com.guo.etc.kernel.app.server.gui;

import com.guo.etc.kernel.app.server.transmission.Transmission;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/4/24.
 */
public class GUI extends JPanel implements Runnable {

    /*
    * 880*580
    * 使用GridBagLayout变一次性写好界面
    *
    * */
    private JLabel ipLabel = new JLabel("IP");
    private JLabel portLabel = new JLabel("Port");
    private JTextField ipTF = new JTextField(20);
    private JTextField portTF = new JTextField(20);
    private JLabel desLabel = new JLabel("连接状态");
    private JButton connectButton = new JButton("连接");
    private JButton disConnectButton = new JButton("断开");

    private JButton sendButton = new JButton("发送");
    private JTextField sendTF = new JTextField(30);
    private JTextArea showMessageTA = new JTextArea(22,20);
    private JTextField desTF = new JTextField(20);

    //关于车辆信息
    private JLabel vehicleIDLabel = new JLabel("车牌号码");
    private JTextField vehicleIDTF = new JTextField(20);

    private JLabel rsuIDLabel = new JLabel("RSU编号");
    private JTextField rsuIDTF = new JTextField(20);

    private JLabel laneIDLabel = new JLabel("车道编号");
    private JTextField laneIDTF = new JTextField(20);

    private static final String END_FLAG = "END_FLAG_SESSION";


    private Thread t = null;

    private static int connectionStatus = 0;
    private volatile boolean runStatus = false;
    private final int BEGIN_CONNECT = 3;
    private final int WAITING_CLIENT = 4;
    private final int CONNECTED = 5;
    private final int DISCONNECTED = 1;
    private final int DISCONNECTING = 2;

    private String sendMessage = null;
    private StringBuffer sendMess = new StringBuffer();

    //Spring相关
    private ApplicationContext context = null;


    public GUI() {
        initClientGUI();
        initCom();
        initActionListener();
    }

    //为JTextField和JButton设置初始值
    private void initCom() {
        disConnectButton.setEnabled(false);
        showMessageTA.setEnabled(false);
        desTF.setEnabled(false);
        sendButton.setEnabled(false);
        sendTF.setEnabled(false);
        ipTF.setText("127.0.0.1");
        portTF.setText("1234");
        vehicleIDTF.setText("京·F23565");
        rsuIDTF.setText("10");
        laneIDTF.setText("3");
        ipTF.setText("127.0.0.1");
        portTF.setText("1234");
    }


    private void initActionListener() {
        //功能完成
        connectButton.addActionListener((n)->{
            if(!ipTF.getText().equals("")&&!portTF.getText().equals("")){
                connectionStatus = BEGIN_CONNECT;
                runStatus = true;
                t = new Thread(this);
                t.start();
            }
        });

        disConnectButton.addActionListener((n)->{
            connectionStatus = DISCONNECTING;
        });

        sendButton.addActionListener((n)->{
            //TODO encode or Decode
            sendMessage = sendTF.getText();
            if (!sendMessage.equals("")) {
                sendButton.setEnabled(false);
                appendContent("服务端 ： "+sendMessage);
                createSendMessage(sendMessage);
            }
            sendTF.setText("");
            sendButton.setEnabled(true);
        });
    }

    private void createSendMessage(String sendMessage) {
        System.out.println("接收的数据是： "+sendMessage);
        sendMess.append(sendMessage+"\n");
    }

    //将接收到的文本添加到TextArea中
    private void appendContent(String s) {
        showMessageTA.append("\n"+s);
    }

    //初始化界面 --View层
    private void initClientGUI() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        //初始化基本c
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 10;
        c.ipady = 15;
        c.weightx = 1;
        //c.weighty = 0.2;
        c.insets = new Insets(5,15,5,5);

        this.addCom(ipLabel,c,0,0,1,1);
        this.addCom(ipTF,c,1,0,3,1);

        this.addCom(portLabel,c,0,1,1,1);
        this.addCom(portTF,c,1,1,3,1);

        this.addCom(desLabel,c,0,2,1,1);
        this.addCom(desTF,c,1,2,3,1);

        this.addCom(vehicleIDLabel,c,0,3,1,1);
        this.addCom(vehicleIDTF,c,1,3,3,1);

        this.addCom(rsuIDLabel,c,0,4,1,1);
        this.addCom(rsuIDTF,c,1,4,3,1);

        this.addCom(laneIDLabel,c,0,5,1,1);
        this.addCom(laneIDTF,c,1,5,3,1);


        this.addCom(connectButton,c,1,10,1,0);
        this.addCom(disConnectButton,c,3,10,1,0);
disConnectButton.setEnabled(false);

        c.weighty = 0.5;
        JScrollPane showJS = new JScrollPane(showMessageTA,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        c.weighty = 0;
        this.addCom(showJS,c,4,0,0,10);

        this.addCom(sendTF,c,4,10,5,1);
        this.addCom(sendButton,c,9,10,0,1);


    }

    //编写一个方法用来添加约束到Panel
    public void addCom(Component c, GridBagConstraints gc,int x,int y,int w,int h) {
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = w;
        gc.gridheight = h;
        add(c,gc);
    }

    //一个线程只有当线程中的所有语句执行完毕的时候，才会自动结束
    @Override
    public void run() {
        while (runStatus) {
            switch (connectionStatus){
                case BEGIN_CONNECT:
                    if (WAITING_CLIENT == Transmission.launchServerSocket(portTF.getText())){
                        //ToDo 更改界面的颜色,同时等待客户端连接
                        connectButton.setEnabled(false);
                        desTF.setText("");
                        desTF.setText("服务启动成功，等待客户端的接入");
                        Transmission.acceptSocket();
                        //TODO 更新界面，打开sendTF，sendButton，disconnectButton
                        updateAcceptGUI();
                        connectionStatus = CONNECTED;
                } else {
                        //TODO 如果打开失败，就要关闭 该线程，重新点击连接按钮来激活该线程
                        connectionStatus = DISCONNECTED;
                    }
                    break;
                case CONNECTED:
                    //进行读操作
                    String receiveMes = "";
                        if ((sendMess.length() != 0)&&(Transmission.sendMes(sendMess))){
                            //TODO 如果sendMessage是不为空的，那么就调用NetWork.sendMes方法,并添加到文本显示区域
                            System.out.println("发送成功");
                            sendMess.setLength(0);
                        } else if (Transmission.ready()){
                            //TODO 如果可以进行读写了就可以采用NetWork.readMes方法，并添加到文本区域
                            receiveMes = Transmission.readMes();
                            if (receiveMes.equals(END_FLAG)) {
                                connectionStatus = DISCONNECTED;
                            } else {
                                appendContent("客户端说："+receiveMes);
                                System.out.println("客户端 ："+receiveMes);
                            }
                        }else {}
                    break;
                case DISCONNECTING:
                    Transmission.breakConnection();
                    connectionStatus = DISCONNECTED;
                    break;
                case DISCONNECTED:
                    //TODO 关闭该线程
                    closeConnectionGUI();
                    runStatus = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("线程结束，准备关闭");
    }

    //关闭完Socket之后界面的显示效果
    private void closeConnectionGUI() {
        desTF.setText("");
        desTF.setText("服务端和客户端已经断开，请重新连接");
        connectButton.setEnabled(true);
        disConnectButton.setEnabled(false);
        sendTF.setEnabled(false);
        sendButton.setEnabled(false);
    }

    //建立好Socket连接之后的界面效果
    private void updateAcceptGUI() {
        sendTF.setEnabled(true);
        sendButton.setEnabled(true);
        disConnectButton.setEnabled(true);
        desTF.setText("");
        desTF.setText("服务端和客户端已经成功建立连接");
    }
}
