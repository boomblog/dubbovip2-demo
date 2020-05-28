package com.luban.consumer;

import com.luban.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class MockDubboConsumerDemo {


//    @Reference(mock = "fail: return 123")
    @Reference(mock = "true")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(MockDubboConsumerDemo.class);

        DemoService demoService = context.getBean(DemoService.class);

        System.out.println((demoService.sayHello("周瑜")));
    }

}
