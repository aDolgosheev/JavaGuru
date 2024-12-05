package com.dolgosheev.productmicroservice.service;

import com.dolgosheev.productmicroservice.service.dto.CreateProductDTO;

public interface ProductService {

    String createProduct(CreateProductDTO createProductDTO);
}
