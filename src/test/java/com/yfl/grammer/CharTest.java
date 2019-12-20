package com.yfl.grammer;

public class CharTest {

    /**
     * link: https://blog.csdn.net/yaoyafeng92/article/details/81484779
     * char类型可以存储一个中文汉字，因为Java中使用的编码是Unicode（不选择任何特定的编码，直接使用字符在字符集中的编号，这是统一的唯一方法），一个char类型占2个字节（16比特），所以放一个中文是没问题的。
     * @param args
     */
    public static void main(String[] args) {
        char c = '你';
        System.out.println(c);
    }
}
