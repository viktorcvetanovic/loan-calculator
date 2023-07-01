package com.loancalculator.backend.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableLoanRequest.class)
@JsonDeserialize(as = ImmutableLoanRequest.class)
public interface LoanRequest {
    Double loanAmount();

    Integer interestRate();

    Integer loanTerm();

}
