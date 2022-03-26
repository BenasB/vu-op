package com.loan.business;

import com.loan.dto.NewInputData;

public class AnnuityMortgage extends Mortgage {

  public AnnuityMortgage(NewInputData inputData) {
    super(inputData);
  }

  @Override
  MonthlyPayment[] generateMonthlyPayments(NewInputData inputData) {
    int months = inputData.years * 12 + inputData.months;
    double monthlyInterest = inputData.yearlyInterest / 12;
    double coefficient = (monthlyInterest * Math.pow((1 + monthlyInterest), months))
        / (Math.pow(1 + monthlyInterest, months) - 1);
    double paymentAmount = coefficient * inputData.amount;

    MonthlyPayment[] result = new MonthlyPayment[months];

    double principal = inputData.amount;
    for (int month = 1; month <= months; month++) {
      double interestRepayment = principal * monthlyInterest;
      double principalRepayment = paymentAmount - interestRepayment;
      result[month - 1] = new MonthlyPayment(month, paymentAmount, principalRepayment, interestRepayment, principal);
      principal -= principalRepayment;
    }

    return result;
  }
}