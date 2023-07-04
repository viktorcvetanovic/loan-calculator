package com.loancalculator.backend.entity;

import com.loancalculator.backend.response.ImmutablePaymentResponse;
import com.loancalculator.backend.response.PaymentResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payment")
@RequiredArgsConstructor
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int id;
    @Column(name = "payment_number")
    private int paymentNumber;
    @Column(name = "loan_payment")
    private Double loanPayment;
    @Column(name = "ending_balance")
    private Double endingBalance;
    @Column(name = "principal_applied")
    private Double principalApplied;
    @Column(name = "interest_payment")
    private Double interestPayment;
    @ManyToOne
    @JoinColumn(name = "loan_fk", referencedColumnName = "loan_id")
    private Loan loan;

    public Payment(int paymentNumber, Double loanPayment, Double endingBalance,
                   Double principalApplied, Double interestPayment) {
        this.paymentNumber = paymentNumber;
        this.loanPayment = loanPayment;
        this.endingBalance = endingBalance;
        this.principalApplied = principalApplied;
        this.interestPayment = interestPayment;
    }


    public PaymentResponse toPaymentResponse(){
        return ImmutablePaymentResponse
                .builder()
                .id(id)
                .paymentNumber(paymentNumber)
                .loanPayment(loanPayment)
                .endingBalance(endingBalance)
                .principalApplied(principalApplied)
                .interestPayment(interestPayment)
                .build();
    }
}
