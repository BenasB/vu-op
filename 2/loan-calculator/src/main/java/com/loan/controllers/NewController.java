package com.loan.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.loan.WindowManager;
import com.loan.business.AnnuityMortgage;
import com.loan.business.LinearMortgage;
import com.loan.dto.NewInputData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewController implements Initializable {

    @FXML
    Button cancelButton;

    @FXML
    Button submitButton;

    @FXML
    TextField loanAmountField;

    @FXML
    Spinner<Integer> yearSpinner;

    @FXML
    Spinner<Integer> monthSpinner;

    @FXML
    ChoiceBox<NewInputData.MortgageType> typeChoiceBox;

    @FXML
    TextField yearlyInterestField;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void createNewMortgage() throws IOException {
        double amount = Double.parseDouble(loanAmountField.getText());
        int years = yearSpinner.getValue();
        int months = monthSpinner.getValue();
        double yearlyInterest = Double.parseDouble(yearlyInterestField.getText());
        NewInputData.MortgageType type = typeChoiceBox.getValue();

        NewInputData inputData = new NewInputData(amount, yearlyInterest, years, months, type);

        switch (type) {
            case Linear:
                new LinearMortgage(inputData);
                break;
            case Annuity:
                new AnnuityMortgage(inputData);
                break;
            default:
                new AnnuityMortgage(inputData);
                break;
        }

        Stage currentStage = (Stage) submitButton.getScene().getWindow();
        WindowManager.changeMainWindowContent("main");
        currentStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<NewInputData.MortgageType> availableChoices = FXCollections
                .observableArrayList(NewInputData.MortgageType.values());
        typeChoiceBox.setItems(availableChoices);
        typeChoiceBox.getSelectionModel().select(availableChoices.get(0));

        SpinnerValueFactory<Integer> yearValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 20);
        yearSpinner.setValueFactory(yearValueFactory);

        SpinnerValueFactory<Integer> monthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11, 0);
        monthSpinner.setValueFactory(monthValueFactory);
    }
}
