package org.example.jsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupTest {

    public static void main(String[] args) throws IOException {
        // 使用 Jsoup.connect(String url).get()方法获取（只支持 http 和 https 协议）:
        // connect(String url)方法创建一个新的 Connection并通过.get()或者.post()方法获得数据。如果从该URL获取HTML时发生错误，便会抛出 IOException，应适当处理。


        Document doc = Jsoup.connect("http://csdn.com/").get();

        String title = doc.title();
        System.out.println(title);

        //Connection 接口还提供一个方法链来解决特殊请求，我们可以在发送请求时带上请求的头部参数，具体如下：
        Document doc1 = Jsoup.connect("http://csdn.com")
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(8000)
                .post();
        System.out.println(doc1);

    }
}
