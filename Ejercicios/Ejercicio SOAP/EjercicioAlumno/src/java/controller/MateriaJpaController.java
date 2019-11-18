/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Alumno;
import entity.Materia;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ricardotg
 */
public class MateriaJpaController implements Serializable {

    public MateriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public MateriaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EjercicioAlumnoPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Materia materia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno noBoleta = materia.getNoBoleta();
            if (noBoleta != null) {
                noBoleta = em.getReference(noBoleta.getClass(), noBoleta.getNoBoleta());
                materia.setNoBoleta(noBoleta);
            }
            em.persist(materia);
            if (noBoleta != null) {
                noBoleta.getMateriaList().add(materia);
                noBoleta = em.merge(noBoleta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMateria(materia.getIdMateria()) != null) {
                throw new PreexistingEntityException("Materia " + materia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Materia materia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia persistentMateria = em.find(Materia.class, materia.getIdMateria());
            Alumno noBoletaOld = persistentMateria.getNoBoleta();
            Alumno noBoletaNew = materia.getNoBoleta();
            if (noBoletaNew != null) {
                noBoletaNew = em.getReference(noBoletaNew.getClass(), noBoletaNew.getNoBoleta());
                materia.setNoBoleta(noBoletaNew);
            }
            materia = em.merge(materia);
            if (noBoletaOld != null && !noBoletaOld.equals(noBoletaNew)) {
                noBoletaOld.getMateriaList().remove(materia);
                noBoletaOld = em.merge(noBoletaOld);
            }
            if (noBoletaNew != null && !noBoletaNew.equals(noBoletaOld)) {
                noBoletaNew.getMateriaList().add(materia);
                noBoletaNew = em.merge(noBoletaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = materia.getIdMateria();
                if (findMateria(id) == null) {
                    throw new NonexistentEntityException("The materia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materia materia;
            try {
                materia = em.getReference(Materia.class, id);
                materia.getIdMateria();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The materia with id " + id + " no longer exists.", enfe);
            }
            Alumno noBoleta = materia.getNoBoleta();
            if (noBoleta != null) {
                noBoleta.getMateriaList().remove(materia);
                noBoleta = em.merge(noBoleta);
            }
            em.remove(materia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Materia> findMateriaEntities() {
        return findMateriaEntities(true, -1, -1);
    }

    public List<Materia> findMateriaEntities(int maxResults, int firstResult) {
        return findMateriaEntities(false, maxResults, firstResult);
    }

    private List<Materia> findMateriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Materia.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Materia findMateria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Materia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMateriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Materia> rt = cq.from(Materia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
