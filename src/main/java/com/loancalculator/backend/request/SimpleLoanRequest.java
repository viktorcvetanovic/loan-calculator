package com.loancalculator.backend.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loancalculator.backend.entity.enums.LoanTermType;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSimpleLoanRequest.class)
@JsonDeserialize(as = ImmutableSimpleLoanRequest.class)
public interface SimpleLoanRequest {

    Double loanAmount();

    Integer interestRate();

    Integer loanTerm();

    LoanTermType loanTermType();

}
