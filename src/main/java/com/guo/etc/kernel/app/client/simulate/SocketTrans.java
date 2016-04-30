package com.guo.etc.kernel.app.client.simulate;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/4/29.
 */
public class SocketTrans {

    //与网络相关的变量
    public static ServerSocket serverSocket = null;
    public static Socket socket = null;
    public static BufferedReader in = null;
    public static PrintWriter out = null;

    //与网络状态相关的变量
    private static int connectionStatus = 0;
    private static final int DISCONNECTED = 1;
    private static final int DISCONNECTING = 2;
    private static final int BEGIN_CONNECT = 3;
    private static final int CONNECTED = 4;
    private static final String END_FLAG = "END_FLAG_SESSION";


    //打开socket
    public static boolean startSocket(String ipString, String portString) {
        try {
            socket = new Socket(ipString,Integer.parseInt(portString));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            return true;
        } catch (IOException e) {
            System.out.println("连接失败，可能是因为服务端尚未开启");
            //e.printStackTrace();
            cleanUp();
            return false;
        }
    }

    //判断是否可以进行连接
    public static boolean ready() {
        try {
            if (in.ready()){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("判断是否接受客户端数据出错");
            return false;
        }
        return false;
    }

    //断开连接之前做的事情
    public static void breakConnection() {
        out.print(END_FLAG);
        out.flush();
        System.out.println("发送结束，正在关闭...");
        cleanUp();
    }

    //发送数据到客户端
    public static boolean sendMes(StringBuffer sendMessage) {
        out.print(sendMessage);
        out.flush();
        System.out.println("要发送的数据是："+sendMessage);
        return true;
    }

    //进行读操作
    public static String readMes() {
        try {
            String receiveMeg = null;
            receiveMeg = in.readLine();
            if (receiveMeg.equals(END_FLAG)){
                breakConnection();
                return END_FLAG;
            }
            else {
                System.out.print("接收来自客户端的数据是：");
                System.out.println(receiveMeg);
                return receiveMeg;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("readMes出现异常，可能是读写出错，请及时检查");
            return  null;
        }
    }

    //释放相应的资源
    private static void cleanUp() {
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
    }
}
