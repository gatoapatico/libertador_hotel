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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class HabitacionJpaController implements Serializable {

    public HabitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public HabitacionJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelElLibertadorPU");
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
            DetalleReserva detalleReservasHabitaciones = habitacion.getDetalleReservasHabitaciones();
            if (detalleReservasHabitaciones != null) {
                detalleReservasHabitaciones = em.getReference(detalleReservasHabitaciones.getClass(), detalleReservasHabitaciones.getId());
                habitacion.setDetalleReservasHabitaciones(detalleReservasHabitaciones);
            }
            em.persist(habitacion);
            if (tipoHabitacion != null) {
                tipoHabitacion.getHabitaciones().add(habitacion);
                tipoHabitacion = em.merge(tipoHabitacion);
            }
            if (detalleReservasHabitaciones != null) {
                detalleReservasHabitaciones.getListahabitaciones().add(habitacion);
                detalleReservasHabitaciones = em.merge(detalleReservasHabitaciones);
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
            DetalleReserva detalleReservasHabitacionesOld = persistentHabitacion.getDetalleReservasHabitaciones();
            DetalleReserva detalleReservasHabitacionesNew = habitacion.getDetalleReservasHabitaciones();
            if (tipoHabitacionNew != null) {
                tipoHabitacionNew = em.getReference(tipoHabitacionNew.getClass(), tipoHabitacionNew.getId());
                habitacion.setTipoHabitacion(tipoHabitacionNew);
            }
            if (detalleReservasHabitacionesNew != null) {
                detalleReservasHabitacionesNew = em.getReference(detalleReservasHabitacionesNew.getClass(), detalleReservasHabitacionesNew.getId());
                habitacion.setDetalleReservasHabitaciones(detalleReservasHabitacionesNew);
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
            if (detalleReservasHabitacionesOld != null && !detalleReservasHabitacionesOld.equals(detalleReservasHabitacionesNew)) {
                detalleReservasHabitacionesOld.getListahabitaciones().remove(habitacion);
                detalleReservasHabitacionesOld = em.merge(detalleReservasHabitacionesOld);
            }
            if (detalleReservasHabitacionesNew != null && !detalleReservasHabitacionesNew.equals(detalleReservasHabitacionesOld)) {
                detalleReservasHabitacionesNew.getListahabitaciones().add(habitacion);
                detalleReservasHabitacionesNew = em.merge(detalleReservasHabitacionesNew);
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
            DetalleReserva detalleReservasHabitaciones = habitacion.getDetalleReservasHabitaciones();
            if (detalleReservasHabitaciones != null) {
                detalleReservasHabitaciones.getListahabitaciones().remove(habitacion);
                detalleReservasHabitaciones = em.merge(detalleReservasHabitaciones);
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

    public void cambiarEstadoHabitacion(int id) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Buscar la habitación por su ID
            Habitacion habitacion = em.find(Habitacion.class, id);

            if (habitacion != null) {
                // Cambiar el estado de la habitación (suponiendo que tienes un atributo 'estado' en tu clase Habitacion)
                if ("Disponible".equals(habitacion.getEstado())) {
                    habitacion.setEstado("No disponible");
                    habitacion.setFechaBaja(new Date());
                } else {
                    habitacion.setEstado("Disponible");
                    habitacion.setFechaBaja(null);
                }

                // Realizar la actualización en la base de datos
                em.merge(habitacion);

                em.getTransaction().commit();
            } else {
                throw new NonexistentEntityException("La habitación con ID " + id + " no existe.");
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                throw new NonexistentEntityException("Error al cambiar el estado de la habitación.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Habitacion> buscarHabitacionesPorNumeroHabitacion(int numero) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT h FROM Habitacion h WHERE h.numhabitacion = :numhabitacion");
            query.setParameter("numhabitacion", numero);

            List<Habitacion> resultados = query.getResultList();

            return resultados;
        } finally {
            em.close();
        }
    }

    public Habitacion buscarHabitacionPorNumeroHabitacion(int numero) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT h FROM Habitacion h WHERE h.numhabitacion = :numhabitacion");
            query.setParameter("numhabitacion", numero);

            Habitacion habitacionEncontrada = (Habitacion) query.getSingleResult();

            return habitacionEncontrada;
        } catch (NoResultException e) {
            return null; // Retorna null si no encuentra ninguna habitación con ese nombre
        } finally {
            em.close();
        }
    }

}
