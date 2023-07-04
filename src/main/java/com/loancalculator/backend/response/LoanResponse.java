package com.loancalculator.backend.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loancalculator.backend.entity.Payment;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableLoanResponse.class)
@JsonDeserialize(as = ImmutableLoanResponse.class)
public interface LoanResponse {
    Integer id();

    Double loanAmount();

    Double loanInterest();

    Integer loanNumberOfPayments();

    List<PaymentResponse> paymentList();
}
