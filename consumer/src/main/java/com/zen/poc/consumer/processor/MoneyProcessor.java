package com.zen.poc.consumer.processor;

import com.zen.poc.model.Money;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MoneyProcessor {

/*   @Bean
    public Consumer<KStream<String, Money>> consumer() {

        return input -> input
            .foreach((key, value) -> log.info("THREAD {} Received key {} and value {}",
                Thread.currentThread().getName(),
                key,
                value)
            );
    }*/

    // batch way
    @Bean
    public Consumer<Message<List<Money>>> consumer() {

        return input -> input.getPayload().forEach(value -> log.info("THREAD {} Received value {}, header={}",
            Thread.currentThread().getName(),
            value,
            input.getHeaders())
        );
    }

}
