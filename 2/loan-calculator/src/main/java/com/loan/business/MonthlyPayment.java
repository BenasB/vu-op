package com.loan.business;

import com.loan.dto.FilterInputData;

class MonthlyPayment {
  private int month;
  private double paymentAmount, principalRepayment, interestRepayment, principal;

  int getMonth() {
    return month;
  };

  double getMonthlyPayment() {
    return paymentAmount;
  };

  double getPrincipalRepayment() {
    return principalRepayment;
  };

  double getInterestRepayment() {
    return interestRepayment;
  };

  double getPrincipal() {
    return principal;
  };

  MonthlyPayment(int month, double paymentAmount, double principalRepayment, double interestRepayment,
      double principal) {
    this.month = month;
    this.paymentAmount = paymentAmount;
    this.principalRepayment = principalRepayment;
    this.interestRepayment = interestRepayment;
    this.principal = principal;
  }

  boolean inInterval(FilterInputData inputData) {
    return (month > (inputData.fromYears * 12 + inputData.fromMonths))
        && (month <= (inputData.toYears * 12 + inputData.toMonths));
  }

  @Override
  public String toString() {
    return "Month: " + month + ", Payment Amount: " + paymentAmount + ", Principal Repayment: " + principalRepayment
        + ", Interest Repayment: " + interestRepayment + ", Principal: " + principal;
  }
}