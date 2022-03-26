package com.loan.controllers;

import java.io.IOException;
import com.loan.WindowManager;
import javafx.fxml.FXML;

public class StartController {

    @FXML
    private void openAbout() throws IOException {
        WindowManager.newWindow("about", "About", false);
    }

    @FXML
    private void openNew() throws IOException {
        WindowManager.newWindow("new", "New mortgage", false);
    }
}
