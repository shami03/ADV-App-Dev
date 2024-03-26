package com.aad.agro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aad.agro.Model.UserDetails;
import com.aad.agro.Model.Users;
import com.aad.agro.Service.UserDetailsServices;

import java.util.List;

@RestController
@RequestMapping("/api/userdetails")
public class UserDetailsController {

    private final UserDetailsServices userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsServices userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
 @GetMapping("/getbyEmail/{email}")
        public Users getMethodName(@RequestParam String email) {
            return userDetailsService.getByEmail(email);
        }
    @GetMapping
    public ResponseEntity<List<UserDetails>> getAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsService.getAllUserDetails();
        return new ResponseEntity<>(userDetailsList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserDetailsById(@PathVariable Long id) {
        UserDetails userDetails = userDetailsService.getUserDetailsById(id);
        if (userDetails != null) {
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDetails> createUserDetails(@RequestBody UserDetails userDetails) {
        UserDetails newUserDetails = userDetailsService.createUserDetails(userDetails);
        return new ResponseEntity<>(newUserDetails, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetails> updateUserDetails(@PathVariable Long id, @RequestBody UserDetails userDetails) {
        UserDetails updatedUserDetails = userDetailsService.updateUserDetails(id, userDetails);
        if (updatedUserDetails != null) {
            return new ResponseEntity<>(updatedUserDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserDetails(@PathVariable Long id) {
        boolean deleted = userDetailsService.deleteUserDetails(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
