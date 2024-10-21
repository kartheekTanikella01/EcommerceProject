package com.example.EcommerceBackend.DTO;

import com.example.EcommerceBackend.Entity.User;

import java.util.List;

public class WishlistDto {

    private Integer id;
    private List<ProductDto> productDtos;

    private User userId;

    public WishlistDto() {

    }

    public WishlistDto(Integer id, List<ProductDto> productDtos, User userId) {
        this.id = id;
        this.productDtos = productDtos;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
