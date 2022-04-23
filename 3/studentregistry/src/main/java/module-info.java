module com.studentregistry {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.studentregistry.controllers to javafx.fxml;
    opens com.studentregistry.business to javafx.base;

    exports com.studentregistry;
}
