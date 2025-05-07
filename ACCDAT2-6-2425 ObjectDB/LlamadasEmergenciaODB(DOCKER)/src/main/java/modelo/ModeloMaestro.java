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
import jakarta.persistence.NoResultException;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
public static List<Tipounidad> LeerDataTipoUnidad() {
    inicializaFactoryController();
    
    TypedQuery<Tipounidad> query = em.createQuery(
        "SELECT t FROM Tipounidad t", 
        Tipounidad.class);

    List<Tipounidad> list = query.getResultList();
    cierraFactoryController();
    return list;
}



   public static List<Estado> LeerDataEstado() {
    try {
        inicializaFactoryController();
        TypedQuery<Estado> query = em.createQuery(
            "SELECT e FROM Estado e", 
            Estado.class);
        
        return query.getResultList();
    } catch (Exception e) {
        e.printStackTrace(); 
        return new ArrayList<>(); 
    } finally {
        cierraFactoryController();
    }
}

//    public static void consultaDatosUsuarioConJPQL(int idUsuario){
//                
//        Usuarios usuario = null;
//        
//        TypedQuery<Usuarios> query = entitymanager.createQuery("Select u from Usuarios u WHERE u.idusuario=:IDUSUARIOP", Usuarios.class);
//        query.setParameter("IDUSUARIOP", idUsuario);
//        
//
//        try{
//            usuario = query.getSingleResult();
//        
//            System.out.print("USUARIO \t");
//            System.out.print("CONTRASEÑA");
//            System.out.println();
//
//            System.out.print(usuario.getNombre()+"\t");
//            System.out.print("\t"+usuario.getContra());
//            System.out.println();
//
//                    
//        } catch (NoResultException e){
//            System.out.println("El usuario no existe");
//        }
//    }
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

        if (!datosExistentes("Tipounidad")) {
            insertarDatosTipoUnidad(); 
        }

        transaction.commit(); 
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();  
        }
        e.printStackTrace();
    } finally {
        // Cierra el EntityManager solo después de finalizar la transacción
        if (em != null && em.isOpen()) {
            em.close();
        }
        
        // Ahora podemos cerrar el EntityManagerFactory ya que no está en uso
        cierraFactoryController();  
    }
}

private boolean datosExistentes(String nombreTabla) {
    // Mapea el nombre de la tabla a la consulta JPQL correspondiente
    Map<String, String> tablaToEntityMap = new HashMap<>();
    tablaToEntityMap.put("Tipounidad", "SELECT COUNT(t) FROM Tipounidad t");
    tablaToEntityMap.put("Unidades", "SELECT COUNT(u) FROM Unidades u");
    tablaToEntityMap.put("Estado", "SELECT COUNT(e) FROM Estado e");  // Agregar la entidad Estado
    // Agregar más entradas según sea necesario

    // Verifica si el nombre de la tabla existe en el mapa
    String jpqlQuery = tablaToEntityMap.get(nombreTabla);
    
    if (jpqlQuery == null) {
        throw new IllegalArgumentException("Tabla desconocida: " + nombreTabla);
    }

    // Ejecuta la consulta JPQL
    Query query = em.createQuery(jpqlQuery);
    Long count = (Long) query.getSingleResult();
    return count > 0;
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
    tipoUnidad1.setNumerotelefono("112");
    tipoUnidad1.setLocalidad("Ciudad Real");
    em.persist(tipoUnidad1);

    Tipounidad tipoUnidad2 = new Tipounidad();
    tipoUnidad2.setTipounidad("BM");
    tipoUnidad2.setNombreunidad("Bombero");
    tipoUnidad2.setNumerotelefono("1006");
    tipoUnidad2.setLocalidad("Ciudad Real");
    em.persist(tipoUnidad2);

    Tipounidad tipoUnidad3 = new Tipounidad();
    tipoUnidad3.setTipounidad("PN");
    tipoUnidad3.setNombreunidad("Policía Nacional");
    tipoUnidad3.setNumerotelefono("091");
    tipoUnidad3.setLocalidad("Ciudad Real");
    em.persist(tipoUnidad3);
}

    
    //JULIAN
    
   public boolean insertaTipoUnidad(String tipo, String nombre, String telefono, String localidad) {
    boolean existe = false;
    inicializaFactoryController();
    TipounidadJpaController tipounidadJpaController = new TipounidadJpaController(emf);

    Tipounidad tipoUnidad = new Tipounidad();
    tipoUnidad.setTipounidad(tipo); 
    tipoUnidad.setNombreunidad(nombre); 
    tipoUnidad.setNumerotelefono(telefono);
    tipoUnidad.setLocalidad(localidad);

    try {
        tipounidadJpaController.create(tipoUnidad);
    } catch (Exception ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        existe = true;
    } finally {
        cierraFactoryController();
    }
    return existe;
}

    public void eliminarTipoUnidad(String tipo) {
    inicializaFactoryController();
    TipounidadJpaController tipounidadJpaController = new TipounidadJpaController(emf);

    Tipounidad tipoUnidad = tipounidadJpaController.findTipounidad(tipo);  

    if (tipoUnidad != null) {
        try {
            // Eliminar unidades asociadas si las hay
            if (tipoUnidad.getUnidadesCollection() != null) {
                for (Unidades u : tipoUnidad.getUnidadesCollection()) {
                    em.getTransaction().begin();
                    em.remove(em.contains(u) ? u : em.merge(u));
                    em.getTransaction().commit();
                }
            }

            tipounidadJpaController.destroy(tipo);  // Ahora puedes eliminarla
        } catch (IllegalOrphanException | NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cierraFactoryController();
        }
    }
}



