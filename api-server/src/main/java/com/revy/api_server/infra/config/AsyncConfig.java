package com.revy.api_server.infra.config;

import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    public AsyncTaskExecutor applicationTaskExecutor() {
        return new TaskExecutorAdapter(newVirtualThreadPerTaskExecutor("V-Async-"));
    }

    @Bean
    public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
        return protocolHandler -> {
            protocolHandler.setExecutor(newVirtualThreadPerTaskExecutor("V-exec-"));
        };
    }

    private ExecutorService newVirtualThreadPerTaskExecutor(String threadName) {
        // 쓰레드 이름 지정을 위해 newVirtualThreadPerTaskExecutor 미사용
        ThreadFactory factory = Thread.ofVirtual()
                .name(threadName, 0)
                .factory();
        return Executors.newThreadPerTaskExecutor(factory);
    }
}
