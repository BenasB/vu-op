package com.studentregistry;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        new WindowManager(stage, "main", "Student registry");
    }

    public static void main(String[] args) {
        launch();
    }

}