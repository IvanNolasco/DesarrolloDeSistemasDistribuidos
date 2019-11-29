package calculatorrmi;

import java.io.Serializable;

public class Mensajes implements Serializable{
    String m;
    int v;
    
    public Mensajes(String m, int v){
        this.m=m;
        this.v=v;
    }
    public static String getMensaje(){
        return "Un mensaje corto";
    }
}
