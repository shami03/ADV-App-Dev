package com.aad.agro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aad.agro.Model.Bank;
import com.aad.agro.Repository.BankRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public Bank createBank(Bank bank) {
        return bankRepository.save(bank);
    }
    public List<String> getBankNames(List<Long> ids) {
        return bankRepository.findNamesByIds(ids);
    }
    public Optional<Bank> getBank(Long id) {
        return bankRepository.findById(id);
    }

    public Bank updateBank(Long id, Bank updatedBank) {
        return bankRepository.findById(id)
                .map(bank -> {
                    bank.setName(updatedBank.getName());
                    bank.setUsername(updatedBank.getUsername());
                    bank.setPassword(updatedBank.getPassword());
                    return bankRepository.save(bank);
                })
                .orElseThrow(() -> new RuntimeException("Bank not found with id " + id));
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
}
