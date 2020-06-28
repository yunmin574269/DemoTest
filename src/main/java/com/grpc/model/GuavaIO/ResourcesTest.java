package com.grpc.model.GuavaIO;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.LineProcessor;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.*;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

//Resources 提供用于处理类路径中的资源的实用程序方法。
public class ResourcesTest {
    // Resources.getResource()
    @Test
    public void getResource() {
        System.out.println(Resources.getResource("application.properties"));
        // 起始路径不一样
        System.out.println(Resources.getResource(ResourcesTest.class, "ResourcesTest.class"));
    }

    // Resources.readLines()
    @Test
    public void readLines() {

        // 我们把application.yml文件的内容读取出来
        URL url = Resources.getResource("application.yml");
        try {
            // Resources.readLines
            List<String> lineList = Resources.readLines(url, Charsets.UTF_8);
            for (String lineItem : lineList) {
                System.out.println(lineItem);
            }
            // Resources.readLines +
            List<String> lineList2 = Resources.readLines(url, Charsets.UTF_8, new LineProcessor<List<String>>() {
                List<String> lines = Lists.newArrayList();
                @Override
                public boolean processLine(String line) throws IOException {
                    lines.add(line);
                    return true;
                }

                @Override
                public List<String> getResult() {
                    return lines;
                }
            });
            for (String lineItem : lineList2) {
                System.out.println(lineItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //ByteArrayResource代表byte[]数组资源，对于getInputStream将返回一个ByteArrayInputStream.
    @Test
    public void testByteArrayResource() throws IOException {
        //1、定义资源
        Resource resource = new ByteArrayResource("Hello World!".getBytes());
        //2、验证资源是否存在
        if(resource.exists()){
            //3、访问资源

            System.out.println(resource.getInputStream());
            System.out.println(resource.isFile());
            OutputStream outputStream=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\test.txt");
            ByteStreams.copy(resource.getInputStream(),outputStream);

            outputStream.close();
        }
    }

    //InputStreamResource 访问输入流资源的实现类。在其内部维护着一个输入流，因此我们可以直接取得该输入流并对其进行访问。
    // 值得注意的是，该输入流只允许读取一次（对应它的 isOpen 方法），若再次读取则会抛出异常。
    @Test
    public void testInputStreamResource() throws IOException {
        // 用 bis 表示输入流
        ByteArrayInputStream bis = new ByteArrayInputStream("hello".getBytes());
        InputStreamResource resource = new InputStreamResource(bis);
        System.out.println(new String(IOUtils.toByteArray(resource.getInputStream()), StandardCharsets.UTF_8));
        System.out.println(new String(IOUtils.toByteArray(resource.getInputStream()), StandardCharsets.UTF_8));
    }

    //FileSystemResource 访问文件系统里资源的实现类,访问绝对路径
    @Test
    public void testFileSystemResource() throws IOException {
        Resource resource = new FileSystemResource("C:\\Users\\Administrator\\Desktop\\test.txt");

// 关键 -> 表示读取 D:/DEMO/HELLO/GOOD.txt 路径下的文件
        Resource resource2 =  resource.createRelative("test1.txt");
        System.out.println(new String(IOUtils.toByteArray(resource.getInputStream()),StandardCharsets.UTF_8));
        System.out.println(new String(IOUtils.toByteArray(resource2.getInputStream()),StandardCharsets.UTF_8));
    }

    //ClassPathResource 用来访问类加载路径下的资源，相对于其他的 Resource 实现类，其主要优势是方便访问类加载路径里的资源，
    // 尤其对于 Web 应用，ClassPathResource 可自动搜索位于 WEB-INF/classes 下的资源文件，无须使用绝对路径访问。
    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource("ByteStreamsTest.class",this.getClass());
        System.out.println(new String(IOUtils.toByteArray(resource.getInputStream()), StandardCharsets.UTF_8));

        Resource resource2 = new ClassPathResource("application.properties");
        System.out.println(new String(IOUtils.toByteArray(resource2.getInputStream()), StandardCharsets.UTF_8));
    }

    //UrlResource 该资源代表 URL 资源，用于简化 URL 资源访问，且它可以被多次读取
    //一般支持如下资源访问：
    //http：通过标准的http协议访问web资源，如new UrlResource(“http://地址”)；
    //ftp：通过ftp协议访问资源，如new UrlResource(“ftp://地址”)；
    //file：通过file协议访问本地文件系统资源，如new UrlResource(“file:d:/test.txt”)；
    @Test
    public void testUrlResource() throws MalformedURLException {
        UrlResource ur = new UrlResource("https://wisdom-backup.oss-cn-beijing.aliyuncs.com/fast-sync.zip");
        System.out.println(ur.getFilename());
    }

}
