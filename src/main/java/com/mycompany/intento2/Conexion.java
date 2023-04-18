package com.mycompany.intento2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author gabri    
 */
public class Conexion {

    static PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public  Connection getConnection() throws SQLException {
        // Definir los datos de conexión
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "";

        // Cargar el driver JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("CONECTOOOOOOOOOOOOO");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver JDBC", e);
        }

        // Obtener una conexión a la base de datos
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;
    }
}