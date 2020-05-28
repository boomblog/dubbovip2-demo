package com.luban.provider.service;

import com.luban.DemoService;
import com.luban.DemoServiceListener;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

//@Service
public class AsyncDemoService implements DemoService {

    private final Map<String, DemoServiceListener> listeners = new ConcurrentHashMap<String, DemoServiceListener>();


    @Override
    public String sayHello(String name) {
        URL url = RpcContext.getContext().getUrl();
        return String.format("%s：%s, Hello, %s", url.getProtocol(), url.getPort(), name);  // 正常访问
    }

    @Override
    public CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
