package com.mycompany.intento2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Date;

/**
 *
 * @author gabri
 */
public class Pedido {
      private int id;
    private Date fecha;
    private Proveedor proveedor;
    
    // constructor
    public Pedido(int id, Date fecha, Proveedor proveedor) {
        this.id = id;
        this.fecha = fecha;
        this.proveedor = proveedor;
    }
    
    // getters y setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
  

