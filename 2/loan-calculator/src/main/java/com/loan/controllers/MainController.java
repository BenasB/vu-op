package com.loan.controllers;

import java.io.IOException;
import com.loan.WindowManager;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void openAbout() throws IOException {
        WindowManager.newWindow("about", "About", false);
    }

    @FXML
    private void openNew() throws IOException {
        WindowManager.newWindow("new", "New mortgage", false);
    }

    @FXML
    private void openDefer() throws IOException {
        WindowManager.newWindow("defer", "Defer", false);
    }

    @FXML
    private void openFilter() throws IOException {
        WindowManager.newWindow("filter", "Filter", false);
    }
}
