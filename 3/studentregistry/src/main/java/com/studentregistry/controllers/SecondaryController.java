package com.studentregistry.controllers;

import java.io.IOException;

import com.studentregistry.App;

import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}