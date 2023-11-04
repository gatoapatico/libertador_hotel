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
import Modelo.DetalleReserva;
import Modelo.Habitacion;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruno Sandoval
 */
public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Habitacion habitacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria tipoHabitacion = habitacion.getTipoHabitacion();
            if (tipoHabitacion != null) {
                tipoHabitacion = em.getReference(tipoHabitacion.getClass(), tipoHabitacion.getId());
                habitacion.setTipoHabitacion(tipoHabitacion);
            }
            DetalleReserva detalleReservaHabitacion = habitacion.getDetalleReservaHabitacion();
            if (detalleReservaHabitacion != null) {
                detalleReservaHabitacion = em.getReference(detalleReservaHabitacion.getClass(), detalleReservaHabitacion.getId());
                habitacion.setDetalleReservaHabitacion(detalleReservaHabitacion);
            }
            em.persist(habitacion);
            if (tipoHabitacion != null) {
                tipoHabitacion.getHabitaciones().add(habitacion);
                tipoHabitacion = em.merge(tipoHabitacion);
            }
            if (detalleReservaHabitacion != null) {
                detalleReservaHabitacion.getListahabitaciones().add(habitacion);
                detalleReservaHabitacion = em.merge(detalleReservaHabitacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Habitacion habitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Habitacion persistentHabitacion = em.find(Habitacion.class, habitacion.getId());
            Categoria tipoHabitacionOld = persistentHabitacion.getTipoHabitacion();
            Categoria tipoHabitacionNew = habitacion.getTipoHabitacion();
            DetalleReserva detalleReservaHabitacionOld = persistentHabitacion.getDetalleReservaHabitacion();
            DetalleReserva detalleReservaHabitacionNew = habitacion.getDetalleReservaHabitacion();
            if (tipoHabitacionNew != null) {
                tipoHabitacionNew = em.getReference(tipoHabitacionNew.getClass(), tipoHabitacionNew.getId());
                habitacion.setTipoHabitacion(tipoHabitacionNew);
            }
            if (detalleReservaHabitacionNew != null) {
                detalleReservaHabitacionNew = em.getReference(detalleReservaHabitacionNew.getClass(), detalleReservaHabitacionNew.getId());
                habitacion.setDetalleReservaHabitacion(detalleReservaHabitacionNew);
            }
            habitacion = em.merge(habitacion);
            if (tipoHabitacionOld != null && !tipoHabitacionOld.equals(tipoHabitacionNew)) {
                tipoHabitacionOld.getHabitaciones().remove(habitacion);
                tipoHabitacionOld = em.merge(tipoHabitacionOld);
            }
            if (tipoHabitacionNew != null && !tipoHabitacionNew.equals(tipoHabitacionOld)) {
                tipoHabitacionNew.getHabitaciones().add(habitacion);
                tipoHabitacionNew = em.merge(tipoHabitacionNew);
            }
            if (detalleReservaHabitacionOld != null && !detalleReservaHabitacionOld.equals(detalleReservaHabitacionNew)) {
                detalleReservaHabitacionOld.getListahabitaciones().remove(habitacion);
                detalleReservaHabitacionOld = em.merge(detalleReservaHabitacionOld);
            }
            if (detalleReservaHabitacionNew != null && !detalleReservaHabitacionNew.equals(detalleReservaHabitacionOld)) {
                detalleReservaHabitacionNew.getListahabitaciones().add(habitacion);
                detalleReservaHabitacionNew = em.merge(detalleReservaHabitacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = habitacion.getId();
                if (findHabitacion(id) == null) {
                    throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.");
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
            Habitacion habitacion;
            try {
                habitacion = em.getReference(Habitacion.class, id);
                habitacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacion with id " + id + " no longer exists.", enfe);
            }
            Categoria tipoHabitacion = habitacion.getTipoHabitacion();
            if (tipoHabitacion != null) {
                tipoHabitacion.getHabitaciones().remove(habitacion);
                tipoHabitacion = em.merge(tipoHabitacion);
            }
            DetalleReserva detalleReservaHabitacion = habitacion.getDetalleReservaHabitacion();
            if (detalleReservaHabitacion != null) {
                detalleReservaHabitacion.getListahabitaciones().remove(habitacion);
                detalleReservaHabitacion = em.merge(detalleReservaHabitacion);
            }
            em.remove(habitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> findHabitacionEntities() {
        return findHabitacionEntities(true, -1, -1);
    }

    public List<Habitacion> findHabitacionEntities(int maxResults, int firstResult) {
        return findHabitacionEntities(false, maxResults, firstResult);
    }

    private List<Habitacion> findHabitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Habitacion.class));
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

    public Habitacion findHabitacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Habitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Habitacion> rt = cq.from(Habitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
