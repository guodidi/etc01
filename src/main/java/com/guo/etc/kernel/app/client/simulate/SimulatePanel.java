package com.guo.etc.kernel.app.client.simulate;

import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;


/**
 * Created by Administrator on 2016/4/24.
 */
public class SimulatePanel extends JPanel implements Runnable{

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
    private JTextField desTF = new JTextField(20);

    //与文本显示相关的变量
    private StringBuffer sendBuffer = new StringBuffer();


    //与网络状态相关的变量
    private static int connectionStatus = 0;
    private final int DISCONNECTED = 1;
    private final int DISCONNECTING = 2;
    private final int BEGIN_CONNECT = 3;
    private final int CONNECTED = 4;
    private final int WAITING_Server = 5;
    private final String END_FLAG = "END_FLAG_SESSION";
    private Thread t = null;
    private volatile boolean runStatus = false;

    //和数据库相关
    private ApplicationContext context = null;


    private static SimulatePanel siPanel = null;

    public static SimulatePanel getInstance(ApplicationContext context){
        if (siPanel == null) {
            siPanel = new SimulatePanel(context);
        }
        return siPanel;
    }

    private SimulatePanel(ApplicationContext context){
        this.context = context;
        initGUI();
        initCom();
        initActionListener();
    }
    //设置组件的基本行为
    private void initCom() {
        disConnectButton.setEnabled(false);
        showMessageTA.setEnabled(false);
        desTF.setEnabled(false);
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
        this.addCom(desTF,c,1,3,3,1);
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
                runStatus = true;
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
    private  void sendString(String s) {
        sendBuffer.append(s+"\n");
    }

    @Override
    public void run() {
        System.out.println("Hello");
        while (runStatus) {
            switch (connectionStatus){
                case BEGIN_CONNECT:
                    boolean startFlag = SocketTrans.startSocket(ipTF.getText(),portTF.getText());
                    if (startFlag){
                        connectionStatus = CONNECTED;
                        startSucceedGUI();
                    }else {
                        connectionStatus = WAITING_Server;
                    }
                    break;
                case WAITING_Server:
                    startFailedGUI();
                    runStatus = false;
                    break;
                case CONNECTED:
                    String receiveMes = "";
                    if ((sendBuffer.length() != 0)&&(SocketTrans.sendMes(sendBuffer))){
                        //TODO 如果sendMessage是不为空的，那么就调用NetWork.sendMes方法,并添加到文本显示区域
                        System.out.println("发送成功");
                        sendBuffer.setLength(0);
                    } else if (SocketTrans.ready()){
                        //TODO 如果可以进行读写了就可以采用NetWork.readMes方法，并添加到文本区域
                        receiveMes = SocketTrans.readMes();

                        if (receiveMes.equals(END_FLAG)) {
                            connectionStatus = DISCONNECTED;
                        } else {
                            appendContent("服务端说："+receiveMes);

                            if (DecodeData.adjustFormat(receiveMes)){
                                if (DecodeData.setContext(context) &&DecodeData.adjust(receiveMes)) {
                                    //TODO 验证成功，对数据正常的车辆进行准确的收费
                                    System.out.println("验证成功，对数据正常的车辆进行准确的收费");
                                } else {
                                    //TODO 验证失败，无法对该车辆进行准确收费，但是仍然将记录存入数据库当中
                                    System.out.println("验证失败，无法对该车辆进行准确收费，但是仍然将记录存入数据库当中");
                                }
                            }else {
                                System.out.println("格式错误");
                            }
                            System.out.println("服务端 ："+receiveMes);
                        }
                    }else {}
                    break;
                case DISCONNECTING:
                    SocketTrans.breakConnection();
                    connectionStatus = DISCONNECTED;
                    break;
                case DISCONNECTED:
                    closeConnectionGUI();
                    runStatus = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("线程关闭");
    }

    private void startFailedGUI() {
        desTF.setText("");
        desTF.setText("连接失败，可能是因为服务端尚未开启");
    }

    private void startSucceedGUI() {
        connectButton.setEnabled(false);
        disConnectButton.setEnabled(true);
        sendButton.setEnabled(true);
        sendTF.setEnabled(true);
        desTF.setText("");
        desTF.setText("客户端成功连接服务端");
    }

    //关闭完Socket之后界面的显示效果
    private void closeConnectionGUI() {
        desTF.setText("");
        desTF.setText("客户端和服务端断开，请重新连接");
        connectButton.setEnabled(true);
        disConnectButton.setEnabled(false);
        sendTF.setEnabled(false);
        sendButton.setEnabled(false);
    }

}
