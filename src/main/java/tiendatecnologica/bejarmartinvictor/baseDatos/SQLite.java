/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.baseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import tiendatecnologica.bejarmartinvictor.objetos.Compra;
import tiendatecnologica.bejarmartinvictor.objetos.Producto;
import tiendatecnologica.bejarmartinvictor.objetos.Usuario;

public class SQLite implements AutoCloseable{
        
    private Connection connection;
    private final String bd ="tienda.db";
//USAR SELECT DISTINC PARA COJER LAS CATEGORIAS
    
/**
     * Crea la conexión a la base de datos
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public SQLite() throws SQLException, ClassNotFoundException {
        //abra la conexión directaments desde el constructor
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:"+bd);
    }
    
    public void inicio() throws SQLException{
        Statement statement = connection.createStatement();

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY,"
                + "nombre TEXT,"
                + "email TEXT,"
                + "calle TEXT,"
                + "numero INTEGER,"
                + "ciudad TEXT,"
                + "pais TEXT"
                + ")");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS producto ("
                + "id INTEGER PRIMARY KEY,"
                + "nombre TEXT,"
                + "categoria TEXT,"
                + "precio REAL,"
                + "descripcion TEXT,"
                + "imagenes TEXT,"
                + "inventario REAL,"
                + "caracteristicas TEXT"
                + ")");

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS ventas ("
                + "id INTEGER PRIMARY KEY,"
                + "idProducto INTEGER,"
                + "idUsuario INTEGER,"
                + "cantidad INTEGER,"
                + "fecha DATE,"
                + "FOREIGN KEY(idProducto) REFERENCES producto(id),"
                + "FOREIGN KEY(idUsuario) REFERENCES usuarios(id)"
                + ")");
    }

    public Connection getConnection() {
        return connection;
    }
    
       /**
     * Cierra la conexión. Este método es llamado automáticamente al usar try-with-resources.
     * 
     * @throws SQLException
     */
	@Override
	public void close() throws SQLException {
            connection.close();
            System.out.println("Conexion cerrada...");
	}
        
    public static void productoBBDD(Producto producto) {
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
        float precio = producto.getPrecio();

        try (SQLite base = new SQLite()) {
            Connection conn = base.getConnection();

            // Crear la consulta SQL
            String query = "INSERT INTO producto (id, nombre, categoria, precio, descripcion, imagenes, inventario, caracteristicas) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // Preparar la consulta
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, nombre);
            statement.setString(3, categoria);
            statement.setFloat(4, precio);
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
    
    public static void usuarioBBDD(Usuario usuario) {
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
            String query = "INSERT INTO usuarios (id, nombre, email, pais, ciudad, calle, numero) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
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
            System.out.println(id);
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Insertar el historial de compras del usuario
        for (Compra comp : usuario.getHistorialCompras()) {
            compraBBDD(comp, id);
        }
    }

    public static void compraBBDD(Compra compra, int idUsuario) {
        int idProducto = compra.getProductoId();
        int cantidad = compra.getCantidad();
        Date fecha = Date.valueOf(compra.getFecha());

        try (SQLite base = new SQLite()) {
            Connection conn = base.getConnection();

            // Crear la consulta SQL
            String query = "INSERT INTO ventas (idProducto, idUsuario, cantidad, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
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
        
    public static void datosJson(){
        try (SQLite base = new SQLite()) {
            //comprobamos si hay datos insertados en la BBDD
            Connection con = base.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM producto");
            if (rs.next()) {
                return;// Si los hay nos salimos del metodo
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si no insertamos los del JSON
        LectorJson.leer();
        for(Producto produc : LectorJson.getProductos()){
            productoBBDD(produc);
        }
        for(Usuario usuario : LectorJson.getUsuarios()){
            usuarioBBDD(usuario);
        }
    }
}
