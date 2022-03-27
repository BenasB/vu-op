module com.loan {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.loan.controllers to javafx.fxml;
    opens com.loan.business to javafx.base;

    exports com.loan;
}
