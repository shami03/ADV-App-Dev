package com.aad.agro.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aad.agro.Model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
@Query("SELECT b.name FROM Bank b WHERE b.id IN :ids")
    List<String> findNamesByIds(@Param("ids") List<Long> ids);
    
}
