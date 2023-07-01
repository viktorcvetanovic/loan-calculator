package com.loancalculator.backend.service;

import com.loancalculator.backend.entity.*;
import java.util.Collection;
import java.util.List;

import com.loancalculator.backend.request.LoanRequest;
import com.loancalculator.backend.response.LoanResponse;
import org.springframework.data.jpa.domain.Specification;

public interface LoanService {

	List<Loan> findAll(Specification<Loan> specification);

	Loan save(Loan loan);

	Loan update(Loan loan);

	Loan findById(Integer loanId);

	void deleteById(Integer loanId);

    LoanResponse calculateLoan(LoanRequest loanRequest);
}