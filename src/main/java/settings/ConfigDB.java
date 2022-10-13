package settings;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    private static final String DB_USER = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_PASSWORD = "root";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Success connected to DB");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
