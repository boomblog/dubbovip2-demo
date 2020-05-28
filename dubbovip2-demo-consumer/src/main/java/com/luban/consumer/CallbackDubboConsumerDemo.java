package com.luban.consumer;

import com.luban.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@EnableAutoConfiguration
public class CallbackDubboConsumerDemo {


    @Reference(loadbalance = "roundrobin")
    private DemoService demoService;

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(CallbackDubboConsumerDemo.class);

        DemoService demoService = context.getBean(DemoService.class);

        // 用来进行callback
        demoService.addListener("d1", new DemoServiceListenerImpl());
    }

}
