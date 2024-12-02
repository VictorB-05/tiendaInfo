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
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String calle;
    private int numero;
    private String ciudad;
    private String pais;
    private ArrayList<Compra> historialCompras = new ArrayList();

    public Usuario(int id, String nombre, String email, String calle, int numero, String ciudad, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.pais = pais;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public ArrayList<Compra> getHistorialCompras() {
        return historialCompras;
    }

    public void addCompra(Compra compra) {
        historialCompras.add(compra);
    }

    @Override
    public String toString() {
        return id+" "+getNombre();
    }
    
    public String infoUsuario(){
        return "NOMBRE: "+nombre+"ID: "+id+
                "\n\nEMAIL: "+email+
                "\n\nDIRECCION: nº: "+numero+" calle: "+calle+" ciudad: "+ciudad+" pais: "+pais;
    }
}
