package com.example.EcommerceBackend.Service;

import com.example.EcommerceBackend.Entity.Address;
import com.example.EcommerceBackend.Entity.User;
import com.example.EcommerceBackend.Repository.AddressRepository;
import com.example.EcommerceBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepo userRepo;


    public Address addAddressToUser(int userId, Address address) throws Exception {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new Exception("User not found with ID: " + userId));
        address.setUser(user);
        return addressRepository.save(address);
    }

    public List<Address> getAddressesForUser(int userId) throws Exception {
        return addressRepository.findByUserId(userId);
    }

    public Address getAddressById(int addressId) throws Exception {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new Exception("Address not found with ID: " + addressId));
    }
}
