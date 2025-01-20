package modelo;

import com.mycompany.llamadasemergencia.LlamadasEmergencia;
import controlador.EstadoJpaController;
import controlador.LlamadasJpaController;
import controlador.TipounidadJpaController;
import controlador.UnidadesJpaController;
import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import modelo.Llamadas;
import modelo.Unidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloMaestro {

    static EntityManagerFactory emf;
    static EntityManager em;
    //--------------------------
    //METODOS DE UTILIDAD
    //--------------------------
    
    public static java.util.Date convertirFecha(String fecha){
        java.util.Date fechaUtil = null;
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");    
            fechaUtil = s.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(fechaUtil.getTime());
    }
    
    public static void inicializaFactoryController()
{
emf=Persistence.createEntityManagerFactory("com.mycompany_LlamadasEmergencia_jar_1.0-SNAPSHOTPU");
em=emf.createEntityManager();

}

public static void cierraFactoryController(){
emf.close();
}


//-----------------------------------
//-----------------------------------
//-----------------------------------




//-----------------------------------
// MÉTODOS FILTROS PARA LAS UNIDADES (JPQL)
//--------------------------------------
 public List<Object[]> filtrarUnidadesPorNumeroUnidadTipoUnidadDisponibilidad(int nUnidad, String tUnidad, boolean estado) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.numerounidad = :numeroUnidad AND u.tipounidad.tipounidad = :tipoUnidad AND u.disponibilidad = :estado", 
            Object[].class);
        
        query.setParameter("numeroUnidad", BigDecimal.valueOf(nUnidad));
        query.setParameter("tipoUnidad", tUnidad);
        query.setParameter("estado", estado);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarUnidadesPorNumeroUnidadTipoUnidad(int nUnidad, String tUnidad) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.numerounidad = :numeroUnidad AND u.tipounidad.tipounidad = :tipoUnidad", 
            Object[].class);
        
        query.setParameter("numeroUnidad", BigDecimal.valueOf(nUnidad));
        query.setParameter("tipoUnidad", tUnidad);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

public List<Object[]> filtrarUnidadesPorNumeroUnidadDisponibilidad(int nUnidad, boolean estado) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.numerounidad = :numeroUnidad AND u.disponibilidad = :estado", 
            Object[].class);
        
        query.setParameter("numeroUnidad", BigDecimal.valueOf(nUnidad));
        query.setParameter("estado", estado);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
}

public List<Object[]> filtrarUnidadesPorNumeroUnidad(int nUnidad) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.numerounidad = :numeroUnidad", 
            Object[].class);
        
        query.setParameter("numeroUnidad", BigDecimal.valueOf(nUnidad));
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarUnidadesPorTipoUnidad(String tUnidad) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.tipounidad.tipounidad = :tipoUnidad", 
            Object[].class);
        
        query.setParameter("tipoUnidad", tUnidad);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarUnidadesPorDisponibilidad(boolean estado) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.disponibilidad = :estado AND", 
            Object[].class);
        
        query.setParameter("estado", estado);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarUnidadesPorTipoUnidadDisponibilidad(String tUnidad, boolean estado) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.tipounidad.tipounidad = :tipoUnidad AND u.disponibilidad = :estado", 
            Object[].class);
        
        query.setParameter("tipoUnidad", tUnidad);
        query.setParameter("estado", estado);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    // Métodos para filtrar llamadas
    public List<Object[]> filtrarLlamadasPorNumeroTelefonoEstadoFecha(int numeroTelefono, String estadoId, String fecha) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.numeroTelf = :numeroTelefono AND l.estado.tipoestado = :estadoId AND l.fechahora = :fecha", 
            Object[].class);
        
        query.setParameter("numeroTelefono", BigDecimal.valueOf(numeroTelefono));
        query.setParameter("estadoId", estadoId);
        query.setParameter("fecha", Date.valueOf(fecha)); 
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarLlamadasPorNumeroTelefonoYEstado(int numeroTelefono, String estadoId) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.numeroTelf = :numeroTelefono AND l.estado.tipoestado = :estadoId", 
            Object[].class);
        
        query.setParameter("numeroTelefono", BigDecimal.valueOf(numeroTelefono));
        query.setParameter("estadoId", estadoId);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarLlamadasPorNumeroTelefonoFecha(int numeroTelefono, String fecha) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.numeroTelf = :numeroTelefono AND l.fechahora = :fecha", 
            Object[].class);
        
        query.setParameter("numeroTelefono", BigDecimal.valueOf(numeroTelefono));
        query.setParameter("fecha", Date.valueOf(fecha)); 
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

