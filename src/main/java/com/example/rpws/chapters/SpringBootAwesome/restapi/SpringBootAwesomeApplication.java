package com.example.rpws.chapters.SpringBootAwesome.restapi;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@SpringBootApplication
public class SpringBootAwesomeApplication implements AsyncConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAwesomeApplication.class, args);
	}

	@Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(5);
        executor.setThreadNamePrefix("Application Async");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
	    return new SimpleAsyncUncaughtExceptionHandler();
    }
}
