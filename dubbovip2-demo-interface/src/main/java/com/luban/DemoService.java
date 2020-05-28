package com.luban;

import java.util.concurrent.CompletableFuture;

public interface DemoService {
    // 同步调用方法
    String sayHello(String name) throws RuntimeException;

    // 异步调用方法
    default CompletableFuture<String> sayHelloAsync(String name) {
        return null;
    };

    // 添加回调
    default void addListener(String key, DemoServiceListener listener) {
    };
}
