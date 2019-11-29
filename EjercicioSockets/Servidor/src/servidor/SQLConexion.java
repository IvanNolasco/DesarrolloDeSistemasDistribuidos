package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLConexion extends Thread {

    protected Socket socketCliente;
    protected BufferedReader entrada;
    protected ObjectOutputStream salida;
    protected String consulta;

    public SQLConexion(Socket socketCliente) {
        this.socketCliente = socketCliente;
        try {
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            salida = new ObjectOutputStream(this.socketCliente.getOutputStream());
        } catch (IOException ex) {
            System.err.println(ex);
            try {
                this.socketCliente.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return;
        }
        start();
    }

    @Override
    public void run() {
        try {
            consulta = entrada.readLine();
            System.out.println("Consulta a ejecutar: " + consulta + ";");
            ejecutaSQL();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ejecutaSQL() {
        Connection cnn;
        Statement st;
        ResultSet rs;
        ResultSetMetaData resultadoMetaData;
        boolean existenMasFilas;
        String driver = "org.postgresql.Driver";
        String usuario = "postgres", clave = "postgres", registro;
        Integer numeroColumnas, i = 0;
        System.out.println("");
        try {
            Class.forName(driver);
            cnn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/proyecto_CI", usuario, clave);
            st = cnn.createStatement();
            rs = st.executeQuery(consulta);
            existenMasFilas = rs.next();

            if (!existenMasFilas) {
                salida.writeObject("No hay más filas.");
                return;
            }

            resultadoMetaData = rs.getMetaData();
            numeroColumnas = resultadoMetaData.getColumnCount();
            System.out.println(numeroColumnas + " columnas en el resultado");

            Tupla tuplasToSent = new Tupla();
            ArrayList<String> atribs;

            ArrayList<String> columnas = new ArrayList<>();
            for (int q = 1; q <= numeroColumnas; q++) {
                columnas.add(resultadoMetaData.getColumnName(q));
            }

            tuplasToSent.columnas = columnas;

            salida.writeObject(tuplasToSent.columnas);    //1er objeto que se envia

            while (existenMasFilas) {
                atribs = new ArrayList<>();
                for (i = 1; i <= numeroColumnas; i++) {
                    atribs.add(rs.getString(i));
                }

                tuplasToSent.valores.add(atribs);
                System.out.println(atribs);
                existenMasFilas = rs.next();
                salida.writeObject(atribs);
                entrada.readLine();
            }

            salida.writeObject(i);

            salida.writeObject(tuplasToSent);
            rs.close();
            st.close();
            cnn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
