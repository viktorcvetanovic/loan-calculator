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
@RequestMapping("/simple-loans")
@RequiredArgsConstructor
public class SimpleLoanController {
	private final SimpleLoanService simpleLoanService;

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllSimpleLoans")
	public ResponseEntity<List<SimpleLoan>> getAllSimpleLoans(@RequestParam(name = "q", required = false) Specification<SimpleLoan> specification) {
		return ResponseEntity.ok(simpleLoanService.findAll(specification));
	}

	@GetMapping("/{simpleLoanId}")
	@ApiOperation(value = "", nickname = "getSimpleLoanById")
	public ResponseEntity<SimpleLoan> getSimpleLoanById(@PathVariable Integer simpleLoanId) {
		return ResponseEntity.ok(simpleLoanService.findById(simpleLoanId));
	}

	@PostMapping
	@ApiOperation(value = "", nickname = "saveSimpleLoan")
	public ResponseEntity<SimpleLoan> saveSimpleLoan(@RequestBody SimpleLoan simpleLoan) {
		return ResponseEntity.status(HttpStatus.CREATED).body(simpleLoanService.save(simpleLoan));
	}

	@PutMapping
	@ApiOperation(value = "", nickname = "updateSimpleLoan")
	public ResponseEntity<SimpleLoan> updateSimpleLoan(@RequestBody SimpleLoan simpleLoan) {
		return ResponseEntity.ok(simpleLoanService.update(simpleLoan));
	}

	@DeleteMapping("/{simpleLoanId}")
	@ApiOperation(value = "", nickname = "deleteSimpleLoanById")
	public void deleteSimpleLoanById(@PathVariable Integer simpleLoanId) {
		simpleLoanService.deleteById(simpleLoanId);
	}

}

