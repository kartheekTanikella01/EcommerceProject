package com.example.EcommerceBackend.DTO;

import com.example.EcommerceBackend.Entity.Address;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {

    private Integer userId;
    private List<OrderItemDto> orderItems;
    private LocalDateTime orderDate;

    private List<OrderItemDto> orderItemDtoList;

    private AddressDTO deliveryAddress;

    public OrderDto() {
    }



    public OrderDto(Integer userId, LocalDateTime orderDate, List<OrderItemDto> orderItemDtoList,AddressDTO deliveryAddress) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderItemDtoList = orderItemDtoList;
        this.deliveryAddress=deliveryAddress;

    }

    public AddressDTO getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(AddressDTO deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

    public void setOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
