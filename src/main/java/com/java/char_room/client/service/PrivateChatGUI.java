package com.java.char_room.client.service;

import com.java.char_room.util.CommUtils;
import com.java.char_room.vo.MessageVO;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class PrivateChatGUI {
    private JPanel privateChatPanel;
    private JTextArea readFromServer;
    private JTextField send2Server;
    private JFrame frame;

    private String friendName;
    private String myName;
    private Connect2Server connect2Server;
    private PrintStream out;
    public PrivateChatGUI(String friendName,String myName, Connect2Server connect2Server) {
        this.friendName = friendName;
        this.myName = myName;
        this.connect2Server = connect2Server;
        //输入流
        try {
            this.out = new PrintStream(connect2Server.getOut(),true,
                    "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        frame = new JFrame("与"+friendName+"私聊中...");
        frame.setContentPane(privateChatPanel);
        // 设置窗口关闭的操作，将其设置为隐藏但不退出
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400,400);
        frame.setVisible(true);//唤醒界面
        // 捕捉输入框的键盘输入
        send2Server.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append(send2Server.getText());
                // 1.当捕捉到按下Enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 2.将当前信息发送到服务端
                    //type:类型,私聊 /content:发送方是谁---->发的内容msg/to:friendname发给谁
                    String msg = sb.toString();
                    MessageVO messageVO = new MessageVO();
                    messageVO.setType("2");
                    messageVO.setContent(myName+"-"+msg);
                    messageVO.setTo(friendName);
                    //把messageVO变成json字符串发出去
                    PrivateChatGUI.this.out.println(CommUtils.object2Json(messageVO));
                    // 3.将自己发送的信息展示到当前私聊界面
                    readFromServer(myName+"说:"+msg);
                    //把输入框还原了
                    send2Server.setText("");
                }
            }
        });
    }
    //当前页面的方法,展示
    public void readFromServer(String msg) {
        readFromServer.append(msg+"\n");
    }

    public JFrame getFrame() {
        return frame;
    }
}
