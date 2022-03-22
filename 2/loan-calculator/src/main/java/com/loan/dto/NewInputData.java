package com.loan.dto;

public class NewInputData {
  public double amount;
  public int years;
  public int months;
  public MortgageType type;

  enum MortgageType {
    Linear, Annuity
  }

  public NewInputData(double amount, int years, int months, MortgageType type) {
    this.amount = amount;
    this.years = years;
    this.months = months;
    this.type = type;
  }
}
