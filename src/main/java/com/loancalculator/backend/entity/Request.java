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
@Table(name = "request")
@RequiredArgsConstructor
public class Request implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Integer id;
	@Column(name = "request_time")
	private String requestTime;
	@ManyToOne
	@JoinColumn(name = "fk_simple_loan", referencedColumnName = "simple_loan_id")
	private SimpleLoan fkSimpleLoan;
	@ManyToOne
	@JoinColumn(name = "fk_loan", referencedColumnName = "loan_id")
	private Loan fkLoan;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Request request = (Request) o;
		return id.equals(request.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


}