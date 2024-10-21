package com.example.EcommerceBackend.Service;

import com.example.EcommerceBackend.DTO.AddressDTO;
import com.example.EcommerceBackend.DTO.OrderDto;
import com.example.EcommerceBackend.DTO.OrderItemDto;
import com.example.EcommerceBackend.Entity.*;
import com.example.EcommerceBackend.Repository.AddressRepository;
import com.example.EcommerceBackend.Repository.CartRepository;
import com.example.EcommerceBackend.Repository.OrderRepo;
import com.example.EcommerceBackend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CartRepository cartRepository;
   @Autowired
    private ProductRepository productRepository;

   @Autowired
   private AddressRepository addressRepository;

   @Transactional
    public OrderDto checkoutCart(Integer userId,Integer addressId) throws Exception {
        // Fetch user's cart
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception("Cart not found for user ID: " + userId));

       Address address = addressRepository.findById(addressId)
               .orElseThrow(() -> new Exception("Address not found"));

        if (cart.getProducts().isEmpty()) {
            throw new Exception("Cart is empty. Cannot proceed with checkout.");
        }
        // Create a new order
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setDeliveryAddress(address);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItems> orderItems = new ArrayList<>();
        for (Product product : cart.getProducts()) {
            OrderItems orderItem = new OrderItems();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setPrice(product.getPrice());
            orderItem.setQuantity(1); // For simplicity, assume 1 quantity per product
            orderItems.add(orderItem);

        }
        order.setOrderItems(orderItems);

        orderRepo.save(order);

        // Clear the cart after checkout
        cart.getProducts().clear();
        cartRepository.save(cart);

        return convertOrderToDto(order);
    }

    // to get the order list
    public List<OrderDto> getALlOrders(){
       List<Order> orders=orderRepo.findAll();
       return orders.stream()
               .map(this::convertOrderToDto)
               .collect(Collectors.toList());
    }




    // convert Order entity to OrderDto
    private OrderDto convertOrderToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUser().getId());
        orderDto.setOrderDate(order.getOrderDate());

        //Set Address
        AddressDTO addressDTO=new AddressDTO();
        addressDTO.setCity(order.getDeliveryAddress().getCity());
        addressDTO.setStreet(order.getDeliveryAddress().getStreet());
        addressDTO.setCountry(order.getDeliveryAddress().getCountry());
        addressDTO.setZipCode(order.getDeliveryAddress().getZipCode());
        orderDto.setDeliveryAddress(addressDTO);




        List<OrderItemDto> orderItemDtos = order.getOrderItems().stream()
                .map(orderItem -> new OrderItemDto(
                        orderItem.getProduct().getId(),
                        orderItem.getProduct().getName(),
                        orderItem.getPrice(),
                        orderItem.getQuantity()))
                .collect(Collectors.toList());
        orderDto.setOrderItems(orderItemDtos);

        return orderDto;
    }

}
