package com.example.EcommerceBackend.Repository;

import com.example.EcommerceBackend.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByUserId(int userId);
}
