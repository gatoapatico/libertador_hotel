package Persistencia;

import Modelo.Categoria;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Servicio;
import java.util.ArrayList;
import java.util.List;
import Modelo.Salon;
import Modelo.Habitacion;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CategoriaJpaController() {
        emf = Persistence.createEntityManagerFactory("hotelElLibertadorPU");
    }

    public void create(Categoria categoria) {
        if (categoria.getServicios() == null) {
            categoria.setServicios(new ArrayList<Servicio>());
        }
        if (categoria.getSalones() == null) {
            categoria.setSalones(new ArrayList<Salon>());
        }
        if (categoria.getHabitaciones() == null) {
            categoria.setHabitaciones(new ArrayList<Habitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedServicios = new ArrayList<Servicio>();
            for (Servicio serviciosServicioToAttach : categoria.getServicios()) {
                serviciosServicioToAttach = em.getReference(serviciosServicioToAttach.getClass(), serviciosServicioToAttach.getId());
                attachedServicios.add(serviciosServicioToAttach);
            }
            categoria.setServicios(attachedServicios);
            List<Salon> attachedSalones = new ArrayList<Salon>();
            for (Salon salonesSalonToAttach : categoria.getSalones()) {
                salonesSalonToAttach = em.getReference(salonesSalonToAttach.getClass(), salonesSalonToAttach.getId());
                attachedSalones.add(salonesSalonToAttach);
            }
            categoria.setSalones(attachedSalones);
            List<Habitacion> attachedHabitaciones = new ArrayList<Habitacion>();
            for (Habitacion habitacionesHabitacionToAttach : categoria.getHabitaciones()) {
                habitacionesHabitacionToAttach = em.getReference(habitacionesHabitacionToAttach.getClass(), habitacionesHabitacionToAttach.getId());
                attachedHabitaciones.add(habitacionesHabitacionToAttach);
            }
            categoria.setHabitaciones(attachedHabitaciones);
            em.persist(categoria);
            for (Servicio serviciosServicio : categoria.getServicios()) {
                serviciosServicio.getCategorias().add(categoria);
                serviciosServicio = em.merge(serviciosServicio);
            }
            for (Salon salonesSalon : categoria.getSalones()) {
                Categoria oldTipoSalonOfSalonesSalon = salonesSalon.getTipoSalon();
                salonesSalon.setTipoSalon(categoria);
                salonesSalon = em.merge(salonesSalon);
                if (oldTipoSalonOfSalonesSalon != null) {
                    oldTipoSalonOfSalonesSalon.getSalones().remove(salonesSalon);
                    oldTipoSalonOfSalonesSalon = em.merge(oldTipoSalonOfSalonesSalon);
                }
            }
            for (Habitacion habitacionesHabitacion : categoria.getHabitaciones()) {
                Categoria oldTipoHabitacionOfHabitacionesHabitacion = habitacionesHabitacion.getTipoHabitacion();
                habitacionesHabitacion.setTipoHabitacion(categoria);
                habitacionesHabitacion = em.merge(habitacionesHabitacion);
                if (oldTipoHabitacionOfHabitacionesHabitacion != null) {
                    oldTipoHabitacionOfHabitacionesHabitacion.getHabitaciones().remove(habitacionesHabitacion);
                    oldTipoHabitacionOfHabitacionesHabitacion = em.merge(oldTipoHabitacionOfHabitacionesHabitacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getId());
            List<Servicio> serviciosOld = persistentCategoria.getServicios();
            List<Servicio> serviciosNew = categoria.getServicios();
            List<Salon> salonesOld = persistentCategoria.getSalones();
            List<Salon> salonesNew = categoria.getSalones();
            List<Habitacion> habitacionesOld = persistentCategoria.getHabitaciones();
            List<Habitacion> habitacionesNew = categoria.getHabitaciones();
            List<Servicio> attachedServiciosNew = new ArrayList<Servicio>();
            for (Servicio serviciosNewServicioToAttach : serviciosNew) {
                serviciosNewServicioToAttach = em.getReference(serviciosNewServicioToAttach.getClass(), serviciosNewServicioToAttach.getId());
                attachedServiciosNew.add(serviciosNewServicioToAttach);
            }
            serviciosNew = attachedServiciosNew;
            categoria.setServicios(serviciosNew);
            List<Salon> attachedSalonesNew = new ArrayList<Salon>();
            for (Salon salonesNewSalonToAttach : salonesNew) {
                salonesNewSalonToAttach = em.getReference(salonesNewSalonToAttach.getClass(), salonesNewSalonToAttach.getId());
                attachedSalonesNew.add(salonesNewSalonToAttach);
            }
            salonesNew = attachedSalonesNew;
            categoria.setSalones(salonesNew);
            List<Habitacion> attachedHabitacionesNew = new ArrayList<Habitacion>();
            for (Habitacion habitacionesNewHabitacionToAttach : habitacionesNew) {
                habitacionesNewHabitacionToAttach = em.getReference(habitacionesNewHabitacionToAttach.getClass(), habitacionesNewHabitacionToAttach.getId());
                attachedHabitacionesNew.add(habitacionesNewHabitacionToAttach);
            }
            habitacionesNew = attachedHabitacionesNew;
            categoria.setHabitaciones(habitacionesNew);
            categoria = em.merge(categoria);
            for (Servicio serviciosOldServicio : serviciosOld) {
                if (!serviciosNew.contains(serviciosOldServicio)) {
                    serviciosOldServicio.getCategorias().remove(categoria);
                    serviciosOldServicio = em.merge(serviciosOldServicio);
                }
            }
            for (Servicio serviciosNewServicio : serviciosNew) {
                if (!serviciosOld.contains(serviciosNewServicio)) {
                    serviciosNewServicio.getCategorias().add(categoria);
                    serviciosNewServicio = em.merge(serviciosNewServicio);
                }
            }
            for (Salon salonesOldSalon : salonesOld) {
                if (!salonesNew.contains(salonesOldSalon)) {
                    salonesOldSalon.setTipoSalon(null);
                    salonesOldSalon = em.merge(salonesOldSalon);
                }
            }
            for (Salon salonesNewSalon : salonesNew) {
                if (!salonesOld.contains(salonesNewSalon)) {
                    Categoria oldTipoSalonOfSalonesNewSalon = salonesNewSalon.getTipoSalon();
                    salonesNewSalon.setTipoSalon(categoria);
                    salonesNewSalon = em.merge(salonesNewSalon);
                    if (oldTipoSalonOfSalonesNewSalon != null && !oldTipoSalonOfSalonesNewSalon.equals(categoria)) {
                        oldTipoSalonOfSalonesNewSalon.getSalones().remove(salonesNewSalon);
                        oldTipoSalonOfSalonesNewSalon = em.merge(oldTipoSalonOfSalonesNewSalon);
                    }
                }
            }
            for (Habitacion habitacionesOldHabitacion : habitacionesOld) {
                if (!habitacionesNew.contains(habitacionesOldHabitacion)) {
                    habitacionesOldHabitacion.setTipoHabitacion(null);
                    habitacionesOldHabitacion = em.merge(habitacionesOldHabitacion);
                }
            }
            for (Habitacion habitacionesNewHabitacion : habitacionesNew) {
                if (!habitacionesOld.contains(habitacionesNewHabitacion)) {
                    Categoria oldTipoHabitacionOfHabitacionesNewHabitacion = habitacionesNewHabitacion.getTipoHabitacion();
                    habitacionesNewHabitacion.setTipoHabitacion(categoria);
                    habitacionesNewHabitacion = em.merge(habitacionesNewHabitacion);
                    if (oldTipoHabitacionOfHabitacionesNewHabitacion != null && !oldTipoHabitacionOfHabitacionesNewHabitacion.equals(categoria)) {
                        oldTipoHabitacionOfHabitacionesNewHabitacion.getHabitaciones().remove(habitacionesNewHabitacion);
                        oldTipoHabitacionOfHabitacionesNewHabitacion = em.merge(oldTipoHabitacionOfHabitacionesNewHabitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = categoria.getId();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> servicios = categoria.getServicios();
            for (Servicio serviciosServicio : servicios) {
                serviciosServicio.getCategorias().remove(categoria);
                serviciosServicio = em.merge(serviciosServicio);
            }
            List<Salon> salones = categoria.getSalones();
            for (Salon salonesSalon : salones) {
                salonesSalon.setTipoSalon(null);
                salonesSalon = em.merge(salonesSalon);
            }
            List<Habitacion> habitaciones = categoria.getHabitaciones();
            for (Habitacion habitacionesHabitacion : habitaciones) {
                habitacionesHabitacion.setTipoHabitacion(null);
                habitacionesHabitacion = em.merge(habitacionesHabitacion);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public void cambiarEstadoCategoria(int id) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            // Buscar la categoría por su ID
            Categoria categoria = em.find(Categoria.class, id);

            if (categoria != null) {
                // Cambiar el estado de la categoría (suponiendo que tienes un atributo 'estado' en tu clase Categoria)
                if ("Activo".equals(categoria.getEstado())) {
                    categoria.setEstado("Desactivado");
                    categoria.setFechaBaja(new Date()); // Establecer la fecha de baja al momento actual
                } else {
                    categoria.setEstado("Activo");
                    categoria.setFechaBaja(null); // Reiniciar la fecha de baja a null
                }

                // Realizar la actualización en la base de datos
                em.merge(categoria);

                em.getTransaction().commit();
            } else {
                throw new NonexistentEntityException("La categoría con ID " + id + " no existe.");
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                throw new NonexistentEntityException("Error al cambiar el estado de la categoría.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> buscarCategoriasPorNombre(String nombre) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.nombre = :nombre");
            query.setParameter("nombre", nombre);

            List<Categoria> resultados = query.getResultList();

            return resultados;
        } finally {
            em.close();
        }
    }
    public Categoria buscarCategoriaPorNombre(String nombre) {
    EntityManager em = getEntityManager();
    try {
        Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.nombre = :nombre");
        query.setParameter("nombre", nombre);

        Categoria categoriaEncontrada = (Categoria) query.getSingleResult();

        return categoriaEncontrada;
    } catch (NoResultException e) {
        return null; // Retorna null si no encuentra ninguna categoría con ese nombre
    } finally {
        em.close();
    }
}

}
