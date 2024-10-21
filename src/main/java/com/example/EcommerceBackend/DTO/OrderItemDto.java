package com.example.EcommerceBackend.DTO;

public class OrderItemDto {
    private Integer productId;
    private String productName;
    private double price;
    private int quantity;

    public OrderItemDto() {
    }

    public OrderItemDto(Integer id, String name, double price, int quantity) {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
