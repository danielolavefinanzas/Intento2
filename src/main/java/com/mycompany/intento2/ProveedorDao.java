/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intento2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {

    private final Connection Conexion;

    public ProveedorDao(Connection Conexion) {
        this.Conexion = Conexion;
    }

    public void agregarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO proveedor (nombre, direccion, telefono) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
            
        }
    }

    public void eliminarProveedor(int id) throws SQLException {
        String sql = "DELETE FROM proveedor WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ? WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.setInt(4, proveedor.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Proveedor> listarProveedores() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM proveedor";
        try (PreparedStatement statement = Conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(resultSet.getInt("id"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setDireccion(resultSet.getString("direccion"));
                proveedor.setTelefono(resultSet.getString("telefono"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
}