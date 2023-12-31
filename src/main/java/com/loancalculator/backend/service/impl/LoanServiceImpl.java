package com.loancalculator.backend.service.impl;

import com.loancalculator.backend.domain.LoanDomain;
import com.loancalculator.backend.entity.*;
import com.loancalculator.backend.entity.enums.LoanTermType;
import com.loancalculator.backend.repository.LoanRepository;
import com.loancalculator.backend.repository.PaymentRepository;
import com.loancalculator.backend.request.LoanRequest;
import com.loancalculator.backend.response.ImmutableLoanResponse;
import com.loancalculator.backend.response.LoanResponse;
import com.loancalculator.backend.response.PaymentResponse;
import com.loancalculator.backend.service.LoanService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final LoanDomain loanDomain;
    private final PaymentRepository paymentRepository;

    @Override
    public List<Loan> findAll(Specification<Loan> specification) {
        return loanRepository.findAll(specification);
    }

    @Override
    public Loan findById(Integer loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new NoSuchElementException("LoanService.notFound"));
    }

    @Override
    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan update(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public void deleteById(Integer loanId) {
        loanRepository.deleteById(loanId);
    }

    @Override
    public LoanResponse calculateLoan(LoanRequest loanRequest) {
       List<Payment> amortizationList = loanDomain.
                createLoanAmortizationPaymentList(loanRequest.loanAmount(),loanRequest.loanTerm(),
                        loanRequest.interestRate());

        Loan savedLoan = save(Loan.from(loanRequest));
        amortizationList.forEach(e-> e.setLoan(savedLoan));
        List<PaymentResponse> paymentResponseList =  paymentRepository.saveAll(amortizationList)
                .stream()
                .map(Payment::toPaymentResponse)
                .collect(Collectors.toList());
        return ImmutableLoanResponse
                .builder()
                .loanAmount(savedLoan.getLoanAmount())
                .addAllPaymentList(paymentResponseList)
                .id(savedLoan.getId())
                .loanInterest(savedLoan.getLoanInterest())
                .loanNumberOfPayments(savedLoan.getLoanNumberOfPayments())
                .build();
    }


}