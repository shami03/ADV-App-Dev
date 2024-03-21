package com.aad.agritech.Model;
//c
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long userId; 
    
    private Long schemeId; 
    private Long bankid;
    private String status;
    
    private String fullName;
    
    private String loanType;
    
    private double amount;
    
    private String applicationDate;
    private double monthlyPayment;
    private String cropType;
    
    private String landSize;
    
    private String requiredMachinery;
    
    private double dueAmount; 
}
