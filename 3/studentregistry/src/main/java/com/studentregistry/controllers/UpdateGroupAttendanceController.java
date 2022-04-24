package com.studentregistry.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.studentregistry.business.Student;
import com.studentregistry.business.StudentRegistry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateGroupAttendanceController implements Initializable {

    private Student studentToUpdate;

    @FXML
    TextField groupField;

    @FXML
    DatePicker datePicker;

    @FXML
    CheckBox attendedCheckBox;

    @FXML
    Button cancelButton;

    @FXML
    private void closeWindow() {
        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
        currentStage.close();
    }

    @FXML
    private void updateGroupAttendance() {
        var students = StudentRegistry.getInstance().getStudents();

        students.forEach(s -> {
            if (s.getGroup().equals(studentToUpdate.getGroup()))
                s.updateAttendance(datePicker.getValue(), attendedCheckBox.isSelected());
        });

        closeWindow();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        studentToUpdate = MainController.getSelectedStudent();

        groupField.setText(studentToUpdate.getGroup());
    }
}