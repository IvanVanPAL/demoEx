package com.example.intellijidea_projects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws SQLException {
        welcomeText.setText("Welcome to JavaFX Application!");

        ResultSet result =  DB.statement.executeQuery("select * from users");

        while(result.next()){
            System.out.println(result.getString(1) + result.getString(2));
        }
    }
}