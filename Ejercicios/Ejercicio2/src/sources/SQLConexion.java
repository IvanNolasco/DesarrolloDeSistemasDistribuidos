package sources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SQLConexion extends Thread {
    protected Socket socketCliente;
    protected BufferedReader entrada;
    protected PrintStream salida;
    protected String consulta;
    public SQLConexion(Socket socketCliente){
        this.socketCliente = socketCliente;
        try {
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            salida = new PrintStream(this.socketCliente.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
            try {
                this.socketCliente.close();
            } catch (IOException e2) {              
            }
            return;
        }
        start();
    }
    
    private static final int PUERTOESCUCHA = 6666;
    private void jButton1ActionPerformed(java.atw.even.ActionEvent evt){
        abrirSocket();
    }

    private void abrirSocket() {
        Socket socket = null;
        try {
            socket = new Socket("localhost", PUERTOESCUCHA);
            PrintStream salida = new PrintStream(socket.getOutputStream());
            salida.println(jTextFIeld1.getText());
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                socket.close();
            } catch (Exception e) {
                
            }
        }
    }
}

