package javaapplication17;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComprobantePagoServicios implements ComprobanteDigital {

    private String titularCuentaRetiro;
    private String noCajero;
    private String ubicacionCajero;
    private String cuentaRetiro;
    private String servicioEmpresa;
    private String referencia;
    private double importe;
    private String guiaCIE;
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

    public String getServicioEmpresa() {
        return servicioEmpresa;
    }

    public void setServicioEmpresa(String servicioEmpresa) {
        this.servicioEmpresa = servicioEmpresa;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getGuiaCIE() {
        return guiaCIE;
    }

    public void setGuiaCIE(String guiaCIE) {
        this.guiaCIE = guiaCIE;
    }

    @Override
    public Date getFechaHoraOperacion() {
        return fechaHoraOperacion;
    }

    @Override
    public void setFechaHoraOperacion(Date fechaHoraOperacion) {
        this.fechaHoraOperacion = fechaHoraOperacion;
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

    public String toString1() {
        return "Titular cuenta retiro: \n Número de cajero: \n Cuenta retiro: \n Servicio empresa: \n Referencia: \n Importe: \n Guía CIE: \n Fecha operación \n Hora operación \nFolio operacion \n Autorización de operación:";
    }

    public String toString2() throws ParseException {
        
        Date now = fechaHoraOperacion;
            String format = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String format2 = new SimpleDateFormat("HH:mm:ss").format(now);
        return titularCuentaRetiro + "\n" + noCajero + "\n" + ubicacionCajero + "\n" + cuentaRetiro + "\n" + servicioEmpresa + "\n" + referencia + "\n" + importe + "\n" + guiaCIE + "\n" + format + "\n" + format2 +"\n" + folioOperacion + "\n" + autorizacionOperacion;
    }

    public ComprobantePagoServicios(String titularCuentaRetiro, String noCajero, String ubicacionCajero, String cuentaRetiro, String servicioEmpresa, String referencia, double importe, String guiaCIE, Date fechaHoraOperacion, long folioOperacion, String autorizacionOperacion) {
        this.titularCuentaRetiro = titularCuentaRetiro;
        this.noCajero = noCajero;
        this.ubicacionCajero = ubicacionCajero;
        this.cuentaRetiro = cuentaRetiro;
        this.servicioEmpresa = servicioEmpresa;
        this.referencia = referencia;
        this.importe = importe;
        this.guiaCIE = guiaCIE;
        this.fechaHoraOperacion = fechaHoraOperacion;
        this.folioOperacion = folioOperacion;
        this.autorizacionOperacion = autorizacionOperacion;
    }
}
