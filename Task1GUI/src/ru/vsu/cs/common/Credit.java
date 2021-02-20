package ru.vsu.cs.common;
public class Credit {
    private double sum;
    private int deadline;
    private double annualInterestRate;
    private PaymentMethods payMethod;

    public Credit(double sum, int deadline, double annualInterestRate, PaymentMethods payMethod) {
        this.sum = sum;
        this.deadline = deadline;
        this.annualInterestRate = annualInterestRate;
        this.payMethod = payMethod;
    }

    public double getSumOfPayForNMonth(int nMonth) {
        double sumOfPay;

        if(nMonth <= deadline && nMonth > 0) {
            if (payMethod == PaymentMethods.differentiatedPayments) {
                int currentMont = 0;
                double restOfAgedDebt = sum;
                double partOfDuty = sum / deadline;
                double amountOfInterest;
                do {
                    amountOfInterest = restOfAgedDebt * annualInterestRate / 100 / 12;
                    restOfAgedDebt = restOfAgedDebt - partOfDuty;
                    currentMont++;
                } while (currentMont < nMonth);
                sumOfPay = partOfDuty + amountOfInterest;

                return sumOfPay;
            }
            if (payMethod == PaymentMethods.annuityPayments) {
                double monthlyRate = annualInterestRate / 100 / 12;
                double tempRate = Math.pow(1 + monthlyRate, deadline);
                double annuityRatio = monthlyRate * tempRate / (tempRate - 1);
                sumOfPay = annuityRatio * sum;

                return sumOfPay;
            }
        }

        return 0;
    }

    public double getTotalSumOfPay() {
        double totalSumOfPay;

        if (payMethod == PaymentMethods.differentiatedPayments) {
            int currentMont = 0;
            double restOfAgedDebt = sum;
            double partOfDuty = sum / deadline;
            double amountOfInterest;
            totalSumOfPay = 0;
            do {
                amountOfInterest = restOfAgedDebt * annualInterestRate / 100 / 12;
                restOfAgedDebt = restOfAgedDebt - partOfDuty;
                currentMont++;
                totalSumOfPay = totalSumOfPay + partOfDuty + amountOfInterest;
            } while (currentMont < deadline);

            return totalSumOfPay;
        }
        if (payMethod == PaymentMethods.annuityPayments) {
            double monthlyRate = annualInterestRate / 100 / 12;
            double tempRate = Math.pow(1 + monthlyRate, deadline);
            double annuityRatio = monthlyRate * tempRate / (tempRate - 1);
            totalSumOfPay = annuityRatio * sum * deadline;

            return totalSumOfPay;
        }

        return 0;
    }
}
