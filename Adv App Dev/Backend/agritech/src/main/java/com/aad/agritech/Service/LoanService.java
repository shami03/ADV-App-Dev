package com.aad.agritech.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aad.agritech.Model.Loan;
import com.aad.agritech.Repository.LoanRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan approveLoan(Long loanId) throws Exception {
        Loan loan = loanRepository.findById(loanId)
                                  .orElseThrow(() -> new Exception("Loan not found"));
        if (!loan.getStatus().equals("Approved")) {
            loan.setStatus("Approved");
            loan.setDueAmount(loan.getAmount());
            loanRepository.save(loan);
        }
        return loan;
    }

    public Loan denyLoan(Long loanId) throws Exception {
        Loan loan = loanRepository.findById(loanId)
                                  .orElseThrow(() -> new Exception("Loan not found"));
        if (!loan.getStatus().equals("Denied")) {
            loan.setStatus("Denied");
            loanRepository.save(loan);
        }
        return loan;
    }

    public List<Loan> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long id) {
        return loanRepository.findById(id);
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(Long id, Loan updatedLoan) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    existingLoan.setUserId(updatedLoan.getUserId());
                    existingLoan.setSchemeId(updatedLoan.getSchemeId());
                    existingLoan.setStatus(updatedLoan.getStatus());
                    existingLoan.setFullName(updatedLoan.getFullName());
                    existingLoan.setLoanType(updatedLoan.getLoanType());
                    existingLoan.setAmount(updatedLoan.getAmount());
                    existingLoan.setApplicationDate(updatedLoan.getApplicationDate());
                    existingLoan.setCropType(updatedLoan.getCropType());
                    existingLoan.setLandSize(updatedLoan.getLandSize());
                    existingLoan.setRequiredMachinery(updatedLoan.getRequiredMachinery());
                    existingLoan.setDueAmount(updatedLoan.getDueAmount());
                    return loanRepository.save(existingLoan);
                })
                .orElseThrow(() -> new RuntimeException("Loan not found with id " + id));
    }

    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }
}

