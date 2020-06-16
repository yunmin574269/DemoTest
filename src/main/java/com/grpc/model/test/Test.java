package com.grpc.model.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Test {

    private int age;

    static {
        System.out.println("执行我啦");
    }

    public Test(){
        System.out.println("新实例化一次");
    }

    public void init(){
        System.out.println("实例化一次");
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws IOException {
        LocalDate localDate=LocalDate.now();
        System.out.println(System.currentTimeMillis());
        System.out.println(localDate);

        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40);
        person.setDate(new Date());
        String jsonString = mapper.writeValueAsString(person);
        System.out.println(jsonString);
        byte[] jsonByte=mapper.writeValueAsBytes(person);
        Person deserializedPerson = mapper.readValue(jsonString, Person.class);
        System.out.println(deserializedPerson);
        Person person1=mapper.readValue(jsonByte,Person.class);
        person1.setAge(20);
        System.out.println(person1);


        String[] s=new String[3];
        s[0]="0";
        s[1]="1";
        s[2]="2";

        List<String> list= new java.util.ArrayList(Arrays.asList(s));
        list.set(0,"10");
        System.out.println(list.get(0));
        list.add(3,"999");
        list.add("777");
        list.remove(2);
        list.clear();;


    }

}
