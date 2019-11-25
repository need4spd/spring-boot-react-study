package com.example.rpws.chapters.SpringBootAwesome.reactivex;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TemperatureController {

    private final TemperatureSensor temperatureSensor;

    public TemperatureController(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @RequestMapping(
            value = "/temperature-stream-reactivex",
            method = RequestMethod.GET)
    public SseEmitter events (HttpServletRequest request) {
        RxSeeEmitter emitter = new RxSeeEmitter();
        temperatureSensor.temperatureStream().subscribe(emitter.getSubscriber());
        return emitter;
    }
}
