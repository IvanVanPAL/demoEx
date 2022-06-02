module com.example.intellijidea_projects {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires pdfbox;


    opens com.example.intellijidea_projects to javafx.fxml;
    exports com.example.intellijidea_projects;
}