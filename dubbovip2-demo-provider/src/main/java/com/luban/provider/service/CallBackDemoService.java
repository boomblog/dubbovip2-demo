package com.luban.provider.service;

import com.luban.DemoService;
import com.luban.DemoServiceListener;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;


// DemoService的addListener方法的index=1的参数是回调对象，服务消费者可以调用addListener方法来添加回调对象，服务提供者一旦执行回调对象的方法就会通知给服务消费者

//@Service(methods = {@Method(name = "addListener", arguments = {@Argument(index = 1, callback = true)})})
public class CallBackDemoService implements DemoService {

    private final Map<String, DemoServiceListener> listeners = new ConcurrentHashMap<String, DemoServiceListener>();

    public CallBackDemoService() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        for(Map.Entry<String, DemoServiceListener> entry : listeners.entrySet()){
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(5000); // 定时触发变更通知
                    } catch (Throwable t) { // 防御容错
                        t.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void addListener(String key, DemoServiceListener listener) {
        listeners.put(key, listener);
        listener.changed(getChanged(key)); // 发送变更通知
    }

    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @Override
    public String sayHello(String name) {
        URL url = RpcContext.getContext().getUrl();
        return String.format("%s：%s, Hello, %s", url.getProtocol(), url.getPort(), name);  // 正常访问
    }

}
