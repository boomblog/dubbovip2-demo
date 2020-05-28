package com.luban.provider.service;

import com.luban.DemoService;
import com.luban.DemoServiceListener;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Service(timeout = 3000)
public class TimeoutDemoService implements DemoService {

    private final Map<String, DemoServiceListener> listeners = new ConcurrentHashMap<String, DemoServiceListener>();

    @Override
    public String sayHello(String name) {
        System.out.println("开始执行"+name);

        // 服务执行5秒
        // 服务超时时间为3秒，但是执行了5秒，服务端会把任务执行完的
        // 服务的超时时间，是指如果服务执行时间超过了指定的超时时间则会抛一个warn
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行结束"+name);

        URL url = RpcContext.getContext().getUrl();
        return String.format("%s：%s, Hello, %s", url.getProtocol(), url.getPort(), name);  // 正常访问
    }

}
