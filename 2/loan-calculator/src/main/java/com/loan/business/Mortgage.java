package com.loan.business;

import java.util.Arrays;

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
}
