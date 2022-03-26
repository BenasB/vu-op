package com.loan.business;

import java.util.Arrays;

import com.loan.dto.FilterInputData;
import com.loan.dto.NewInputData;

public abstract class Mortgage {

    private MonthlyPayment[] monthlyPayments;
    private NewInputData startingData;
    private static Mortgage instance;

    public MonthlyPayment[] getMonthlyPayments() {
        return monthlyPayments;
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
        instance = this;
    }

    abstract MonthlyPayment[] generateMonthlyPayments(NewInputData inputData);

    public void filter(FilterInputData inputData) {
        monthlyPayments = Arrays.stream(monthlyPayments).filter(payment -> payment.inInterval(inputData))
                .toArray(MonthlyPayment[]::new);
    }
}
