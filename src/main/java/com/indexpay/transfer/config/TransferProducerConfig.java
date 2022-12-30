package com.indexpay.transfer.config;

import com.indexpay.transfer.exception.GenericException;
import com.indexpay.transfer.service.dto.BankTransferRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.retrytopic.RetryTopicConfiguration;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TransferProducerConfig {

    private final KafkaConfigProperties properties;

    public TransferProducerConfig(KafkaConfigProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ProducerFactory<String, BankTransferRequest> transferProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, BankTransferRequest> transferTemplate() {
        return new KafkaTemplate<>(transferProducerFactory());
    }
    @Bean
    public RetryTopicConfiguration retryTopic(KafkaTemplate<String, BankTransferRequest> template) {
        return RetryTopicConfigurationBuilder
                .newInstance()
                .exponentialBackoff(2000, 5, 200000)
                .maxAttempts(properties.getMaxAttempts())
                .retryOn(GenericException.class)
                .create(template);
    }
}
