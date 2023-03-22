/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intento2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDao {
    private Connection conexion;

    public PedidoDao(Connection con) {
        this.conexion = con;
    }

    // Método para insertar un nuevo pedido en la tabla pedidos
    public boolean insertarPedido(Pedido pedido) {
        try {
            PreparedStatement pst = conexion.prepareStatement("INSERT INTO pedidos (fecha_pedido, estado) VALUES (?, ?)");
            pst.setDate(1, new java.sql.Date(pedido.getFecha().getTime()));
            pst.setString(2, pedido.getEstado_pedido());
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los pedidos en la tabla pedidos
    public List<Pedido> obtenerPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos");
            while (rs.next()) {
                Pedido pedido = new Pedido(
                rs.getInt("id"),
                rs.getDate("fecha"),
                rs.getString("estado_pedido")            
                );
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setFecha(rs.getDate("fecha_pedido"));
                pedido.setEstado_pedido(rs.getString("estado"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}