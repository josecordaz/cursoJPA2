/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.lobos.controllers;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.com.lobos.controllers.exceptions.NonexistentEntityException;
import mx.com.lobos.entities.Estadios;

/**
 *
 * @author joseo
 */
public class EstadiosJpaController implements Serializable {

    public EstadiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadios estadios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadios estadios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadios = em.merge(estadios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadios.getIdEstadio();
                if (findEstadios(id) == null) {
                    throw new NonexistentEntityException("The estadios with id " + id + " no longer exists.");
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
            Estadios estadios;
            try {
                estadios = em.getReference(Estadios.class, id);
                estadios.getIdEstadio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadios with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadios> findEstadiosEntities() {
        return findEstadiosEntities(true, -1, -1);
    }

    public List<Estadios> findEstadiosEntities(int maxResults, int firstResult) {
        return findEstadiosEntities(false, maxResults, firstResult);
    }

    private List<Estadios> findEstadiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadios.class));
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

    public Estadios findEstadios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadios> rt = cq.from(Estadios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
