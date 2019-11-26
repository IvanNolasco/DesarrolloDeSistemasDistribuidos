/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.alumno;

import controller.AlumnoJpaController;
import controller.MateriaJpaController;
import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import entity.Alumno;
import entity.Materia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author ricardotg
 */
@WebService(serviceName = "AlumnoWS")
@Stateless()
public class AlumnoWS {

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "listAlumno")
    public List<Alumno> listAlumno() {
        AlumnoJpaController alumnoController = new AlumnoJpaController();
        List<Alumno> alumnos = alumnoController.findAlumnoEntities();
        return alumnos;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createAlumno")
    public boolean createAlumno(@WebParam(name = "alumno") Alumno alumno) {
        AlumnoJpaController alumnoController = new AlumnoJpaController();
        try {
            alumnoController.create(alumno);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findAlumno")
    public Alumno findAlumno(@WebParam(name = "noBoleta") String noBoleta) {
        AlumnoJpaController alumnoController = new AlumnoJpaController();
        Alumno a = alumnoController.findAlumno(noBoleta);
        return a;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteAlumno")
    public boolean deleteAlumno(@WebParam(name = "noBoleta") String noBoleta) {
        AlumnoJpaController alumnoController = new AlumnoJpaController();
        try {
            alumnoController.destroy(noBoleta);
            return true;
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateAlumno")
    public boolean updateAlumno(@WebParam(name = "alumno") Alumno alumno) {
        AlumnoJpaController alumnoController = new AlumnoJpaController();
        try {
            alumnoController.edit(alumno);
            return true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "listMateria")
    public List<Materia> listMateria() {
        MateriaJpaController materiaController = new MateriaJpaController();
        List<Materia> materias = materiaController.findMateriaEntities();
        return materias;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createMateria")
    public boolean createMateria(@WebParam(name = "idMateria") int idMateria, @WebParam(name = "nombreMateria") String nombreMateria, @WebParam(name = "noBoleta") String noBoleta) {
        MateriaJpaController materiaController = new MateriaJpaController();
        AlumnoJpaController alumnoController = new AlumnoJpaController();
        Materia materia = new Materia(idMateria,nombreMateria);
        Alumno a = alumnoController.findAlumno(noBoleta);
        materia.setNoBoleta(a);
        try {
            materiaController.create(materia);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateMateria")
    public boolean updateMateria(@WebParam(name = "Materia") Materia materia) {
        MateriaJpaController materiaController = new MateriaJpaController();
        try {
            materiaController.edit(materia);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteMateria")
    public boolean deleteMateria(@WebParam(name = "idMateria") int idMateria) {
        MateriaJpaController materiaController = new MateriaJpaController();
        try {
            materiaController.destroy(idMateria);
            return true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AlumnoWS.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "findMateria")
    public Materia findMateria(@WebParam(name = "idMateria") int idMateria) {
        MateriaJpaController materiaController = new MateriaJpaController();
        Materia m = materiaController.findMateria(idMateria);
        return m;
    }
}
