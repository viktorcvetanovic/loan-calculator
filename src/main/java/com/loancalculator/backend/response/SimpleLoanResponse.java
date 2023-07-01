package com.loancalculator.backend.response;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableSimpleLoanResponse.class)
@JsonDeserialize(as = ImmutableSimpleLoanResponse.class)
public interface SimpleLoanResponse {

    Double monthlyPayment();

    Double totalInterestPaid();

}
