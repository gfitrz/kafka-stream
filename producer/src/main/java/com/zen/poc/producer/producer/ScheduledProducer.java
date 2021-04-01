package com.zen.poc.producer.producer;

import com.zen.poc.model.Currency;
import com.zen.poc.model.Money;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ScheduledProducer {

    private static final List<Currency> CURRENCIES = List.of(Currency.values());
    private static final int SIZE = CURRENCIES.size();
    private static final Random RANDOM = new Random();

    @Bean
    public Supplier<Message<Money>> moneySupplier() {
        return () -> {
            BigDecimal value = BigDecimal.valueOf(Math.random() * 100);
            Currency currency = CURRENCIES.get(RANDOM.nextInt(SIZE));
            return MessageBuilder
                .withPayload(new Money(UUID.randomUUID(), value, currency))
                .setHeader("header", Math.random())
                .build();
        };
    }
}
