package com.studentregistry.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.studentregistry.WindowManager;
import com.studentregistry.business.Student;
import com.studentregistry.business.StudentRegistry;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class MainController implements Initializable {

    private static Student selectedStudent;
    private StudentRegistry registry;

    static Student getSelectedStudent() {
        return selectedStudent;
    }

    @FXML
    TableView<Student> tableView;

    @FXML
    private void openNewStudent() throws IOException {
        WindowManager.newWindow("new-student", "New student", false);
    }

    @FXML
    private void openUpdateStudent() throws IOException {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null)
            return;

        WindowManager.newWindow("update-student", "Update student", false);
    }

    @FXML
    private void openUpdateStudentAttendance() throws IOException {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null)
            return;

        WindowManager.newWindow("update-student-attendance", "Update student attendance", false);
    }

    @FXML
    private void openUpdateGroupAttendance() throws IOException {
        selectedStudent = tableView.getSelectionModel().getSelectedItem();

        if (selectedStudent == null)
            return;

        WindowManager.newWindow("update-group-attendance", "Update group attendance", false);
    }

    @FXML
    private void deleteStudent() {
        Student studentToDelete = tableView.getSelectionModel().getSelectedItem();
        registry.deleteStudent(studentToDelete);
    }

    @FXML
    private void exportStudentsCSV() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.exportStudentsCSV(currentStage);
    }

    @FXML
    private void exportStudentsExcel() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.exportStudentsExcel(currentStage);
    }

    @FXML
    private void importStudentsCSV() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.importStudentsCSV(currentStage);
    }

    @FXML
    private void importStudentsExcel() {
        Stage currentStage = (Stage) tableView.getScene().getWindow();
        registry.importStudentsExcel(currentStage);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            registry = new StudentRegistry(tableView);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        tableView.setItems(registry.getStudents());
    }
}
