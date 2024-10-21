package com.example.EcommerceBackend.Service;

import com.example.EcommerceBackend.Entity.User;
import com.example.EcommerceBackend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userRepo.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User updateUser(Integer id, User userDetails) throws Exception {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found"));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setAddresses(userDetails.getAddresses());
        // Update other fields as necessary

        return userRepo.save(user);
    }

    public void deleteUser(Integer id) throws Exception {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new Exception("User with ID " + id + " not found"));

        userRepo.delete(user);
    }
}



