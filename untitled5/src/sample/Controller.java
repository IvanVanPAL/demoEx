package sample;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField main_field;

    @FXML
    private Button add_task;

    @FXML
    private VBox all_tasks;

    // Объект на основен нашего класса для работы с БД
    DB db = null;

    @FXML
    void initialize() {

        // Инициируем объект
        db = new DB();

        // Обработчик события. Сработает при нажатии на кнопку
//        add_task.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent> () {
//            // Метод, что будет срабатывать
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                try {
//                    // Проверяем является ли поле заполненным
//                    if(!main_field.getText().trim().equals("")) {
//                        // Вызываем метод из класса DB
//                        // через этот метод будет добавлено новое задание
//                        db.insertTask(main_field.getText());
//                        loadInfo(); // Метод для подгрузки заданий внутрь программы
//                        main_field.setText(""); // Очищаем поле
//                    }
//                } catch (SQLException e) { // Отслеживаем ошибки
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        // Метод для подгрузки заданий внутрь программы
        loadInfo();
    }

    // Метод для подгрузки заданий внутрь программы
    void loadInfo() {
        try {
            // Сначала очищаем от прошлых значений
            all_tasks.getChildren().clear();

            // Получаем все задания из базы данных
            ArrayList<String> tasks1 = db.getTasks("Title");
            ArrayList<String> tasks2 = db.getTasks("Cost");
            ArrayList<String> tasks4 = db.getTasks("Discount");
            all_tasks.setSpacing(5);
            for(int i = 0; i < tasks1.size(); i++) {
                // Перебираем их через цикл
                // Добавляем каждое задание в объект VBox (all_tasks)
                VBox vbox = new VBox();
                vbox.setStyle("-fx-border-style: solid;" + "-fx-border-width: 1;" + "-fx-border-color: white");
                if (tasks4.get(i).equals("0.0")) {
                    Label lab1 = new Label(tasks1.get(i));
                    Label lab2 = new Label(tasks2.get(i) + " рублей");
                  //  all_tasks.getChildren().add(vbox);
                    vbox.getChildren().add(lab1);
                    vbox.getChildren().add(lab2);
                } else {
                    HBox hbox1 = new HBox();
                    hbox1.setSpacing(4);
                    Label lab1 = new Label(tasks1.get(i));
                    Label lab2 = new Label(tasks2.get(i));
                    Double l2 = Double.parseDouble(tasks2.get(i));
                    Double l4 = Double.parseDouble(tasks4.get(i));
                    Label lab3 = new Label(String.valueOf(l2 - (l2 * l4 / 100)) + " рублей");
                    Label lab4 = new Label("* скидка " + tasks4.get(i) + "%");
                    lab2.getStylesheets().addAll(getClass().getResource(
                            "strikethrough.css"
                    ).toExternalForm());
                    hbox1.getChildren().add(lab2);
                    hbox1.getChildren().add(lab3);
                    vbox.getChildren().add(lab1);
                    vbox.getChildren().add(hbox1);
                    vbox.getChildren().add(lab4);

                }
                HBox hbox2 = new HBox();
                hbox2.setSpacing(2);
                Button btn1 = new Button("редактировать");
                Button btn2 = new Button("удалить");
                hbox2.getChildren().addAll(btn1, btn2);
                vbox.getChildren().add(hbox2);
                all_tasks.getChildren().add(vbox);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


