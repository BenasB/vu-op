package com.studentregistry.controllers;

import java.io.IOException;

import com.studentregistry.App;

import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
