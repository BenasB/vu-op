module com.studentregistry {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.studentregistry.controllers to javafx.fxml;
    exports com.studentregistry;
}
