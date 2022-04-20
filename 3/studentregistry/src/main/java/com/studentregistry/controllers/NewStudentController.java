package com.studentregistry.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewStudentController {
    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    ChoiceBox<String> groupChoiceBox;

    @FXML
    Button cancelButton;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }
}