public void modificarTipoUnidad(String tipo, String nombre) {
    inicializaFactoryController();
    TipounidadJpaController tipounidadJpaController = new TipounidadJpaController(emf);

    Tipounidad tipoUnidad = tipounidadJpaController.findTipounidad(tipo);  // Buscar tipo de unidad

    if (tipoUnidad != null) {
        tipoUnidad.setNombreunidad(nombre);  // Modificar el nombre de la unidad
        try {
            tipounidadJpaController.edit(tipoUnidad);  // Guardar los cambios
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    cierraFactoryController();
}


public List<Object[]> cargarTipoUnidad() {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT t.tipounidad, t.nombreunidad FROM Tipounidad t", 
        Object[].class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public boolean insertaEstado(String tipo, String nombre) {
    boolean existe = false;
    inicializaFactoryController();
    EstadoJpaController estadoJpaController = new EstadoJpaController(emf);

    Estado estado = new Estado();
    estado.setTipoestado(tipo);
    estado.setNombreestado(nombre);

    try {
        estadoJpaController.create(estado);
    } catch (Exception ex) {
        Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        existe = true;
    } finally {
        cierraFactoryController();
    }
    return existe;
}

public void modificarEstado(String tipo, String nombre) {
    inicializaFactoryController();
    EstadoJpaController estadoJpaController = new EstadoJpaController(emf);

    Estado estado = estadoJpaController.findEstado(tipo);

    if (estado != null) {
        try {
            estado.setNombreestado(nombre);
            estadoJpaController.edit(estado);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    cierraFactoryController();
}
public List<Estado> cargarEstados() {
    inicializaFactoryController();
    TypedQuery<Estado> query = em.createQuery("SELECT e FROM Estado e", Estado.class);
    List<Estado> list = query.getResultList();
    cierraFactoryController();
    return list;
}


public List<Object[]> cargarEstado() {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT e.tipoestado, e.nombreestado FROM Estado e", 
        Object[].class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}
public List<Estado> buscarEstadosFiltrados(String tipo, String nombre) {
    inicializaFactoryController();
    EstadoJpaController estadoJpaController = new EstadoJpaController(emf);
    List<Estado> lista = estadoJpaController.buscarEstadosConFiltros(tipo, nombre);
    cierraFactoryController();
    return lista;
}

public void eliminarEstado(String tipoEstado) {
    inicializaFactoryController(); 
    EstadoJpaController estadoJpaController = new EstadoJpaController(emf); 

    Estado estado = estadoJpaController.findEstado(tipoEstado); 

    if (estado != null) { // Si el estado existe, lo eliminamos
        try {
            estadoJpaController.destroy(tipoEstado); // Eliminar el estado de la base de datos
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex); 
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ModeloMaestro.class.getName()).log(Level.SEVERE, null, ex); 
        } finally {
            cierraFactoryController(); 
        }
    }
}
/*public List<Tipounidad> buscarTipoUnidadFiltrado(String telefono, String localidad) {
    inicializaFactoryController();
    List<Tipounidad> resultado = new ArrayList<>();
    try {
        var em = emf.createEntityManager();
        String query = "SELECT t FROM Tipounidad t WHERE 1=1";
        if (telefono != null && !telefono.isEmpty()) {
            query += " AND t.numerotelefono = :telefono";
        }
        if (localidad != null && !localidad.isEmpty()) {
            query += " AND t.localidad = :localidad";
        }
        TypedQuery<Tipounidad> q = em.createQuery(query, Tipounidad.class);
        if (telefono != null && !telefono.isEmpty()) {
            q.setParameter("telefono", telefono);
        }
        if (localidad != null && !localidad.isEmpty()) {
            q.setParameter("localidad", localidad);
        }
        resultado = q.getResultList();
    } finally {
        cierraFactoryController();
    }
    return resultado;
}*/

public List<Tipounidad> buscarTipoUnidadFiltrado(String telefono, String localidad) {
    inicializaFactoryController();
    List<Tipounidad> resultado = new ArrayList<>();
    System.out.println("Consulta almacenada con parametros funcionando...");
    try {
        var em = emf.createEntityManager();

        if ((telefono == null || telefono.isEmpty()) && (localidad == null || localidad.isEmpty())) {
            resultado = em.createNamedQuery("Tipounidad.findAll", Tipounidad.class).getResultList();
        } else if (telefono != null && !telefono.isEmpty() && (localidad == null || localidad.isEmpty())) {
            resultado = em.createNamedQuery("Tipounidad.findByTelefono", Tipounidad.class)
                          .setParameter("telefono", telefono)
                          .getResultList();
        } else if ((telefono == null || telefono.isEmpty()) && localidad != null && !localidad.isEmpty()) {
            resultado = em.createNamedQuery("Tipounidad.findByLocalidad", Tipounidad.class)
                          .setParameter("localidad", localidad)
                          .getResultList();
        } else {
            resultado = em.createNamedQuery("Tipounidad.findByTelefonoYLocalidad", Tipounidad.class)
                          .setParameter("telefono", telefono)
                          .setParameter("localidad", localidad)
                          .getResultList();
        }

    } finally {
        cierraFactoryController();
    }
    return resultado;
}


    public List<String> obtenerTelefonosUnicos() {
        inicializaFactoryController();
        EntityManager em = emf.createEntityManager();
        List<String> telefonos = em.createQuery("SELECT DISTINCT t.numerotelefono FROM Tipounidad t", String.class).getResultList();

        em.close();
        cierraFactoryController();

        return telefonos;
    }


public List<String> obtenerLocalidadesUnicas() {
    inicializaFactoryController();
    EntityManager em = emf.createEntityManager();
    List<String> localidades = em.createQuery("SELECT DISTINCT t.localidad FROM Tipounidad t", String.class).getResultList();
    em.close();
    cierraFactoryController();
    return localidades;
}






    
    

  
}