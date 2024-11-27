package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdventCalendar extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        int day = 1;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                LocalDate date = LocalDate.of(2024, 12, day);
                String formattedDate = date.format(formatter);

                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.setSpacing(5);

                Text dayText = new Text("Tag " + day);
                dayText.setFont(Font.font(18));

                Text dateText = new Text(formattedDate);
                dateText.setFont(Font.font(10));

                Button button = new Button();
                button.setPrefSize(150, 150); // Doppelte Größe
                button.setGraphic(vbox);
                int finalDay = day;
                button.setOnAction(e -> showImage(finalDay));

                vbox.getChildren().addAll(dayText, dateText);
                grid.add(button, col, row);
                day++;
            }
        }

        Scene scene = new Scene(grid, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Advent Calendar");
        primaryStage.show();
    }

    private void showImage(int day) {
        Stage stage = new Stage();
        Image image = new Image(getClass().getResourceAsStream("/images/DayX.jpg"));
        ImageView imageView = new ImageView(image);
        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Day " + day);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
