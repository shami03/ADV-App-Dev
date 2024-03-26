package com.aad.agro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aad.agro.Model.BankScheme;
import com.aad.agro.Service.BankSchemeService;

import java.util.List;

@RestController
@RequestMapping("/bank-schemes")
public class BankSchemeController {

    @Autowired
    private BankSchemeService bankSchemeService;

    @PostMapping
    public ResponseEntity<BankScheme> createBankScheme(@RequestBody BankScheme bankScheme) {
        BankScheme createdBankScheme = bankSchemeService.createBankScheme(bankScheme);
        return ResponseEntity.ok(createdBankScheme);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankScheme> getBankScheme(@PathVariable Long id) {
        return bankSchemeService.getBankScheme(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankScheme> updateBankScheme(@PathVariable Long id, @RequestBody BankScheme updatedBankScheme) {
        try {
            BankScheme bankScheme = bankSchemeService.updateBankScheme(id, updatedBankScheme);
            return ResponseEntity.ok(bankScheme);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankScheme(@PathVariable Long id) {
        if (bankSchemeService.getBankScheme(id).isPresent()) {
            bankSchemeService.deleteBankScheme(id);
            return ResponseEntity.ok("Bank Scheme deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BankScheme>> getAllBankSchemes() {
        List<BankScheme> bankSchemes = bankSchemeService.getAllBankSchemes();
        if (bankSchemes.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(bankSchemes);
        }
    }
}

