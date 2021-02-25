package cz.osu.semProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
    private static Connection connection;

    private static final String DB_NAME = "test_database";
    private static final String DB_USERNAME = "test_user";
    private static final String DB_PASSWORD = "123456789";

    private static void openConnection() throws SQLException {
        if(connection != null && !connection.isClosed()){
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=yes&characterEncoding=UTF-8", DB_USERNAME, DB_PASSWORD);
    }

    public static Statement getStatement(){
        try{
            openConnection();
            return connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}