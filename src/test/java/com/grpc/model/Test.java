package com.grpc.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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


    public static void main(String[] args) {

    }
}
