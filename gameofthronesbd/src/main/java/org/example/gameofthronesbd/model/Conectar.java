package org.example.gameofthronesbd.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conectar {
    public static Connection conectarusuarios() {

        Connection conexion = null;
        try {
            //1.Realizar Conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/usuarios_db";
            String user = "root";
            String password = "root";
            conexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return conexion;
    }

    public static Connection conectarGoT() {

        Connection conexion = null;
        try {
            //1.Realizar Conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/game_of_thrones";
            String user = "root";
            String password = "root";
            conexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return conexion;
    }
}
