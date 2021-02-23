package ru.vsu.cs.gui;

import ru.vsu.cs.common.*;

public class CreditCalculator {
    private CommonCredit credit;
    private double result;

    public void setCredit(PaymentMethod paymentMethod) {
        if(paymentMethod == PaymentMethod.DIFFERENTIATED_PAYMENT)
        this.credit = new DifferentiatedCredit();

        if(paymentMethod == PaymentMethod.ANNUITY_PAYMENT)
        this.credit = new AnnuityCredit();
    }

    public void calculateSumOfPayForNMonth(int nMonth, double sum, int deadline, double annualInterestRate) {
        this.result = credit.getSumOfPayForNMonth(nMonth, sum, deadline, annualInterestRate);
    }

    public void calculateTotalSumOfPay(double sum, int deadline, double annualInterestRate) {
        this.result = credit.getTotalSumOfPay(sum, deadline, annualInterestRate);
    }

    public double getResult() {
        return this.result;
    }

}

