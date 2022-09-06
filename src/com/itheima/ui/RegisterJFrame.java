package com.itheima.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    // RegisterJFrame : 注册界面，跟注册相关的逻辑都写在这个类中
    public RegisterJFrame(){
        // 设置宽、高
        this.setSize(488, 500);
        // 设置界面的标题
        this.setTitle("注册");
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
