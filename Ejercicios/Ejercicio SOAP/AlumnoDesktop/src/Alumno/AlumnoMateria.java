/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alumno;

import org.me.alumno.Alumno;

/**
 *
 * @author ricardotg
 */
public class AlumnoMateria {
    public static java.util.List<org.me.alumno.Alumno> listAlumno() {
        org.me.alumno.AlumnoWS_Service service = new org.me.alumno.AlumnoWS_Service();
        org.me.alumno.AlumnoWS port = service.getAlumnoWSPort();
        return port.listAlumno();
    }

    public static boolean createAlumno(org.me.alumno.Alumno alumno) {
        org.me.alumno.AlumnoWS_Service service = new org.me.alumno.AlumnoWS_Service();
        service.setHandlerResolver(new JaxWsHandlerResolver());
        org.me.alumno.AlumnoWS port = service.getAlumnoWSPort();
        return port.createAlumno(alumno);
    }

    public static boolean deleteAlumno(java.lang.String noBoleta) {
        org.me.alumno.AlumnoWS_Service service = new org.me.alumno.AlumnoWS_Service();
        org.me.alumno.AlumnoWS port = service.getAlumnoWSPort();
        return port.deleteAlumno(noBoleta);
    }

    public static Alumno findAlumno(java.lang.String noBoleta) {
        org.me.alumno.AlumnoWS_Service service = new org.me.alumno.AlumnoWS_Service();
        org.me.alumno.AlumnoWS port = service.getAlumnoWSPort();
        return port.findAlumno(noBoleta);
    }
    
}
