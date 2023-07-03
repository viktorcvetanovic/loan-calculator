package com.loancalculator.backend.service.impl;

import com.loancalculator.backend.domain.LoanDomain;
import com.loancalculator.backend.entity.*;
import com.loancalculator.backend.repository.SimpleLoanRepository;
import com.loancalculator.backend.request.ImmutableSimpleLoanRequest;
import com.loancalculator.backend.request.SimpleLoanRequest;
import com.loancalculator.backend.response.ImmutableSimpleLoanResponse;
import com.loancalculator.backend.response.SimpleLoanResponse;
import com.loancalculator.backend.service.SimpleLoanService;

import java.util.List;
import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleLoanServiceImpl implements SimpleLoanService {
    private final SimpleLoanRepository simpleLoanRepository;
    private final LoanDomain loanDomain;

    @Override
    public List<SimpleLoan> findAll(Specification<SimpleLoan> specification) {
        return simpleLoanRepository.findAll(specification);
    }

    @Override
    public SimpleLoan findById(Integer simpleLoanId) {
        return simpleLoanRepository.findById(simpleLoanId)
                .orElseThrow(() -> new NoSuchElementException("SimpleLoanService.notFound"));
    }

    @Override
    public SimpleLoan save(SimpleLoan simpleLoan) {
        return simpleLoanRepository.save(simpleLoan);
    }

    @Override
    public SimpleLoan update(SimpleLoan simpleLoan) {
        return simpleLoanRepository.save(simpleLoan);
    }

    @Override
    public void deleteById(Integer simpleLoanId) {
        simpleLoanRepository.deleteById(simpleLoanId);
    }

    @Override
    public SimpleLoanResponse calculateLoan(SimpleLoanRequest loanRequest) {
        Double monthlyPayment = loanDomain.calculateMonthlyPayment(loanRequest.loanAmount(), loanRequest.loanTerm(),
                loanRequest.interestRate(), 0, loanRequest.loanTermType());
        Double totalInterestPaid = loanDomain.calculateInterestAccrued(monthlyPayment, loanRequest.loanAmount(),
                0, loanRequest.loanTerm(), loanRequest.loanTermType());

        SimpleLoan savedSimpleLoan = save(SimpleLoan.fromRequestAndData(loanRequest, monthlyPayment, totalInterestPaid));

        return ImmutableSimpleLoanResponse
                .builder()
                .monthlyPayment(savedSimpleLoan.getMonthlyPayment())
                .totalInterestPaid(savedSimpleLoan.getTotalInterestPaid())
                .build();
    }


}