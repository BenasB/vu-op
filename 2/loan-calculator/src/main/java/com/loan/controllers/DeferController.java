package com.loan.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.loan.WindowManager;
import com.loan.business.Mortgage;
import com.loan.dto.DeferInputData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeferController implements Initializable {

    @FXML
    Button cancelButton;

    @FXML
    Button submitButton;

    @FXML
    Spinner<Integer> fromYearSpinner;

    @FXML
    Spinner<Integer> fromMonthSpinner;

    @FXML
    Spinner<Integer> durationYearSpinner;

    @FXML
    Spinner<Integer> durationMonthSpinner;

    @FXML
    TextField yearlyInterest;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void defer() throws IOException {
        Mortgage mortgage = Mortgage.getInstance();
        DeferInputData inputData = new DeferInputData(fromYearSpinner.getValue(), fromMonthSpinner.getValue(),
                durationYearSpinner.getValue(), durationMonthSpinner.getValue(),
                Double.parseDouble(yearlyInterest.getText()));

        mortgage.defer(inputData);

        Stage currentStage = (Stage) submitButton.getScene().getWindow();
        WindowManager.changeMainWindowContent("main");
        currentStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Maybe set max val depending on existing mortgage term ?
        SpinnerValueFactory<Integer> fromYearValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 99,
                0);
        fromYearSpinner.setValueFactory(fromYearValueFactory);
        SpinnerValueFactory<Integer> durationYearValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                99, 0);
        durationYearSpinner.setValueFactory(durationYearValueFactory);

        SpinnerValueFactory<Integer> fromMonthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 11,
                0);
        fromMonthSpinner.setValueFactory(fromMonthValueFactory);

        SpinnerValueFactory<Integer> durationMonthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                11, 0);
        durationMonthSpinner.setValueFactory(durationMonthValueFactory);
    }
}
