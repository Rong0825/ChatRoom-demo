package com.java.char_room.client.service;

import com.java.char_room.util.CommUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;
/*客户端的连接,方便后续传递Socket是传递输入输出流*/
public class Connect2Server {
    private static final String IP;
    private static final int PORT;
    static {
        Properties pros = CommUtils.loadProperties("socket.properties");
        IP = pros.getProperty("address");
        PORT = Integer.parseInt(pros.getProperty("port"));
    }
    private Socket client;
    private InputStream in;
    private OutputStream out;

    public Connect2Server() {
        try {
            client = new Socket(IP,PORT);
            //读取服务器端输入的数据
            in = client.getInputStream();
            //向服务器发送数据
            out = client.getOutputStream();
        } catch (IOException e) {
            System.err.println("与服务器建立连接失败");
            e.printStackTrace();
        }
    }

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }
}

