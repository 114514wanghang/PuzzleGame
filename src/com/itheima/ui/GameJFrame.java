package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.util.Random;

import static javax.swing.border.BevelBorder.LOWERED;

public class GameJFrame extends JFrame {
    int [][] arr = new int[4][4];
    //游戏主界面
    public GameJFrame() {
        initData();
        initJFrame();
        //初始化菜单
        initJMenueBar();
        //初始化界面
        initImage();
        //打乱图片

        //设置界面可见
        this.setVisible(true);
    }

    private void initData() {
        int [] data = new int[16];
        int temp = 0;
        for(int i = 0 ;i<=15;i++){
            data[i] = i;
        }
        Random random = new Random();
        for(int i = 0;i < data.length;i++){
            int index = random.nextInt(16);
            temp = data[i];
            data[i] = data[index];
            data[index] = temp;
        }
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0;j < arr[i].length;j++){
                arr[i][j] = data[index];
                index ++;
            }
        }
    }

    private void initImage() {

        for (int i = 0; i < 4 ; i++) {
            for(int j = 0; j < 4 ; j++){
                String path = "image\\animal\\animal3\\" + arr[i][j] + ".jpg";
                JLabel image = new JLabel(new ImageIcon(path));
                image.setBounds(105 * j + 83, 105 * i + 134, 105, 105); //设置图片大小偏移
                image.setBorder(new BevelBorder(LOWERED));
                this.getContentPane().add(image);
            }
        }
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
    }
    private void initJMenueBar() {
        JMenuBar  jMenuBar = new JMenuBar();
        JMenu function = new JMenu("功能");
        JMenu about = new JMenu("关于我们");
        //功能下的item
        JMenuItem replay_login = new JMenuItem("重新登录");
        JMenuItem replay_register = new JMenuItem("重新注册");
        JMenuItem replay_exit = new JMenuItem("退出游戏");
        //关于下的item
        JMenuItem about_us = new JMenuItem("公众号");
        //添加item
        function.add(replay_login);
        function.add(replay_register);
        function.add(replay_exit);
        about.add(about_us);
        //添加关于我们
        jMenuBar.add(function);
        jMenuBar.add(about);
        //设置添加菜单
        this.setJMenuBar(jMenuBar);
    }
    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机游戏 v1.0");
        //界面置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //设置隐藏容器框架 为空
        setLayout(null);
        //关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
