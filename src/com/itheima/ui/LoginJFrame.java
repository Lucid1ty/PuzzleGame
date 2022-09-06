package com.itheima.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    // LoginJFrame : 登录界面，跟登录相关的逻辑都写在这个类中

    public LoginJFrame(){
        // 在创建登录界面的时候，同时给这个界面设置一些信息
        // 比如：宽、高、可见性
        this.setSize(488, 430);
        // 设置界面的标题
        this.setTitle("登录");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置可见性
        this.setVisible(true);
    }


}
