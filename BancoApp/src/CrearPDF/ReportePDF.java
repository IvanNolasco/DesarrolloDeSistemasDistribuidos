/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CrearPDF;

import CrearPDF.Creacion;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication17.ComprobanteDomiciliacion;

/**
 *
 * @author jonat
 */
public class ReportePDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CrearPDF.Creacion nuevoTicket=new Creacion(new ComprobanteDomiciliacion("Ricardo Flores Magón ", "0567", "Reforma 560", "123123", "***345", "2", new Date(), 0, "099"));
        
        try {
            nuevoTicket.createPDF(new File("reporteS.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
