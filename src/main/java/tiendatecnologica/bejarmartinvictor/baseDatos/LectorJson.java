/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.baseDatos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import tiendatecnologica.bejarmartinvictor.objetos.Producto;
import tiendatecnologica.bejarmartinvictor.objetos.Usuario;
import tiendatecnologica.bejarmartinvictor.objetos.Compra;

/**
 *
 * @author alumno
 */
public class LectorJson {
    
    private static ArrayList<Producto> productos = new ArrayList();
    private static ArrayList<Usuario> usuarios = new ArrayList();

    public static ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public static void leer(){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/TiendaDatos.json")){
            Object obj = jsonParser.parse(reader);
            JSONObject frist = (JSONObject) obj;
            JSONObject tienda = (JSONObject) frist.get("tienda");
            JSONArray arrayCategoria = (JSONArray) tienda.get("categorias");
            for (Object aux : arrayCategoria){
                JSONObject categoria = (JSONObject) aux;
                String cate = (String) categoria.get("nombre");
                System.out.println(cate);
                JSONArray arrayProductos = (JSONArray) categoria.get("productos");
                addProductos(arrayProductos,cate);
            }
            JSONArray arrayUsuario = (JSONArray) tienda.get("usuarios");
            for (Object aux : arrayUsuario){
                JSONObject usuario = (JSONObject) aux;
                System.out.println(usuario);
                addUsuario(usuario);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LectorJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LectorJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(LectorJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private static void addProductos(JSONArray arrayProductos, String categoria) {
       for(Object aux : arrayProductos){
           JSONObject producto = (JSONObject) aux;
           System.out.println(producto);
           String descrip = (String) producto.get("descripcion");
           int id = (int) (long) producto.get("id");
           String nombre = (String) producto.get("nombre");
           double precio = (double) producto.get("precio");
           String caracteristicas = producto.get("caracteristicas").toString();
           System.out.println(caracteristicas);
           ArrayList<String> imagenes = new ArrayList();
           JSONArray imagenesJ = (JSONArray) producto.get("imagenes");
           int inventario = (int) (long) producto.get("inventario");
           for(Object img : imagenesJ){
               String imagen = (String) img;
               imagenes.add(imagen);
           }
           Producto productObj = new Producto(categoria,id, nombre, id, descrip, caracteristicas, imagenes, inventario);
           productos.add(productObj);
       }
    }
     private static void addUsuario(JSONObject usuario) {
        int id = (int) (long) usuario.get("id");
        String nombre = (String) usuario.get("nombre");
        String email = (String) usuario.get("email");
        JSONObject direccion = (JSONObject) usuario.get("direccion");
        String calle = (String) direccion.get("calle");
        int numero = (int) (long) direccion.get("numero");
        String ciudad = (String) direccion.get("ciudad");
        String pais = (String) direccion.get("pais");
        Usuario user = new Usuario(id, nombre, email, calle, numero, ciudad, pais);
        
        JSONArray historialCompras = (JSONArray) usuario.get("historialCompras");
        for(Object comprar : historialCompras){
            JSONObject compra = (JSONObject) comprar;
            int productoId = (int) (long) compra.get("productoId");
            int cantidad = (int) (long) compra.get("cantidad");
            String fechaS = (String) compra.get("fecha");
            LocalDate fecha = LocalDate.parse(fechaS);
            Compra compraObj = new Compra(productoId,cantidad,fecha);
            user.addCompra(compraObj);
        }
        usuarios.add(user);
     }
    
}
