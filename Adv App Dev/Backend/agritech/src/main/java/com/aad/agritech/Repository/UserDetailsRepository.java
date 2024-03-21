package com.aad.agritech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aad.agritech.Model.UserDetails;
//c
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    
}
