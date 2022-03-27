package com.loan.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;

public class ChartController implements Initializable {

    @FXML
    LineChart<Integer, Double> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
