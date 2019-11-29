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
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.json.JsonReader;
import javax.json.JsonValue;
/**
 *
 * @author ricardotg
 */
public class ArticuloWS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        Articulo a = client.target("http://127.0.0.1:8000/articulos/A001/")
                .request(MediaType.APPLICATION_JSON)
                .get(Articulo.class);
        a.setExistencias(800);
        Response r = client.target("http://127.0.0.1:8000/articulos/A001/")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(a, MediaType.APPLICATION_JSON));
        a.setClaveArticulo("A005");
        Response r2 = client.target("http://127.0.0.1:8000/articulos/")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(a, MediaType.APPLICATION_JSON));
        
        Response r3 = client.target("http://127.0.0.1:8000/articulos/A005/")
                .request(MediaType.APPLICATION_JSON)
                .delete();
        //Obtener todos los articulos
        String jsonList = client.target("http://127.0.0.1:8000/articulos/")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        List<Articulo> articulos = Articulo.getArticulosList(jsonList);
    }
    
}
