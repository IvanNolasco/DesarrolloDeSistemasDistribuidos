package javaapplication17;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComprobanteDomiciliacion implements ComprobanteDigital{
    private String titularCuentaRetiro;
    private String noCajero;
    private String ubicacionCajero;
    private String cuentaRetiro;
    private String tarjetaDomiciliada;
    private String pagoDomiciliado;
    private Date fechaHoraOperacion;
    private long folioOperacion;
    private String autorizacionOperacion;
    
    @Override
    public String getTitularCuentaRetiro() {
       return this.titularCuentaRetiro;
    }

    @Override
    public void setTitularCuentaRetiro(String titularCuentaRetiro) {
        this.titularCuentaRetiro = titularCuentaRetiro;
    }

    @Override
    public String getNoCajero() {
        return this.noCajero;
    }

    @Override
    public void setNoCajero(String noCajero) {
        this.noCajero = noCajero;
    }

    @Override
    public String getUbicacionCajero() {
        return this.ubicacionCajero;
    }

    @Override
    public void setUbicacionCajero(String ubicacionCajero) {
        this.ubicacionCajero = ubicacionCajero;
    }
    
    @Override
    public String getCuentaRetiro() {
        return this.cuentaRetiro;
    }

    @Override
    public void setCuentaRetiro(String cuentaRetiro) {
        this.cuentaRetiro = cuentaRetiro;
    }
    
    public String getTarjetaDomiciliada(){
        return this.tarjetaDomiciliada;
    }
    
    public void setTarjetaDomiciliada(String tarjetaDomiciliada){
        this.tarjetaDomiciliada = tarjetaDomiciliada;
    }
    
    public String getPagoDomiciliado(){
       return this.pagoDomiciliado;
    }
    
    public void setPagoDomiciliado(String pagoDomiciliado){
        this.pagoDomiciliado = pagoDomiciliado;
    }

    @Override
    public Date getFechaHoraOperacion() {
        return this.fechaHoraOperacion;
    }

    @Override
    public void setFechaHoraOperacion(Date fechaOperacion) {
        this.fechaHoraOperacion = fechaOperacion;
    }

    @Override
    public long getFolioOperacion() {
        return this.folioOperacion;
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

    public ComprobanteDomiciliacion(String titularCuentaRetiro, String noCajero, String ubicacionCajero, String cuentaRetiro, String tarjetaDomiciliada, String pagoDomiciliado, Date fechaHoraOperacion, long folioOperacion, String autorizacionOperacion) {
        this.titularCuentaRetiro = titularCuentaRetiro;
        this.noCajero = noCajero;
        this.ubicacionCajero = ubicacionCajero;
        this.cuentaRetiro = cuentaRetiro;
        this.tarjetaDomiciliada = tarjetaDomiciliada;
        this.pagoDomiciliado = pagoDomiciliado;
        this.fechaHoraOperacion = fechaHoraOperacion;
        this.folioOperacion = folioOperacion;
        this.autorizacionOperacion = autorizacionOperacion;
    }

    
    public String toString1() {
        return "Titular cuenta retiro: \n Número de cajero:  \nUbicacion cajero: \nCuenta retiro: \nTarjeta domiciliada: \nPago domiciliado:  \nFecha de operacion: \nHora de operación: \nFolio operacion: \nAutorizacion operacion:";
    }

  
    public String toString2() throws ParseException {
            Date now = fechaHoraOperacion;
            String format = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String format2 = new SimpleDateFormat("HH:mm:ss").format(now);
            return  titularCuentaRetiro + "\n" + noCajero + "\n" + ubicacionCajero + "\n" + cuentaRetiro + "\n" + tarjetaDomiciliada + "\n" + pagoDomiciliado + "\n" + format  + "\n" + format2 + "\n" + folioOperacion + "\n" + autorizacionOperacion;
        
    }
    
    
    
    

    
    
    
}
