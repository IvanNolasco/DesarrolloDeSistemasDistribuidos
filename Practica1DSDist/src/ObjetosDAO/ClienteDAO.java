/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosDAO;

import Conexion.Conexion;
import Objetos.Cliente;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author jonat
 */
public class ClienteDAO {
    
    public static synchronized boolean insertarCliente(Cliente cliente) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean respuesta = false;
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false); //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spInsertarCliente(?,?,?,?,?,?,?,?)}"); //se define una cadena con la sentencia SQL como llamada
            cs.setString(1, cliente.getContrasenia());          
            cs.setString(2, cliente.getNombre());
            cs.setString(3, cliente.getApellidoPaterno());
            cs.setString(4, cliente.getApellidoMaterno());
            cs.setString(5, cliente.getNumeroTelefonico());
            cs.setString(6, cliente.getCalle());
            cs.setString(7, cliente.getColonia());
            cs.setInt(8, cliente.getNumero());

            respuesta = cs.executeUpdate() == 1 ? true : false;

            if (respuesta) {
                cn.commit();    //si se ejecuto el update, hacer commit (confirmar los cambios para que se reflejen en el server, sin poder deshacerse)
            } else {
                Conexion.deshacerCambios(cn);
            }
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);

        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }

        return respuesta;
    }
    
    public static synchronized boolean actualizarCliente(Cliente cliente) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean respuesta = false;      
                
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spActualizarCliente(?,?,?,?,?,?,?,?,?)}"); //se define una cadena con la sentencia SQL como llamada
            cs.setString(1, cliente.getContrasenia());          
            cs.setString(2, cliente.getNombre());
            cs.setString(3, cliente.getApellidoPaterno());
            cs.setString(4, cliente.getApellidoMaterno());
            cs.setString(5, cliente.getNumeroTelefonico());
            cs.setString(6, cliente.getCalle());
            cs.setString(7, cliente.getColonia());
            cs.setInt(8, cliente.getNumero());
            cs.setInt(9, cliente.getIdcliente());

            respuesta = cs.executeUpdate() == 1 ? true : false;

            if (respuesta) {
                cn.commit(); //si se ejecuto el update, hacer commit (confirmar los cambios para que se reflejen en el server, sin poder deshacerse)
            } else {
                Conexion.deshacerCambios(cn); //se deshace culaquier cambio hacho en la BD
            }
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);

        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }

        return respuesta;
    }
    
    public static synchronized Cliente leerCliente(int idCliente) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;        
        Boolean respuesta;
        Cliente cte=new Cliente();
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spSelectOneCliente(?)}");    //se define una cadena con la sentencia SQcomo llamada
            cs.setInt(1, idCliente);

            respuesta = cs.execute();

            try (ResultSet rs1 = cs.getResultSet()) {
                while (rs1.next()) {
                    System.out.println(rs1.getString(4));
                    cte.setIdcliente(rs1.getInt(1));
                    cte.setContrasenia(rs1.getString(2));
                    cte.setNombre(rs1.getString(3));
                    cte.setApellidoPaterno(rs1.getString(4));
                    cte.setApellidoMaterno(rs1.getString(5));
                    cte.setNumeroTelefonico(rs1.getString(6));
                    cte.setCalle(rs1.getString(7));
                    cte.setColonia(rs1.getString(8));
                    cte.setNumero(rs1.getInt(9));
                }
                //se cierrael conjunto de tuplas resultado
            }

            if (respuesta) {
                cn.commit(); //si se ejecuto el update, hacer commit (confirmar los cambios para que se reflejen en el server, sin poder deshacerse)
            } else {
                Conexion.deshacerCambios(cn); //se deshace culaquier cambio hacho en la BD
            }
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);

        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }
        System.out.println("pwd found: "+cte.getContrasenia());
        System.out.println(cte.getApellidoMaterno());
        return cte;
    }
    
    public static synchronized boolean deleteCliente(int idCte) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean respuesta = false;
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spDeleteCliente(?)}");
            cs.setInt(1, idCte);

            respuesta = cs.executeUpdate() == 1 ? true : false;

            if (respuesta) {
                cn.commit();
            } else {
                Conexion.deshacerCambios(cn);
            }
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);

        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }

        return respuesta;
    }
    
}
