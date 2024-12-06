package com.dolgosheev.productmicroservice.service;

import com.dolgosheev.productmicroservice.service.dto.CreateProductDTO;
import com.dolgosheev.productmicroservice.service.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDTO createProductDTO) throws ExecutionException, InterruptedException {
        //TODO save DB

        String productID = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productID, createProductDTO.getTitle(),
                createProductDTO.getPrice(), createProductDTO.getQuantity());

        SendResult<String, ProductCreatedEvent> result =
                kafkaTemplate.send("product-created-events-topic", productID, productCreatedEvent).get();

//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
//                kafkaTemplate.send("product-created-events-topic", productID, productCreatedEvent);

//        future.whenComplete((result, exception) -> {
//           if (exception != null) {
//                LOGGER.error("Failed to send message: {}", exception.getMessage());
//           } else {
//               LOGGER.info("Message sent successfully: {}", result.getRecordMetadata());
//           }
//        });

//        future.join();

        LOGGER.info("Topic: {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition: {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset: {}", result.getRecordMetadata().offset());

        LOGGER.info("Return: {}", productID);

        return productID;
    }
}
