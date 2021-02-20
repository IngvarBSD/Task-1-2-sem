package ru.vsu.cs.gui;

import ru.vsu.cs.common.Credit;
import ru.vsu.cs.common.PaymentMethods;

public class CreditCalculator {
    private Credit credit;
    private double result;

    public void setCredit(double sum, int deadline, double interestRate, PaymentMethods payMethod) {
        this.credit = new Credit(sum, deadline, interestRate, payMethod);
    }

    public void calculateSumOfPayForNMonth(int nMonth) {
        this.result = credit.getSumOfPayForNMonth(nMonth);
    }

    public void calculateTotalSumOfPay() {
        this.result = credit.getTotalSumOfPay();
    }

    public double getResult() {
        return this.result;
    }

}

