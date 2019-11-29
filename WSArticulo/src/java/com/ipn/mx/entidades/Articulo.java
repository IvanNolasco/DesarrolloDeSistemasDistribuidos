/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jonat
 */
@Entity
@Table(name="Articulo", catalog="inventariosimple")
public class Articulo implements java.io.Serializable{
    @Id
    @Column(name="claveArticulo", nullable = false, length = 10, unique = true)     //OPCIONAL, se puede evitar, llamando igual a los atributos
    private String claveArticulo;
    private String descripcion;
    private double precio;
    private int existencias;

    public Articulo() {
    }

    public Articulo(String claveArticulo, String descripcion, double precio, int existencias) {
        this.claveArticulo = claveArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }

    public String getClaveArticulo() {
        return claveArticulo;
    }

    public void setClaveArticulo(String claveArticulo) {
        this.claveArticulo = claveArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    @Override
    public String toString() {
        return "Articulo{" + "claveArticulo=" + claveArticulo + ", descripcion=" + descripcion + ", precio=" + precio + ", existencias=" + existencias + '}';
    }
    
    
    
}
