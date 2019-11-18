/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materia;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author ricardotg
 */
public class AlumnoJpaController implements Serializable {
    @PersistenceUnit
    private EntityManagerFactory emf = null;

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public AlumnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("EjercicioAlumnoPU");
        
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) throws PreexistingEntityException, Exception {
        if (alumno.getMateriaList() == null) {
            alumno.setMateriaList(new ArrayList<Materia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Materia> attachedMateriaList = new ArrayList<Materia>();
            for (Materia materiaListMateriaToAttach : alumno.getMateriaList()) {
                materiaListMateriaToAttach = em.getReference(materiaListMateriaToAttach.getClass(), materiaListMateriaToAttach.getIdMateria());
                attachedMateriaList.add(materiaListMateriaToAttach);
            }
            alumno.setMateriaList(attachedMateriaList);
            em.persist(alumno);
            for (Materia materiaListMateria : alumno.getMateriaList()) {
                Alumno oldNoBoletaOfMateriaListMateria = materiaListMateria.getNoBoleta();
                materiaListMateria.setNoBoleta(alumno);
                materiaListMateria = em.merge(materiaListMateria);
                if (oldNoBoletaOfMateriaListMateria != null) {
                    oldNoBoletaOfMateriaListMateria.getMateriaList().remove(materiaListMateria);
                    oldNoBoletaOfMateriaListMateria = em.merge(oldNoBoletaOfMateriaListMateria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAlumno(alumno.getNoBoleta()) != null) {
                throw new PreexistingEntityException("Alumno " + alumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getNoBoleta());
            List<Materia> materiaListOld = persistentAlumno.getMateriaList();
            List<Materia> materiaListNew = alumno.getMateriaList();
            List<String> illegalOrphanMessages = null;
            for (Materia materiaListOldMateria : materiaListOld) {
                if (!materiaListNew.contains(materiaListOldMateria)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Materia " + materiaListOldMateria + " since its noBoleta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Materia> attachedMateriaListNew = new ArrayList<Materia>();
            for (Materia materiaListNewMateriaToAttach : materiaListNew) {
                materiaListNewMateriaToAttach = em.getReference(materiaListNewMateriaToAttach.getClass(), materiaListNewMateriaToAttach.getIdMateria());
                attachedMateriaListNew.add(materiaListNewMateriaToAttach);
            }
            materiaListNew = attachedMateriaListNew;
            alumno.setMateriaList(materiaListNew);
            alumno = em.merge(alumno);
            for (Materia materiaListNewMateria : materiaListNew) {
                if (!materiaListOld.contains(materiaListNewMateria)) {
                    Alumno oldNoBoletaOfMateriaListNewMateria = materiaListNewMateria.getNoBoleta();
                    materiaListNewMateria.setNoBoleta(alumno);
                    materiaListNewMateria = em.merge(materiaListNewMateria);
                    if (oldNoBoletaOfMateriaListNewMateria != null && !oldNoBoletaOfMateriaListNewMateria.equals(alumno)) {
                        oldNoBoletaOfMateriaListNewMateria.getMateriaList().remove(materiaListNewMateria);
                        oldNoBoletaOfMateriaListNewMateria = em.merge(oldNoBoletaOfMateriaListNewMateria);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = alumno.getNoBoleta();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getNoBoleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Materia> materiaListOrphanCheck = alumno.getMateriaList();
            for (Materia materiaListOrphanCheckMateria : materiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Alumno (" + alumno + ") cannot be destroyed since the Materia " + materiaListOrphanCheckMateria + " in its materiaList field has a non-nullable noBoleta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            System.out.println(q.getResultList());
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(String id) {
        System.out.println(id);
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
