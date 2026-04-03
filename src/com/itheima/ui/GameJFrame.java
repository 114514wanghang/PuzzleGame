package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static javax.swing.border.BevelBorder.LOWERED;

public class GameJFrame extends JFrame implements KeyListener {
    int [][] arr = new int[4][4];
    int x = 0;//空白x
    int y = 0;//空白y
    int temp = 0;//临时变量
    String path = "image/animal/animal3/";
    int [][] win = new int[][]{
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    //游戏主界面
    public GameJFrame() {
        //打乱图片
        initData();
        initJFrame();
        //初始化菜单
        initJMenueBar();
        //初始化界面
        initImage();


        //设置界面可见
        this.setVisible(true);
    }

    private void initData() {
        int [] data = new int[16];
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
        this.getContentPane().removeAll();
        if (isWin()){
            JLabel win = new JLabel(new ImageIcon("image/win.png"));
            win.setBounds(203,283,197,73);
            this.getContentPane().add(win);
        }
        for (int i = 0; i < 4 ; i++) {
            for(int j = 0; j < 4 ; j++){
                if(arr[i][j] == 0){
                    //记录空白方块
                    x = i;
                    y = j;
                }
                JLabel image = new JLabel(new ImageIcon(path + arr[i][j] + ".jpg"));
                image.setBounds(105 * j + 83, 105 * i + 134, 105, 105); //设置图片大小偏移
                image.setBorder(new BevelBorder(LOWERED));
                this.getContentPane().add(image);

            }
        }
        initBackground();
    }

    private void initBackground() {
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
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
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //按下不松开
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65 && !isWin()) { // 胜利后不能查看完整图片
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path+"all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            initBackground();
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(isWin()){
            return;
        }
        //上 38 下 40 左 37 右 39
       int code = e.getKeyCode();
       switch (code){
           case 37:
               System.out.println("向左移动");
               if (y > 0) {
                   temp = arr[x][y];
                   arr[x][y] = arr[x][y - 1];
                   arr[x][y - 1] = temp;
                   y--;
                   initImage();
               }
               break;
           case 38:
               System.out.println("向上移动");
               if (x > 0) {
                   temp = arr[x][y];
                   arr[x][y] = arr[x - 1][y];
                   arr[x - 1][y] = temp;
                   x--;
                   initImage();
               }
               break;
           case 39:
               System.out.println("向右移动");
               if (y < 3) {
                   temp = arr[x][y];
                   arr[x][y] = arr[x][y + 1];
                   arr[x][y + 1] = temp;
                   y++;
                   initImage();
               }
               break;
           case 40:
               System.out.println("向下移动");
               if (x < 3) {
                   temp = arr[x][y];
                   arr[x][y] = arr[x + 1][y];
                   arr[x + 1][y] = temp;
                   x++;
                   initImage();
               }
               break;
           case 65:
               initImage();
               break;
           case 87:
               arr = new int[][]{
                       {1,2,3,4},
                       {5,6,7,8},
                       {9,10,11,12},
                       {13,14,15,0}
               };
               initImage();
               break;
       }
    }

    //判断数据是否一致
    public boolean isWin(){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
