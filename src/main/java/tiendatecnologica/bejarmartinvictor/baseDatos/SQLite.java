/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendatecnologica.bejarmartinvictor.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
        LectorJson.leer();
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
                + "precio REAL,"
                + "descripcion TEXT,"
                + "pantalla TEXT,"
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

        statement.executeUpdate("CREATE TABLE IF NOT EXISTS imagenes ("
                + "id INTEGER PRIMARY KEY,"
                + "idProducto INTEGER,"
                + "imagenurl TEXT,"
                + "FOREIGN KEY(idProducto) REFERENCES producto(id)"
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
        
    public void datosJson(){
        
    }
}
