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
import modelo.Llamadas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.Estado;

/**
 *
 * @author Fernando A
 */
public class EstadoJpaController implements Serializable {

    public EstadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) throws PreexistingEntityException, Exception {
        if (estado.getLlamadasCollection() == null) {
            estado.setLlamadasCollection(new ArrayList<Llamadas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Llamadas> attachedLlamadasCollection = new ArrayList<Llamadas>();
            for (Llamadas llamadasCollectionLlamadasToAttach : estado.getLlamadasCollection()) {
                llamadasCollectionLlamadasToAttach = em.getReference(llamadasCollectionLlamadasToAttach.getClass(), llamadasCollectionLlamadasToAttach.getNumerotelf());
                attachedLlamadasCollection.add(llamadasCollectionLlamadasToAttach);
            }
            estado.setLlamadasCollection(attachedLlamadasCollection);
            em.persist(estado);
            for (Llamadas llamadasCollectionLlamadas : estado.getLlamadasCollection()) {
                Estado oldEstadoOfLlamadasCollectionLlamadas = llamadasCollectionLlamadas.getEstado();
                llamadasCollectionLlamadas.setEstado(estado);
                llamadasCollectionLlamadas = em.merge(llamadasCollectionLlamadas);
                if (oldEstadoOfLlamadasCollectionLlamadas != null) {
                    oldEstadoOfLlamadasCollectionLlamadas.getLlamadasCollection().remove(llamadasCollectionLlamadas);
                    oldEstadoOfLlamadasCollectionLlamadas = em.merge(oldEstadoOfLlamadasCollectionLlamadas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstado(estado.getTipoestado()) != null) {
                throw new PreexistingEntityException("Estado " + estado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estado persistentEstado = em.find(Estado.class, estado.getTipoestado());
            Collection<Llamadas> llamadasCollectionOld = persistentEstado.getLlamadasCollection();
            Collection<Llamadas> llamadasCollectionNew = estado.getLlamadasCollection();
            List<String> illegalOrphanMessages = null;
            for (Llamadas llamadasCollectionOldLlamadas : llamadasCollectionOld) {
                if (!llamadasCollectionNew.contains(llamadasCollectionOldLlamadas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Llamadas " + llamadasCollectionOldLlamadas + " since its estado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Llamadas> attachedLlamadasCollectionNew = new ArrayList<Llamadas>();
            for (Llamadas llamadasCollectionNewLlamadasToAttach : llamadasCollectionNew) {
                llamadasCollectionNewLlamadasToAttach = em.getReference(llamadasCollectionNewLlamadasToAttach.getClass(), llamadasCollectionNewLlamadasToAttach.getNumerotelf());
                attachedLlamadasCollectionNew.add(llamadasCollectionNewLlamadasToAttach);
            }
            llamadasCollectionNew = attachedLlamadasCollectionNew;
            estado.setLlamadasCollection(llamadasCollectionNew);
            estado = em.merge(estado);
            for (Llamadas llamadasCollectionNewLlamadas : llamadasCollectionNew) {
                if (!llamadasCollectionOld.contains(llamadasCollectionNewLlamadas)) {
                    Estado oldEstadoOfLlamadasCollectionNewLlamadas = llamadasCollectionNewLlamadas.getEstado();
                    llamadasCollectionNewLlamadas.setEstado(estado);
                    llamadasCollectionNewLlamadas = em.merge(llamadasCollectionNewLlamadas);
                    if (oldEstadoOfLlamadasCollectionNewLlamadas != null && !oldEstadoOfLlamadasCollectionNewLlamadas.equals(estado)) {
                        oldEstadoOfLlamadasCollectionNewLlamadas.getLlamadasCollection().remove(llamadasCollectionNewLlamadas);
                        oldEstadoOfLlamadasCollectionNewLlamadas = em.merge(oldEstadoOfLlamadasCollectionNewLlamadas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = estado.getTipoestado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getTipoestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Llamadas> llamadasCollectionOrphanCheck = estado.getLlamadasCollection();
            for (Llamadas llamadasCollectionOrphanCheckLlamadas : llamadasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estado (" + estado + ") cannot be destroyed since the Llamadas " + llamadasCollectionOrphanCheckLlamadas + " in its llamadasCollection field has a non-nullable estado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
