package com.loancalculator.backend.domain;

import com.loancalculator.backend.entity.Payment;
import com.loancalculator.backend.entity.enums.LoanTermType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanDomainTest {


    // Assuming the class containing the calculateMonthlyPayment method is named LoanCalculator
    private LoanDomain loanCalculator = new LoanDomain();

    // Test case for a regular loan with valid inputs
    @Test
    void testCalculateMonthlyPaymentRegularLoan() {
        double loanAmount = 10000.0;
        int loanTerm = 12;
        double interestRate = 5.0;
        double downPayment = 2000.0;
        LoanTermType loanTermType = LoanTermType.MONTH;

        double expectedMonthlyPayment = 684.85;
        double calculatedMonthlyPayment = loanCalculator.calculateMonthlyPayment(loanAmount, loanTerm, interestRate, downPayment, loanTermType);
        assertEquals(expectedMonthlyPayment, calculatedMonthlyPayment, 0.1);
    }

    // Test case for zero rate
    @Test
    void testCalculateMonthlyPaymentZeroRate() {
        double loanAmount = 10000.0;
        int loanTerm = 12;
        double interestRate = 0.0;
        double downPayment = 2000.0;
        LoanTermType loanTermType = LoanTermType.MONTH;

        assertThrows(NullPointerException.class, () -> loanCalculator.calculateMonthlyPayment(loanAmount, loanTerm, interestRate, downPayment, loanTermType));
    }

    // Test case for zero loan amount
    @Test
    void testCalculateMonthlyPaymentZeroLoanAmount() {
        double loanAmount = 0.0;
        int loanTerm = 12;
        double interestRate = 5.0;
        double downPayment = 2000.0;
        LoanTermType loanTermType = LoanTermType.MONTH;

        assertThrows(NullPointerException.class, () -> loanCalculator.calculateMonthlyPayment(loanAmount, loanTerm, interestRate, downPayment, loanTermType));
    }

    // Test case for zero loan term
    @Test
    void testCalculateMonthlyPaymentZeroLoanTerm() {
        double loanAmount = 10000.0;
        int loanTerm = 0;
        double interestRate = 5.0;
        double downPayment = 2000.0;
        LoanTermType loanTermType = LoanTermType.MONTH;

        assertThrows(NullPointerException.class, () -> loanCalculator.calculateMonthlyPayment(loanAmount, loanTerm, interestRate, downPayment, loanTermType));
    }



    @Test
    void testCreateLoanAmortizationPaymentList() {
        double loanAmount = 10000.0;
        int interestRate = 5;
        int numberOfPayments = 12;

        List<Payment> amortizationSchedule = loanCalculator.createLoanAmortizationPaymentList(loanAmount, interestRate, numberOfPayments);

        // Check if the amortization schedule has the expected number of payments
        assertEquals(numberOfPayments, amortizationSchedule.size());

        // Check if the loan payments and interest payments are greater than zero
        for (Payment payment : amortizationSchedule) {
            assertTrue(payment.getLoanPayment() > 0);
            assertTrue(payment.getInterestPayment() > 0);
            assertTrue(payment.getPrincipalApplied() >= 0);
            assertTrue(payment.getEndingBalance() >= 0);
        }
    }

    @Test
    void testCreateLoanAmortizationPaymentListWithZeroLoanAmount() {
        double loanAmount = 0.0;
        int interestRate = 5;
        int numberOfPayments = 12;

        // The method should throw an exception when the loan amount is zero
        assertThrows(NullPointerException.class, () -> loanCalculator.createLoanAmortizationPaymentList(loanAmount, interestRate, numberOfPayments));
    }

}