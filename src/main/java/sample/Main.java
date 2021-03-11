package sample;

import cz.osu.semProject.MysqlConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader fxmlLoader = new FXMLLoader();
        ResourceBundle rs = ResourceBundle.getBundle("localization.Bundle",Locale.ENGLISH);
        //noinspection ConstantConditions
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlFiles/addNew.fxml"), rs);
        primaryStage.setTitle("Test app");
        Scene loginScene = new Scene(root,1280,720);
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();


//        testDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }


    private void testDatabase(){
        try{
            ResultSet rs = MysqlConnection.getStatement().executeQuery("SELECT * FROM test_table");
            while (rs.next()){
                System.out.println(rs.getInt("id") + " : " + rs.getInt("number") + " " + rs.getString("text") + " " + rs.getString("datetime"));
            }
//            MysqlConnection.getStatement().executeUpdate("INSERT INTO test_table VALUES (NULL, 'Ahojda', '25', NOW())");
            //MysqlConnection.getStatement().executeUpdate("DELETE FROM test_table WHERE id = '1'");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}