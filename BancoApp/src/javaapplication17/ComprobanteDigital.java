package javaapplication17;

import java.util.Date;

public interface ComprobanteDigital {
    //Titular Cuenta Cajero
    public String getTitularCuentaRetiro();
    
    public void setTitularCuentaRetiro(String titularCuentaRetiro);
    
    //Numero Cajero
    public String getNoCajero();
    
    public void setNoCajero(String noCajero);
    
    //Ubicacion Cajero
    public String getUbicacionCajero();
    
    public void setUbicacionCajero(String ubicacion);
    
    //Cuenta de Retiro
    public String getCuentaRetiro();
    
    public void setCuentaRetiro(String cuentaRetiro);
    
    //Fecha/Hora Operacion
    public Date getFechaHoraOperacion();
    
    public void setFechaHoraOperacion(Date fechaOperacion);
   
    //Folio Operacion
    public long getFolioOperacion();
    
    public void setFolioOperacion(long folioOperacion);
    
    //Autorizacion Operación
    public String getAutorizacionOperacion();

    public void setAutorizacionOperacion(String autorizacionOperacion);
}