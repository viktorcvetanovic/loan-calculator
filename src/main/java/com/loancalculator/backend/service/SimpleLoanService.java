package com.loancalculator.backend.service;

import com.loancalculator.backend.entity.*;
import java.util.Collection;
import java.util.List;

import com.loancalculator.backend.request.SimpleLoanRequest;
import com.loancalculator.backend.response.SimpleLoanResponse;
import org.springframework.data.jpa.domain.Specification;

public interface SimpleLoanService {

	List<SimpleLoan> findAll(Specification<SimpleLoan> specification);

	SimpleLoan save(SimpleLoan simpleLoan);

	SimpleLoan update(SimpleLoan simpleLoan);

	SimpleLoan findById(Integer simpleLoanId);

	void deleteById(Integer simpleLoanId);

    SimpleLoanResponse calculateLoan(SimpleLoanRequest loanRequest);
}