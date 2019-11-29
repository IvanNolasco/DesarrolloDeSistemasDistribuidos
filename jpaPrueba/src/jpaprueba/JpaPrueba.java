/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaprueba;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jonat
 */
public class JpaPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("jpaPruebaPU");
        EntityManager em=emf.createEntityManager();
        
        Cliente cte=new Cliente(220, "22", "22", "er", "pp", "2323", "venus", "colonia", 7);
        em.getTransaction().begin();
        
        
        em.persist(cte);

        
        em.getTransaction().commit();
        
        Query q =em.createNamedQuery("Cliente.findAll");
         
        System.out.println(q.getResultList().toString());
    }
    
}
