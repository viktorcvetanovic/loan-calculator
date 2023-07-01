package com.loancalculator.backend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payment")
@RequiredArgsConstructor
public class Payment {
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

    public Payment(int paymentNumber, Double loanPayment, Double endingBalance, Double principalApplied, Double interestPayment) {
        this.paymentNumber = paymentNumber;
        this.loanPayment = loanPayment;
        this.endingBalance = endingBalance;
        this.principalApplied = principalApplied;
        this.interestPayment = interestPayment;
    }
}
