package com.example.EcommerceBackend.Repository;

import com.example.EcommerceBackend.Entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepo extends JpaRepository<OrderItems,Integer> {

}
