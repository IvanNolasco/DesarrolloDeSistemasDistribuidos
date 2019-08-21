
package paquete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author navi_
 */
public class Servidor {
    public static final int PUERTO = 5000;
    
    private static String metodoUno(){
        GregorianCalendar cal = new GregorianCalendar();
        Date time = cal.getTime();
        DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT);
        return df.format(time);
    }
    
    private static String metodoDos(){
        GregorianCalendar cal = new GregorianCalendar();
        Date fecha = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        return df.format(fecha);
    }
    
    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(PUERTO);
        while(true){
            Socket sc = ss.accept();
            PrintWriter pw = new PrintWriter(sc.getOutputStream());
            pw.println(metodoUno());
            pw.println(metodoDos());
            pw.close();
        }
    }


}
