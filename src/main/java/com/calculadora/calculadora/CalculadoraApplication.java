package com.calculadora.calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CalculadoraApplication extends Application {
    public static Stage stage1;
    @Override
    public void start(Stage stage) throws IOException {
        stage1 = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(CalculadoraApplication.class.getResource("calculadora.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 402, 402, Color.TRANSPARENT);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/icon.png")));
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}