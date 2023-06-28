package com.loancalculator.backend.controller;

import com.loancalculator.backend.entity.*;
import com.loancalculator.backend.service.*;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {
	private final LoanService loanService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllLoans")
	public ResponseEntity<List<Loan>> getAllLoans(@RequestParam(name = "q", required = false) Specification<Loan> specification) {
		return ResponseEntity.ok(loanService.findAll(specification));
	}

	@GetMapping("/{loanId}")
	@ApiOperation(value = "", nickname = "getLoanById")
	public ResponseEntity<Loan> getLoanById(@PathVariable Integer loanId) {
		return ResponseEntity.ok(loanService.findById(loanId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveLoan")
	public ResponseEntity<Loan> saveLoan(@RequestBody Loan loan) {
		return ResponseEntity.status(HttpStatus.CREATED).body(loanService.save(loan));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateLoan")
	public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan) {
		return ResponseEntity.ok(loanService.update(loan));
	}

	@DeleteMapping("/{loanId}")
	@ApiOperation(value = "", nickname = "deleteLoanById")
	public void deleteLoanById(@PathVariable Integer loanId) {
		loanService.deleteById(loanId);
	}

}

