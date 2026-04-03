package com.itheima.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    public LoginJFrame() {
        this.setTitle("拼图登录界面");
        this.setSize(488, 430);
        //界面置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
