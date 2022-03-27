package com.loan.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.loan.business.MonthlyPayment;
import com.loan.business.Mortgage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class ChartController implements Initializable {

    @FXML
    LineChart<Integer, Double> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Mortgage mortgage = Mortgage.getInstance();
        XYChart.Series<Integer, Double> series = new XYChart.Series<Integer, Double>();

        for (MonthlyPayment payment : mortgage.getDisplayPayments()) {
            series.getData().add(new XYChart.Data(payment.getMonth(), payment.getMonthlyPayment()));
        }

        lineChart.getData().add(series);
    }
}
