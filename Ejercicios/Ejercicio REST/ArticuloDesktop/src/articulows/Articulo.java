/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulows;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author ricardotg
 */
public class Articulo {
    private String claveArticulo;
    private String descripcion;
    private double precio;
    private int existencias;

    public Articulo(String claveArticulo, String descripcion, double precio, int existencias) {
        this.claveArticulo = claveArticulo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Articulo() {
    }
    

    public String getClaveArticulo() {
        return claveArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setClaveArticulo(String claveArticulo) {
        this.claveArticulo = claveArticulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
    
    public static List<Articulo> getArticulosList(String jsonList){
        JsonReader jsonReader = Json.createReader(new StringReader(jsonList));
        JsonObject object = jsonReader.readObject();
        JsonArray list = object.getJsonArray("results");
        JsonObject job = list.getJsonObject(0);
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        for (JsonValue jv : list) {
            String claveArticulo = jv.asJsonObject().getJsonString("claveArticulo").getString();
            String descripcion = jv.asJsonObject().getJsonString("descripcion").getString();
            String precio  = jv.asJsonObject().getJsonString("precio").getString();
            int existencias = jv.asJsonObject().getJsonNumber("existencias").intValue();
            articulos
            .add(new Articulo(claveArticulo,descripcion,Float.parseFloat(precio),existencias));
            }
        jsonReader.close();
        return articulos;
    }
}
