package tcpSocket;

import com.sun.corba.se.spi.activation.Server;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/4/24.
 */
public class ServerGUI extends JPanel implements Runnable {

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

    private JLabel vehicleTypeLabel = new JLabel("车辆类型");
    private JTextField vehicleTypeTF = new JTextField(20);

    private JLabel rsuIDLabel = new JLabel("RSU编号");
    private JTextField rsuIDTF = new JTextField(20);

    private JLabel rsuSiteLabel = new JLabel("RSU位置");//车站的站点,这个可能需要取消2016-4-27
    private JTextField rsuSiteTF = new JTextField(20);

    private JLabel laneIDLabel = new JLabel("车道编号");
    private JTextField laneIDTF = new JTextField(20);


    //与文本显示相关的变量
    private static StringBuffer toAppend = new StringBuffer("");
    private static StringBuffer toSend = new StringBuffer("");

    //与网络相关的变量
    private ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static BufferedReader in = null;
    private static PrintWriter out = null;

    private Thread t = null;
    private Runnable repaintGUI = null;
    //
    private static int connectionStatus = 0;
    private final int NULL= 0;
    private final int DISCONNECTED = 1;
    private final int DISCONNECTING = 2;
    private final int BEGIN_CONNECT = 3;
    private final int CONNECTED = 4;

    private final String END_FLAG = "END_FLAG_SESSION";


    public ServerGUI() {
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
        vehicleTypeTF.setText("小型车");
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
                appendContent("服务端 ： "+sendMessage);
                sendString(sendMessage);
            }
            sendTF.setText("");
            sendButton.setEnabled(true);
        });

        //界面重绘,尚未使用
        repaintGUI = ()->{
            //该线程可以用于重回界面
            try {
                while(true) {
                    Thread.sleep(1000);
                    //System.out.println("你好，我是重回界面的功能");
                }
            } catch (InterruptedException e) {
                System.out.println("重回界面问题出现，请及时解决");
                e.printStackTrace();
            }
        };

    }


    //将接收到的文本添加到TextArea中
    private void appendContent(String s) {
        showMessageTA.append("\n"+s);
    }


    // 发送数据到服务端
    private static void sendString(String s) {
        synchronized (toSend) {
            //toSend在这个地方呗赋值了
            toSend.append(s + "\n");
        }
    }

    //善后工作
    private  void cleanUp() {
        try {
            if (serverSocket != null){
                serverSocket.close();
                serverSocket = null;
                }

            } catch (IOException e) {
                serverSocket = null;
            }

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

    //初始化界面
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("ServerGUI");
        ServerGUI clientGUI = new ServerGUI();
        frame.add(clientGUI);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(880,580));
        frame.setVisible(true);
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
            serverSocket = new ServerSocket(Integer.parseInt(portTF.getText()));
            desTF.setText("启动成功，等待连接");
            connectButton.setEnabled(false);
            socket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            disConnectButton.setEnabled(true);
            desTF.setText("");
            desTF.setText("服务端，连接成功");
            sendButton.setEnabled(true);
            sendTF.setEnabled(true);
            //new Thread(repaintGUI).start();
            connectionStatus = CONNECTED;
        } catch (IOException e) {
            desTF.setText("");
            desTF.setText("连接失败");
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
                    System.out.print("接收来自客户端的数据是：");
                    System.out.println(s);

/*                    String[] info = EncodeData.getInfo(s);
                    for (String ss: info) {
                        System.out.println(ss);
                    }*/
                    /*
                    * 根据得到的字符串，解析过得数据
                    * */
                    appendContent("客户端 ： "+s);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //善后工作
    private void breakConnection() {
        out.print(END_FLAG);
        out.flush();
        desTF.setText("");
        desTF.setText("尚未连接");
        connectButton.setEnabled(true);
        disConnectButton.setEnabled(false);
        connectionStatus = DISCONNECTED;
        cleanUp();
    }
}
