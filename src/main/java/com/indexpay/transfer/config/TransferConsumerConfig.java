package com.indexpay.transfer.config;

import com.indexpay.transfer.service.dto.BankTransferRequest;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class TransferConsumerConfig {
    private final KafkaConfigProperties properties;

    public TransferConsumerConfig(KafkaConfigProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ConsumerFactory<String, BankTransferRequest> transferConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                properties.getBootstrapServers());
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                properties.getGroupId());
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(BankTransferRequest.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BankTransferRequest>
    transferListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, BankTransferRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(transferConsumerFactory());
        return factory;
    }
}