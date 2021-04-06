package com.zen.poc.consumer.processor;

import com.zen.poc.model.Money;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
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
        return input -> {
            log.info("THREAD {} batch size ={}", Thread.currentThread().getName(), input.getPayload().size());
/*            try {
                new CountDownLatch(1).await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
/*            Acknowledgment acknowledgment = input.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
            if (acknowledgment != null) {
                System.out.println("Acknowledgment provided");
                acknowledgment.acknowledge();
            }*/
        };
    }

}
