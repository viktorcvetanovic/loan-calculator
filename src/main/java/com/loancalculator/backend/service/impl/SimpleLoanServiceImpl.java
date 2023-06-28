package com.loancalculator.backend.service.impl;

import com.loancalculator.backend.entity.*;
import com.loancalculator.backend.repository.SimpleLoanRepository;
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


}