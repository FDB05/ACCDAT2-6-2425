/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.exceptions.IllegalOrphanException;
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
import modelo.Tipounidad;
import modelo.Movilizaciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.Unidades;

/**
 *
 * @author Fernando A
 */
public class UnidadesJpaController implements Serializable {

    public UnidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidades unidades) throws PreexistingEntityException, Exception {
        if (unidades.getMovilizacionesCollection() == null) {
            unidades.setMovilizacionesCollection(new ArrayList<Movilizaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tipounidad tipounidad = unidades.getTipounidad();
            if (tipounidad != null) {
                tipounidad = em.getReference(tipounidad.getClass(), tipounidad.getTipounidad());
                unidades.setTipounidad(tipounidad);
            }
            Collection<Movilizaciones> attachedMovilizacionesCollection = new ArrayList<Movilizaciones>();
            for (Movilizaciones movilizacionesCollectionMovilizacionesToAttach : unidades.getMovilizacionesCollection()) {
                movilizacionesCollectionMovilizacionesToAttach = em.getReference(movilizacionesCollectionMovilizacionesToAttach.getClass(), movilizacionesCollectionMovilizacionesToAttach.getIdmovilizacion());
                attachedMovilizacionesCollection.add(movilizacionesCollectionMovilizacionesToAttach);
            }
            unidades.setMovilizacionesCollection(attachedMovilizacionesCollection);
            em.persist(unidades);
            if (tipounidad != null) {
                tipounidad.getUnidadesCollection().add(unidades);
                tipounidad = em.merge(tipounidad);
            }
            for (Movilizaciones movilizacionesCollectionMovilizaciones : unidades.getMovilizacionesCollection()) {
                Unidades oldNumerounidadOfMovilizacionesCollectionMovilizaciones = movilizacionesCollectionMovilizaciones.getNumerounidad();
                movilizacionesCollectionMovilizaciones.setNumerounidad(unidades);
                movilizacionesCollectionMovilizaciones = em.merge(movilizacionesCollectionMovilizaciones);
                if (oldNumerounidadOfMovilizacionesCollectionMovilizaciones != null) {
                    oldNumerounidadOfMovilizacionesCollectionMovilizaciones.getMovilizacionesCollection().remove(movilizacionesCollectionMovilizaciones);
                    oldNumerounidadOfMovilizacionesCollectionMovilizaciones = em.merge(oldNumerounidadOfMovilizacionesCollectionMovilizaciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUnidades( unidades.getNumerounidad() )!= null) {
                throw new PreexistingEntityException("Unidades " + unidades + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Unidades unidades) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidades persistentUnidades = em.find(Unidades.class, unidades.getNumerounidad());
            Tipounidad tipounidadOld = persistentUnidades.getTipounidad();
            Tipounidad tipounidadNew = unidades.getTipounidad();
            Collection<Movilizaciones> movilizacionesCollectionOld = persistentUnidades.getMovilizacionesCollection();
            Collection<Movilizaciones> movilizacionesCollectionNew = unidades.getMovilizacionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Movilizaciones movilizacionesCollectionOldMovilizaciones : movilizacionesCollectionOld) {
                if (!movilizacionesCollectionNew.contains(movilizacionesCollectionOldMovilizaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movilizaciones " + movilizacionesCollectionOldMovilizaciones + " since its numerounidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (tipounidadNew != null) {
                tipounidadNew = em.getReference(tipounidadNew.getClass(), tipounidadNew.getTipounidad());
                unidades.setTipounidad(tipounidadNew);
            }
            Collection<Movilizaciones> attachedMovilizacionesCollectionNew = new ArrayList<Movilizaciones>();
            for (Movilizaciones movilizacionesCollectionNewMovilizacionesToAttach : movilizacionesCollectionNew) {
                movilizacionesCollectionNewMovilizacionesToAttach = em.getReference(movilizacionesCollectionNewMovilizacionesToAttach.getClass(), movilizacionesCollectionNewMovilizacionesToAttach.getIdmovilizacion());
                attachedMovilizacionesCollectionNew.add(movilizacionesCollectionNewMovilizacionesToAttach);
            }
            movilizacionesCollectionNew = attachedMovilizacionesCollectionNew;
            unidades.setMovilizacionesCollection(movilizacionesCollectionNew);
            unidades = em.merge(unidades);
            if (tipounidadOld != null && !tipounidadOld.equals(tipounidadNew)) {
                tipounidadOld.getUnidadesCollection().remove(unidades);
                tipounidadOld = em.merge(tipounidadOld);
            }
            if (tipounidadNew != null && !tipounidadNew.equals(tipounidadOld)) {
                tipounidadNew.getUnidadesCollection().add(unidades);
                tipounidadNew = em.merge(tipounidadNew);
            }
            for (Movilizaciones movilizacionesCollectionNewMovilizaciones : movilizacionesCollectionNew) {
                if (!movilizacionesCollectionOld.contains(movilizacionesCollectionNewMovilizaciones)) {
                    Unidades oldNumerounidadOfMovilizacionesCollectionNewMovilizaciones = movilizacionesCollectionNewMovilizaciones.getNumerounidad();
                    movilizacionesCollectionNewMovilizaciones.setNumerounidad(unidades);
                    movilizacionesCollectionNewMovilizaciones = em.merge(movilizacionesCollectionNewMovilizaciones);
                    if (oldNumerounidadOfMovilizacionesCollectionNewMovilizaciones != null && !oldNumerounidadOfMovilizacionesCollectionNewMovilizaciones.equals(unidades)) {
                        oldNumerounidadOfMovilizacionesCollectionNewMovilizaciones.getMovilizacionesCollection().remove(movilizacionesCollectionNewMovilizaciones);
                        oldNumerounidadOfMovilizacionesCollectionNewMovilizaciones = em.merge(oldNumerounidadOfMovilizacionesCollectionNewMovilizaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = unidades.getNumerounidad();
                if (findUnidades(id) == null) {
                    throw new NonexistentEntityException("The unidades with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Unidades unidades;
            try {
                unidades = em.getReference(Unidades.class, id);
                unidades.getNumerounidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The unidades with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Movilizaciones> movilizacionesCollectionOrphanCheck = unidades.getMovilizacionesCollection();
            for (Movilizaciones movilizacionesCollectionOrphanCheckMovilizaciones : movilizacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Unidades (" + unidades + ") cannot be destroyed since the Movilizaciones " + movilizacionesCollectionOrphanCheckMovilizaciones + " in its movilizacionesCollection field has a non-nullable numerounidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tipounidad tipounidad = unidades.getTipounidad();
            if (tipounidad != null) {
                tipounidad.getUnidadesCollection().remove(unidades);
                tipounidad = em.merge(tipounidad);
            }
            em.remove(unidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Unidades> findUnidadesEntities() {
        return findUnidadesEntities(true, -1, -1);
    }

    public List<Unidades> findUnidadesEntities(int maxResults, int firstResult) {
        return findUnidadesEntities(false, maxResults, firstResult);
    }

    private List<Unidades> findUnidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Unidades.class));
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

    public Unidades findUnidades(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Unidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Unidades> rt = cq.from(Unidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
