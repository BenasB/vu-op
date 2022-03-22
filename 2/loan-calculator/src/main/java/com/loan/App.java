package com.loan;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

import com.loan.business.AnnuityMortgage;
import com.loan.dto.NewInputData;

public class App extends Application {

    final NewInputData testInputData = new NewInputData(12000f, 0.005f, 3, 0, NewInputData.MortgageType.Annuity);

    @Override
    public void start(Stage stage) throws IOException {
        new AnnuityMortgage(testInputData);
        new WindowManager(stage, "main", "Mortgage calculator");
    }

    public static void main(String[] args) {
        launch();
    }

}