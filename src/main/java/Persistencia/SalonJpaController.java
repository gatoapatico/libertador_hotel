/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Categoria;
import Modelo.Salon;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruno Sandoval
 */
public class SalonJpaController implements Serializable {

    public SalonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salon salon) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria tipoSalon = salon.getTipoSalon();
            if (tipoSalon != null) {
                tipoSalon = em.getReference(tipoSalon.getClass(), tipoSalon.getId());
                salon.setTipoSalon(tipoSalon);
            }
            em.persist(salon);
            if (tipoSalon != null) {
                tipoSalon.getSalones().add(salon);
                tipoSalon = em.merge(tipoSalon);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Salon salon) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon persistentSalon = em.find(Salon.class, salon.getId());
            Categoria tipoSalonOld = persistentSalon.getTipoSalon();
            Categoria tipoSalonNew = salon.getTipoSalon();
            if (tipoSalonNew != null) {
                tipoSalonNew = em.getReference(tipoSalonNew.getClass(), tipoSalonNew.getId());
                salon.setTipoSalon(tipoSalonNew);
            }
            salon = em.merge(salon);
            if (tipoSalonOld != null && !tipoSalonOld.equals(tipoSalonNew)) {
                tipoSalonOld.getSalones().remove(salon);
                tipoSalonOld = em.merge(tipoSalonOld);
            }
            if (tipoSalonNew != null && !tipoSalonNew.equals(tipoSalonOld)) {
                tipoSalonNew.getSalones().add(salon);
                tipoSalonNew = em.merge(tipoSalonNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = salon.getId();
                if (findSalon(id) == null) {
                    throw new NonexistentEntityException("The salon with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon salon;
            try {
                salon = em.getReference(Salon.class, id);
                salon.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salon with id " + id + " no longer exists.", enfe);
            }
            Categoria tipoSalon = salon.getTipoSalon();
            if (tipoSalon != null) {
                tipoSalon.getSalones().remove(salon);
                tipoSalon = em.merge(tipoSalon);
            }
            em.remove(salon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Salon> findSalonEntities() {
        return findSalonEntities(true, -1, -1);
    }

    public List<Salon> findSalonEntities(int maxResults, int firstResult) {
        return findSalonEntities(false, maxResults, firstResult);
    }

    private List<Salon> findSalonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salon.class));
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

    public Salon findSalon(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Salon.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salon> rt = cq.from(Salon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
