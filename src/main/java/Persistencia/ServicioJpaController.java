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
import Modelo.Servicio;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Bruno Sandoval
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getCategorias() == null) {
            servicio.setCategorias(new ArrayList<Categoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Categoria> attachedCategorias = new ArrayList<Categoria>();
            for (Categoria categoriasCategoriaToAttach : servicio.getCategorias()) {
                categoriasCategoriaToAttach = em.getReference(categoriasCategoriaToAttach.getClass(), categoriasCategoriaToAttach.getId());
                attachedCategorias.add(categoriasCategoriaToAttach);
            }
            servicio.setCategorias(attachedCategorias);
            em.persist(servicio);
            for (Categoria categoriasCategoria : servicio.getCategorias()) {
                categoriasCategoria.getServicios().add(servicio);
                categoriasCategoria = em.merge(categoriasCategoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getId());
            List<Categoria> categoriasOld = persistentServicio.getCategorias();
            List<Categoria> categoriasNew = servicio.getCategorias();
            List<Categoria> attachedCategoriasNew = new ArrayList<Categoria>();
            for (Categoria categoriasNewCategoriaToAttach : categoriasNew) {
                categoriasNewCategoriaToAttach = em.getReference(categoriasNewCategoriaToAttach.getClass(), categoriasNewCategoriaToAttach.getId());
                attachedCategoriasNew.add(categoriasNewCategoriaToAttach);
            }
            categoriasNew = attachedCategoriasNew;
            servicio.setCategorias(categoriasNew);
            servicio = em.merge(servicio);
            for (Categoria categoriasOldCategoria : categoriasOld) {
                if (!categoriasNew.contains(categoriasOldCategoria)) {
                    categoriasOldCategoria.getServicios().remove(servicio);
                    categoriasOldCategoria = em.merge(categoriasOldCategoria);
                }
            }
            for (Categoria categoriasNewCategoria : categoriasNew) {
                if (!categoriasOld.contains(categoriasNewCategoria)) {
                    categoriasNewCategoria.getServicios().add(servicio);
                    categoriasNewCategoria = em.merge(categoriasNewCategoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getId();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Categoria> categorias = servicio.getCategorias();
            for (Categoria categoriasCategoria : categorias) {
                categoriasCategoria.getServicios().remove(servicio);
                categoriasCategoria = em.merge(categoriasCategoria);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
