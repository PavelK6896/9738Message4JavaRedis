package web.app.pavelk.message4.broker1.bean;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

//принемает сообщения
@AllArgsConstructor
public class Receiver {
    private final CountDownLatch countDownLatch;

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        countDownLatch.countDown();//+1 старт
    }

}
