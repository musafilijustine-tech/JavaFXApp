package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Changed the welcome message to include your name
        Label message = new Label("Welcome, Musafili Justine!");

        // 2. Changed the button text from "Click Me" to "Start"
        Button button = new Button("Start");
        button.setOnAction(event -> message.setText("Great! You started the app."));

        // 3. Created the Reset button
        Button reset = new Button("Reset");
        reset.setOnAction(event -> message.setText("Welcome, Musafili Justine!"));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        // 4. Added the reset button to the layout
        layout.getChildren().addAll(message, button, reset);

        Scene scene = new Scene(layout, 500, 300);

        // 5. Changed the window title to include your student number
        stage.setTitle("My First JavaFX Application - 202508384");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}