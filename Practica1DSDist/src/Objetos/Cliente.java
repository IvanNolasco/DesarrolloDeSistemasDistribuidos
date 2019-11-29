package Objetos;

import java.io.Serializable;

public class Cliente implements Serializable{
    private int idcliente;
    private String contrasenia;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroTelefonico;
    private String calle;
    private String colonia;
    private int numero;

    public Cliente(int idcliente, String contrasenia, String nombre, String apellidoPaterno, String apellidoMaterno, String numeroTelefonico, String calle, String colonia, int numero) {
        this.idcliente = idcliente;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.numeroTelefonico = numeroTelefonico;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public Cliente() {
    }    
    
    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idcliente=" + idcliente + ", contrasenia=" + contrasenia + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", numeroTelefonico=" + numeroTelefonico + ", calle=" + calle + ", colonia=" + colonia + ", numero=" + numero + '}';
    }
    
}
