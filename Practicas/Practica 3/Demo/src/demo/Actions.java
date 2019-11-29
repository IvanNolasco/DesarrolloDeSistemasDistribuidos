package demo;

import entity.Alumno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Actions {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Demo");
    EntityManager em = emf.createEntityManager();
    
    public void crear(Alumno a){
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }
    
    public void eliminar(Alumno a){
        em.getTransaction().begin();
        em.remove(a);
        em.getTransaction().commit();
    }
    
    public void actualizar(Alumno a){
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }
    
    public Alumno buscar(Alumno a){
        Alumno b = new Alumno();
        em.getTransaction().begin();
        b = em.find(Alumno.class, a.getNoBoleta());
        em.getTransaction().commit();
        return b;
    }
    
    public List buscarTodos(Alumno a){
        List l = new ArrayList<Alumno>();
        em.getTransaction().begin();
        Query q = em.createQuery("select a from Alumno as a");
        q.getResultList();
        em.getTransaction().commit();
        return  l;
    }
}
