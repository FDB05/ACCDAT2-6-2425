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
import jakarta.persistence.TypedQuery;
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
        List<Llamadas> attachedLlamadasCollection = new ArrayList<>();
        for (Llamadas llamadasCollectionLlamadasToAttach : estado.getLlamadasCollection()) {
            llamadasCollectionLlamadasToAttach = em.getReference(llamadasCollectionLlamadasToAttach.getClass(), llamadasCollectionLlamadasToAttach.getNumerotelf());
            attachedLlamadasCollection.add(llamadasCollectionLlamadasToAttach);
        }
        estado.setLlamadasCollection(attachedLlamadasCollection);
        em.persist(estado);
        for (Llamadas llamadasCollectionLlamadas : estado.getLlamadasCollection()) {
            Estado oldEstado = llamadasCollectionLlamadas.getEstado();
            llamadasCollectionLlamadas.setEstado(estado);
            llamadasCollectionLlamadas = em.merge(llamadasCollectionLlamadas);
            if (oldEstado != null) {
                oldEstado.getLlamadasCollection().remove(llamadasCollectionLlamadas);
                oldEstado = em.merge(oldEstado);
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

        List<Llamadas> llamadasCollectionOld = persistentEstado.getLlamadasCollection();
        if (llamadasCollectionOld == null) {
            llamadasCollectionOld = new ArrayList<>();
        }

        List<Llamadas> llamadasCollectionNew = estado.getLlamadasCollection();
        if (llamadasCollectionNew == null) {
            llamadasCollectionNew = new ArrayList<>();
        }

        List<String> illegalOrphanMessages = null;
        for (Llamadas llamadasOld : llamadasCollectionOld) {
            if (!llamadasCollectionNew.contains(llamadasOld)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("You must retain Llamadas " + llamadasOld + " since its estado field is not nullable.");
            }
        }

        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }

        List<Llamadas> attachedLlamadasCollectionNew = new ArrayList<>();
        for (Llamadas llamadasNewToAttach : llamadasCollectionNew) {
            llamadasNewToAttach = em.getReference(
                llamadasNewToAttach.getClass(),
                llamadasNewToAttach.getNumerotelf()
            );
            attachedLlamadasCollectionNew.add(llamadasNewToAttach);
        }

        llamadasCollectionNew = attachedLlamadasCollectionNew;
        estado.setLlamadasCollection(llamadasCollectionNew);
        estado = em.merge(estado);

        for (Llamadas llamadasNew : llamadasCollectionNew) {
            if (!llamadasCollectionOld.contains(llamadasNew)) {
                Estado oldEstado = llamadasNew.getEstado();
                llamadasNew.setEstado(estado);
                llamadasNew = em.merge(llamadasNew);
                if (oldEstado != null && !oldEstado.equals(estado)) {
                    List<Llamadas> oldList = oldEstado.getLlamadasCollection();
                    if (oldList != null) {
                        oldList.remove(llamadasNew);
                        oldEstado.setLlamadasCollection(oldList);
                        em.merge(oldEstado);
                    }
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
            estado.getTipoestado(); // Asegura que está cargado
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("El estado con id " + id + " ya no existe.", enfe);
        }

        List<String> illegalOrphanMessages = null;
        Collection<Llamadas> llamadasCollectionOrphanCheck = estado.getLlamadasCollection();

        if (llamadasCollectionOrphanCheck != null) {
            for (Llamadas llamada : llamadasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("No se puede eliminar el Estado (" + estado.getTipoestado() +
                        ") porque la llamada (" + llamada.getEstado()+
                        ") aún está asociada y su campo estado no puede ser nulo.");
            }
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
    public List<Estado> buscarEstadosConFiltros(String tipo, String nombre) {
    EntityManager em = getEntityManager();
    try {
        String jpql = "SELECT e FROM Estado e WHERE 1=1";

        if (tipo != null && !tipo.isEmpty()) {
            jpql += " AND e.tipoestado = :tipo";
        }
        if (nombre != null && !nombre.isEmpty()) {
            jpql += " AND e.nombreestado LIKE :nombre";
        }

        TypedQuery<Estado> query = em.createQuery(jpql, Estado.class);

        if (tipo != null && !tipo.isEmpty()) {
            query.setParameter("tipo", tipo);
        }
        if (nombre != null && !nombre.isEmpty()) {
            query.setParameter("nombre", "%" + nombre + "%");
        }

        return query.getResultList();
    } finally {
        em.close();
    }
}

    
}
