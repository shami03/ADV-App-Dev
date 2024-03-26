package com.aad.agro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aad.agro.Model.UserDetails;
import com.aad.agro.Model.Users;
import com.aad.agro.Repository.UserDetailsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServices {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsServices(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepository.findAll();
    }

    public UserDetails getUserDetailsById(Long id) {
        return userDetailsRepository.findById(id).orElse(null);
    }

    public UserDetails createUserDetails(UserDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails updateUserDetails(Long id, UserDetails userDetails) {
        if (userDetailsRepository.existsById(id)) {
            userDetails.setId(id); 
            return userDetailsRepository.save(userDetails);
        } else {
            return null;
        }
    }

    public boolean deleteUserDetails(Long id) {
        if (userDetailsRepository.existsById(id)) {
            userDetailsRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
      public Users getByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
                Optional<Users> user = userDetailsRepository.findByEmail(email);
        
        if (user == null) {
            throw new IllegalArgumentException("User with email " + email + " not found");
        }
        
        return user.get();
    }
}
