package com.example.intellijidea_projects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginWinController {

    @FXML
    private Label labelLog;

    @FXML
    private TextField login;

    @FXML
    private TextField pass;

    @FXML
    private Button singButton;

    @FXML
    private void onButClick() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = "SELECT * FROM users Where login = ? and password = ?";
        PreparedStatement preparedStatement = DB.connection.prepareStatement(sql);
        preparedStatement.setString(1, login.getText());
        preparedStatement.setString(2, pass.getText());

        ResultSet result = preparedStatement.executeQuery();
        labelLog.setText("Welcome!!!");
        if(result.next()){
            //result.previous();
            do{

                System.out.println("AAAAAAAAAAAAAAAA");

            }while(result.next());

        }else{
            labelLog.setText("Wrong login or pass!!!");
        }

//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//        document.addPage(page);
//
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//
//        contentStream.setFont(PDType1Font.COURIER, 12);
//        contentStream.beginText();
//        contentStream.showText("Hello World");
//        contentStream.endText();
//        contentStream.close();
//
//        document.save("pdfBoxHelloWorld1.pdf");
//        document.close();

        try (PDDocument doc = new PDDocument()) {

            PDPage myPage = new PDPage();
            doc.addPage(myPage);

            try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

                cont.beginText();

                cont.setFont(PDType1Font.TIMES_ROMAN, 12);
                cont.setLeading(14.5f);

                cont.newLineAtOffset(25, 700);
                String line1 = "World War II (often abbreviated to WWII or WW2), "
                        + "also known as the Second World War,";
                cont.showText(line1);

                cont.newLine();

                String line2 = "was a global war that lasted from 1939 to 1945, "
                        + "although related conflicts began earlier.";
                cont.showText(line2);
                cont.newLine();

                String line3 = "It involved the vast majority of the world's "
                        + "countries—including all of the great powers—";
                cont.showText(line3);
                cont.newLine();

                String line4 = "eventually forming two opposing military "
                        + "alliances: the Allies and the Axis.";
                cont.showText(line4);
                cont.newLine();

                cont.endText();
            }

            doc.save("src/main/resources/wwii.pdf");
        }

    }




}
