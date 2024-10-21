package com.example.EcommerceBackend.Repository;

import com.example.EcommerceBackend.Entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<WishList,Integer> {
    Optional<WishList> findByUserId(Integer userId);
}
