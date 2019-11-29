package ObjetosDAO;

import Conexion.Conexion;
import Objetos.Cliente;
import Objetos.Cuenta;
import Objetos.Cuenta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CuentaDAO {
    
    public static synchronized boolean insertarCuenta(Cuenta cta) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean rescuesta = false;
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false); //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call scInsertarCuenta(?,?)}"); //se define una cadena con la sentencia SQL como llamada
            cs.setDouble(1, cta.getBalance());   
            cs.setInt(2, cta.getCliente().getIdcliente());
               

            rescuesta = cs.executeUpdate() == 1 ? true : false;

            if (rescuesta) {
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

        return rescuesta;
    }
    
    public static synchronized boolean actualizarCuenta(Cuenta cta) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean rescuesta = false;      
                
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call scActualizarCuenta(?,?,?)}"); //se define una cadena con la sentencia SQL como llamada
            
            cs.setDouble(1, cta.getBalance());
            System.out.println(cta.toString());
            cs.setInt(2, cta.getCliente().getIdcliente());
            cs.setLong(3, cta.getIdcuenta());
                        
            rescuesta = cs.executeUpdate() == 1 ? true : false;

            if (rescuesta) {
                cn.commit(); //si se ejecuto el update, hacer commit (confirmar los cambios para que se reflejen en el server, sin poder deshacerse)
            } else {
                Conexion.deshacerCambios(cn); //se deshace culaquier cambio hacho en la BD
            }
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);

        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }

        return rescuesta;
    }
    
    public static synchronized Cuenta leerCuenta(Long idCuenta) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;        
        Boolean rescuesta;
        Cuenta cta=new Cuenta();
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call scSelectCuenta(?)}");    //se define una cadena con la sentencia SQcomo llamada
            cs.setLong(1, idCuenta);

            rescuesta = cs.execute();

            ResultSet rs1 = cs.getResultSet();

            while (rs1.next()) {
                cta.setIdcuenta(rs1.getLong(1));
                cta.setBalance(rs1.getDouble(2));                 
                Cliente auxCte=new Cliente();
                auxCte.setIdcliente(rs1.getInt(3));
                cta.setCliente(auxCte);
            }
            rs1.close(); //se cierrael conjunto de tuplas resultado

            if (rescuesta) {
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

        return cta;
    }

public static synchronized ArrayList<Cuenta> leerCuentasByCliente(int idCte) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;        
        Boolean rescuesta;
        ArrayList<Cuenta> ctas=new ArrayList<>();
        Cuenta cta=new Cuenta();
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spSelectCtaByCte(?)}");    //se define una cadena con la sentencia SQcomo llamada
            cs.setInt(1, idCte);

            rescuesta = cs.execute();

            ResultSet rs1 = cs.getResultSet();

            while (rs1.next()) {
                cta.setIdcuenta(rs1.getLong(1));
                cta.setBalance(rs1.getDouble(2));                 
                Cliente auxCte=new Cliente();
                auxCte.setIdcliente(rs1.getInt(3));
                cta.setCliente(auxCte);
                
                ctas.add(cta);
                cta=new Cuenta();
            }
            rs1.close(); //se cierrael conjunto de tuplas resultado

            if (rescuesta) {
                cn.commit(); //si se ejecuto el update, hacer commit (confirmar los cambios para que se reflejen en el server, sin poder deshacerse)
            } else {
                Conexion.deshacerCambios(cn); //se deshace culaquier cambio hacho en la BD
            }
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);

        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCallSt(cs);
            Conexion.cerrar(cn);
            e.printStackTrace(); //impresión de la pila de trazo de excepciones
        }

        return ctas;
    }
    
    public static synchronized boolean deleteCuenta(int idOp) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean rescuesta = false;
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call scDeleteCuenta(?)}");
            cs.setInt(1, idOp);

            rescuesta = cs.executeUpdate() == 1 ? true : false;

            if (rescuesta) {
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

        return rescuesta;
    }
}
