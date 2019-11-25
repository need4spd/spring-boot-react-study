package com.example.rpws.chapters.SpringBootAwesome.restapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

@Slf4j
@RestController
public class AsyncTestRestController {
    @GetMapping("/hello")
    public WebAsyncTask sayHello(@RequestParam(defaultValue="Demo user") String name){
        log.info("service start...");
        WebAsyncTask task = new WebAsyncTask(4000, () -> {
            log.info("task execution start...");
            int waitSeconds = 2;
            if("timeout".equals(name)) {
                waitSeconds = 5;
            }
            Thread.sleep(waitSeconds * 1000);
            if("error".equals(name)) {
                throw new RuntimeException("Manual exception at runtime");
            }
            log.info("task execution end...");
            return "Welcome "+name+"!";
        });
        task.onTimeout(()->{
            log.info("onTimeout...");
            return "Request timed out...";
        });
        task.onError(()->{
            log.info("onError...");
            return "Some error occurred...";
        });
        task.onCompletion(()->{
            log.info("onCompletion...");
        });
        log.info("service end...");
        return task;
    }
}
