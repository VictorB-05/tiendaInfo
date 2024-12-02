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
}
