package entity;
// Generated 6/11/2019 04:07:07 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Alumno generated by hbm2java
 */
public class Alumno  implements java.io.Serializable {


     private String noBoleta;
     private String nombre;
     private String paterno;
     private String materno;
     private String email;
     private Set materias = new HashSet(0);

    public Alumno() {
    }

	
    public Alumno(String noBoleta, String nombre, String paterno, String materno, String email) {
        this.noBoleta = noBoleta;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.email = email;
    }
    public Alumno(String noBoleta, String nombre, String paterno, String materno, String email, Set materias) {
       this.noBoleta = noBoleta;
       this.nombre = nombre;
       this.paterno = paterno;
       this.materno = materno;
       this.email = email;
       this.materias = materias;
    }
   
    public String getNoBoleta() {
        return this.noBoleta;
    }
    
    public void setNoBoleta(String noBoleta) {
        this.noBoleta = noBoleta;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPaterno() {
        return this.paterno;
    }
    
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }
    public String getMaterno() {
        return this.materno;
    }
    
    public void setMaterno(String materno) {
        this.materno = materno;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Set getMaterias() {
        return this.materias;
    }
    
    public void setMaterias(Set materias) {
        this.materias = materias;
    }




}


