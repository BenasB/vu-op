package com.loan.business;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.loan.dto.FilterInputData;

public class MonthlyPayment {
  private int month;
  private double monthlyPayment, principalRepayment, interestRepayment, principal;

  public int getMonth() {
    return month;
  };

  public double getMonthlyPayment() {
    return round(monthlyPayment, 2);
  };

  public double getPrincipalRepayment() {
    return round(principalRepayment, 2);
  };

  public double getInterestRepayment() {
    return round(interestRepayment, 2);
  };

  public double getPrincipal() {
    return round(principal, 2);
  };

  MonthlyPayment(int month, double monthlyPayment, double principalRepayment, double interestRepayment,
      double principal) {
    this.month = month;
    this.monthlyPayment = monthlyPayment;
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
    return "Month: " + month + ", Payment Amount: " + monthlyPayment + ", Principal Repayment: " + principalRepayment
        + ", Interest Repayment: " + interestRepayment + ", Principal: " + principal;
  }

  private static double round(double value, int places) {
    if (places < 0)
      throw new IllegalArgumentException();

    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}