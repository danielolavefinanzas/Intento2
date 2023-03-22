package com.mycompany.intento2;

import java.awt.List;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class ProductoDao {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    
    public boolean registrarProducto(Productos producto){
        String sql = "INSERT INTO productos (nombre, descripcion, codigo, precio, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getCodigo());
            ps.setBigDecimal(4, producto.getPrecio());
            ps.setInt(5, producto.getCategoria().getId());
            ps.execute();
            return true;

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.toString());
            return false;
        }finally{
            try{
                con.close();
            }catch(SQLException e){
                System.out.println(e.toString());
            }
        }
    }
    // Resto de métodos de consulta, eliminación, etc.
    

public Productos buscarProducto(int id) {
        Productos producto = null;
        String sql = "SELECT * FROM productos WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                Categoria categoria = new Categoria(resultSet.getInt("categoria_id"), null);

                producto = new Productos(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("codigo"),
                        resultSet.getBigDecimal("precio"),
                        categoria
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

  public ArrayList<Productos> listarProductos() {
        
    ArrayList<Productos> listaProductos  = new ArrayList<>();

        String sql = "SELECT * FROM productos";
        try (Statement statement = Conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                 Categoria categoria = new Categoria(resultSet.getInt("categoria_id"), null);
                Productos producto = new Productos(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getString("codigo"),
                        resultSet.getBigDecimal("precio"),
                        categoria
                );
                listaProductos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProductos;
 }

    public void actualizarProducto(Productos producto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, codigo = ?, precio = ?, categoria_id = ? WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setString(3, producto.getCodigo());
            statement.setBigDecimal(4, producto.getPrecio());
            statement.setInt(5, producto.getCategoria().getId());            
            statement.setInt(6, producto.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}