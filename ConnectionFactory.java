import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Properties props = new Properties();

    public static Connection getCon() throws SQLException, IOException {
        Connection con;
        props.load(new FileInputStream("src/SQLconfig.properties"));
        String username, password;
        username = props.getProperty("username");
        password = props.getProperty("password");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tv_operator", username, password);
        return con;
    }
}
