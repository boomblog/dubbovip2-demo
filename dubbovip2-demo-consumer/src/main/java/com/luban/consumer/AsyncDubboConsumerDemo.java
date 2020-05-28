package com.luban.consumer;

import com.luban.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@EnableAutoConfiguration
public class AsyncDubboConsumerDemo {


    @Reference
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(AsyncDubboConsumerDemo.class);

        DemoService demoService = context.getBean(DemoService.class);

        // 调用直接返回CompletableFuture
        CompletableFuture<String> future = demoService.sayHelloAsync("异步调用");

        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("Response: " + v);
            }
        });

        System.out.println("结束了");

    }

}
