package com.itheima.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener{
    Random r = new Random();
    // 创建一个二位数组
    // 目的：用于管理数据
    // 加载图片的时候，会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];

    // 记录空白方块在二维数组中的位置
    int x = 0;
    int y = 0;

    // 定义一个变量，记录当前展示图片的路径
    String path = "image\\girl\\girl1\\";

    // 定义一个二维数字，储存正确的数据
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0},
    };

    // 定义变量用来统计步数
    int step = 0;
    // 创建JMenuItem的对象
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem accountItem = new JMenuItem("公众号");

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
        // 定义一个一维数组
        int[] tempArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        // 打乱数组中的数据的顺序
        // 遍历数组，得到每一个元素，拿着每一个元素跟随机索引上的数据交换
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
            }
            // 取余(取模)操作时，被除数小于除数时，运算结果等于被除数
            data[i /4][i % 4] = tempArr[i];
        }
    }


    /**
     * 初始化图片
     * 添加图片的时候，就需要按照二维数组中管理的数据添加图片
     */
    private void initImage() {
        // 清空已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()){
            // 返回胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("C:\\Project\\PuzzleGame\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        // 显示统计步数
        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);


        // 细节：先加载的图片在上方，后加载的图片在下方
        // 利用循环加载所有图片
        // 外循环：把内循环重复执行了4次
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 获取当前要加载图片的序号
                int num = data[i][j];
                // 内循环：表示在一行中添加4张图片
                // 创建一个JLabel对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon(path + num +".jpg"));
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
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
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
        JMenu changeImage = new JMenu("更换图片");

        // 把美女，动物，运动添加到更换图片当中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        // 将每一个选项下面的条目添加到选项中
        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        // 公众号
        aboutJMenu.add(accountItem);

        // 给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        // 将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    // 按下不松手时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // 当按下A时
        if (code == 65) {
            // 把界面中的所有图片全部删除
            this.getContentPane().removeAll();
            // 加载第一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 加载背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            // 把背景图片添加到界面当中
            this.getContentPane().add(background);
            // 刷新一下界面
            this.getContentPane().repaint();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // 判断游戏是否胜利，如果胜利，此方法需要直接结束，不能再执行下面的移动代码了
        if (victory()) {
            // 结束方法
            return;
        }
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
            // 每移动一次，计数器就自增一次
            step++;
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
            // 每移动一次，计数器就自增一次
            step++;
            initImage();
        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            // 每移动一次，计数器就自增一次
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            // 每移动一次，计数器就自增一次
            step++;
            initImage();
        } else if (code == 65) {
            // 松开A键以后重新加载图片
            System.out.println("查看原图");
            initImage();
        } else if (code == 87) {
            // 按下W直接通关
            System.out.println("一键通关！");
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }


    /**
     * 判断data数组中的数据是否跟win数组相同
     * @return true：data数组中的数据与win数组完全相同
     */
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] != win[i][j]){
                    // 只要有一个不一样就返回false
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取当前被点击的条目对象
        Object obj = e.getSource();
        // 判断
        if (obj == replayItem){
            System.out.println("重新游戏");
            // 计步器清零
            step = 0;
            // 再次打乱二维数组中的数据
            initData();
            // 重新加载图片
            initImage();
        } else if (obj == reLoginItem) {
            System.out.println("重新登录");
            // 关闭当前界面
            this.setVisible(false);
            // 打开登录界面
            new LoginJFrame();
        }else if (obj == closeItem) {
            System.out.println("关闭游戏");
            // 直接关闭虚拟机即可
            System.exit(0);
        }else if (obj == accountItem) {
            System.out.println("公众号");
            // 创建一个弹窗对象
            JDialog jDialog = new JDialog();
            // 创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            // 设置jLabel的位置和宽、高
            jLabel.setBounds(0, 0, 258, 258);
            // 把图片添加到弹框当中
            jDialog.getContentPane().add(jLabel);
            // 设置弹框(jDialog)的大小
            jDialog.setSize(344, 344);
            // 置顶
            jDialog.setAlwaysOnTop(true);
            // 居中
            jDialog.setLocationRelativeTo(null);
            // 弹框不关闭则无法操作下面的界面
            jDialog.setModal(true);
            // 可见性
            jDialog.setVisible(true);
        } else if (obj == girl) {
            System.out.println("更换图片为美女");
            // 13张图片：girl1 到 girl13
            // 获取随机数 1- 13
            int randomNumber = r.nextInt(13) + 1;
            // 更改目录
            path = "image\\girl\\girl" + randomNumber + "\\";
            // 要先打乱
            initData();
            // 再加载图片
            initImage();
        } else if (obj == animal) {
            System.out.println("更换图片为动物");
            int randomNumber = r.nextInt(8) + 1;
            // 更改目录
            path = "image\\animal\\animal" + randomNumber + "\\";
            // 要先打乱
            initData();
            // 再加载图片
            initImage();
        }else if (obj == sport) {
            System.out.println("更换图片为运动");
            int randomNumber = r.nextInt(10) + 1;
            // 更改目录
            path = "image\\sport\\sport" + randomNumber + "\\";
            // 要先打乱
            initData();
            // 再加载图片
            initImage();
        }
    }
}
