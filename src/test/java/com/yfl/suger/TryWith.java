package com.yfl.suger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TryWith {
    /*
        不需要再写finally手动关闭inputStream, try-with-resource对实现了AutoCloseable的类会自动执行关闭方法
        参考：
        https://github.com/sjsdfg/effective-java-3rd-chinese/blob/master/docs/notes/09.%20%E4%BD%BF%E7%94%A8try-with-resources%E8%AF%AD%E5%8F%A5%E6%9B%BF%E4%BB%A3try-finally%E8%AF%AD%E5%8F%A5.md
     */
    public static void main(String[] args) throws IOException {

        try (
                InputStream inputStream = new FileInputStream("test.txt");
        ) {
            save(inputStream);
        }
    }

    private static void save(InputStream inputStream) {

    }
}
