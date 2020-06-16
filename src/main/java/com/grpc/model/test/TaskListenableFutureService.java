package com.grpc.model.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskListenableFutureService {
    private AsyncSearch asyncSearch;

    public TaskListenableFutureService(AsyncSearch asyncSearch) {
        this.asyncSearch=asyncSearch;
    }

    public List<String> search(List<String> keywords){
        CountDownLatch latch=new CountDownLatch(keywords.size());
        List<String> allResult= Collections.synchronizedList(new ArrayList<>());
        keywords.stream()
                .forEach(keyword->asyncFetch(latch,allResult,keyword));
        await(latch);
        return allResult;
    }

    private void asyncFetch(CountDownLatch latch, List<String> result, String keyword){
        asyncSearch.asyncFetch(keyword)
                .addCallback(
                        key->onSuccess(result,latch,key),
                        ex -> onError(latch,ex)
                );
    }

    private void await(CountDownLatch latch){
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
    private static void onSuccess(List<String> result, CountDownLatch latch, String keyword){
        result.add(keyword);
        latch.countDown();
    }
    private static void onError(CountDownLatch latch,Throwable ex){
        ex.printStackTrace();
        latch.countDown();
    }


    private static class AsyncSearch{
        public AsyncSearch() {
        }

        @Async
        public ListenableFuture<String> asyncFetch(String keyword){
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new AsyncResult<>("keyword="+keyword);
        }
    }

    public static void main(String[] args){
        long start=System.currentTimeMillis();
        List<String> list = new TaskListenableFutureService(new AsyncSearch()).search(Arrays.asList("java", "html", "spring"));
        list.stream().forEach(p-> System.out.println(p));
        long end=System.currentTimeMillis();
        System.out.println("=======>"+(end-start));
    }
}
