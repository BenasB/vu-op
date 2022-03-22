package com.loan.dto;

public class DeferInputData {
  public int fromYears;
  public int fromMonths;
  public int durationYears;
  public int durationMonths;
  public double interest;

  public DeferInputData(int fromYears, int fromMonths, int durationYears, int durationMonths, double interest) {
    this.fromYears = fromYears;
    this.fromMonths = fromMonths;
    this.durationYears = durationYears;
    this.durationMonths = durationMonths;
    this.interest = interest;
  }
}
