package web.app.pavelk.message4.broker1.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
@AllArgsConstructor
public class Main {

    private final StringRedisTemplate stringRedisTemplate;
    private final CountDownLatch countDownLatch;


    @GetMapping("/")
    public String getMain() throws InterruptedException {
        stringRedisTemplate.convertAndSend("chat", "Hello from Redis!");
        countDownLatch.await(); //ждать
        return "send";
    }
}
