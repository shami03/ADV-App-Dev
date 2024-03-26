package com.aad.agro.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aad.agro.Model.Users;
import com.aad.agro.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

     @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    public ResponseEntity<Users> createUser(Users user) {
        if (existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users createdUser = userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> userList = userRepo.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<Users> getUserById(Long id) {
        Users user = userRepo.findById(id).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }


    public ResponseEntity<Void> deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateUser(Long id, Users user) {
        return userRepo.findById(id)
                .map(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    existingUser.setFilled(user.isFilled());

                    userRepo.save(existingUser);
                    return ResponseEntity.ok("User updated successfully.");
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id));
    }

  
    
   
}

