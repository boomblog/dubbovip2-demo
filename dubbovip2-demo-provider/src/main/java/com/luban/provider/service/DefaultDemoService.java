package com.luban.provider.service;

import com.luban.DemoService;
import com.luban.DemoServiceListener;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


//@Service
public class DefaultDemoService implements DemoService {

    private final Map<String, DemoServiceListener> listeners = new ConcurrentHashMap<String, DemoServiceListener>();

    @Override
    public String sayHello(String name)  {
        System.out.println("执行了");
        URL url = RpcContext.getContext().getUrl();
        return String.format("%s：%s, Hello, %s", url.getProtocol(), url.getPort(), name);  // 正常访问
//        throw new RpcException();
    }

}
