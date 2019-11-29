package javaapplication17;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonat
 */
public class ComprobantePagoTDCTerceros implements ComprobanteDigital{
    private String titularCuentaRetiro;
    private String noCajero;
    private String ubicacionCajero;
    private String cuentaRetiro;
    private String tarjetaDeposito;
    private String Beneficiario;
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

    public String getTarjetaDeposito() {
        return tarjetaDeposito;
    }

    public void setTarjetaDeposito(String tarjetaDeposito) {
        this.tarjetaDeposito = tarjetaDeposito;
    }

    public String getBeneficiario() {
        return Beneficiario;
    }

    public void setBeneficiario(String Beneficiario) {
        this.Beneficiario = Beneficiario;
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

    public ComprobantePagoTDCTerceros(String titularCuentaRetiro, String noCajero, String ubicacionCajero, String cuentaRetiro, String tarjetaDeposito, String Beneficiario, double importe, Date fechaHoraOperacion, long folioOperacion, String autorizacionOperacion) {
        this.titularCuentaRetiro = titularCuentaRetiro;
        this.noCajero = noCajero;
        this.ubicacionCajero = ubicacionCajero;
        this.cuentaRetiro = cuentaRetiro;
        this.tarjetaDeposito = tarjetaDeposito;
        this.Beneficiario = Beneficiario;
        this.importe = importe;
        this.fechaHoraOperacion = fechaHoraOperacion;
        this.folioOperacion = folioOperacion;
        this.autorizacionOperacion = autorizacionOperacion;
    }

    
    public String toString1() {
        return "Titular de cuenta de retiro: \nNúmero de cajero: \nUbicación cajero: \nCuenta de retiro: \nTarjeta Deposito: \nBeneficiario: \nImporte \nFecha operacion \nHora operación: \nFolio de operacion: \nAutorización de operación:";
    }

    public String toString2() throws ParseException{
            Date now = fechaHoraOperacion;
            String format = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String format2 = new SimpleDateFormat("HH:mm:ss").format(now);
            return titularCuentaRetiro + "\n" + noCajero + "\n" + ubicacionCajero + "\n" + cuentaRetiro + "\n" + tarjetaDeposito + "\n" + Beneficiario + "\n" + importe + "\n" + format + "\n" + format2 +"\n" + "\n" + folioOperacion + "\n" + autorizacionOperacion + '}';
         
    }
    
    
    
    
    

     
}
