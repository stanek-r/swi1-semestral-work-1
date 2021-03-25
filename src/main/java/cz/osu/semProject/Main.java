package cz.osu.semProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ResourceBundle rb = ResourceBundle.getBundle("localization.Bundle",Locale.ENGLISH);

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlFiles/login.fxml"), rb);
        primaryStage.setTitle("Test app");
        Scene loginScene = new Scene(root);
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}