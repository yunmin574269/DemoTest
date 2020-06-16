package com.grpc.model.enev;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.logging.Logger;

//为了防止客户端不断调用带来的开销(短连接)，下面的例子给出了连接池的方式
public class HelloWorldClientFactory extends BasePooledObjectFactory<HelloWorldClient> {

    @Override
    public HelloWorldClient create() throws Exception {
        return new HelloWorldClient("localhost", 50051);
    }

    @Override
    public PooledObject<HelloWorldClient> wrap(HelloWorldClient helloWorldClient) {
        return new DefaultPooledObject<HelloWorldClient>(helloWorldClient);
    }

    @Override
    public void destroyObject(PooledObject<HelloWorldClient> p) throws Exception {
        HelloWorldClient client = p.getObject();
        client.shutdown();
        super.destroyObject(p);
    }

    public static void main(String[] args) throws Exception {

        /** 连接池的配置 */
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

        /** 下面的配置均为默认配置,默认配置的参数可以在BaseObjectPoolConfig中找到 */
        poolConfig.setMaxTotal(5); // 池中的最大连接数
        poolConfig.setMinIdle(0); // 最少的空闲连接数
        poolConfig.setMaxIdle(5); // 最多的空闲连接数
        poolConfig.setMaxWaitMillis(-1); // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
        poolConfig.setLifo(true); // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
        poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 3L); // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟
        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞,默认为true

        /** 连接池创建 */
        GenericObjectPool<HelloWorldClient> objectPool = new GenericObjectPool<HelloWorldClient>(new HelloWorldClientFactory(), poolConfig);


        new Thread(makeTask(objectPool,"Thread1")).start();
        new Thread(makeTask(objectPool,"Thread2")).start();
        new Thread(makeTask(objectPool,"Thread3")).start();
        new Thread(makeTask(objectPool,"Thread4")).start();
        new Thread(makeTask(objectPool,"Thread5")).start();
        new Thread(makeTask(objectPool,"Thread6")).start();
        new Thread(makeTask(objectPool,"Thread7")).start();
        new Thread(makeTask(objectPool,"Thread8")).start();
        new Thread(makeTask(objectPool,"Thread9")).start();
        new Thread(makeTask(objectPool,"Thread10")).start();
        new Thread(makeTask(objectPool,"Thread11")).start();
        new Thread(makeTask(objectPool,"Thread12")).start();
        new Thread(makeTask(objectPool,"Thread13")).start();
        new Thread(makeTask(objectPool,"Thread14")).start();
        new Thread(makeTask(objectPool,"Thread15")).start();
        new Thread(makeTask(objectPool,"Thread16")).start();
        new Thread(makeTask(objectPool,"Thread17")).start();
        new Thread(makeTask(objectPool,"Thread18")).start();
        new Thread(makeTask(objectPool,"Thread19")).start();
        new Thread(makeTask(objectPool,"Thread20")).start();
        new Thread(makeTask(objectPool,"Thread21")).start();

//        Thread.sleep(100000);

    }

    private static Runnable makeTask(GenericObjectPool<HelloWorldClient> objectPool,String threadname){
        return () -> {
            HelloWorldClient client = null;
            try {
                client = objectPool.borrowObject();

            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                String req = "world!";
                client.greet(req);
                System.out.println("------>"+threadname);
            } finally {
                /** 将连接对象返回给连接池 */
                objectPool.returnObject(client);
            }
        };
    }

}
