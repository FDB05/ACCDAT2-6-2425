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
import modelo.Estado;
import modelo.Movilizaciones;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.Llamadas;

/**
 *
 * @author Fernando A
 */
public class LlamadasJpaController implements Serializable {

    public LlamadasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Llamadas llamadas) throws PreexistingEntityException, Exception {
        if (llamadas.getMovilizacionesCollection() == null) {
            llamadas.setMovilizacionesCollection(new ArrayList<Movilizaciones>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado estado = llamadas.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getTipoestado());
                llamadas.setEstado(estado);
            }
            Collection<Movilizaciones> attachedMovilizacionesCollection = new ArrayList<Movilizaciones>();
            for (Movilizaciones movilizacionesCollectionMovilizacionesToAttach : llamadas.getMovilizacionesCollection()) {
                movilizacionesCollectionMovilizacionesToAttach = em.getReference(movilizacionesCollectionMovilizacionesToAttach.getClass(), movilizacionesCollectionMovilizacionesToAttach.getIdmovilizacion());
                attachedMovilizacionesCollection.add(movilizacionesCollectionMovilizacionesToAttach);
            }
            llamadas.setMovilizacionesCollection(attachedMovilizacionesCollection);
            em.persist(llamadas);
            if (estado != null) {
                estado.getLlamadasCollection().add(llamadas);
                estado = em.merge(estado);
            }
            for (Movilizaciones movilizacionesCollectionMovilizaciones : llamadas.getMovilizacionesCollection()) {
                Llamadas oldNumerotelfOfMovilizacionesCollectionMovilizaciones = movilizacionesCollectionMovilizaciones.getNumerotelf();
                movilizacionesCollectionMovilizaciones.setNumerotelf(llamadas);
                movilizacionesCollectionMovilizaciones = em.merge(movilizacionesCollectionMovilizaciones);
                if (oldNumerotelfOfMovilizacionesCollectionMovilizaciones != null) {
                    oldNumerotelfOfMovilizacionesCollectionMovilizaciones.getMovilizacionesCollection().remove(movilizacionesCollectionMovilizaciones);
                    oldNumerotelfOfMovilizacionesCollectionMovilizaciones = em.merge(oldNumerotelfOfMovilizacionesCollectionMovilizaciones);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLlamadas(llamadas.getNumerotelf()) != null) {
                throw new PreexistingEntityException("Llamadas " + llamadas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Llamadas llamadas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Llamadas persistentLlamadas = em.find(Llamadas.class, llamadas.getNumerotelf());
            Estado estadoOld = persistentLlamadas.getEstado();
            Estado estadoNew = llamadas.getEstado();
            Collection<Movilizaciones> movilizacionesCollectionOld = persistentLlamadas.getMovilizacionesCollection();
            Collection<Movilizaciones> movilizacionesCollectionNew = llamadas.getMovilizacionesCollection();
            List<String> illegalOrphanMessages = null;
            for (Movilizaciones movilizacionesCollectionOldMovilizaciones : movilizacionesCollectionOld) {
                if (!movilizacionesCollectionNew.contains(movilizacionesCollectionOldMovilizaciones)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Movilizaciones " + movilizacionesCollectionOldMovilizaciones + " since its numerotelf field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getTipoestado());
                llamadas.setEstado(estadoNew);
            }
            Collection<Movilizaciones> attachedMovilizacionesCollectionNew = new ArrayList<Movilizaciones>();
            for (Movilizaciones movilizacionesCollectionNewMovilizacionesToAttach : movilizacionesCollectionNew) {
                movilizacionesCollectionNewMovilizacionesToAttach = em.getReference(movilizacionesCollectionNewMovilizacionesToAttach.getClass(), movilizacionesCollectionNewMovilizacionesToAttach.getIdmovilizacion());
                attachedMovilizacionesCollectionNew.add(movilizacionesCollectionNewMovilizacionesToAttach);
            }
            movilizacionesCollectionNew = attachedMovilizacionesCollectionNew;
            llamadas.setMovilizacionesCollection(movilizacionesCollectionNew);
            llamadas = em.merge(llamadas);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getLlamadasCollection().remove(llamadas);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getLlamadasCollection().add(llamadas);
                estadoNew = em.merge(estadoNew);
            }
            for (Movilizaciones movilizacionesCollectionNewMovilizaciones : movilizacionesCollectionNew) {
                if (!movilizacionesCollectionOld.contains(movilizacionesCollectionNewMovilizaciones)) {
                    Llamadas oldNumerotelfOfMovilizacionesCollectionNewMovilizaciones = movilizacionesCollectionNewMovilizaciones.getNumerotelf();
                    movilizacionesCollectionNewMovilizaciones.setNumerotelf(llamadas);
                    movilizacionesCollectionNewMovilizaciones = em.merge(movilizacionesCollectionNewMovilizaciones);
                    if (oldNumerotelfOfMovilizacionesCollectionNewMovilizaciones != null && !oldNumerotelfOfMovilizacionesCollectionNewMovilizaciones.equals(llamadas)) {
                        oldNumerotelfOfMovilizacionesCollectionNewMovilizaciones.getMovilizacionesCollection().remove(movilizacionesCollectionNewMovilizaciones);
                        oldNumerotelfOfMovilizacionesCollectionNewMovilizaciones = em.merge(oldNumerotelfOfMovilizacionesCollectionNewMovilizaciones);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = llamadas.getNumerotelf();
                if (findLlamadas(id) == null) {
                    throw new NonexistentEntityException("The llamadas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Llamadas llamadas;
            try {
                llamadas = em.getReference(Llamadas.class, id);
                llamadas.getNumerotelf();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The llamadas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Movilizaciones> movilizacionesCollectionOrphanCheck = llamadas.getMovilizacionesCollection();
            for (Movilizaciones movilizacionesCollectionOrphanCheckMovilizaciones : movilizacionesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Llamadas (" + llamadas + ") cannot be destroyed since the Movilizaciones " + movilizacionesCollectionOrphanCheckMovilizaciones + " in its movilizacionesCollection field has a non-nullable numerotelf field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estado estado = llamadas.getEstado();
            if (estado != null) {
                estado.getLlamadasCollection().remove(llamadas);
                estado = em.merge(estado);
            }
            em.remove(llamadas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Llamadas> findLlamadasEntities() {
        return findLlamadasEntities(true, -1, -1);
    }

    public List<Llamadas> findLlamadasEntities(int maxResults, int firstResult) {
        return findLlamadasEntities(false, maxResults, firstResult);
    }

    private List<Llamadas> findLlamadasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Llamadas.class));
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

    public Llamadas findLlamadas(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Llamadas.class, id);
        } finally {
            em.close();
        }
    }

    public int getLlamadasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Llamadas> rt = cq.from(Llamadas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
