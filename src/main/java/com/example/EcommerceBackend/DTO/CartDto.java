package com.example.EcommerceBackend.DTO;



import com.example.EcommerceBackend.Entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private Integer userid;

    private List<ProductDto> product=new ArrayList<>();

    public CartDto() {
    }

    public CartDto(Integer userid) {
        this.userid = userid;
    }

    public CartDto(List<ProductDto> product) {

        this.product = product;
    }

    public CartDto(int userId, List<Product> products) {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public List<ProductDto> getProduct() {
        return product;
    }

    public void setProduct(List<ProductDto> product) {
        this.product = product;
    }
}
