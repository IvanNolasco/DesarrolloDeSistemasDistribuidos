package javaapplication17;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComprobanteRetiro implements ComprobanteDigital{
    private String titularCuentaRetiro;
    private String noCajero;
    private String ubicacionCajero;
    private String cuentaRetiro;
    private double importe;
    private Date fechaHoraOperacion;
    private long folioOperacion;
    private String autorizacionOperacion;

    @Override
    public String getTitularCuentaRetiro() {
        return titularCuentaRetiro;
    }

    @Override
    public void setTitularCuentaRetiro(String titularCuentaRetiro) {
        this.titularCuentaRetiro = titularCuentaRetiro;
    }

    @Override
    public String getNoCajero() {
        return noCajero;
    }

    @Override
    public void setNoCajero(String noCajero) {
        this.noCajero = noCajero;
    }

    @Override
    public String getUbicacionCajero() {
        return ubicacionCajero;
    }

    @Override
    public void setUbicacionCajero(String ubicacionCajero) {
        this.ubicacionCajero = ubicacionCajero;
    }
    
    @Override
    public String getCuentaRetiro() {
        return cuentaRetiro;
    }
    
    @Override
    public void setCuentaRetiro(String cuentaRetiro) {
        this.cuentaRetiro = cuentaRetiro;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public Date getFechaHoraOperacion() {
        return fechaHoraOperacion;
    }

    @Override
    public void setFechaHoraOperacion(Date fechaOperacion) {
        this.fechaHoraOperacion = fechaOperacion;
    }

    @Override
    public long getFolioOperacion() {
        return folioOperacion;
    }

    @Override
    public void setFolioOperacion(long folioOperacion) {
        this.folioOperacion = folioOperacion;
    }

    @Override
    public String getAutorizacionOperacion() {
        return autorizacionOperacion;
    }

    @Override
    public void setAutorizacionOperacion(String autorizacionOperacion) {
        this.autorizacionOperacion = autorizacionOperacion;
    }

    public ComprobanteRetiro(String titularCuentaRetiro, String noCajero, String ubicacionCajero, String cuentaRetiro, double importe, Date fechaHoraOperacion, long folioOperacion, String autorizacionOperacion) {
        this.titularCuentaRetiro = titularCuentaRetiro;
        this.noCajero = noCajero;
        this.ubicacionCajero = ubicacionCajero;
        this.cuentaRetiro = cuentaRetiro;
        this.importe = importe;
        this.fechaHoraOperacion = fechaHoraOperacion;
        this.folioOperacion = folioOperacion;
        this.autorizacionOperacion = autorizacionOperacion;
    }

    
    public String toString1() {
        return "Titular cuenta retiro: \n Núm.Cajero: \n Ubicación cajero: \n Cuenta retiro: \n Importe: \n Fecha operacion: \n Hora operacion: \n Folio operación: \n Autorizacion operacion:";
    }

    
    public String toString2() throws ParseException {
        Date now = fechaHoraOperacion;
            String format = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String format2 = new SimpleDateFormat("HH:mm:ss").format(now);
        return titularCuentaRetiro + "\n" + noCajero + "\n" + ubicacionCajero + "\n" + cuentaRetiro + "\n" + importe + "\n" +  format + "\n" + format2 + fechaHoraOperacion + "\n" + folioOperacion + "\n" + autorizacionOperacion + '}';
    }
    
    
    

    
    
    
    
}
