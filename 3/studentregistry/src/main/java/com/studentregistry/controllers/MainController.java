package com.studentregistry.controllers;

import java.io.IOException;

import com.studentregistry.WindowManager;

import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void openNewGroup() throws IOException {
        WindowManager.newWindow("new-group", "New group", false);
    }

    @FXML
    private void openNewStudent() throws IOException {
        WindowManager.newWindow("new-student", "New student", false);
    }
}
