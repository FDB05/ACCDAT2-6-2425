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
import jakarta.persistence.LockModeType;
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
 
 public List<Object[]> CargarAllUnidades() {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT * FROM Unidades", 
        Object[].class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
            return list ;

}
 
 public List<Object[]> filtrarUnidadesPorDisponibilidad(boolean disponibilidad) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.disponibilidad = :estado", 
            Object[].class);
        
        query.setParameter("estado", disponibilidad);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
                return list;

    }

    public List<Object[]> filtrarUnidadesPorTipo(String estado) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT u.numerounidad, u.tipounidad, u.disponibilidad FROM Unidades u WHERE u.estado = :estado", 
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
                return list ;

    }
    
    
    //----------------------------------
    //METODOS PARA FILTRAR LLAAMADAS
    //------------------------------------
    
    public List<Object[]> CargarAllLlamadas() {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT * FROM Llamadas l ", 
        Object[].class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
            return list != null ? list : new ArrayList<>();

}
    
    public List<Object[]> filtrarLlamadasPorEstadoFecha(String estadoId, String fecha) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado,l.descripcion,l.ubicacion FROM Llamadas l WHERE l.estado.tipoestado = :estadoId AND l.fechahora LIKE fecha", 
            Object[].class);
        
        query.setParameter("estadoId", estadoId);
        query.setParameter("fecha",convertirFecha(fecha)); 
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
                return list != null ? list : new ArrayList<>();

    }

    public List<Object[]> filtrarLlamadasPorEstado(String estadoId) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado,l.descripcion,l.ubicacion FROM Llamadas l WHERE l.estado.tipoestado = :estadoId", 
            Object[].class);
        
        query.setParameter("estadoId", estadoId);
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
                return list != null ? list : new ArrayList<>();

    }

    public List<Object[]> filtrarLlamadasPorFecha(String fecha) {
        inicializaFactoryController();
        TypedQuery<Object[]> query = em.createQuery(
            "SELECT l.numeroTelf, l.estado,l.descripcion,l.ubicacion FROM Llamadas l WHERE l.fechahora LIKE :fecha", 
            Object[].class);
        
        query.setParameter("fecha", convertirFecha(fecha)); 
        
        List<Object[]> list = query.getResultList();
        cierraFactoryController();
                return list != null ? list : new ArrayList<>();
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
        
        Unidades unidad = unidadesJpaController.findUnidades(numU );
        
        cierraFactoryController();
        return unidad;
    }

public void eliminarUnidad(int numUnidad) {
    try {
        inicializaFactoryController();
        UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
        unidadesJpaController.destroy(numUnidad);
    } catch (IllegalOrphanException ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cierraFactoryController();
    }
}

public void modificarUnidad(int numUnidad, boolean estado, String tipoUnidad) {
    try {
        inicializaFactoryController();
        UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
        
        Unidades unidad = unidadesJpaController.findUnidades(numUnidad);
        
        if (unidad != null) {
            unidad.setDisponibilidad(estado);
            unidad.setTipounidad(leerTipoUnidad(tipoUnidad)); 
            unidadesJpaController.edit(unidad);
        } else {
            System.out.println("La unidad no existe.");
        }
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cierraFactoryController();
    }
}

public boolean insertaUnidad(int numu,boolean disponibilidad,String tipounidad) {
    boolean existe=false;
    inicializaFactoryController();
    UnidadesJpaController unidadesJpaController = new UnidadesJpaController(emf);
    
    Unidades unidad = new Unidades();
    unidad.setNumerounidad(numu); 
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

public void eliminarLlamada(int numTelefono) {
    try {
        inicializaFactoryController();
        LlamadasJpaController llamadasJpaController = new LlamadasJpaController(emf);
        
        llamadasJpaController.destroy(BigDecimal.valueOf(numTelefono));
    } catch (IllegalOrphanException ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cierraFactoryController();
    }
}


public Estado leerUnEstado(String estado){
    inicializaFactoryController();
        EstadoJpaController estadoJpaController = new EstadoJpaController(emf);
        
        Estado unidad = estadoJpaController.findEstado(estado);
        
        cierraFactoryController();
        return unidad;
    }


public void modificarLlamada(int numTelefono, String fecha, String ubicacion, String descripcion, String estado) {
    try {
        inicializaFactoryController();
        LlamadasJpaController llamadasJpaController = new LlamadasJpaController(emf);
        Llamadas llamada = llamadasJpaController.findLlamadas(BigDecimal.valueOf(numTelefono));
        
        if (llamada != null) {
            
            llamada.setFechahora(convertirFecha(fecha));
            llamada.setUbicacion(ubicacion);
            llamada.setDescripcion(descripcion);
            llamada.setEstado(leerUnEstado(estado)); 

            llamadasJpaController.edit(llamada);
        } else {
            System.out.println("La llamada no existe.");
        }
    } catch (NonexistentEntityException ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        cierraFactoryController();
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