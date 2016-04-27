package tcpSocket;

/**
 * Created by Administrator on 2016/4/24.
 */
import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class TCPChat implements Runnable {
    // Connect status constants
    //连接状态
    public final static int NULL = 0;
    public final static int DISCONNECTED = 1;
    public final static int DISCONNECTING = 2;
    public final static int BEGIN_CONNECT = 3;
    public final static int CONNECTED = 4;

    // Other constants
    public final static String statusMessages[] = {
            " Error! Could not connect!", " Disconnected",
            " Disconnecting...", " Connecting...", " Connected"
    };
    public final static TCPChat tcpObj = new TCPChat();
    public final static String END_CHAT_SESSION ="END_FLAG_SESSION"; // Indicates the end of a session

    // Connection atate info
    public static String hostIP = "localhost";
    public static int port = 1234;
    public static int connectionStatus = DISCONNECTED;
    public static boolean isHost = true;
    public static String statusString = statusMessages[connectionStatus];
    public static StringBuffer toAppend = new StringBuffer("");
    public static StringBuffer toSend = new StringBuffer("");

    // Various GUI components and info
    public static JFrame mainFrame = null;
    public static JTextArea chatText = null;
    public static JTextField chatLine = null;
    public static JPanel statusBar = null;
    public static JLabel statusField = null;
    public static JTextField statusColor = null;
    public static JTextField ipField = null;
    public static JTextField portField = null;
    public static JRadioButton hostOption = null;
    public static JRadioButton guestOption = null;
    public static JButton connectButton = null;
    public static JButton disconnectButton = null;

    // TCP Components
    public static ServerSocket hostServer = null;
    public static Socket socket = null;
    public static BufferedReader in = null;
    public static PrintWriter out = null;

    /////////////////////////////////////////////////////////////////

    private static JPanel initOptionsPane() {
        JPanel pane = null;
        ActionAdapter buttonListener = null;

        // Create an options pane
        JPanel optionsPane = new JPanel(new GridLayout(4, 1));

        // IP address input
        pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pane.add(new JLabel("Host IP:"));
        ipField = new JTextField(10);
        ipField.setText(hostIP);
        ipField.setEnabled(false);
        //为ipField提供了焦点监听
        ipField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                ipField.selectAll();
                // Should be editable only when disconnected
                if (connectionStatus != DISCONNECTED) {
                    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
                    changeStatusNTS(NULL, true);
                }
                else {
                    hostIP = ipField.getText();
                }
            }
        });
        pane.add(ipField);
        optionsPane.add(pane);

        // Port input
        pane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pane.add(new JLabel("Port:"));
        portField = new JTextField(10);
        portField.setEditable(true);
        portField.setText((new Integer(port)).toString());
        portField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                portField.selectAll();
                // should be editable only when disconnected
                if (connectionStatus != DISCONNECTED) {
                    changeStatusNTS(NULL, true);
                    System.out.println("55555555555555555555555555555555555");
                }
                else {
                    int temp;
                    try {
                        temp = Integer.parseInt(portField.getText());
                        port = temp;
                    }
                    catch (NumberFormatException nfe) {
                        portField.setText((new Integer(port)).toString());
                        mainFrame.repaint();
                    }
                }
            }
        });
        pane.add(portField);
        optionsPane.add(pane);

        // Host/guest option over-guo
        buttonListener = new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                if (connectionStatus != DISCONNECTED) {
                    changeStatusNTS(NULL, true);
                }
                else {
                    isHost = e.getActionCommand().equals("host");

                    // Cannot supply host IP if host option is chosen
                    if (isHost) {
                        ipField.setEnabled(false);
                        ipField.setText("localhost");
                        hostIP = "localhost";
                    }
                    else {
                        ipField.setEnabled(true);
                    }
                }
            }
        };
        ButtonGroup bg = new ButtonGroup();
        hostOption = new JRadioButton("Host", true);
        hostOption.setMnemonic(KeyEvent.VK_H);
        hostOption.setActionCommand("host");
        hostOption.addActionListener(buttonListener);
        guestOption = new JRadioButton("Guest", false);
        guestOption.setMnemonic(KeyEvent.VK_G);
        guestOption.setActionCommand("guest");
        guestOption.addActionListener(buttonListener);
        bg.add(hostOption);
        bg.add(guestOption);
        pane = new JPanel(new GridLayout(1, 2));
        pane.add(hostOption);
        pane.add(guestOption);
        optionsPane.add(pane);

        // Connect/disconnect buttons
        JPanel buttonPane = new JPanel(new GridLayout(1, 2));
        //使用了同一个变量，不会对上面的产生影响吗？
        //答案是：会！！！但是程序中的buttonListener中是必须先选择JRadioButton的，这时候点击这里的时候
        //JRadioButton已经点击完毕，因此不再需要这个实例，所以可以覆盖掉这个变量原来的值。
        buttonListener = new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                // Request a connection initiation
                if (e.getActionCommand().equals("connect")) {
                    changeStatusNTS(BEGIN_CONNECT, true);
                }
                // Disconnect
                else {
                    changeStatusNTS(DISCONNECTING, true);
                }
            }
        };
        connectButton = new JButton("Connect");
        connectButton.setMnemonic(KeyEvent.VK_C);
        connectButton.setActionCommand("connect");
        connectButton.addActionListener(buttonListener);
        connectButton.setEnabled(true);

        disconnectButton = new JButton("Disconnect");
        disconnectButton.setMnemonic(KeyEvent.VK_D);
        disconnectButton.setActionCommand("disconnect");
        disconnectButton.addActionListener(buttonListener);
        disconnectButton.setEnabled(false);

        buttonPane.add(connectButton);
        buttonPane.add(disconnectButton);
        optionsPane.add(buttonPane);

        return optionsPane;
    }

    /////////////////////////////////////////////////////////////////

    // Initialize all the GUI components and display the frame
    private static void initGUI() {
        // Set up the status bar
        statusField = new JLabel();
        statusField.setText(statusMessages[DISCONNECTED]);
        statusColor = new JTextField(1);
        statusColor.setBackground(Color.red);
        statusColor.setEditable(false);
        statusBar = new JPanel(new BorderLayout());
        statusBar.add(statusColor, BorderLayout.WEST);
        statusBar.add(statusField, BorderLayout.CENTER);

        // Set up the options pane
        JPanel optionsPane = initOptionsPane();

        // Set up the chat pane
        JPanel chatPane = new JPanel(new BorderLayout());
        chatText = new JTextArea(10, 20);
        chatText.setLineWrap(true);
        chatText.setEditable(false);
        chatText.setForeground(Color.blue);
        JScrollPane chatTextPane = new JScrollPane(chatText,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatLine = new JTextField();
        chatLine.setEnabled(false);
        chatLine.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                String s = chatLine.getText();
                if (!s.equals("")) {
                    appendToChatBox("OUTGOING: " + s + "\n");
                    chatLine.selectAll();

                    // Send the string
                    sendString(s);
                }
            }
        });
        chatPane.add(chatLine, BorderLayout.SOUTH);
        chatPane.add(chatTextPane, BorderLayout.CENTER);
        chatPane.setPreferredSize(new Dimension(200, 200));

        // Set up the main pane
        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.add(statusBar, BorderLayout.SOUTH);
        mainPane.add(optionsPane, BorderLayout.WEST);
        mainPane.add(chatPane, BorderLayout.CENTER);

        // Set up the main frame
        mainFrame = new JFrame("Simple TCP Chat");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setContentPane(mainPane);
        mainFrame.setSize(mainFrame.getPreferredSize());
        mainFrame.setLocation(200, 200);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /////////////////////////////////////////////////////////////////


    /*以线程安全的方式当改变状态时刷新界面
    * 主函数里面使用
    * changeStatusTCPServer
    * */
    private static void changeStatusTS(int newConnectStatus, boolean noError) {
        // Change state if valid state
        if (newConnectStatus != NULL) {
            connectionStatus = newConnectStatus;
            //statusString="卧槽你妈呀3";
        }

        // If there is no error, display the appropriate status message
        if (noError) {
            /*连接成功*/
            statusString="卧槽你妈呀1";
            //statusString = statusMessages[connectionStatus];
        }
        // Otherwise, display error message
        else {
            //statusString = statusMessages[NULL];
            /*连接失败*/
            statusString="卧槽你妈呀2";
        }

        /*
        * 以下的语句可以让程序点击错误后，重画tcpObj。例如，点击了两个都是host的时候，如果没有以下的语句，
        * 界面就会卡死不动。如果是没有连接服务端，直接打开客户端，客户端会一直连接下去，而正确处理应当是
        * 让客户端连接一会，然后自己断开，并给错误信息
        * */
        SwingUtilities.invokeLater(tcpObj);
    }

    /////////////////////////////////////////////////////////////////

    /*以线程安全的方式当改变状态时刷新界面
    * 在
    * */
    private static void changeStatusNTS(int newConnectStatus, boolean noError) {
        // Change state if valid state
        if (newConnectStatus != NULL) {
            connectionStatus = newConnectStatus;
        }

        // If there is no error, display the appropriate status message
        if (noError) {
            //statusString = statusMessages[connectionStatus];
            /*当来连接的过程中*/
            statusString="卧槽你妈呀3";
        }
        // Otherwise, display error message
        else {
            //statusString = statusMessages[NULL];
            statusString="卧槽你妈呀4";
        }
        //run线程完成的工作是，在相应的状态下程序的界面会有相应的修改
        tcpObj.run();
    }

    /////////////////////////////////////////////////////////////////

    // Thread-safe way to append to the chat box
    private static void appendToChatBox(String s) {
        synchronized (toAppend) {
            toAppend.append(s);
        }
    }

    /////////////////////////////////////////////////////////////////

    // Add text to send-buffer
    private static void sendString(String s) {
        synchronized (toSend) {
            toSend.append(s + "\n");
        }
    }

    /////////////////////////////////////////////////////////////////

    // Cleanup for disconnect
    private static void cleanUp() {
        try {
            if (hostServer != null) {
                hostServer.close();
                hostServer = null;
            }
        }
        catch (IOException e) { hostServer = null; }

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
    }

    /////////////////////////////////////////////////////////////////

    // Checks the current state and sets the enables/disables
    // accordingly

    public void run() {
        switch (connectionStatus) {
            case DISCONNECTED:
                connectButton.setEnabled(true);
                disconnectButton.setEnabled(false);
                ipField.setEnabled(true);
                portField.setEnabled(true);
                hostOption.setEnabled(true);
                guestOption.setEnabled(true);
                chatLine.setText(""); chatLine.setEnabled(false);
                statusColor.setBackground(Color.red);
                break;

            case DISCONNECTING:
                connectButton.setEnabled(false);
                disconnectButton.setEnabled(false);
                ipField.setEnabled(false);
                portField.setEnabled(false);
                hostOption.setEnabled(false);
                guestOption.setEnabled(false);
                chatLine.setEnabled(false);
                statusColor.setBackground(Color.orange);
                break;

            case CONNECTED:
                connectButton.setEnabled(false);
                disconnectButton.setEnabled(true);
                ipField.setEnabled(false);
                portField.setEnabled(false);
                hostOption.setEnabled(false);
                guestOption.setEnabled(false);
                chatLine.setEnabled(true);
                statusColor.setBackground(Color.green);
                break;

            case BEGIN_CONNECT:
                connectButton.setEnabled(false);
                disconnectButton.setEnabled(false);
                ipField.setEnabled(false);
                portField.setEnabled(false);
                hostOption.setEnabled(false);
                guestOption.setEnabled(false);
                chatLine.setEnabled(false);
                chatLine.grabFocus();
                statusColor.setBackground(Color.orange);
                break;
        }

        // Make sure that the button/text field states are consistent
        // with the internal states
        ipField.setText(hostIP);
        portField.setText((new Integer(port)).toString());
        hostOption.setSelected(isHost);
        guestOption.setSelected(!isHost);
        statusField.setText(statusString);
        chatText.append(toAppend.toString());
        toAppend.setLength(0);

        mainFrame.repaint();
    }

    /////////////////////////////////////////////////////////////////

    // The main procedure
    public static void main(String args[]) {
        String s;

        //初始化界面
        initGUI();

        //进入一个死循环
        while (true) {
            try { // 睡眠10毫秒
                Thread.sleep(10);
            }
            catch (InterruptedException e) {}

            //switch中判断来连接状态
            switch (connectionStatus) {
                case BEGIN_CONNECT:
                    try {
                        // Try to set up a server if host
                        System.out.println("老子要连接到你那去");
                        /*
                        * 判断是Host还是Guest,同时要初始化in，out
                        * */
                        if (isHost) {
                            hostServer = new ServerSocket(port);
                            socket = hostServer.accept();
                        }

                        // If guest, try to connect to the server
                        else {
                            socket = new Socket(hostIP, port);
                        }

                        in = new BufferedReader(new
                                InputStreamReader(socket.getInputStream()));
                        out = new PrintWriter(socket.getOutputStream(), true);
                        changeStatusTS(CONNECTED, true);
                        System.out.println();
                    }
                    /*如果初始化失败，那么就要先断开一切来连接，然后显示错误信息*/
                    catch (IOException e) {
                        System.out.println("老子连接失败了1");
                        cleanUp();
                        changeStatusTS(DISCONNECTED, false);
                    }
                    break;

                case CONNECTED:
                    try {
                        // Send data
                        if (toSend.length() != 0) {
                            out.print(toSend);
                            out.flush();
                            toSend.setLength(0);
                            changeStatusTS(NULL, true);
                        }

                        // Receive data
                        if (in.ready()) {
                            s = in.readLine();

                            if ((s != null) &&  (s.length() != 0)) {
                                // Check if it is the end of a trasmission
                                if (s.equals(END_CHAT_SESSION)) {
                                    changeStatusTS(DISCONNECTING, true);
                                }

                                // Otherwise, receive what text
                                else {
                                    System.out.println();
                                    appendToChatBox("INCOMING: " + s + "\n");
                                    changeStatusTS(NULL, true);
                                }
                            }
                        }
                    }
                    catch (IOException e) {
                        cleanUp();
                        changeStatusTS(DISCONNECTED, false);
                    }
                    break;

                case DISCONNECTING:
                    // Tell other chatter to disconnect as well
                    out.print(END_CHAT_SESSION);
                    out.flush();

                    // Clean up (close all streams/sockets)
                    cleanUp();
                    changeStatusTS(DISCONNECTED, true);
                    break;

                default: break; // do nothing
            }
        }
    }
}

////////////////////////////////////////////////////////////////////

// Action adapter for easy event-listener coding
class ActionAdapter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println("h");
    }
}

////////////////////////////////////////////////////////////////////
