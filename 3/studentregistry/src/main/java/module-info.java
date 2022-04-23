module com.studentregistry {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;

    opens com.studentregistry.controllers to javafx.fxml;
    opens com.studentregistry.business to javafx.base;

    exports com.studentregistry;
}
