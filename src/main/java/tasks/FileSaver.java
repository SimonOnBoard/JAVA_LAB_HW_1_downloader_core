package tasks;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.sql.*;

public class FileSaver {
    //language=SQL
    private static final String SQL_createTable =
            "CREATE TABLE IF NOT EXISTS files (id serial primary key, name VARCHAR(50),path VARCHAR (200), file_size INTEGER);";
    //language=SQL
    private static final String SQL_saveInfo =
            "INSERT into files(name, path, file_size) VALUES (?,?,?);";
    public static void saveInfo(Connection connection, String path){
        File file = new File(path);
        try {

            Statement statement1 = connection.createStatement();
            statement1.execute(SQL_createTable);

            PreparedStatement statement = connection.prepareStatement(SQL_saveInfo);
            statement.setString(1,path.substring(path.lastIndexOf("/")+1));
            statement.setString(2,path);
            statement.setLong(3,file.length());
            statement.executeUpdate();
        } catch (SQLException e) {
            new IllegalArgumentException(e);
        }

    }
}
