package com.loan.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class NewController implements Initializable {

    @FXML
    ChoiceBox<String> choiceBox;

    @FXML
    Button cancelButton;

    @FXML
    Spinner<Integer> yearSpinner;

    @FXML
    Spinner<Integer> monthSpinner;

    @FXML
    private void closeWindow() throws IOException {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> availableChoices = FXCollections.observableArrayList("Linear", "Annuity");
        choiceBox.setItems(availableChoices);
        choiceBox.getSelectionModel().select(availableChoices.get(0));

        SpinnerValueFactory<Integer> yearValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 20);
        yearSpinner.setValueFactory(yearValueFactory);

        SpinnerValueFactory<Integer> monthValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 11, 0);
        monthSpinner.setValueFactory(monthValueFactory);
    }
}
