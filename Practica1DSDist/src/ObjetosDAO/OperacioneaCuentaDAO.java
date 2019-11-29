package ObjetosDAO;

import Conexion.Conexion;
import Objetos.Cuenta;
import Objetos.OperacionesCuenta;
import Objetos.OperacionesCuenta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author jonat
 */
public class OperacioneaCuentaDAO {

    public static synchronized boolean insertarOperacionCta(OperacionesCuenta opCta) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean respuesta = false;
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false); //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spInsertarOperacionCuenta(?,?,?,?)}"); //se define una cadena con la sentencia SQL como llamada
            cs.setLong(1, opCta.getCuenta().getIdcuenta());
            
            //fechas
            DateFormat formatter = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            Date myDate = formatter.parse(opCta.getFechaOperacion().toString());
            java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
            
            cs.setDate(2, sqlDate);
            ////fin de fechas
            System.out.println(String.valueOf(opCta.getTipoMovto()));
            cs.setString(3, String.valueOf(opCta.getTipoMovto()));
            
            System.out.println(String.valueOf(opCta.getCantidad()));
            cs.setDouble(4, opCta.getCantidad());

            respuesta = cs.executeUpdate() == 1 ? true : false;
            System.out.println("Objeto: "+opCta.toString());
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

    public static synchronized boolean actualizarOperacionCta(OperacionesCuenta opCta) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean respuesta = false;

        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spActualizarOperacionCta(?,?,?,?,?)}"); //se define una cadena con la sentencia SQL como llamada
            cs.setLong(1, opCta.getCuenta().getIdcuenta());
            cs.setString(2, opCta.getFechaOperacion().toString());
            cs.setInt(3, opCta.getTipoMovto());
            cs.setDouble(4, opCta.getCantidad());
            cs.setLong(5, opCta.getIdOperacionesCuenta());

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

    public static synchronized OperacionesCuenta leerOperacionCta(int idOperacionCta) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        Boolean respuesta;
        OperacionesCuenta opCta = new OperacionesCuenta();
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spSelectOneOperacionCta(?)}");    //se define una cadena con la sentencia SQcomo llamada
            cs.setInt(1, idOperacionCta);

            respuesta = cs.execute();

            ResultSet rs1 = cs.getResultSet();

            while (rs1.next()) {
                opCta.setIdOperacionesCuenta(rs1.getLong(1));

                Cuenta ctaAux = new Cuenta();
                ctaAux.setIdcuenta(rs1.getLong(2));
                opCta.setCuenta(ctaAux);

                opCta.setFechaOperacion(rs1.getDate(3));
                opCta.setTipoMovto(rs1.getString(4).charAt(0));
                opCta.setCantidad(rs1.getDouble(5));

            }
            rs1.close(); //se cierrael conjunto de tuplas resultado

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

        return opCta;
    }

    public static synchronized boolean deleteOperacionCta(int idOp) {//hacer uno para cada CRUD, y otro mas para mosrar porductos por categoría, de modo que al final queden 6 métodos
        Connection cn = null;
        CallableStatement cs = null;
        boolean respuesta = false;
        try {
            cn = Conexion.getConexion();    //se obtiene una conexión
            cn.setAutoCommit(false);        //se deshabilita la opcion de hacer commit por cada transacción
            cs = cn.prepareCall("{call spDeleteOperacionCta(?)}");
            cs.setInt(1, idOp);

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
