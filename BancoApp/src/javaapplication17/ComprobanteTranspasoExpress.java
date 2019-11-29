package javaapplication17;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ComprobanteTranspasoExpress implements ComprobanteDigital{
    private String titularCuentaRetiro;
    private String noCajero;
    private String ubicacionCajero;
    private String cuentaRetiro;
    private String cuentaExpress;
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

    public String getCuentaExpress() {
        return cuentaExpress;
    }

    public void setCuentaExpress(String cuentaExpress) {
        this.cuentaExpress = cuentaExpress;
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

    public ComprobanteTranspasoExpress(String titularCuentaRetiro, String noCajero, String ubicacionCajero, String cuentaRetiro, String cuentaExpress, String Beneficiario, double importe, Date fechaHoraOperacion, long folioOperacion, String autorizacionOperacion) {
        this.titularCuentaRetiro = titularCuentaRetiro;
        this.noCajero = noCajero;
        this.ubicacionCajero = ubicacionCajero;
        this.cuentaRetiro = cuentaRetiro;
        this.cuentaExpress = cuentaExpress;
        this.Beneficiario = Beneficiario;
        this.importe = importe;
        this.fechaHoraOperacion = fechaHoraOperacion;
        this.folioOperacion = folioOperacion;
        this.autorizacionOperacion = autorizacionOperacion;
    }

    public String toString1() {
        return "Titular cuenta retiro \n Núm.Cajero: \nUbicación cajero: \nCuenta retiro:  \nCuenta Express: \nBeneficiario: \nImporte: \nFecha operación: \nHora operación:\nFolio operacion: \nAutorizacion operacion:";
    }

    public String toString2() throws ParseException {
        Date now = fechaHoraOperacion;
            String format = new SimpleDateFormat("yyyy-MM-dd").format(now);
            String format2 = new SimpleDateFormat("HH:mm:ss").format(now);
        return titularCuentaRetiro + "\n" + noCajero + "\n" + ubicacionCajero + "\n" + cuentaRetiro + "\n" + cuentaExpress + "\n" + Beneficiario + "\n" + importe + "\n" + format + "\n" + format2 + fechaHoraOperacion + "\n" + folioOperacion + "\n" + autorizacionOperacion;
    }
    
    

    
}
