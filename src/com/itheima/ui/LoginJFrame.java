package com.itheima.ui;

import com.itheima.util.CodeUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginJFrame extends JFrame implements ActionListener {
    // LoginJFrame : 登录界面，跟登录相关的逻辑都写在这个类中

    // 添加登录按钮
    JButton login = new JButton();
    // 添加注册按钮
    JButton register = new JButton();
    // 用户名输入框
    JTextField username = new JTextField();
    // 密码输入框
    JTextField password = new JTextField();
    // 用户输入的验证码
    JTextField code = new JTextField();
    // 随机出现的验证码
    String codeStr =  CodeUtil.getCode();


    public LoginJFrame(){
        //初始化界面
        initJFrame();
        //在这个界面中添加内容
        initView();
        // 设置可见性
        this.setVisible(true);
    }


    private void initView() {
        // 添加用户名(图片)
        JLabel usernameText = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        // 设置用户名输入框(文本输入框)
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);


        // 添加密码(图片)
        JLabel passwordText = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        // 设置密码输入框(文本输入框)
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        // 验证码(图片)
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        // 设置验证码的输入框(文本输入框)
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);
        // 正确的验证码
        JLabel rightCode = new JLabel();
        // 设置内容
        rightCode.setText(codeStr);
        // 位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        // 添加到界面
        this.getContentPane().add(rightCode);

        // 设置登录按钮
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        // 去除按钮的默认边框
        login.setBorderPainted(false);
        // 去除按钮的默认背景
        login.setContentAreaFilled(false);
        // 为界面中添加登录按钮
        this.getContentPane().add(login);
        // 为登录按钮添加监听
        login.addActionListener(this);


        // 设置注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        // 去除按钮的默认边框
        register.setBorderPainted(false);
        // 去除按钮的默认背景
        register.setContentAreaFilled(false);
        // 为界面中添加注册按钮
        this.getContentPane().add(register);
        // 为注册按钮添加监听
        register.addActionListener(this);

        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        // 把背景图片添加道界面中
        this.getContentPane().add(background);
    }


    public void initJFrame() {
        this.setSize(488, 430); // 设置宽高
        this.setTitle("拼图游戏 V1.0登录"); // 设置标题
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // 设置关闭模式
        this.setLocationRelativeTo(null); // 居中
        this.setAlwaysOnTop(true); // 置顶
        this.setLayout(null); // 取消内部默认布局
    }


    /**
     * 弹窗，显示一段话
     * @param content 传入你要显示的字符串
     */
    public void showJDialog(String content) {
        // 创建一个弹框对象
        JDialog jDialog = new JDialog();
        // 给弹框设置大小
        jDialog.setSize(200, 150);
        // 让弹框置顶
        jDialog.setAlwaysOnTop(true);
        // 让弹框居中
        jDialog.setLocationRelativeTo(null);
        // 弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);
        // 创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);
        // 让弹框展示出来
        jDialog.setVisible(true);
    }




    /**
     * 监听登录界面的登录按钮和注册按钮
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();
        // 判断是登录按钮被点击还是注册按钮被点击
        if (obj == login){
            System.out.println("登录按钮被点击了！");
            // 异常处理：
            // 用户名、密码、验证码都不能为空
            if (username.getText().equals("")){
                System.out.println("用户名不能为空！");
                showJDialog("用户名不能为空！");
            } else if (password.getText().equals("")) {
                System.out.println("密码不能为空！");
                showJDialog("密码不能为空！");
            } else if (code.getText().equals("")) {
                System.out.println("验证码不能为空！");
                showJDialog("验证码不能为空！");
            } else if (!code.getText().equals(codeStr)) {
                // 检查验证码是否正确
                System.out.println("您输入的验证码有误！");
                showJDialog("您输入的验证码有误!");
            } else if (findUserName(username.getText())) {
                // 在数据库里找是否存在这个用户名
                System.out.println("用户名不存在！");
                showJDialog("用户名不存在！");
            } else if (verifyPassword(password.getText())){
                // 对比密码：用户输入的密码(文本框中)和正确的密码是否一致
                System.out.println("密码错误！");
                showJDialog("密码错误！");
            }else{
                System.out.println("登录成功！");
                // 隐藏当前的登录窗口
                this.setVisible(false);
                // 加载游戏窗口
                new GameJFrame();
            }
        } else if (obj == register) {
            System.out.println("注册按钮被点击了！");
            // 隐藏当前登录窗口
            this.setVisible(false);
            // 创建注册界面
            new RegisterJFrame();
        }
    }

    private boolean verifyPassword(String passWord) {
        // 遍历users列表中的每个用户，获取密码
        for (User user : RegisterJFrame.users) {
            if (user.getPassWord().equals(passWord)){
                return false;
            }
        }
        // 密码不正确，返回true
        return true;
    }

    /**
     * 查找用户名
     * @return  true：不存在该用户 false：存在该用户
     */
    private boolean findUserName(String userName) {
        // 遍历users列表中的每个用户，获取用户名
        for (User user : RegisterJFrame.users) {
            if (user.getUserName().equals(userName)){
                return false;
            }
        }
        // 不存在该用户，返回true
        return true;
    }
}