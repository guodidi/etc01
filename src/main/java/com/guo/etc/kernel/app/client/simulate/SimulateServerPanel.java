package com.guo.etc.kernel.app.client.simulate;

import com.guo.etc.kernel.app.server.encode.EncodeData;
import com.guo.etc.kernel.app.server.transmission.Transmission;

import javax.swing.*;

/**
 * Created by Administrator on 2016/5/1.
 */
public class SimulateServerPanel extends JPanel implements Runnable {
    private static SimulateServerPanel simulateServerPanel = null;
    private static final String END_FLAG = "END_FLAG_SESSION";
    private JPanel contentPanel;
    private JTextField ipTF;
    private JTextField portTF;
    private JTextField desTF;
    private JTextField vehicleIDTF;
    private JTextField vehicleTypeTF;
    private JTextField obuIDTF;
    private JTextField rsuIDTF;
    private JTextField laneIDTF;
    private JButton sendButton;
    private JButton connectButton;
    private JButton disConnectButton;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JLabel desLabel;
    private JLabel vehicleIDLabel;
    private JLabel vehicleTypeLabel;
    private JLabel obuIDLabel;
    private JLabel rsuIDLabel;
    private JLabel laneIDLabel;

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

    private SimulateServerPanel() {
        this.add(contentPanel);
        //initClientGUI();
        initCom();
        initActionListener();
    }

    public static SimulateServerPanel getInstance(){
        if (simulateServerPanel == null) {
            simulateServerPanel = new SimulateServerPanel();
        }
        return simulateServerPanel;
    }

    //为JTextField和JButton设置初始值
    private void initCom() {
        disConnectButton.setEnabled(false);
        desTF.setEnabled(false);
        sendButton.setEnabled(false);
        ipTF.setText("127.0.0.1");
        portTF.setText("1234");
        vehicleIDTF.setText("闽C");
        vehicleTypeTF.setText("小型车");
        rsuIDTF.setText("10");
        laneIDTF.setText("3");
        ipTF.setText("127.0.0.1");
        portTF.setText("1234");
        desTF.setText("服务器尚未连接");
        obuIDTF.setText("123");
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
            sendMessage = EncodeData.combinedAllData(vehicleIDTF.getText(), vehicleTypeTF.getText(), obuIDTF.getText(), rsuIDTF.getText(), laneIDTF.getText());
            if (!sendMessage.equals("")) {
                sendButton.setEnabled(false);
                createSendMessage(sendMessage);
            }
            sendButton.setEnabled(true);
        });
    }


    public static void main(String[] args) {
    JFrame frame = new JFrame("SimulateServerPanel");
    frame.setContentPane(SimulateServerPanel.getInstance());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
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

    private void createSendMessage(String sendMessage) {
        System.out.println("接收的数据是： "+sendMessage);
        sendMess.append(sendMessage+"\n");
    }

    //关闭完Socket之后界面的显示效果
    private void closeConnectionGUI() {
        desTF.setText("");
        desTF.setText("服务端和客户端已经断开，请重新连接");
        connectButton.setEnabled(true);
        disConnectButton.setEnabled(false);
        sendButton.setEnabled(false);
    }

    //建立好Socket连接之后的界面效果
    private void updateAcceptGUI() {
        sendButton.setEnabled(true);
        disConnectButton.setEnabled(true);
        desTF.setText("");
        desTF.setText("服务端和客户端已经成功建立连接");
    }

}
