package ru.vsu.cs.common;

public class AnnuityCredit extends CommonCredit{

    @Override
    public double getSumOfPayForNMonth(int nMonth, double sum, int deadline, double annualInterestRate) {
        if(nMonth <= deadline && nMonth > 0)
        {
            double sumOfPay;
            double monthlyRate = annualInterestRate / 100 / 12;
            double tempRate = Math.pow(1 + monthlyRate, deadline);
            double annuityRatio = monthlyRate * tempRate / (tempRate - 1);
            sumOfPay = annuityRatio * sum;

            return sumOfPay;
        }

        return 0;
    }

    @Override
    public double getTotalSumOfPay(double sum, int deadline, double annualInterestRate) {
        double totalSumOfPay;
        double monthlyRate = annualInterestRate / 100 / 12;
        double tempRate = Math.pow(1 + monthlyRate, deadline);
        double annuityRatio = monthlyRate * tempRate / (tempRate - 1);
        totalSumOfPay = annuityRatio * sum * deadline;

        return totalSumOfPay;
    }
}
