package com.loan.business;

import com.loan.dto.NewInputData;

public class LinearMortgage extends Mortgage {

  LinearMortgage(NewInputData inputData) {
    super(inputData);
  }

  @Override
  MonthlyPayment[] generateMonthlyPayments(NewInputData inputData) {
    // TODO Auto-generated method stub
    return null;
  }

}