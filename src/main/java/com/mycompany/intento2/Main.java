/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.intento2;

import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class Main {

    public static void main(String[] args) throws SQLException {
       Conexion con = new Conexion();
       con.getConnection();
    }
    
}
