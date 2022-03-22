package com.loan.business;

import java.util.Arrays;

import com.loan.dto.FilterInputData;
import com.loan.dto.NewInputData;

public abstract class Mortgage {

    private MonthlyPayment[] monthlyPayments;

    public MonthlyPayment[] getMonthlyPayments() {
        return monthlyPayments;
    }

    Mortgage(NewInputData inputData) {
        monthlyPayments = generateMonthlyPayments(inputData);
    }

    abstract MonthlyPayment[] generateMonthlyPayments(NewInputData inputData);

    public void filter(FilterInputData inputData) {
        monthlyPayments = Arrays.stream(monthlyPayments).filter(payment -> payment.inInterval(inputData))
                .toArray(MonthlyPayment[]::new);
    }
}
