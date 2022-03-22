package com.loan.dto;

public class FilterInputData {
  public int fromYears;
  public int fromMonths;
  public int toYears;
  public int toMonths;

  public FilterInputData(int fromYears, int fromMonths, int toYears, int toMonths) {
    this.fromYears = fromYears;
    this.fromMonths = fromMonths;
    this.toYears = toYears;
    this.toMonths = toMonths;
  }
}
