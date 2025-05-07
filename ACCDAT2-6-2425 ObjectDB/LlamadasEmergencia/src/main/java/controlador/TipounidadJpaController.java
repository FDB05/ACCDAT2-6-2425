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
import modelo.Unidades;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.Tipounidad;

/**
 *
 * @author Fernando A
 */
public class TipounidadJpaController implements Serializable {

    public TipounidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipounidad tipounidad) throws PreexistingEntityException, Exception {
        if (tipounidad.getUnidadesCollection() == null) {
            tipounidad.setUnidadesCollection(new ArrayList<Unidades>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Unidades> attachedUnidadesCollection = new ArrayList<Unidades>();
            for (Unidades unidadesCollectionUnidadesToAttach : tipounidad.getUnidadesCollection()) {
                unidadesCollectionUnidadesToAttach = em.getReference(unidadesCollectionUnidadesToAttach.getClass(), unidadesCollectionUnidadesToAttach.getNumerounidad());
                attachedUnidadesCollection.add(unidadesCollectionUnidadesToAttach);
            }
            tipounidad.setUnidadesCollection(attachedUnidadesCollection);
            em.persist(tipounidad);
            for (Unidades unidadesCollectionUnidades : tipounidad.getUnidadesCollection()) {
                Tipounidad oldTipounidadOfUnidadesCollectionUnidades = unidadesCollectionUnidades.getTipounidad();
                unidadesCollectionUnidades.setTipounidad(tipounidad);
                unidadesCollectionUnidades = em.merge(unidadesCollectionUnidades);
                if (oldTipounidadOfUnidadesCollectionUnidades != null) {
                    oldTipounidadOfUnidadesCollectionUnidades.getUnidadesCollection().remove(unidadesCollectionUnidades);
                    oldTipounidadOfUnidadesCollectionUnidades = em.merge(oldTipounidadOfUnidadesCollectionUnidades);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipounidad(tipounidad.getTipounidad()) != null) {
                throw new PreexistingEntityException("Tipounidad " + tipounidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipounidad tipounidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();

        Tipounidad persistentTipounidad = em.find(Tipounidad.class, tipounidad.getTipounidad());

        List<Unidades> unidadesCollectionOld = persistentTipounidad.getUnidadesCollection();
        if (unidadesCollectionOld == null) {
            unidadesCollectionOld = new ArrayList<>();
        }

        List<Unidades> unidadesCollectionNew = tipounidad.getUnidadesCollection();
        if (unidadesCollectionNew == null) {
            unidadesCollectionNew = new ArrayList<>();
        }

        List<String> illegalOrphanMessages = null;
        for (Unidades unidadesCollectionOldUnidades : unidadesCollectionOld) {
            if (!unidadesCollectionNew.contains(unidadesCollectionOldUnidades)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("You must retain Unidades " + unidadesCollectionOldUnidades + " since its tipounidad field is not nullable.");
            }
        }

        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }

        List<Unidades> attachedUnidadesCollectionNew = new ArrayList<>();
        for (Unidades unidadesCollectionNewUnidadesToAttach : unidadesCollectionNew) {
            unidadesCollectionNewUnidadesToAttach = em.getReference(
                unidadesCollectionNewUnidadesToAttach.getClass(),
                unidadesCollectionNewUnidadesToAttach.getNumerounidad()
            );
            attachedUnidadesCollectionNew.add(unidadesCollectionNewUnidadesToAttach);
        }

        unidadesCollectionNew = attachedUnidadesCollectionNew;
        tipounidad.setUnidadesCollection(unidadesCollectionNew);
        tipounidad = em.merge(tipounidad);

        for (Unidades unidadesCollectionNewUnidades : unidadesCollectionNew) {
            if (!unidadesCollectionOld.contains(unidadesCollectionNewUnidades)) {
                Tipounidad oldTipounidad = unidadesCollectionNewUnidades.getTipounidad();
                unidadesCollectionNewUnidades.setTipounidad(tipounidad);
                unidadesCollectionNewUnidades = em.merge(unidadesCollectionNewUnidades);
                if (oldTipounidad != null && !oldTipounidad.equals(tipounidad)) {
                    List<Unidades> oldList = oldTipounidad.getUnidadesCollection();
                    if (oldList != null) {
                        oldList.remove(unidadesCollectionNewUnidades);
                        oldTipounidad.setUnidadesCollection(oldList);
                        em.merge(oldTipounidad);
                    }
                }
            }
        }

        em.getTransaction().commit();
    } catch (Exception ex) {
        String msg = ex.getLocalizedMessage();
        if (msg == null || msg.length() == 0) {
            String id = tipounidad.getTipounidad();
            if (findTipounidad(id) == null) {
                throw new NonexistentEntityException("The tipounidad with id " + id + " no longer exists.");
            }
        }
        throw ex;
    } finally {
        if (em != null) {
            em.close();
        }
    }
}


   public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
    EntityManager em = null;
    try {
        em = getEntityManager();
        em.getTransaction().begin();
        Tipounidad tipounidad;
        try {
            tipounidad = em.getReference(Tipounidad.class, id);
            tipounidad.getTipounidad(); // Asegura que existe
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("La Tipounidad con id " + id + " ya no existe.", enfe);
        }

        List<String> illegalOrphanMessages = null;

        Collection<Unidades> unidadesCollectionOrphanCheck = tipounidad.getUnidadesCollection();
        if (unidadesCollectionOrphanCheck != null) {
            for (Unidades unidad : unidadesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("No se puede eliminar Tipounidad (" + tipounidad.getTipounidad() +
                        ") porque la unidad (" + unidad.getTipounidad()+
                        ") aún está asociada y su campo tipounidad no puede ser nulo.");
            }
        }

        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }

        em.remove(tipounidad);
        em.getTransaction().commit();
    } finally {
        if (em != null) {
            em.close();
        }
    }
}


    public List<Tipounidad> findTipounidadEntities() {
        return findTipounidadEntities(true, -1, -1);
    }

    public List<Tipounidad> findTipounidadEntities(int maxResults, int firstResult) {
        return findTipounidadEntities(false, maxResults, firstResult);
    }

    private List<Tipounidad> findTipounidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipounidad.class));
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

    public Tipounidad findTipounidad(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipounidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipounidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipounidad> rt = cq.from(Tipounidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
