package web.app.pavelk.message4.broker1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import web.app.pavelk.message4.broker1.bean.Receiver;

import java.util.concurrent.CountDownLatch;

@Configuration
public class ApplicationConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory,
                                            MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        //подключение по уолчанию порт 6379
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //регистрация обработчика с темой chat
        redisMessageListenerContainer.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return redisMessageListenerContainer;
    }

    //адаптер для получения сообщения
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        //вызом метода receiveMessage класса при получении сообщений
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    //принемает сообщения
    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }

    @Bean
    CountDownLatch latch() {//защелка
        return new CountDownLatch(1);
    }

    //Для отправки сообщения вам также необходим шаблон Redis
    //ключ и значение имеют тип String
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

}
