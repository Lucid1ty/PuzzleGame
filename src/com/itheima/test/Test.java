package com.itheima.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        // 把一个一维数组中的数据：0-15 打乱顺序
        // 然后再按照4个一组的方式添加到二维数组中

        // 1.定义一个一维数组
        int[] tempArr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        for (int i : tempArr) {
            System.out.print(i + " ");
        }

        System.out.println();

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
        // 3.遍历数组
        for (int i : tempArr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // 4.创建一个二位数组
        int[][] data = new int[4][4];

        // 给二位数组添加数据
        // 解法一
        // 遍历一维数组tempArr得到每一个元素，把每一个元素依次添加到二维数组中
        for (int i = 0; i < tempArr.length; i++) {
            // 取余(取模)操作时，被除数小于除数时，运算结果等于被除数
            data[i /4][i % 4] = tempArr[i];
        }

        // 遍历二维数组
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
