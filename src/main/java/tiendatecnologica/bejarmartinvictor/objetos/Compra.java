/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.objetos;

import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class Compra {
    private Integer productoId = null;
    private Producto producto;
    private int cantidad;
    private LocalDate fecha;

    public Compra(Producto producto, int cantidad, LocalDate fecha) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public Compra(int productoId, int cantidad, LocalDate fecha) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getProductoId() {
        if(productoId==null){
            return producto.getId();
        }else{
            return productoId;
        }
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double precioTotal(){
        Double precioT = producto.getPrecio()*cantidad;
        return precioT;
    }
    
    @Override
    public String toString() {
        return  producto.getNombre() +" ID: "+getProductoId()+ " cantidad: " + cantidad + " fecha: " + fecha+"precio total: "+(Math.round(precioTotal() * 100) / 100d);
    }
   
}
