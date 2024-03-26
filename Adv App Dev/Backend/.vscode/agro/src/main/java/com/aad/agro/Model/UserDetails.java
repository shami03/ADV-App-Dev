package com.aad.agro.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
    
    @Id
    
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String aadharNumber;
    private String panNumber;
    private String addressLine1;
    private int age;
    private String gender;
    private String category;
    private double income;
    private Date dob;
    private String coapplicantname;
    private String accNo;
    private String accType;
    private String branch;
    private String ifsc;
}
