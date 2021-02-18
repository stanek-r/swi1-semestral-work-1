import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {
    private static Connection connection;

    private static void openConnection() throws SQLException {
        if(connection != null && !connection.isClosed()){
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube?useUnicode=yes&characterEncoding=UTF-8", "user", "password");
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
