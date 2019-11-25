package com.example.rpws.chapters.SpringBootAwesome.reactivex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscriber;

import java.io.IOException;

@Slf4j
public class RxSeeEmitter extends SseEmitter {
    static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
    private final Subscriber<Temperature> subscriber;

    RxSeeEmitter() {
        super(SSE_SESSION_TIMEOUT);
        this.subscriber = new Subscriber<Temperature>() {
            @Override
            public void onCompleted() {
                log.info("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Temperature temperature) {
                try {
                    log.info("on next : {}", temperature);
                    RxSeeEmitter.this.send(temperature);
                } catch (IOException e) {
                    unsubscribe();
                }
            }
        };

        onCompletion(subscriber::unsubscribe);
        onTimeout(subscriber::unsubscribe);
    }

    Subscriber<Temperature> getSubscriber() {
        return subscriber;
    }
}
