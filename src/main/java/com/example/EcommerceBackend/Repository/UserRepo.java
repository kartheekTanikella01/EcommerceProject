package com.example.EcommerceBackend.Repository;

import com.example.EcommerceBackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer > {

}
