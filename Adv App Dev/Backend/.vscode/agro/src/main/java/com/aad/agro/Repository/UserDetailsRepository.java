package com.aad.agro.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aad.agro.Model.UserDetails;
import com.aad.agro.Model.Users;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<Users> findByEmail(String email);
    
}
