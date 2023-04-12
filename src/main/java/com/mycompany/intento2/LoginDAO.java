/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intento2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HPM
 */
public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public Login log (String correo, String pass){
        Login l = new Login();
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND pass = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(1, pass);
            ps.executeQuery();
            if(rs.next()){
                l.setId(rs.getInt("id"));
                l.setNombre(rs.getString("nombre"));
                l.setCorreo(rs.getString("correo"));
                l.setPass(rs.getString("pass"));
                
            }
            
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return l;
    }
            
}
