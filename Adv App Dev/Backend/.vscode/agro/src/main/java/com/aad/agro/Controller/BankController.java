package com.aad.agro.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aad.agro.Model.Bank;
import com.aad.agro.Service.BankService;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;
    @GetMapping("/banks")
    public List<String> getBankNames(@RequestParam List<Long> ids) {
        return bankService.getBankNames(ids);
    }
    @PostMapping
    public ResponseEntity<String> createBank(@RequestBody Bank bank) {
        bankService.createBank(bank);
        return ResponseEntity.ok("Bank created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bank> getBank(@PathVariable Long id) {
        return bankService.getBank(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBank(@PathVariable Long id, @RequestBody Bank updatedBank) {
        try {
            bankService.updateBank(id, updatedBank);
            return ResponseEntity.ok("Bank updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBank(@PathVariable Long id) {
        if (bankService.getBank(id).isPresent()) {
            bankService.deleteBank(id);
            return ResponseEntity.ok("Bank deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        if (banks.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(banks);
        }
    }
}
