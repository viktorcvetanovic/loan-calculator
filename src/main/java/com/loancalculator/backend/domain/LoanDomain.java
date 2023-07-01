package com.loancalculator.backend.domain;


import com.loancalculator.backend.entity.enums.LoanTermType;
import org.springframework.stereotype.Component;

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
        int termsInMonths = convertTermTime(loanTermType, term);
        loan -= downPay;
        double totalCost = monthlyPayment * termsInMonths;
        return totalCost - loan;
    }

    private Integer convertTermTime(LoanTermType loanTermType, int term) {
        return loanTermType == LoanTermType.MONTH ? term : term * 12;
    }

}
