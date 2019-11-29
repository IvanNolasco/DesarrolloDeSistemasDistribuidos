/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author jonat
 */
public class DAOroles {
    
    public List<Roles> getRoles() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio16OctPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        //Query q = em.createNamedQuery("Alumnos.findAll");
        TypedQuery<Roles> q = em.createNamedQuery("Roles.findAll", Roles.class);
        List<Roles> listaRoles = q.getResultList();
        System.out.println(listaRoles);
        em.close();
        return listaRoles;
    }
    public Roles getRolesPorNombre(String cad)  {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio16OctPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        //Query q = em.createNamedQuery("Alumnos.findAll");
        TypedQuery<Roles> q = em.createNamedQuery("Roles.findByNombreRol", Roles.class);
        q.setParameter("nombreRol", cad);
        Roles car = q.getSingleResult();
        em.close();
        return car;
    }
    
    public Roles getRolesPorID(int cad) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio16OctPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.getTransaction().commit();
        //Query q = em.createNamedQuery("Alumnos.findAll");
        TypedQuery<Roles> q = em.createNamedQuery("Roles.findById", Roles.class);
        q.setParameter("id", cad);
        Roles car = q.getSingleResult();
        em.close();
        return car;
    }
    
    public int createRoles(Roles al)  {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio16OctPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //Query q = em.createNamedQuery("Alumnos.findAll");
        em.persist(al);
        em.getTransaction().commit();
        em.close();
        return 0;
    }

    public int updateRoles(Roles al) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio16OctPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //Query q = em.createNamedQuery("Alumnos.findAll");
        em.merge(al);
        em.getTransaction().commit();
        em.close();
        System.out.println("hecho");
        return 0;
    }
    
    
}
