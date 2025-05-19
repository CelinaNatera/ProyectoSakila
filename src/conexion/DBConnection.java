package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASSWORD = "celi02"; //Contraseña

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println(" Conexion exitosa a la base de datos Sakila.");
            con.close();
        } catch (SQLException e) {
            System.out.println(" Error de conexion:");
            e.printStackTrace();
        }
    }
}
