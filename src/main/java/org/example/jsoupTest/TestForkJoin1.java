package org.example.jsoupTest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 我需要循环多次网络io操作 ，并且将结果汇集，假如这些url存在一个list中， 如何 使用java ForkJoinpool 将任务拆分来优化，阈值为1，网络io的结果是string，最后都放到list中
 */
public class TestForkJoin1 {
    public static void main(String[] args) throws IOException {

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("http://127.0.0.1:8080/hello?name=user" + i);
        }

        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        NetworkIOTask networkIOTask = new NetworkIOTask(strings);

        List<String> result = forkJoinPool.invoke(networkIOTask);
        System.out.println(result);

        long end = System.currentTimeMillis();

        System.out.println(end - start);


    }

    static class NetworkIOTask extends RecursiveTask<List<String>> {
        private List<String> urlList;

        public NetworkIOTask(List<String> urlList) {
            this.urlList = urlList;
        }

        @Override
        protected List<String> compute() {
            if (urlList.size() == 1) {
                System.out.println(Thread.currentThread().getName());
                return Collections.singletonList(performNetworkIO(urlList.get(0)));
            } else {
                int mid = urlList.size() / 2;
                NetworkIOTask leftTask = new NetworkIOTask(urlList.subList(0, mid));
                NetworkIOTask rightTask = new NetworkIOTask(urlList.subList(mid, urlList.size()));

                // 提交子任务给线程池
                leftTask.fork();
                rightTask.fork();

                List<String> resultList = new ArrayList<>(leftTask.join());
                resultList.addAll(rightTask.join());
                return resultList;
            }
        }

        private String performNetworkIO(String url) {

            String body = null;
            try {
                body = Jsoup.connect(url)
                        .method(Connection.Method.GET)
                        .execute().body();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return body;
        }
    }


}
