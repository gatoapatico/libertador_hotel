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
import Modelo.Usuario;
import Modelo.DetalleReserva;
import Modelo.Reserva;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public ReservaJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelElLibertadorPU");
    }

    public void create(Reserva reserva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = reserva.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId());
                reserva.setUsuario(usuario);
            }
            DetalleReserva detalleReserva = reserva.getDetalleReserva();
            if (detalleReserva != null) {
                detalleReserva = em.getReference(detalleReserva.getClass(), detalleReserva.getId());
                reserva.setDetalleReserva(detalleReserva);
            }
            em.persist(reserva);
            if (usuario != null) {
                usuario.getReservas().add(reserva);
                usuario = em.merge(usuario);
            }
            if (detalleReserva != null) {
                Reserva oldReservaOfDetalleReserva = detalleReserva.getReserva();
                if (oldReservaOfDetalleReserva != null) {
                    oldReservaOfDetalleReserva.setDetalleReserva(null);
                    oldReservaOfDetalleReserva = em.merge(oldReservaOfDetalleReserva);
                }
                detalleReserva.setReserva(reserva);
                detalleReserva = em.merge(detalleReserva);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getId());
            Usuario usuarioOld = persistentReserva.getUsuario();
            Usuario usuarioNew = reserva.getUsuario();
            DetalleReserva detalleReservaOld = persistentReserva.getDetalleReserva();
            DetalleReserva detalleReservaNew = reserva.getDetalleReserva();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId());
                reserva.setUsuario(usuarioNew);
            }
            if (detalleReservaNew != null) {
                detalleReservaNew = em.getReference(detalleReservaNew.getClass(), detalleReservaNew.getId());
                reserva.setDetalleReserva(detalleReservaNew);
            }
            reserva = em.merge(reserva);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getReservas().remove(reserva);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getReservas().add(reserva);
                usuarioNew = em.merge(usuarioNew);
            }
            if (detalleReservaOld != null && !detalleReservaOld.equals(detalleReservaNew)) {
                detalleReservaOld.setReserva(null);
                detalleReservaOld = em.merge(detalleReservaOld);
            }
            if (detalleReservaNew != null && !detalleReservaNew.equals(detalleReservaOld)) {
                Reserva oldReservaOfDetalleReserva = detalleReservaNew.getReserva();
                if (oldReservaOfDetalleReserva != null) {
                    oldReservaOfDetalleReserva.setDetalleReserva(null);
                    oldReservaOfDetalleReserva = em.merge(oldReservaOfDetalleReserva);
                }
                detalleReservaNew.setReserva(reserva);
                detalleReservaNew = em.merge(detalleReservaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = reserva.getId();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
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
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = reserva.getUsuario();
            if (usuario != null) {
                usuario.getReservas().remove(reserva);
                usuario = em.merge(usuario);
            }
            DetalleReserva detalleReserva = reserva.getDetalleReserva();
            if (detalleReserva != null) {
                detalleReserva.setReserva(null);
                detalleReserva = em.merge(detalleReserva);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
