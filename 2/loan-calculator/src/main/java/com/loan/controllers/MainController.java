package com.loan.controllers;

import java.io.IOException;
import com.loan.WindowManager;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void switchToAbout() throws IOException {
        WindowManager.newWindow("about", "About", false);
    }
}
