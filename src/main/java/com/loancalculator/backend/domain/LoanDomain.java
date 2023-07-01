package com.loancalculator.backend.domain;


import com.loancalculator.backend.entity.Payment;
import com.loancalculator.backend.entity.enums.LoanTermType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanDomain {

    /**
     * Returns monthly payment amount on a loan.
     *
     * @param loan    Loan amount
     * @param term    Loan term in years
     * @param rate    Interest rate per year on a loan
     * @param downPay Downpayment on a loan
     * @return Monthly payment on a loan
     */
    public double calculateMonthlyPayment(double loan, int term, double rate,
                                          double downPay, LoanTermType loanTermType) {
        if(rate == 0 || loan == 0 || term == 0){
            throw new RuntimeException("Check your parameters. none of these cant be zero");
        }
        double monthlyRate = (rate / 100.0) / 12;
        int termsInMonths = convertTermTime(loanTermType, term);
        loan -= downPay;
        return (monthlyRate * loan) / (1 - Math.pow((1 + monthlyRate), -termsInMonths));
    }

    /**
     * Returns total interest accrued for the period of a loan.
     *
     * @param loan           Loan amount
     * @param term           Loan term in years
     * @param downPay        Downpayment on a loan
     * @param monthlyPayment Monthly payment on a loan
     * @return Total interest accrued
     */
    public double calculateInterestAccrued(double monthlyPayment, double loan, double downPay,
                                           int term, LoanTermType loanTermType) {
        if(monthlyPayment == 0 || loan == 0 || term == 0){
            throw new RuntimeException("Check your parameters. none of these cant be zero");
        }

        int termsInMonths = convertTermTime(loanTermType, term);
        loan -= downPay;
        double totalCost = monthlyPayment * termsInMonths;
        return totalCost - loan;
    }

    private Integer convertTermTime(LoanTermType loanTermType, int term) {
        return loanTermType == LoanTermType.MONTH ? term : term * 12;
    }


    //TODO: ONLY TO MAKE THIS METHOD TO WORK AS SHOULD
     public List<Payment> createLoanAmortizationPaymentList(double monthlyPayment, double loan, double downPay, int term) {
        List<Payment> amortizationSchedule = new ArrayList<>();

        // Calculate the loan amount after the down payment
        double remainingLoan = loan - downPay;

        // Calculate the monthly interest rate
        double monthlyInterestRate = 0.0; // Assuming annual interest rate is already converted to a monthly rate

        // Calculate the amortization schedule
        for (int month = 1; month <= term; month++) {
            // Calculate the interest for this month
            double interest = remainingLoan * monthlyInterestRate;

            // Calculate the principal amount for this month
            double principal = monthlyPayment - interest;

            // Calculate the remaining balance after this month's payment
            double balance = remainingLoan - principal;

            // Create a Payment object for this month and add it to the amortization schedule list
            Payment payment = new Payment(month, monthlyPayment, balance, principal, interest);
            amortizationSchedule.add(payment);

            // Update the remaining loan amount
            remainingLoan = balance;
        }

        return amortizationSchedule;
    }

}
