package com.grpc.model.guava;

import com.google.common.base.Utf8;
import com.google.common.collect.*;
import com.grpc.model.test.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class CollectionsTest {

    @Test
    public void test1(){
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        //通过list创建一个不可变的unmodifiableList集合
        List<String> unmodifiableList= Collections.unmodifiableList(list);
        System.out.println(unmodifiableList);

        //通过list添加元素
        list.add("ddd");
        System.out.println("往list添加一个元素:"+list);
        System.out.println("通过list添加元素之后的unmodifiableList:"+unmodifiableList);

        //通过unmodifiableList添加元素
        unmodifiableList.add("eee");
        System.out.println("往unmodifiableList添加一个元素:"+unmodifiableList);
    }

    @Test
    public void test2(){
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        //ImmutableList.copyOf
        ImmutableList<String> imlist=ImmutableList.copyOf(list);
        System.out.println("imlist："+imlist);

        //ImmutableList.of
        ImmutableList<String> imOflist=ImmutableList.of("peida","jerry","harry");
        System.out.println("imOflist："+imOflist);

        ImmutableSortedSet<String> imSortList=ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println("imSortList："+imSortList);

        list.add("baby");
        //关键看这里是否imlist也添加新元素了
        System.out.println("list添加新元素之后看imlist:"+imlist);

        ImmutableSet<Color> imColorSet =
                ImmutableSet.<Color>builder()
                        .add(new Color(0, 255, 255))
                        .add(new Color(0, 191, 255))
                        .build();

        System.out.println("imColorSet:"+imColorSet);
    }

    @AllArgsConstructor
    @Data
    public static class Color{
        private int a;
        private int b;
        private int c;
    }

    @Test
    public void test3(){
        ImmutableSet<String> imSet=ImmutableSet.of("peida","jerry","harry","lisa");
        System.out.println("imSet："+imSet);

        //set直接转list
        ImmutableList<String> imlist=ImmutableList.copyOf(imSet);
        System.out.println("imlist："+imlist);

        //list直接转SortedSet
        ImmutableSortedSet<String> imSortSet=ImmutableSortedSet.copyOf(imSet);
        System.out.println("imSortSet："+imSortSet);

        List<String> list=new ArrayList<String>();
        for(int i=0;i<=10;i++){
            list.add(i+"x");
        }
        System.out.println("list："+list);

        //截取集合部分元素
        ImmutableList<String> imInfolist=ImmutableList.copyOf(list.subList(2, 8));
        System.out.println("imInfolist："+imInfolist);
    }

    @Test
    public void Test4(){
        Multiset<String> multiset= HashMultiset.create();
        multiset.add("aa");
        multiset.add("bb");
        multiset.add("bb");
        multiset.add("cc",2);
        multiset.add("cc");
        System.out.println(multiset);//[aa, bb x 2, cc x 3]
        System.out.println(multiset.size()); //6
        System.out.println(multiset.count("cc"));//3
        multiset.setCount("bb",4);
        System.out.println(multiset);//[aa, bb x 4, cc x 3]
    }

    @Test
    public void Test5(){
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("fruit", "bannana");
        multimap.put("fruit", "apple");//key可以重复
        multimap.put("fruit", "apple");//value可以重复,不会覆盖之前的
        multimap.put("fruit", "peach");
        multimap.put("fish", "crucian");//欧洲鲫鱼
        multimap.put("fish", "carp");//鲤鱼
        Collection<String> fruits = multimap.get("fruit");
        System.err.println(fruits);//[bannana, apple, apple, peach]

        //对比 HashMultimap
        Multimap<String,String> multimap2= HashMultimap.create();
        multimap2.put("fruit2", "bannana");
        multimap2.put("fruit2", "apple");
        multimap2.put("fruit2", "apple");

        System.err.println(multimap2.size());//2
        System.err.println(multimap2.get("fruit2"));//[apple, bannana]     注意: 这里只有一个apple
    }

    @Test
    public void Test6(){
        BiMap<String, Integer> userId = HashBiMap.create();
        userId.put("lhx",30);
        userId.put("zll",28);
        String userForId = userId.inverse().get(30);
        System.out.println(userForId);//lhx

        userId.put("zll",40);
//        userId.put("jm",30);//报错
        userId.forcePut("jm",30);//强制替换value
        String userForId2 = userId.inverse().get(40);
        System.out.println(userForId2);//zll
        System.out.println(userId.get("zll"));
        System.out.println(userId);
    }

    @Test
    public void Test7(){
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("a", "b", 4);
        table.put("a", "b", 4);
        table.put("a", "c", 20);
        table.put("b", "c", 5);

        Map<String, Integer> a = table.row("a");// returns a Map mapping {b=4, c=20}
        System.out.println(a);

        Map<String, Integer> column = table.column("c");// returns a Map mapping {a=20, b=5}
        System.out.println(column);

        Integer integer = table.get("a", "c");
        System.out.println(integer); //20

        System.out.println(table.contains("a","e"));
        System.out.println(table.containsRow("a"));
    }

    @Test
    public void Test8() throws DecoderException, UnsupportedEncodingException {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();

        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}

        System.out.println(rangeSet);//[[1..5], [10..10], [11..20)]

        RangeMap<Integer, String> level = TreeRangeMap.create();
        level.put(Range.closed(90,100), "A");
        level.put(Range.closedOpen(80,90), "B");
        level.put(Range.lessThan(80), "C");

        System.out.println(level.get(90));//包含90和100
        System.out.println(level.get(100));
        System.out.println(level.get(95));
        System.out.println(level.get(85));
        System.out.println(level.get(75));
        System.out.println(Long.MAX_VALUE);

        String s="~!@#$%^&*()_+|\\\\=-,./?><;'][{}\\";
        byte[] b=s.getBytes("utf-8");
//        byte[] b= Hex.decodeHex(s.toCharArray());
        System.out.println(new String(b, "UTF-8"));
        System.out.println(Hex.encodeHexString(b));
    }

    @Data
    class Pesson{
        private String name;
        private Integer age;
    }

    @Test
    public void Test9(){
        RestTemplate restTemplate = new RestTemplate();
//        Person person=restTemplate.getForObject("http://localhost:8888//test3",Person.class);
//        System.out.println(person.toString());
//
//        String sss=restTemplate.getForObject("http://localhost:8888//test2",String.class);
//        System.out.println(sss);

        String jsonArray=restTemplate.getForObject("http://47.74.183.249:19585/block/{1}",String.class,1379399);
        System.out.println(jsonArray.toString());

        Map<String,String> map = new HashMap();
        map.put("height","44");
//        map.put("publicKeyHash","00945fb2c0ccb5c1e1b290246e102832c8966cdd");
//        map.put("blockHash","8f6e69a4b2aba4c61622f82a53539796dfbf62f79eb49278fb89d03b6fba1592");
        String s=restTemplate.getForObject("http://47.74.183.249:19585/internal/accountState?publicKeyHash={1}&blockHash={2}",String.class,"90f28fe9eeea5ea733febb71596eb560093e321b","30d18c3a8e341a88f533dc28dc5c0881142f08973644ef1e4035f90c33436704");
        System.out.println(s);
        JSONArray jsonArrays=new JSONArray();
//        jsonArray = restTemplate

        String url = "http://47.74.183.249:19585/WisdomCore/sendCoinBase?height={1}";
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", MediaType.ALL_VALUE);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("token","NUMtD0dEXungVX7eLuXkEurH5BCJzw");
//        MultiValueMap<String, Long> maps= new LinkedMultiValueMap<>();
//        maps.add("height", 200000L);
        Map<String,Long> maps = new HashMap();
        maps.put("height",200000L);
//        HttpEntity<> request = ;
        ResponseEntity<Map> response = restTemplate.exchange( url, HttpMethod.GET,new HttpEntity<>(headers),Map.class ,200000L);
        System.out.println(response.getBody().toString());

    }

}
