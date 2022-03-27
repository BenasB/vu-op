package com.loan.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.loan.dto.DeferInputData;
import com.loan.dto.FilterInputData;
import com.loan.dto.NewInputData;

public abstract class Mortgage {

    private MonthlyPayment[] displayPayments;
    private MonthlyPayment[] monthlyPayments;
    private NewInputData startingData;
    private static Mortgage instance;

    public MonthlyPayment[] getDisplayPayments() {
        return displayPayments;
    }

    public static Mortgage getInstance() {
        return instance;
    }

    public NewInputData getStartingData() {
        return startingData;
    }

    Mortgage(NewInputData inputData) {
        startingData = inputData;
        monthlyPayments = generateMonthlyPayments(inputData);
        displayPayments = generateMonthlyPayments(inputData);
        instance = this;
    }

    abstract MonthlyPayment[] generateMonthlyPayments(NewInputData inputData);

    public void filter(FilterInputData inputData) {
        displayPayments = Arrays.stream(monthlyPayments).filter(payment -> payment.inInterval(inputData))
                .toArray(MonthlyPayment[]::new);
    }

    public void defer(DeferInputData inputData) {
        int startingMonth = inputData.fromYears * 12 + inputData.fromMonths;
        int durationInMonths = inputData.durationYears * 12 + inputData.durationMonths;
        MonthlyPayment startingPayment = monthlyPayments[startingMonth];
        double interestRepayment = startingPayment.getPrincipal() * inputData.yearlyInterest / (12 * 100);
        List<MonthlyPayment> list = new ArrayList<>(Arrays.asList(monthlyPayments));

        for (int i = durationInMonths; i > 0; i--) {
            list.add(startingMonth, new MonthlyPayment(startingMonth + i, interestRepayment, 0,
                    interestRepayment, startingPayment.getPrincipal()));
        }

        monthlyPayments = list.toArray(monthlyPayments);

        for (int i = startingMonth + durationInMonths; i < monthlyPayments.length; i++) {
            monthlyPayments[i].setMonth(i + 1);
        }

        displayPayments = monthlyPayments.clone();
    }
}
