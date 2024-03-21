package com.aad.agritech.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aad.agritech.Dto.LoginRequest;
import com.aad.agritech.Dto.UpdatePasswordRequest;
import com.aad.agritech.Model.Users;
import com.aad.agritech.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> updatePassword(UpdatePasswordRequest request) {
        Users user = userRepo.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with email: " + request.getEmail());
        }

        user.setPassword(request.getNewPassword());
        userRepo.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }

    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> userList = userRepo.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    public ResponseEntity<Users> getUserById(Long id) {
        Users user = userRepo.findById(id).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<Users> createUser(Users user) {
        try {
            Users createdUser = userRepo.save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
     public ResponseEntity<Users> login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        Users user = userRepo.findByEmail(email);
        
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

