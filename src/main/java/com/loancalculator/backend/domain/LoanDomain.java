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
        if (rate == 0 || loan == 0 || term == 0) {
            throw new NullPointerException("Check your parameters. none of these cant be zero");
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
        if (monthlyPayment == 0 || loan == 0 || term == 0) {
            throw new NullPointerException("Check your parameters. none of these cant be zero");
        }

        int termsInMonths = convertTermTime(loanTermType, term);
        loan -= downPay;
        double totalCost = monthlyPayment * termsInMonths;
        return totalCost - loan;
    }

    /**
     * Method used to convert Term if type is YEAR.
     *
     * @param loanTermType type of loan
     * @param term         term for loan
     * @return converted term
     */
    private Integer convertTermTime(LoanTermType loanTermType, int term) {
        return loanTermType == LoanTermType.MONTH ? term : term * 12;
    }

    /**
     * Method used to create a list of payments where is loan amortization calculated.
     * @param loanAmount amount of loan
     * @param interestRate rate of interest
     * @param numberOfPayments number of payments
     * @return created list of payments
     */
    public List<Payment> createLoanAmortizationPaymentList(double loanAmount, int interestRate, int numberOfPayments) {
        if (loanAmount == 0 || interestRate == 0 || numberOfPayments == 0) {
            throw new NullPointerException("Check your parameters. none of these cant be zero");
        }
        List<Payment> amortizationSchedule = new ArrayList<>();
        double monthlyPayment = calculateMonthlyPayment(loanAmount, numberOfPayments,
                interestRate, 0, LoanTermType.MONTH);
        double interest = calculateInterestAccrued(monthlyPayment, loanAmount, 0,
                numberOfPayments, LoanTermType.MONTH);
        double entireLoanAmount = loanAmount + interest;
        // Calculate the amortization schedule
        double balanceOwed = loanAmount;
        for (int month = 1; month <= numberOfPayments; month++) {


            System.out.println("Entire loan amount: " + entireLoanAmount);
            System.out.println("Month: " + month);
            System.out.println("Payment Amount: " + monthlyPayment);
            double monthInterestAmount = (double)interestRate/100/12*balanceOwed;
            double monthPrincipalAmount = monthlyPayment - monthInterestAmount;

            System.out.println("Interest Amount: " + monthInterestAmount);
            System.out.println("Principal amount: " + monthPrincipalAmount);
            System.out.println("Balance owed: " + balanceOwed);
            System.out.println("-----------------------------------");

            Payment payment = new Payment();
            payment.setLoanPayment(monthlyPayment);
            payment.setPaymentNumber(month);
            payment.setPrincipalApplied(monthPrincipalAmount);
            payment.setInterestPayment(monthInterestAmount);
            payment.setEndingBalance(balanceOwed);

            amortizationSchedule.add(payment);

            balanceOwed -=monthPrincipalAmount;


        }

        return amortizationSchedule;
    }

}
