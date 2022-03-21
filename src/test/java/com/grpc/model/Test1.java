package com.grpc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test1 {

    @Test
    public void test1(){
        List<person> list=new ArrayList<>();
        list.add(new person("张统",11));
        list.add(new person("张统",12));
        list.add(new person("张统",12));
        list.add(new person("aaaaa",13));
        System.out.println(list.toString());//有重复
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class person{
        private String name;
        private int ages;


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            person person = (person) o;
            return ages == person.ages &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, ages);
        }
    }
}
