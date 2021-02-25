package sample;

import cz.osu.semProject.MysqlConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Test app");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        testDatabase();
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

            //MysqlConnection.getStatement().executeUpdate("INSERT INTO test_table VALUES (NULL, 'Text text text', '25', NOW())");
            //MysqlConnection.getStatement().executeUpdate("DELETE FROM test_table WHERE id = '1'");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}