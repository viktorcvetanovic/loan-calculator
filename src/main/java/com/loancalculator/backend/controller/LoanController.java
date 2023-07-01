package com.loancalculator.backend.controller;

import com.loancalculator.backend.request.ImmutableSimpleLoanRequest;
import com.loancalculator.backend.request.LoanRequest;
import com.loancalculator.backend.request.SimpleLoanRequest;
import com.loancalculator.backend.response.LoanResponse;
import com.loancalculator.backend.response.SimpleLoanResponse;
import com.loancalculator.backend.service.*;
import io.swagger.annotations.ApiOperation;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

	private final SimpleLoanService simpleLoanService;
	private final LoanService loanService;

	@PostMapping("/simple")
	@ApiOperation(value = "", nickname = "calculate-simple-loan")
	public ResponseEntity<SimpleLoanResponse> calculateSimpleLoan(@RequestBody SimpleLoanRequest loanRequest) {
		return ResponseEntity.ok(simpleLoanService.calculateLoan(loanRequest));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "calculate-loan")
	public ResponseEntity<LoanResponse> calculateLoan(@RequestBody LoanRequest loanRequest) {
		return ResponseEntity.ok(loanService.calculateLoan(loanRequest));
	}
}

