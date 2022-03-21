package com.grpc.model;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Test {

    @ParameterizedTest
    @CsvSource({ "foo, 1", "bar, 2", "'baz, qux', 3" })
    public void testWithCsvSource(String first, int second) {
        assertNotNull(first);
        assertNotEquals(0, second);
        System.out.println("111");
    }


    public static void main(String[] args) throws IOException {
        InputStream inputStream =new FileInputStream("F:/MSP3cycle=14");
        String lt= IOUtils.toString(inputStream);
        lt= lt.replaceAll("0x00", "");

        OutputStream outputStream = new FileOutputStream("F:/test1");
        IOUtils.write(lt, outputStream);
    }
}