public List<Object[]> filtrarLlamadasPorEstadoFecha(String estadoId, String fecha) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.estado.tipoestado = :estadoId AND l.fechahora = :fecha", 
            Object[].class);
        
        query.setParameter("estadoId", estadoId);
        query.setParameter("fecha", Date.valueOf(fecha)); 
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarLlamadasPorNumeroTelefono(int numeroTelefono) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.numeroTelf = :numeroTelefono", 
            Object[].class);
        
        query.setParameter("numeroTelefono", BigDecimal.valueOf(numeroTelefono));
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarLlamadasPorEstado(String estadoId) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.estado.tipoestado = :estadoId", 
            Object[].class);
        
        query.setParameter("estadoId", estadoId);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

    public List<Object[]> filtrarLlamadasPorFecha(String fecha) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado, l.fechahora FROM Llamadas l WHERE l.fechahora = :fecha", 
            Object[].class);
        
        query.setParameter("fecha", Date.valueOf(fecha)); 
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

///-------------------------
//--------------------------
///-------------------------
public static List<Object[]> LeerDataTipoUnidad() {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT t.tipounidad, t.nombreunidad FROM Tipounidad t", 
            Object[].class);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
        return list;
    }

   public static List<Object[]> LeerDataEstado() {
    try {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT e.tipoestado, e.nombreestado FROM Estado e", 
            Object[].class);
        
        List<Object[]> list = query.getResultList();
        return list;
    } catch (Exception e) {
        e.printStackTrace(); 
        return new ArrayList<>(); 
    } finally {
        cierraFactoryController();
    }
}
//-----------------------------------
//-----------------------------------
//-----------------------------------
        

///-----------------------------------
///Modificar,leer inidvidual,insertar,borrar(Tabla Unidades)
///------------------------------------


private Tipounidad leerTipoUnidad(String tunidad){
inicializaFactoryController();

TipounidadJpaController tipounidadJpaController=new  TipounidadJpaController(emf);
Tipounidad tipounidad=tipounidadJpaController.findTipounidad(tunidad);

cierraFactoryController();
return tipounidad;
}

public Unidades leerUnaUnidad(int numU){
    inicializaFactoryController();
        UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
        
        Unidades unidad = unidadesJpaController.findUnidades(BigDecimal.valueOf(numU) );
        
        cierraFactoryController();
        return unidad;
    }

