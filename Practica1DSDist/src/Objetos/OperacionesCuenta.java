/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author jonat
 */
public class OperacionesCuenta implements Serializable{
    private Long idOperacionesCuenta;
    private Cuenta cuenta;
    private Date fechaOperacion;
    private char tipoMovto;
    private double cantidad;

    public OperacionesCuenta(Long idOperacionesCuenta, Cuenta cuenta, Date fechaOperacion, char tipoMovto, double cantidad) {
        this.idOperacionesCuenta = idOperacionesCuenta;
        this.cuenta = cuenta;
        this.fechaOperacion = fechaOperacion;
        this.tipoMovto = tipoMovto;
        this.cantidad = cantidad;
    }

    public OperacionesCuenta() {
    }
    
    

    public Long getIdOperacionesCuenta() {
        return idOperacionesCuenta;
    }

    public void setIdOperacionesCuenta(Long idOperacionesCuenta) {
        this.idOperacionesCuenta = idOperacionesCuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Date getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public char getTipoMovto() {
        return tipoMovto;
    }

    public void setTipoMovto(char tipoMovto) {
        this.tipoMovto = tipoMovto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "OperacionesCuenta{" + "idOperacionesCuenta=" + idOperacionesCuenta + ", cuenta=" + cuenta + ", fechaOperacion=" + fechaOperacion + ", tipoMovto=" + tipoMovto + ", cantidad=" + cantidad + '}';
    }
    
    
    
}
