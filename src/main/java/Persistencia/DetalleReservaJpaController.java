package Persistencia;

import Modelo.DetalleReserva;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Reserva;
import Modelo.Salon;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DetalleReservaJpaController implements Serializable {

    public DetalleReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public DetalleReservaJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelElLibertadorPU");
    }
    
    public void create(DetalleReserva detalleReserva) {
        if (detalleReserva.getListaSalones() == null) {
            detalleReserva.setListaSalones(new ArrayList<Salon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva = detalleReserva.getReserva();
            if (reserva != null) {
                reserva = em.getReference(reserva.getClass(), reserva.getId());
                detalleReserva.setReserva(reserva);
            }
            List<Salon> attachedListaSalones = new ArrayList<Salon>();
            for (Salon listaSalonesSalonToAttach : detalleReserva.getListaSalones()) {
                listaSalonesSalonToAttach = em.getReference(listaSalonesSalonToAttach.getClass(), listaSalonesSalonToAttach.getId());
                attachedListaSalones.add(listaSalonesSalonToAttach);
            }
            detalleReserva.setListaSalones(attachedListaSalones);
            em.persist(detalleReserva);
            if (reserva != null) {
                DetalleReserva oldDetalleReservaOfReserva = reserva.getDetalleReserva();
                if (oldDetalleReservaOfReserva != null) {
                    oldDetalleReservaOfReserva.setReserva(null);
                    oldDetalleReservaOfReserva = em.merge(oldDetalleReservaOfReserva);
                }
                reserva.setDetalleReserva(detalleReserva);
                reserva = em.merge(reserva);
            }
            for (Salon listaSalonesSalon : detalleReserva.getListaSalones()) {
                DetalleReserva oldDetalleReservasSalonesOfListaSalonesSalon = listaSalonesSalon.getDetalleReservasSalones();
                listaSalonesSalon.setDetalleReservasSalones(detalleReserva);
                listaSalonesSalon = em.merge(listaSalonesSalon);
                if (oldDetalleReservasSalonesOfListaSalonesSalon != null) {
                    oldDetalleReservasSalonesOfListaSalonesSalon.getListaSalones().remove(listaSalonesSalon);
                    oldDetalleReservasSalonesOfListaSalonesSalon = em.merge(oldDetalleReservasSalonesOfListaSalonesSalon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleReserva detalleReserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetalleReserva persistentDetalleReserva = em.find(DetalleReserva.class, detalleReserva.getId());
            Reserva reservaOld = persistentDetalleReserva.getReserva();
            Reserva reservaNew = detalleReserva.getReserva();
            List<Salon> listaSalonesOld = persistentDetalleReserva.getListaSalones();
            List<Salon> listaSalonesNew = detalleReserva.getListaSalones();
            if (reservaNew != null) {
                reservaNew = em.getReference(reservaNew.getClass(), reservaNew.getId());
                detalleReserva.setReserva(reservaNew);
            }
            List<Salon> attachedListaSalonesNew = new ArrayList<Salon>();
            for (Salon listaSalonesNewSalonToAttach : listaSalonesNew) {
                listaSalonesNewSalonToAttach = em.getReference(listaSalonesNewSalonToAttach.getClass(), listaSalonesNewSalonToAttach.getId());
                attachedListaSalonesNew.add(listaSalonesNewSalonToAttach);
            }
            listaSalonesNew = attachedListaSalonesNew;
            detalleReserva.setListaSalones(listaSalonesNew);
            detalleReserva = em.merge(detalleReserva);
            if (reservaOld != null && !reservaOld.equals(reservaNew)) {
                reservaOld.setDetalleReserva(null);
                reservaOld = em.merge(reservaOld);
            }
            if (reservaNew != null && !reservaNew.equals(reservaOld)) {
                DetalleReserva oldDetalleReservaOfReserva = reservaNew.getDetalleReserva();
                if (oldDetalleReservaOfReserva != null) {
                    oldDetalleReservaOfReserva.setReserva(null);
                    oldDetalleReservaOfReserva = em.merge(oldDetalleReservaOfReserva);
                }
                reservaNew.setDetalleReserva(detalleReserva);
                reservaNew = em.merge(reservaNew);
            }
            for (Salon listaSalonesOldSalon : listaSalonesOld) {
                if (!listaSalonesNew.contains(listaSalonesOldSalon)) {
                    listaSalonesOldSalon.setDetalleReservasSalones(null);
                    listaSalonesOldSalon = em.merge(listaSalonesOldSalon);
                }
            }
            for (Salon listaSalonesNewSalon : listaSalonesNew) {
                if (!listaSalonesOld.contains(listaSalonesNewSalon)) {
                    DetalleReserva oldDetalleReservasSalonesOfListaSalonesNewSalon = listaSalonesNewSalon.getDetalleReservasSalones();
                    listaSalonesNewSalon.setDetalleReservasSalones(detalleReserva);
                    listaSalonesNewSalon = em.merge(listaSalonesNewSalon);
                    if (oldDetalleReservasSalonesOfListaSalonesNewSalon != null && !oldDetalleReservasSalonesOfListaSalonesNewSalon.equals(detalleReserva)) {
                        oldDetalleReservasSalonesOfListaSalonesNewSalon.getListaSalones().remove(listaSalonesNewSalon);
                        oldDetalleReservasSalonesOfListaSalonesNewSalon = em.merge(oldDetalleReservasSalonesOfListaSalonesNewSalon);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = detalleReserva.getId();
                if (findDetalleReserva(id) == null) {
                    throw new NonexistentEntityException("The detalleReserva with id " + id + " no longer exists.");
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
            DetalleReserva detalleReserva;
            try {
                detalleReserva = em.getReference(DetalleReserva.class, id);
                detalleReserva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleReserva with id " + id + " no longer exists.", enfe);
            }
            Reserva reserva = detalleReserva.getReserva();
            if (reserva != null) {
                reserva.setDetalleReserva(null);
                reserva = em.merge(reserva);
            }
            List<Salon> listaSalones = detalleReserva.getListaSalones();
            for (Salon listaSalonesSalon : listaSalones) {
                listaSalonesSalon.setDetalleReservasSalones(null);
                listaSalonesSalon = em.merge(listaSalonesSalon);
            }
            em.remove(detalleReserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleReserva> findDetalleReservaEntities() {
        return findDetalleReservaEntities(true, -1, -1);
    }

    public List<DetalleReserva> findDetalleReservaEntities(int maxResults, int firstResult) {
        return findDetalleReservaEntities(false, maxResults, firstResult);
    }

    private List<DetalleReserva> findDetalleReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleReserva.class));
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

    public DetalleReserva findDetalleReserva(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleReserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleReserva> rt = cq.from(DetalleReserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
