/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import tiendatecnologica.bejarmartinvictor.objetos.Compra;
import tiendatecnologica.bejarmartinvictor.objetos.Producto;
import tiendatecnologica.bejarmartinvictor.objetos.Usuario;

/**
 *
 * @author alumno
 */
public class UsarBBDD {

    public static void compraBBDDInsert(Compra compra, int idUsuario) {
        int idProducto = compra.getProductoId();
        int cantidad = compra.getCantidad();
        Date fecha = Date.valueOf(compra.getFecha());
        try (SQLite base = new SQLite()) {
            Connection conn = base.getConnection();
            // Crear la consulta SQL
            String sentencia = "INSERT INTO ventas (idProducto, idUsuario, cantidad, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setInt(1, idProducto);
            statement.setInt(2, idUsuario);
            statement.setInt(3, cantidad);
            statement.setDate(4, fecha);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void datosJson() {
        try (SQLite base = new SQLite()) {
            //comprobamos si hay datos insertados en la BBDD
            Connection con = base.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM producto");
            if (rs.next()) {
                return; // Si los hay nos salimos del metodo
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si no insertamos los del JSON
        LectorJson.leer();
        for (Producto produc : LectorJson.getProductos()) {
            productoBBDDInsert(produc);
        }
        for (Usuario usuario : LectorJson.getUsuarios()) {
            usuarioBBDDInsert(usuario);
        }
    }

    public static void productoBBDDInsert(Producto producto) {
        String nombre = producto.getNombre();
        String caract = producto.getCaracteristicas();
        String descrip = producto.getDescripcion();
        String categoria = producto.getCategoria();
        StringBuilder imagenes = new StringBuilder();
        for (String img : producto.getImagenes()) {
            imagenes.append(img).append(","); // Concatenar la url de imágenes separadas por coma.
        }
        // Eliminar la última coma si hay imágenes.
        if (imagenes.length() > 0) {
            imagenes.deleteCharAt(imagenes.length() - 1);
        }
        int id = producto.getId();
        int inventario = producto.getInventario();
        double precio = producto.getPrecio();
        try (SQLite base = new SQLite()) {
            Connection conn = base.getConnection();
            // Crear la consulta SQL
            String sentencia = "INSERT INTO producto (id, nombre, categoria, precio, descripcion, imagenes, inventario, caracteristicas) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            // Preparar la consulta
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, categoria);
            statement.setDouble(4, precio);
            statement.setString(5, descrip);
            statement.setString(6, imagenes.toString());
            statement.setInt(7, inventario);
            statement.setString(8, caract);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void usuarioBBDDInsert(Usuario usuario) {
        String nombre = usuario.getNombre();
        String email = usuario.getEmail();
        String pais = usuario.getPais();
        String ciudad = usuario.getCiudad();
        String calle = usuario.getCalle();
        int numero = usuario.getNumero();
        int id = usuario.getId();
        try (SQLite base = new SQLite()) {
            Connection conn = base.getConnection();
            // Crear la consulta SQL
            String sentencia = "INSERT INTO usuarios (id, nombre, email, pais, ciudad, calle, numero) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sentencia);
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, email);
            statement.setString(4, pais);
            statement.setString(5, ciudad);
            statement.setString(6, calle);
            statement.setInt(7, numero);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Insertar el historial de compras del usuario
        for (Compra comp : usuario.getHistorialCompras()) {
            compraBBDDInsert(comp, id);
        }
    }
    
    public static ArrayList<Usuario> usuarioBBDDSacar(){
        ArrayList<Usuario> usuarios = new ArrayList();
        try (SQLite base = new SQLite()) {    
            Connection conn = base.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * from usuarios");
            while(rs.next()){                        
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String pais = rs.getString("pais");
                String ciudad = rs.getString("ciudad");
                String calle = rs.getString("calle");
                int numero = rs.getInt("numero");
                Usuario usuario = new Usuario(id, nombre, email, calle, numero, ciudad, pais);
                usuarios.add(usuario);
            }
        }catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
    public static ArrayList<String> categoriasBBDDSacar(){
        //USAR SELECT DISTINC PARA COJER LAS CATEGORIAS
        ArrayList<String> categorias = new ArrayList();
        try (SQLite base = new SQLite()) {    
            Connection conn = base.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT DISTINCT categoria FROM producto");
            while(rs.next()){                        
                String categoria = rs.getString("categoria");
                categorias.add(categoria);
            }
        }catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }
    
    public static ArrayList<Producto> prodcutoBBDDSacar(String categoria){
        ArrayList<Producto> productos = new ArrayList();
        try (SQLite base = new SQLite()) {    
            Connection conn = base.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM producto WHERE categoria = ?");
            pstm.setString(1, categoria);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){                        
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                String descripcion = rs.getString("descripcion");
                String imagen = rs.getString("imagenes");
                ArrayList<String> imagenes = new ArrayList();
                for(String imgURL : imagen.split(",")){
                    imagenes.add(imgURL);
                }
                int inventario = rs.getInt("inventario");
                String caracteristicas = rs.getString("caracteristicas");
                Producto producto = new Producto(categoria, id, nombre, precio, descripcion, caracteristicas, imagenes, inventario);
                productos.add(producto);
            }
        }catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }
    
        public static Usuario prodcutoBBDDSacar(Usuario usuario){
        try (SQLite base = new SQLite()) {    
            Connection conn = base.getConnection();
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM producto WHERE idUsuario = ?");
            pstm.setInt(1, usuario.getId());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){                        
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                float precio = rs.getFloat("precio");
                String descripcion = rs.getString("descripcion");
                String imagen = rs.getString("imagenes");
                ArrayList<String> imagenes = new ArrayList();
                for(String imgURL : imagen.split(",")){
                    imagenes.add(imgURL);
                }
                int inventario = rs.getInt("inventario");
                String caracteristicas = rs.getString("caracteristicas");
            }
        }catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}
