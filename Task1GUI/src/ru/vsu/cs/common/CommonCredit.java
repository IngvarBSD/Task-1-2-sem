package ru.vsu.cs.common;

public abstract class CommonCredit {
    public abstract double getSumOfPayForNMonth(int nMonth, double sum, int deadline, double annualInterestRate);
    public abstract double getTotalSumOfPay(double sum, int deadline, double annualInterestRate);
}
