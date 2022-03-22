package com.loan.business;

import com.loan.dto.NewInputData;

public class AnnuityMortgage extends Mortgage {

  public AnnuityMortgage(NewInputData inputData) {
    super(inputData);
  }

  @Override
  MonthlyPayment[] generateMonthlyPayments(NewInputData inputData) {
    int months = inputData.years * 12 + inputData.months;
    double coefficient = (inputData.interest * Math.pow((1 + inputData.interest), months))
        / (Math.pow(1 + inputData.interest, months) - 1);
    double paymentAmount = coefficient * inputData.amount;

    MonthlyPayment[] result = new MonthlyPayment[months];

    double principal = inputData.amount;
    for (int month = 1; month <= months; month++) {
      double interestRepayment = principal * inputData.interest;
      double principalRepayment = paymentAmount - interestRepayment;
      result[month - 1] = new MonthlyPayment(month, paymentAmount, principalRepayment, interestRepayment, principal);
      principal -= principalRepayment;
    }

    return result;
  }
}