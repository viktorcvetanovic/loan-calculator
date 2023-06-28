package com.loancalculator.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "loan")
@RequiredArgsConstructor
public class Loan implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "loan_id")
	private Integer id;
	@Column(name = "loan_amount")
	private Double loanAmount;
	@Column(name = "loan_interest")
	private Double loanInterest;
	@Column(name = "loan_number_of_payments")
	private Integer loanNumberOfPayments;
	@Column(name = "loan_payment_frequency")
	private String loanPaymentFrequency;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Loan loan = (Loan) o;
		return id.equals(loan.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}