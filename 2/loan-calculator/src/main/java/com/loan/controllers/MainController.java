package com.loan.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.loan.WindowManager;
import com.loan.business.MonthlyPayment;
import com.loan.business.Mortgage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable {

    @FXML
    private TableView<MonthlyPayment> tableView;

    @FXML
    private TableColumn<MonthlyPayment, Integer> tcMonth;

    @FXML
    private TableColumn<MonthlyPayment, Double> tcMonthlyPayment;

    @FXML
    private TableColumn<MonthlyPayment, Double> tcPrincipalRepayment;

    @FXML
    private TableColumn<MonthlyPayment, Double> tcInterestRepayment;

    @FXML
    private TableColumn<MonthlyPayment, Double> tcPrincipal;

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

    @FXML
    private void openChart() throws IOException {
        WindowManager.newWindow("chart", "Chart", false);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tcMonth.setCellValueFactory(new PropertyValueFactory<MonthlyPayment, Integer>("month"));
        tcMonthlyPayment.setCellValueFactory(new PropertyValueFactory<MonthlyPayment, Double>("monthlyPayment"));
        tcPrincipalRepayment
                .setCellValueFactory(new PropertyValueFactory<MonthlyPayment, Double>("principalRepayment"));
        tcInterestRepayment.setCellValueFactory(new PropertyValueFactory<MonthlyPayment, Double>("interestRepayment"));
        tcPrincipal.setCellValueFactory(new PropertyValueFactory<MonthlyPayment, Double>("principal"));

        updateTable();
    }

    private void updateTable() {
        Mortgage mortgage = Mortgage.getInstance();
        ObservableList<MonthlyPayment> list = FXCollections.observableArrayList(mortgage.getDisplayPayments());
        tableView.setItems(list);
    }
}