public void eliminarUnaUnidad(int numU){
        try {
            inicializaFactoryController();
            UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
            
            unidadesJpaController.destroy( BigDecimal.valueOf(numU) );
   cierraFactoryController();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
       
}

public void modicarUnidad(int numU,boolean estado,String tipoUnidad){
        try {
            inicializaFactoryController();
            UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
            
            Unidades unidad = unidadesJpaController.findUnidades(BigDecimal.valueOf(numU) );
            
            unidad.setDisponibilidad(estado);
            unidad.setTipounidad(leerTipoUnidad(tipoUnidad));
            
            
            unidadesJpaController.edit(unidad);
            cierraFactoryController();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public boolean insertaUnidad(int numu,boolean disponibilidad,String tipounidad) {
    boolean existe=false;
    inicializaFactoryController();
    UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
    
    Unidades unidad = new Unidades();
    unidad.setNumerounidad(BigDecimal.valueOf(numu)); 
    unidad.setDisponibilidad(disponibilidad); 

    TipounidadJpaController tipounidadJpaController = new TipounidadJpaController(emf);
    Tipounidad tipoUnidad = tipounidadJpaController.findTipounidad(tipounidad);
    unidad.setTipounidad(tipoUnidad);
    
    try {
        unidadesJpaController.create(unidad);
    } catch (Exception ex) {
        Logger.getLogger(LlamadasEmergencia.class.getName()).log(Level.SEVERE, null, ex);
        existe=true;
    }finally{
    cierraFactoryController();
    }
return existe;
}



///-----------------------------------
///Modificar,leer inidvidual,insertar,borrar(Tabla Llamadas)
///------------------------------------


public Llamadas leerUnallamada(int ntef){
    inicializaFactoryController();
        LlamadasJpaController  llamadasJpaController= new LlamadasJpaController(emf);
        
        Llamadas llamada = llamadasJpaController.findLlamadas(BigDecimal.valueOf(ntef) );
        
        cierraFactoryController();
        return llamada;
    }

public void eliminarUnallamada(int numtef){
        try {
            inicializaFactoryController();
             LlamadasJpaController llmadasJpaController = new LlamadasJpaController(emf);
            
            llmadasJpaController.destroy(BigDecimal.valueOf(numtef) );
   cierraFactoryController();
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
       
}


public Estado leerUnEstado(String estado){
    inicializaFactoryController();
        EstadoJpaController estadoJpaController = new EstadoJpaController(emf);
        
        Estado unidad = estadoJpaController.findEstado(estado);
        
        cierraFactoryController();
        return unidad;
    }


public void modificarLlamada(int numU,String fechahora,String ubicacion,String descripcion,String estado){
        try {
            
            inicializaFactoryController();
            LlamadasJpaController llamadasJpaController = new LlamadasJpaController(emf);
            
            Llamadas llamada = llamadasJpaController.findLlamadas(BigDecimal.valueOf(numU));
            
            llamada.setDescripcion(descripcion);
            llamada.setEstado(leerUnEstado(estado));
            
            llamada.setFechahora(convertirFecha(fechahora));
            llamada.setUbicacion(ubicacion);
            
            
            llamadasJpaController.edit(llamada);
            cierraFactoryController();
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public boolean insertaLlamada(int numtef,String fecha,String ubicacion,String descripcion,String state) {
    boolean existe=false;
    inicializaFactoryController();
    LlamadasJpaController llamadasJpaController = new LlamadasJpaController(emf);
    Date date=(Date) convertirFecha(fecha);
    Llamadas llamada = new Llamadas();
    llamada.setNumerotelf(BigDecimal.valueOf(numtef)); 
    llamada.setFechahora(date); 
    llamada.setUbicacion(ubicacion); 
    llamada.setDescripcion(descripcion); 
    
    // Si necesitas establecer el estado, primero debes obtenerlo
    EstadoJpaController estadoJpaController = new EstadoJpaController(emf);
    Estado estado = estadoJpaController.findEstado(state); 
    llamada.setEstado(estado);
    
    try {
        llamadasJpaController.create(llamada);
    } catch (Exception ex) {
        Logger.getLogger(LlamadasEmergencia.class.getName()).log(Level.SEVERE, null, ex);
        existe=true;
        }finally{
    cierraFactoryController();
    }
    return existe;
}
//----------------------------------
//CREACION DE DATOS PERSISTENTES
//---------------------------------

public void inicializarDatos() {
        inicializaFactoryController();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

           
            if (!datosExistentes("Estado")) {
                insertarDatosEstado();
            }

            
            if (!datosExistentes("TipoUnidad")) {
                insertarDatosTipoUnidad();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            cierraFactoryController();
        }
    }

    private boolean datosExistentes(String nombreTabla) {
    Query query = em.createNativeQuery("SELECT COUNT(*) FROM " + nombreTabla);
    BigDecimal count = (BigDecimal) query.getSingleResult(); 
    return count.compareTo(BigDecimal.ZERO) > 0; 
}

    private void insertarDatosEstado() {
        Estado estado1 = new Estado();
        estado1.setTipoestado("DE");
        estado1.setNombreestado("Denegado");
        em.persist(estado1);

        Estado estado2 = new Estado();
        estado2.setTipoestado("AT");
        estado2.setNombreestado("Atendida");
        em.persist(estado2);

        Estado estado3 = new Estado();
        estado3.setTipoestado("NT");
        estado3.setNombreestado("Sin atender");
        em.persist(estado3);

        Estado estado4 = new Estado();
        estado4.setTipoestado("PC");
        estado4.setNombreestado("En proceso");
        em.persist(estado4);
    }

    private void insertarDatosTipoUnidad() {
        Tipounidad tipoUnidad1 = new Tipounidad();
        tipoUnidad1.setTipounidad("AM");
        tipoUnidad1.setNombreunidad("Ambulancia");
        em.persist(tipoUnidad1);

        Tipounidad tipoUnidad2 = new Tipounidad();
        tipoUnidad2.setTipounidad("BM");
        tipoUnidad2.setNombreunidad("Bombero");
        em.persist(tipoUnidad2);

        Tipounidad tipoUnidad3 = new Tipounidad();
        tipoUnidad3.setTipounidad("PN");
        tipoUnidad3.setNombreunidad("Policia Nacional");
        em.persist(tipoUnidad3);
    }
}