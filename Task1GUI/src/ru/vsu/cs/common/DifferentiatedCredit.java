package ru.vsu.cs.common;

public class DifferentiatedCredit extends CommonCredit{

    @Override
    public double getSumOfPayForNMonth(int nMonth, double sum, int deadline, double annualInterestRate) {
        if(nMonth <= deadline && nMonth > 0) {
            double sumOfPay;
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

        return 0;
    }

    @Override
    public double getTotalSumOfPay(double sum, int deadline, double annualInterestRate) {
        double totalSumOfPay;
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
}
