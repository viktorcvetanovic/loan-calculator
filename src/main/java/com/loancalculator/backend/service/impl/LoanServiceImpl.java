package com.loancalculator.backend.service.impl;

import com.loancalculator.backend.entity.*;
import com.loancalculator.backend.repository.LoanRepository;
import com.loancalculator.backend.service.LoanService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
	private final LoanRepository loanRepository;

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


}