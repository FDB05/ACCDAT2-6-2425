/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import controlador.exceptions.PreexistingEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import modelo.Llamadas;
import modelo.Movilizaciones;
import modelo.Unidades;

/**
 *
 * @author Fernando A
 */
public class MovilizacionesJpaController implements Serializable {

    public MovilizacionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movilizaciones movilizaciones) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Llamadas numerotelf = movilizaciones.getNumerotelf();
            if (numerotelf != null) {
                numerotelf = em.getReference(numerotelf.getClass(), numerotelf.getNumerotelf());
                movilizaciones.setNumerotelf(numerotelf);
            }
            Unidades numerounidad = movilizaciones.getNumerounidad();
            if (numerounidad != null) {
                numerounidad = em.getReference(numerounidad.getClass(), numerounidad.getNumerounidad());
                movilizaciones.setNumerounidad(numerounidad);
            }
            em.persist(movilizaciones);
            if (numerotelf != null) {
                numerotelf.getMovilizacionesCollection().add(movilizaciones);
                numerotelf = em.merge(numerotelf);
            }
            if (numerounidad != null) {
                numerounidad.getMovilizacionesCollection().add(movilizaciones);
                numerounidad = em.merge(numerounidad);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMovilizaciones(movilizaciones.getIdmovilizacion()) != null) {
                throw new PreexistingEntityException("Movilizaciones " + movilizaciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movilizaciones movilizaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movilizaciones persistentMovilizaciones = em.find(Movilizaciones.class, movilizaciones.getIdmovilizacion());
            Llamadas numerotelfOld = persistentMovilizaciones.getNumerotelf();
            Llamadas numerotelfNew = movilizaciones.getNumerotelf();
            Unidades numerounidadOld = persistentMovilizaciones.getNumerounidad();
            Unidades numerounidadNew = movilizaciones.getNumerounidad();
            if (numerotelfNew != null) {
                numerotelfNew = em.getReference(numerotelfNew.getClass(), numerotelfNew.getNumerotelf());
                movilizaciones.setNumerotelf(numerotelfNew);
            }
            if (numerounidadNew != null) {
                numerounidadNew = em.getReference(numerounidadNew.getClass(), numerounidadNew.getNumerounidad());
                movilizaciones.setNumerounidad(numerounidadNew);
            }
            movilizaciones = em.merge(movilizaciones);
            if (numerotelfOld != null && !numerotelfOld.equals(numerotelfNew)) {
                numerotelfOld.getMovilizacionesCollection().remove(movilizaciones);
                numerotelfOld = em.merge(numerotelfOld);
            }
            if (numerotelfNew != null && !numerotelfNew.equals(numerotelfOld)) {
                numerotelfNew.getMovilizacionesCollection().add(movilizaciones);
                numerotelfNew = em.merge(numerotelfNew);
            }
            if (numerounidadOld != null && !numerounidadOld.equals(numerounidadNew)) {
                numerounidadOld.getMovilizacionesCollection().remove(movilizaciones);
                numerounidadOld = em.merge(numerounidadOld);
            }
            if (numerounidadNew != null && !numerounidadNew.equals(numerounidadOld)) {
                numerounidadNew.getMovilizacionesCollection().add(movilizaciones);
                numerounidadNew = em.merge(numerounidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = movilizaciones.getIdmovilizacion();
                if (findMovilizaciones(id) == null) {
                    throw new NonexistentEntityException("The movilizaciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movilizaciones movilizaciones;
            try {
                movilizaciones = em.getReference(Movilizaciones.class, id);
                movilizaciones.getIdmovilizacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movilizaciones with id " + id + " no longer exists.", enfe);
            }
            Llamadas numerotelf = movilizaciones.getNumerotelf();
            if (numerotelf != null) {
                numerotelf.getMovilizacionesCollection().remove(movilizaciones);
                numerotelf = em.merge(numerotelf);
            }
            Unidades numerounidad = movilizaciones.getNumerounidad();
            if (numerounidad != null) {
                numerounidad.getMovilizacionesCollection().remove(movilizaciones);
                numerounidad = em.merge(numerounidad);
            }
            em.remove(movilizaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movilizaciones> findMovilizacionesEntities() {
        return findMovilizacionesEntities(true, -1, -1);
    }

    public List<Movilizaciones> findMovilizacionesEntities(int maxResults, int firstResult) {
        return findMovilizacionesEntities(false, maxResults, firstResult);
    }

    private List<Movilizaciones> findMovilizacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movilizaciones.class));
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

    public Movilizaciones findMovilizaciones(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movilizaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovilizacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movilizaciones> rt = cq.from(Movilizaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
