package com.studentregistry.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.studentregistry.business.Student;
import com.studentregistry.dto.UpdateStudentInputData;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.stage.Stage;

public class UpdateStudentController implements Initializable {

    Student studentToUpdate;

    @FXML
    TextField idField;

    @FXML
    TextField firstNameField;

    @FXML
    TextField lastNameField;

    @FXML
    IntegerSpinnerValueFactory courseSpinner;

    @FXML
    TextField groupField;

    @FXML
    Button cancelButton;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void updateStudent() {
        UpdateStudentInputData inputData = new UpdateStudentInputData(firstNameField.getText(), lastNameField.getText(),
                courseSpinner.getValue(), groupField.getText());

        studentToUpdate.update(inputData);

        closeWindow();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        studentToUpdate = MainController.getSelectedStudent();

        idField.setText(studentToUpdate.getId());
        firstNameField.setText(studentToUpdate.getFirstName());
        lastNameField.setText(studentToUpdate.getLastName());
        courseSpinner.setValue(studentToUpdate.getCourse());
        groupField.setText(studentToUpdate.getGroup());
    }
}