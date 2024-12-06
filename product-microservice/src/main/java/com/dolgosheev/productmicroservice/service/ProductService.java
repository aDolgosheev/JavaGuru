package com.dolgosheev.productmicroservice.service;

import com.dolgosheev.productmicroservice.service.dto.CreateProductDTO;

import java.util.concurrent.ExecutionException;

public interface ProductService {

    String createProduct(CreateProductDTO createProductDTO) throws ExecutionException, InterruptedException;
}
