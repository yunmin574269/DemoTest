package com.grpc.model.GuavaIO;

import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.*;
import java.net.URL;

//ByteStreams里面提供用于处理字节数组和I / O流的实用程序方法。
public class ByteStreamsTest {

    // ByteStreams.copy()方法,数据拷贝
    @Test
    public void copy() {
        URL url = Resources.getResource("application.properties");
        File f = new File(url.getFile());    // 声明File对象

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        try {
            OutputStream outputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop" + File.separator + "abc.txt");
            // 把InputStream里面的内容写入到OutputStream里面去
            ByteStreams.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ByteStreams.toByteArray()方法，把InputStream里面的数据读到数组里面去
    @Test
    public void toByteArray() {
        URL url = Resources.getResource("application.properties");
        File f = new File(url.getFile());    // 声明File对象
        // InputStream
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
        try {
            // InputStream里面的内容读到byte数组里面去
            byte[] byteArrary = ByteStreams.toByteArray(inputStream);
            System.out.println(new String(byteArrary));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ByteStreams.read() 把
    @Test
    public void read() {
        URL url = Resources.getResource("application.properties");
        File f = new File(url.getFile());    // 声明File对象
        // InputStream
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
        try {
            byte[] byteArray = new byte[1024];
            int readLength = ByteStreams.read(inputStream, byteArray, 0, 1024);
            System.out.println("读取都的数据长度 = " + readLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
