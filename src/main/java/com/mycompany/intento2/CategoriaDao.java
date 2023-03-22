/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intento2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {
    private final Connection Conexion;

    public CategoriaDao(Connection conexion) {
        this.Conexion = conexion;
    }
    
    
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM categoria";
        try (Statement statement = Conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Categoria categoria = new Categoria(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                );
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
    
   
    public Categoria buscarCategoria(int id) {
        Categoria categoria = null;
        String sql = "SELECT id, nombre FROM categoria WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                categoria = new Categoria(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    
    public void agregarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categoria (id, nombre) VALUES (?, ?)";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, categoria.getId());
            statement.setString(2, categoria.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarCategoria(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ? WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setString(1, categoria.getNombre());
            statement.setInt(2, categoria.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void eliminarCategoria(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}