package com.aad.agro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aad.agro.Model.BankScheme;
import com.aad.agro.Repository.BankSchemeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BankSchemeService {

    @Autowired
    private BankSchemeRepository bankSchemeRepository;

    public BankScheme createBankScheme(BankScheme bankScheme) {
        return bankSchemeRepository.save(bankScheme);
    }

    public Optional<BankScheme> getBankScheme(Long id) {
        return bankSchemeRepository.findById(id);
    }

    public BankScheme updateBankScheme(Long id, BankScheme updatedBankScheme) {
        return bankSchemeRepository.findById(id)
                .map(bankScheme -> {
                    bankScheme.setSchemeName(updatedBankScheme.getSchemeName());
                    bankScheme.setBankid(updatedBankScheme.getBankid());
                    bankScheme.setInterestRate(updatedBankScheme.getInterestRate());
                    bankScheme.setMaximumLoanAmount(updatedBankScheme.getMaximumLoanAmount());
                    bankScheme.setDescription(updatedBankScheme.getDescription());
                    bankScheme.setEligibilityCriteria(updatedBankScheme.getEligibilityCriteria());
                    return bankSchemeRepository.save(bankScheme);
                })
                .orElseThrow(() -> new RuntimeException("Bank Scheme not found with id " + id));
    }

    public void deleteBankScheme(Long id) {
        bankSchemeRepository.deleteById(id);
    }

    public List<BankScheme> getAllBankSchemes() {
        return bankSchemeRepository.findAll();
    }
}

