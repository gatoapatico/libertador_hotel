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
import Modelo.Reserva;
import Modelo.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruno Sandoval
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getReservas() == null) {
            usuario.setReservas(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Reserva> attachedReservas = new ArrayList<Reserva>();
            for (Reserva reservasReservaToAttach : usuario.getReservas()) {
                reservasReservaToAttach = em.getReference(reservasReservaToAttach.getClass(), reservasReservaToAttach.getId());
                attachedReservas.add(reservasReservaToAttach);
            }
            usuario.setReservas(attachedReservas);
            em.persist(usuario);
            for (Reserva reservasReserva : usuario.getReservas()) {
                Usuario oldUsuarioOfReservasReserva = reservasReserva.getUsuario();
                reservasReserva.setUsuario(usuario);
                reservasReserva = em.merge(reservasReserva);
                if (oldUsuarioOfReservasReserva != null) {
                    oldUsuarioOfReservasReserva.getReservas().remove(reservasReserva);
                    oldUsuarioOfReservasReserva = em.merge(oldUsuarioOfReservasReserva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Reserva> reservasOld = persistentUsuario.getReservas();
            List<Reserva> reservasNew = usuario.getReservas();
            List<Reserva> attachedReservasNew = new ArrayList<Reserva>();
            for (Reserva reservasNewReservaToAttach : reservasNew) {
                reservasNewReservaToAttach = em.getReference(reservasNewReservaToAttach.getClass(), reservasNewReservaToAttach.getId());
                attachedReservasNew.add(reservasNewReservaToAttach);
            }
            reservasNew = attachedReservasNew;
            usuario.setReservas(reservasNew);
            usuario = em.merge(usuario);
            for (Reserva reservasOldReserva : reservasOld) {
                if (!reservasNew.contains(reservasOldReserva)) {
                    reservasOldReserva.setUsuario(null);
                    reservasOldReserva = em.merge(reservasOldReserva);
                }
            }
            for (Reserva reservasNewReserva : reservasNew) {
                if (!reservasOld.contains(reservasNewReserva)) {
                    Usuario oldUsuarioOfReservasNewReserva = reservasNewReserva.getUsuario();
                    reservasNewReserva.setUsuario(usuario);
                    reservasNewReserva = em.merge(reservasNewReserva);
                    if (oldUsuarioOfReservasNewReserva != null && !oldUsuarioOfReservasNewReserva.equals(usuario)) {
                        oldUsuarioOfReservasNewReserva.getReservas().remove(reservasNewReserva);
                        oldUsuarioOfReservasNewReserva = em.merge(oldUsuarioOfReservasNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Reserva> reservas = usuario.getReservas();
            for (Reserva reservasReserva : reservas) {
                reservasReserva.setUsuario(null);
                reservasReserva = em.merge(reservasReserva);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
