package com.guo.etc.kernel.app.client.simulate;

import org.springframework.context.ApplicationContext;

import javax.swing.*;

/**
 * Created by Administrator on 2016/5/3.
 *
 * 该Form已经在2016-5-12晚上21：46被废止了，因为这个类在打包为Jar包之后，无法正常运行，但是IDEA本地进行仿真的时候，完全可以实现
 * 具体原因，为个人猜测应该是在IDEA生成的Form的问题吧。
 */
public class SimulateClientPanel_has_abandon extends JPanel implements Runnable {
    private JPanel contentPanelClient;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JPanel inputPan;
    private JTextField ipTF;
    private JTextField portTF;
    private JTextField desTF;
    private JTextArea showMessageTA;
    private JButton connectButton;
    private JButton sendButton;
    private JButton disConnectButton;
    private JPanel showPanel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JLabel desLabel;
    private JScrollPane showJScroPanel;

    private static SimulateClientPanel_has_abandon simulateClientPanel = null;

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



    private SimulateClientPanel_has_abandon(ApplicationContext context){
        this.context = context;
        this.add(contentPanelClient);
        initCom();
        initActionListener();

    }

    public static SimulateClientPanel_has_abandon getInstance(ApplicationContext context){
        if (simulateClientPanel == null) {
            simulateClientPanel = new SimulateClientPanel_has_abandon(context);
        }
        return simulateClientPanel;
    }

    //设置组件的基本行为
    private void initCom() {
        sendButton.setEnabled(false);
        disConnectButton.setEnabled(false);
        showMessageTA.setEnabled(false);
        desTF.setEnabled(false);
        ipTF.setText("127.0.0.1");
        portTF.setText("1234");
        desTF.setText("尚未打开客户端");
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
    }

    @Override
    public void run() {
        System.out.println("Hello");
        while (runStatus) {
            switch (connectionStatus){
                case BEGIN_CONNECT:
                    boolean startFlag = SocketTrans.startSocket(ipTF.getText(), portTF.getText());
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

    private void appendContent(String s) {
        showMessageTA.append("\n"+s);
    }

    private void startFailedGUI() {
        desTF.setText("");
        desTF.setText("连接失败，可能是因为服务端尚未开启");
    }

    private void startSucceedGUI() {
        connectButton.setEnabled(false);
        disConnectButton.setEnabled(true);
        desTF.setText("");
        desTF.setText("客户端成功连接服务端");
    }

    //关闭完Socket之后界面的显示效果
    private void closeConnectionGUI() {
        desTF.setText("");
        desTF.setText("客户端和服务端断开，请重新连接");
        connectButton.setEnabled(true);
        disConnectButton.setEnabled(false);
    }

}
