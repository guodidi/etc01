package com.guo.etc.kernel.app.server.transmission;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/4/28.
 */
public class Transmission {

    //与网络相关的变量
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static BufferedReader in = null;
    private static PrintWriter out = null;

    //网络状态值相关
    private static final int DISCONNECTED = 1;
    private static final int WAITING_CLIENT = 4;

    private static final String END_FLAG = "END_FLAG_SESSION";

    /**
     * @param string 将ServerSocket的端口从portTF获得
     * @return boolean
     * 如果为true，那么设置连接状态是：连接成功，等待客户端连接
     * 如果是false，则连接状态是：连接失败，是否已经打开一个服务端程序
     */
    public static int launchServerSocket(String string) {
        try {
            serverSocket = new ServerSocket(Integer.parseInt(string));
            return WAITING_CLIENT;
        } catch (IOException e) {
            System.out.println("连接失败，请检查是否已经启动过");
            cleanUp();
            return DISCONNECTED;
        }
    }

    /**
     *
     * @return boolean
     * 如果是true，则设置断开按钮是true，同时连接状态是已连接，并将TF和sendButton设置true
     * 如果是false，保持不变
     */
    public static void acceptSocket(){
        try {
            //等待一个客户端连接
            socket = serverSocket.accept();
            //客户端连接都初始化资源
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务端等待一个客户端连接失败，请检查是否超时~");
            cleanUp();
        }
    }

    //断开连接之前做的事情
    public static void breakConnection() {
        out.print(END_FLAG);
        out.flush();
        System.out.println("发送结束，正在关闭...");
        cleanUp();
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

    //发送数据到客户端
    public static boolean sendMes(StringBuffer sendMessage) {
        out.print(sendMessage);
        out.flush();
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
}
