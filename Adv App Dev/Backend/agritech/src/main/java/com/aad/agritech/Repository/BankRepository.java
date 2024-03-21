package com.aad.agritech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aad.agritech.Model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    
}
