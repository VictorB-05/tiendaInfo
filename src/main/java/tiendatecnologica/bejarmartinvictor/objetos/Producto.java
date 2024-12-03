/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.objetos;

import java.util.ArrayList;

/**
 *
 * @author alumno
 */
public class Producto {
    private String categoria;
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private String caracteristicas;
    private ArrayList<String> imagenes;
    private int inventario;

    public Producto(String categoria, int id, String nombre, double precio, String descripcion, String caracteristicas, ArrayList<String> imagenes, int inventario) {
        this.categoria = categoria;
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.imagenes = imagenes;
        this.inventario = inventario;
    }

    public Producto(int id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    @Override
    public String toString() {
        return id +" "+ nombre;
    }
    
    public String datos() {
        return "PRODUCTO "+id +": "+ nombre + " " +  (Math.round(precio * 100) / 100d) + "€ categoria:" + categoria +  "\nDescripcion:" + descripcion + "\nCaracteristicas: " + caracteristicas + "\nCantidad: " + inventario;
    }
    
}
