package com.dolgosheev.productmicroservice.service;

import com.dolgosheev.productmicroservice.service.dto.CreateProductDTO;
import com.dolgosheev.productmicroservice.service.event.ProductCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDTO createProductDTO) {
        //TODO save DB

        String productID = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productID, createProductDTO.getTitle(),
                createProductDTO.getPrice(), createProductDTO.getQuantity());

        kafkaTemplate.send("product-created-events-topic", productID, productCreatedEvent);

        return productID;
    }
}
