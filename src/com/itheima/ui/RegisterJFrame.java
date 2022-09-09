package com.itheima.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RegisterJFrame extends JFrame implements ActionListener {
    // RegisterJFrame : 注册界面，跟注册相关的逻辑都写在这个类中

    // 静态成员变量，用于给其他类调用(如：LoginJFrame)
    static ArrayList<User> users = new ArrayList<>();
    // 静态代码块，new RegisterJFrame 的时候最先执行
    static {
        users.add(new User("zhangsan", "123"));
    }
    // 用户名输入框
    JTextField username = new JTextField();
    // 密码输入框
    JTextField password = new JTextField();
    // 确认密码
    JTextField confirmPassword = new JTextField();
    // 添加注册按钮
    JButton register = new JButton();


    public RegisterJFrame(){
        // 初始化注册界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        // 设置可见性
        this.setVisible(true);
    }

    private void initView() {
        // 注册用户名(图片)
        JLabel usernameText = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameText.setBounds(116, 140, 79, 17);
        this.getContentPane().add(usernameText);

        // 注册用户名(输入框)
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        // 注册密码(图片)
        JLabel passwordText = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordText.setBounds(130, 200, 64, 16);
        this.getContentPane().add(passwordText);

        // 注册密码(输入框)
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);


        // 再次输入密码(图片)
        JLabel confirmPasswordText = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        confirmPasswordText.setBounds(100, 260, 96, 17);
        this.getContentPane().add(confirmPasswordText);

        // 再次输入密码(输入框)
        confirmPassword.setBounds(195, 256, 200, 30);
        this.getContentPane().add(confirmPassword);

        // 添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        // 去除按钮的默认边框
        register.setBorderPainted(false);
        // 去除按钮的默认背景
        register.setContentAreaFilled(false);
        // 为界面中添加登录按钮
        this.getContentPane().add(register);
        // 为注册按钮添加监听
        register.addActionListener(this);

        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void initJFrame() {
        // 设置宽、高
        this.setSize(488, 430);
        // 设置界面的标题
        this.setTitle("拼图游戏 注册");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        //让弹框展示出来
        jDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == register){
            System.out.println("注册按钮被点击了");
            if (username.getText() == null || password.getText() == null || confirmPassword.getText() == null){
                System.out.println("用户名和密码不能为空！");
            } else if (password.getText().equals(confirmPassword.getText())) {
                // 两次密码相同，存入用户信息
                users.add(new User(username.getText(), password.getText()));
                System.out.println("注册成功！");
                // 隐藏当前注册页面
                this.setVisible(false);
                // 返回登录页面
                new LoginJFrame();
            } else {
                System.out.println("两次密码输入不一致！");
                showJDialog("两次密码输入不一致!");
            }
        }
    }
}
