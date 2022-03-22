package com.loan.business;

import com.loan.dto.NewInputData;

public class LinearMortgage extends Mortgage {

  public LinearMortgage(NewInputData inputData) {
    super(inputData);
  }

  @Override
  MonthlyPayment[] generateMonthlyPayments(NewInputData inputData) {
    int months = inputData.years * 12 + inputData.months;
    double principalRepayment = inputData.amount / months;

    MonthlyPayment[] result = new MonthlyPayment[months];

    double principal = inputData.amount;
    for (int month = 1; month <= months; month++) {
      double interestRepayment = principal * inputData.interest;
      double paymentAmount = principalRepayment + interestRepayment;
      result[month - 1] = new MonthlyPayment(month, paymentAmount, principalRepayment, interestRepayment, principal);
      principal -= principalRepayment;
    }

    return result;
  }

}