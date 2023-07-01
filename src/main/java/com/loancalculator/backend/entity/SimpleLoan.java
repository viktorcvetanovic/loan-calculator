package com.loancalculator.backend.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

import com.loancalculator.backend.entity.enums.LoanTermType;
import com.loancalculator.backend.request.SimpleLoanRequest;
import com.loancalculator.backend.response.SimpleLoanResponse;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "simple_loan")
public class SimpleLoan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "simple_loan_id")
	private Integer id;
	@Column(name = "simple_loan_amount")
	private Double simpleLoanAmount;
	@Column(name = "simple_loan_interest")
	private Integer simpleLoanInterest;
	@Column(name = "simple_loan_term")
	private Integer simpleLoanTerm;
	@Column(name = "simple_loan_term_type")
	private LoanTermType simpleLoanTermType;
	@Column(name = "monthly_payment")
	private Double monthlyPayment;
	@Column(name = "total_interest_paid")
	private Double totalInterestPaid;

	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SimpleLoan simpleLoan = (SimpleLoan) o;
		return id.equals(simpleLoan.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public static SimpleLoan fromRequestAndResponse(SimpleLoanRequest request,
													SimpleLoanResponse simpleLoanResponse){
		SimpleLoan simpleLoan = new SimpleLoan();
		simpleLoan.simpleLoanAmount = request.loanAmount();
		simpleLoan.simpleLoanInterest = request.interestRate();
		simpleLoan.simpleLoanTerm = request.loanTerm();
		simpleLoan.simpleLoanTermType = request.loanTermType();
		simpleLoan.monthlyPayment = simpleLoanResponse.monthlyPayment();
		simpleLoan.totalInterestPaid = simpleLoanResponse.totalInterestPaid();
		return simpleLoan;
	}
}