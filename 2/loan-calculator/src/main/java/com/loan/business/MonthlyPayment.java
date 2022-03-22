package com.loan.business;

import com.loan.dto.FilterInputData;

class MonthlyPayment {
  private int month;
  private double paymentAmount, principalRepayment, interestRepayment, principal;

  public int getMonth() {
    return month;
  };

  public double getMonthlyPayment() {
    return paymentAmount;
  };

  public double getPrincipalRepayment() {
    return principalRepayment;
  };

  public double getInterestRepayment() {
    return interestRepayment;
  };

  public double getPrincipal() {
    return principal;
  };

  MonthlyPayment(int month, double monthlyPayment, double principalRepayment, double interestRepayment,
      double principal) {
    this.month = month;
    this.paymentAmount = monthlyPayment;
    this.principalRepayment = principalRepayment;
    this.interestRepayment = interestRepayment;
    this.principal = principal;
  }

  boolean inInterval(FilterInputData inputData) {
    return (month >= (inputData.fromYears * 12 + inputData.fromMonths))
        && (month <= (inputData.toYears * 12 + inputData.toMonths));
  }
}