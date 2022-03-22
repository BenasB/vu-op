package com.loan.dto;

public class NewInputData {
  public double amount;
  public double interest;
  public int years;
  public int months;
  public MortgageType type;

  public enum MortgageType {
    Linear, Annuity
  }

  public NewInputData(double amount, double interest, int years, int months, MortgageType type) {
    this.amount = amount;
    this.interest = interest;
    this.years = years;
    this.months = months;
    this.type = type;
  }
}
