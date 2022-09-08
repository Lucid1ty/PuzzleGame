package com.itheima.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test3 {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        // 设置界面的宽、高
        jFrame.setSize(603, 680);
        // 设置界面的标题
        jFrame.setTitle("事件演示");
        // 设置界面置顶
        jFrame.setAlwaysOnTop(true);
        // 设置界面居中
        jFrame.setLocationRelativeTo(null);
        // 设置关闭模式
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        jFrame.setLayout(null);


        // 创建一个按钮对象
        JButton jtb = new JButton("点我啊");
        // 设置位置和宽、高
        jtb.setBounds(0, 0, 100, 50);
        // 给按钮添加动作监听
        // addActionListener : 动作监听，鼠标左键、空格
        // 触发监听后就会执行 : MyActionListener() 里面的代码
//        jtb.addActionListener(new MyActionListener());

        // 利用匿名内部类来写
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("不要点我呀~");
            }
        });


        // 将按钮添加到界面当中
        jFrame.getContentPane().add(jtb);

        // 设置可见性
        jFrame.setVisible(true);
    }
}
