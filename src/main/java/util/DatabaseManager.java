package util;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseManager {
    private static Connection con;


    public static boolean conectar() throws ClassNotFoundException, SQLException, IOException {
        if (con != null) return true;

        Properties configuration = new Properties();
        configuration.load(R.getProperties("database.properties"));
        String host = configuration.getProperty("host");
        String port = configuration.getProperty("port");
        String name = configuration.getProperty("name");
        String username = configuration.getProperty("username");
        String password = configuration.getProperty("password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + name + "?serverTimezone=UTC",
                username, password);

        return con != null;

    }

    public static void desconectar() throws SQLException {
        if (con != null) con.close();

    }

    public static boolean existeConexion() {
        return con != null;

    }

    public static Connection getCon() {
        return con;

    }
}
