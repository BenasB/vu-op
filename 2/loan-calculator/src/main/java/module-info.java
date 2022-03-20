module com.loan {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.loan to javafx.fxml;
    exports com.loan;
}
