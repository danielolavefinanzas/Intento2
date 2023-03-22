/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intento2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDao {

    private final Connection Conexion;

    public DetallePedidoDao(Connection Conexion) {
        this.Conexion = Conexion;
    }

    public void agregarDetallePedido(DetallePedido detallePedido) throws SQLException {
        String sql = "INSERT INTO detalle_pedido (producto, cantidad, precio) VALUES (?, ?, ?)";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, detallePedido.getProducto().getId());
            statement.setBigDecimal(2, detallePedido.getCantidad());
            statement.setBigDecimal(3, detallePedido.getPrecio());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarDetallePedido(DetallePedido detallePedido) throws SQLException {
        String sql = "UPDATE detalle_pedido SET producto = ?, cantidad = ?, precio = ? WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, detallePedido.getProducto().getId());
            statement.setBigDecimal(2, detallePedido.getCantidad());
            statement.setBigDecimal(3, detallePedido.getPrecio());
            statement.setInt(4, detallePedido.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDetallePedido(int id) throws SQLException {
        String sql = "DELETE FROM detalle_pedido WHERE id = ?";
        try (PreparedStatement statement = Conexion.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DetallePedido> obtenerTodosDetallePedidos() throws SQLException {
        String sql = "SELECT dp.id, dp.cantidad, dp.precio, p.id, p.nombre FROM detalle_pedido dp JOIN producto p ON dp.producto = p.id";
        List<DetallePedido> listaDetallePedidos = new ArrayList<>();
        try (PreparedStatement statement = Conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                DetallePedido detallePedido = new DetallePedido();
                detallePedido.setId(resultSet.getInt(1));
                detallePedido.setCantidad(resultSet.getBigDecimal(2));
                detallePedido.setPrecio(resultSet.getBigDecimal(3));
                Productos producto = new Productos();
                producto.setId(resultSet.getInt(4));
                producto.setNombre(resultSet.getString(5));
                detallePedido.setProducto(producto);
                listaDetallePedidos.add(detallePedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDetallePedidos;
    }
}





