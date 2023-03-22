/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intento2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class VentaDao {
    
    private Connection Conexion;

    public VentaDao(Connection conexion) {
        this.Conexion = conexion;
    }
    
   public void agregarVenta(Venta venta) throws SQLException {
       String sql = "INSERT INTO venta (fecha) VALUES (?)";
       try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
        statement.setDate(1, java.sql.Date.valueOf(venta.getFecha().toString()));
        statement.executeUpdate();
     } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void eliminarVenta(int id) {
        String sql = "DELETE FROM venta WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Venta obtenerVentaPorId(int id) {
        String sql = "SELECT * FROM venta WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Venta(
                        resultSet.getInt("id"),
                        resultSet.getDate("fecha")//.toLocalDate()//getDate("fecha").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Venta> obtenerTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM venta";
        try (PreparedStatement statement = Conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ventas.add(new Venta(
                    resultSet.getInt("id"),
                    resultSet.getDate("fecha")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ventas;
    }
    
}