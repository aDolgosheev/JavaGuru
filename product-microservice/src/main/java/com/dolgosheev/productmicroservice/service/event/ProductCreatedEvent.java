package com.dolgosheev.productmicroservice.service.event;

import java.math.BigDecimal;

public class ProductCreatedEvent {

    private String productID;

    private String title;

    private BigDecimal price;

    private Integer quantity;

    public ProductCreatedEvent() {
    }

    public ProductCreatedEvent(String productID, String title, BigDecimal price, Integer quantity) {
        this.productID = productID;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
