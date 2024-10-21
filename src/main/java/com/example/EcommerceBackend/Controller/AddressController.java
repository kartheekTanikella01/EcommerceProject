package com.example.EcommerceBackend.Controller;

import com.example.EcommerceBackend.Entity.Address;
import com.example.EcommerceBackend.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<Address> addAddress(@PathVariable int userId, @RequestBody Address address) {
        try {
            Address newAddress = addressService.addAddressToUser(userId, address);
            return ResponseEntity.ok(newAddress);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getAddressesForUser(@PathVariable int userId) {
        try {
            List<Address> addresses = addressService.getAddressesForUser(userId);
            return ResponseEntity.ok(addresses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
