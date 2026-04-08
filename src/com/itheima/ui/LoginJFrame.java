package com.itheima.ui;

import javax.swing.*;
import java.awt.event.*;

public class LoginJFrame extends JFrame implements MouseListener {
    JButton login_button = new JButton(new ImageIcon("image/login/登录按钮.png"));
    JButton register_button = new JButton(new ImageIcon("image/login/注册按钮.png"));
    JTextField user_text = new JTextField();
    JPasswordField password_text = new JPasswordField();
    JTextField captcha_text = new JTextField();
    JLabel rightCode = new JLabel();
    public LoginJFrame() {
        initView();
        initJFrame();
    }
    private void initView(){
        JLabel user_label = new JLabel(new ImageIcon("image/login/用户名.png"));
        JLabel password_label = new JLabel(new ImageIcon("image/login/密码.png"));
        JLabel captcha = new JLabel(new ImageIcon("image/login/验证码.png"));
        String codeStr = String.valueOf(CodeUtil.getCode());

        login_button.setBorderPainted(false);
        register_button.setBorderPainted(false);
        login_button.setContentAreaFilled(false);
        register_button.setContentAreaFilled(false);

        // 给登录按钮和注册按钮添加鼠标监听器
        login_button.addMouseListener(this);
        register_button.addMouseListener(this);
        user_label.setBounds(120,130,47,17);
        password_label.setBounds(135,190,32,16);
        captcha.setBounds(120,250,52,21);

        user_text.setBounds(180,124,200,30);
        password_text.setBounds(180,182,200,30);
        captcha_text.setBounds(180,247,100,30);

        rightCode.setBounds(300,246,50,30);
        rightCode.setText(codeStr);
        rightCode.addMouseListener(this);

        login_button.setBounds(123,310,128,47);
        register_button.setBounds(256,310,128,47);

        this.getContentPane().add(user_label);
        this.getContentPane().add(user_text);
        this.getContentPane().add(password_label);
        this.getContentPane().add(password_text);
        this.getContentPane().add(captcha);
        this.getContentPane().add(captcha_text);
        this.getContentPane().add(rightCode);
        this.getContentPane().add(login_button);
        this.getContentPane().add(register_button);
        initBackground();
    }
    private void initBackground() {
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
    }
    private void initJFrame() {
        this.setTitle("拼图登录界面");
        this.setSize(488, 430);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void showDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setTitle("提示");
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        JLabel warning = new JLabel(content, SwingConstants.CENTER);
        warning.setBounds(0, 0, 200, 150);
        jDialog.add(warning);
        jDialog.setVisible(true);
    }

    private void clearAndRefresh() {
        String codeStr = String.valueOf(CodeUtil.getCode());
        rightCode.setText(codeStr);
        user_text.setText("");
        password_text.setText("");
        captcha_text.setText("");
        rightCode.repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == rightCode) {
            String codeStr = String.valueOf(CodeUtil.getCode());
            rightCode.setText(codeStr);
            rightCode.repaint();
        } else if (obj == login_button) {
            String user_name = user_text.getText();
            String password = new String(password_text.getPassword());
            String captcha = captcha_text.getText();
            if (captcha.isEmpty()) {
                showDialog("验证码不能为空");
                clearAndRefresh();
                return;
            }
            if (!captcha.equals(rightCode.getText())) {
                showDialog("验证码错误");
                clearAndRefresh();
                return;
            }
            if (user_name.equals("admin") && password.equals("123") && captcha.equals(rightCode.getText())) {
                this.setVisible(false);
                new GameJFrame();
            } else if (user_name.isEmpty() || password.isEmpty()){
                showDialog("用户名或者密码为空");
                clearAndRefresh();
            }else {
                showDialog("用户名或者密码错误");
                clearAndRefresh();
            }

        } else if (obj == register_button) {
            this.setVisible(false);
            new RegisterJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login_button) {
            login_button.setIcon(new ImageIcon("image/login/登录按下.png"));
        } else if (obj == register_button) {
            register_button.setIcon(new ImageIcon("image/register/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if (obj == login_button) {
            // 鼠标释放时恢复原图片
            login_button.setIcon(new ImageIcon("image/login/登录按钮.png"));
        } else if (obj == register_button) {
            register_button.setIcon(new ImageIcon("image/register/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
