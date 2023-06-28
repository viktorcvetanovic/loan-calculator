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
	private String simpleLoanTermType;
	
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


}