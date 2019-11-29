/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author jonat
 */
public class Cuenta implements Serializable{
    private Long idcuenta;
    private double balance;
    private Cliente cliente;

    public Cuenta(Long idcuenta, double balance, Cliente cliente) {
        this.idcuenta = idcuenta;
        this.balance = balance;
        this.cliente = cliente;
    }

    public Cuenta() {
    }
    
    

    public Long getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(Long idcuenta) {
        this.idcuenta = idcuenta;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "idcuenta=" + idcuenta + ", balance=" + balance + ", cliente=" + cliente + '}';
    }
    
    
    
}
