package com.example.EcommerceBackend.Controller;

import com.example.EcommerceBackend.DTO.OrderDto;
import com.example.EcommerceBackend.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    // checking out cart and creating an order
    @PostMapping("/user/{userId}/{addressId}")
    public ResponseEntity<OrderDto> checkout(@PathVariable("userId") Integer userId,@PathVariable("addressId") Integer addressId) {
        try {
            OrderDto orderDto = orderService.checkoutCart(userId,addressId);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orderDtos=orderService.getALlOrders();
        return ResponseEntity.ok(orderDtos);
    }
}
