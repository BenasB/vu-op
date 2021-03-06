package com.loan.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.loan.WindowManager;
import com.loan.business.Mortgage;
import com.loan.dto.FilterInputData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class FilterController implements Initializable {
    @FXML
    Button submitButton;

    @FXML
    Button cancelButton;

    @FXML
    Spinner<Integer> fromYearSpinner;

    @FXML
    Spinner<Integer> fromMonthSpinner;

    @FXML
    Spinner<Integer> toYearSpinner;

    @FXML
    Spinner<Integer> toMonthSpinner;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void filterPayments() throws IOException {
        Mortgage mortgage = Mortgage.getInstance();
        FilterInputData inputData = new FilterInputData(fromYearSpinner.getValue(), fromMonthSpinner.getValue(),
                toYearSpinner.getValue(), toMonthSpinner.getValue());
        mortgage.filter(inputData);

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
        SpinnerValueFactory<Integer> toYearValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                99, 0);
        toYearSpinner.setValueFactory(toYearValueFactory);

        SpinnerValueFactory<Integer> fromMonthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0, 11,
                0);
        fromMonthSpinner.setValueFactory(fromMonthValueFactory);

        SpinnerValueFactory<Integer> toMonthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(
                0,
                11, 0);
        toMonthSpinner.setValueFactory(toMonthValueFactory);
    }
}
