package Conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
//leerlos a partir de archivos de propiedades y comentar linea a linea que hace C/U
    //En qué consiste el patrón Data Access Object

    private static String url;
    private static String user;
    private static String password;

    public static synchronized boolean LookForProps() { //método para leer el archivo de properties
        InputStream inputstream = null;    //flujo de entrada para leer el archivo        

        Properties prop = new Properties(); //Lista de propiedades
        String filename = "config.properties"; //nombre  y ubicacion del archivo a leer
        try {
            inputstream = Conexion.class.getClassLoader().getResourceAsStream(filename); //se intenta obtener el recurso (archivo de propiedades) como flujo de bits
            if (inputstream == null) {
                System.out.println("No se encontró el archivo: " + filename);   //aviso de no encontrado
                return false;
            }
            //load a properties file from class path, inside static method
            prop.load(inputstream); //en la lista de propiedades se carga el flujo, que "leyo" el archivo de propiedades

            //get the property value and print it out

            url = prop.getProperty("url"); //se cambiar el parametro url de la clase
            user = prop.getProperty("user"); //se cambia el param user
            password = prop.getProperty("password"); //se cambia el param contraseña

            return true;

        } catch (IOException ex) {
            ex.printStackTrace(); //impresión de la pila de trazo de excepciones
            return false;
        } finally {
            if (inputstream != null) {
                try {
                    inputstream.close();//cierre del flujo de lectura del archivo de props
                } catch (IOException e) {
                    e.printStackTrace();//impresión de la pila de trazo de excepciones
                }
            }
        }
    }

    public static synchronized Connection getConexion() {//metodo para obtener una conexión a la BD
        Connection cn = null;
        LookForProps();//se buscan atributos de conexion en el archivo
        try {
            Class.forName("com.mysql.jdbc.Driver");//se crea referencia al manejador para la conexion a la BD
            cn = DriverManager.getConnection(url, user, password); //se crea la conexión dados los atributos leidos
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        } finally {
            return cn; //se devuelve la conexion
        }
    }

    public static synchronized void cerrarCallSt(CallableStatement cs) {//Metodo para cerrar el CallStatemnt
        try {
            cs.close();
        } catch (SQLException e) {
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }
    }

    public static synchronized void cerrar(Connection rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }
    }

    public static synchronized void error(Connection cn) {
        try {
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void deshacerCambios(Connection cn) {
        try {
            cn.rollback();
        } catch (SQLException e) {
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }
    }
}
