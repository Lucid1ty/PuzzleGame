package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener {
    // 创建一个二位数组
    // 目的：用于管理数据
    // 加载图片的时候，会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];

    // 记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    // JFrame : 界面、窗体
    // GameJFrame : 游戏的主界面，以后跟游戏相关的逻辑都写在这个类中
    public GameJFrame(){
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数据(打乱)
        initData();

        // 初始化图片(根据打乱的结果加载图片)
        initImage();

        // 让界面显示出来,建议写在最后
        this.setVisible(true);
    }


    /**
     * 初始化数据(打乱)
     */
    private void initData() {
        // 1.定义一个一维数组
        int[] tempArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        // 2.打乱数组中的数据的顺序
        // 遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据交换
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            // 获取随机索引
            int index = r.nextInt(tempArr.length);
            // 拿着遍历到的每一个数据，跟随机索引上的数据进行交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        // 给二位数组添加数据
        // 解法一
        // 遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组中
        for (int i = 0; i < tempArr.length; i++) {
            // 判断是否为0
            if (tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }else {
                // 取余(取模)操作时，被除数小于除数时，运算结果等于被除数
                data[i /4][i % 4] = tempArr[i];
            }
        }
    }

    /**
     * 初始化图片
     * 添加图片的时候，就需要按照二维数组中管理的数据添加图片
     */
    private void initImage() {
        // 清空已经出现的所有图片
        this.getContentPane().removeAll();
        // 细节：先加载的图片在上方，后加载的图片在下方

        // 利用循环加载所有图片
        // 外循环：把内循环重复执行了4次
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 获取当前要加载图片的序号
                int num = data[i][j];
                // 内循环：表示在一行中添加4张图片
                // 创建一个JLabel对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon(".\\image\\animal\\animal3\\" + num +".jpg"));
                // 指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                // 0:表示让图片凸起来，常量形式：BevelBorder.RAISED
                // 1:表示让图片凹下去，常量形式：BevelBorder.LOWERED
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }

        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon(".\\image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        // 把背景图片添加到界面当中
        this.getContentPane().add(background);

        // 刷新一下界面
        this.getContentPane().repaint();
    }


    private void initJFrame(){
        // 设置界面的宽、高
        this.setSize(603, 680);
        // 设置界面的标题
        this.setTitle("拼图游戏单机版 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    private void initJMenuBar(){
        // 创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        // 创建菜单上面的两个选项的对象(功能、关于我们)
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");

        JMenuItem accountItem = new JMenuItem("公众号");

        // 将每一个选项下面的条目添加到选项中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        // 将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 对上、下、左、右进行判断
        // 左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        if (code == 37){
            System.out.println("向左移动");
            // 表示空白方块已经到最左边了，无法再向左移动
            if (y == 0) {
                return;
            }
            // 把白色方块向左移动
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            // 按照移动后的样子绘制新图片
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            initImage();
        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            initImage();
        }
    }
}
