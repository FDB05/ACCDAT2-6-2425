package modelo;

import com.mycompany.llamadasemergencia.LlamadasEmergencia;
import controlador.LlamadasJpaController;
import controlador.UnidadesJpaController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Llamadas;
import modelo.Unidades;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModeloMaestro {

    static EntityManagerFactory emf;
    static EntityManager em;
    //--------------------------
    //METODOS DE UTILIDAD
    //--------------------------
    
    private static java.util.Date convertirFecha(String fecha){
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

}

public static void cierraFactoryController(){
emf.close();
}


//-----------------------------------
//-----------------------------------
//-----------------------------------



//-----------------------------------
//METODOS FILTROS PARA LAS UNIDADES(JPQL)
//--------------------------------------
     public static List<Object[]> filtrarUnidadesPorNumeroUnidadTipoUnidadDisponibilidad(int nUnidad,String tUnidad,boolean Estado){
      inicializaFactoryController();
         Query query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad =  LIKE '%' || :numeroUnidad || '%'  AND u.tipounidad = :tipoUnidad AND u.disponibilidad = :estado",Unidades.class);
      
     query.setParameter("numeroUnidad", nUnidad);
    query.setParameter("tipoUnidad", tUnidad);
    query.setParameter("estado", Estado);
    
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      
      }
     
     
      public static List<Object[]> filtrarUnidadesPorNumeroUnidadTipoUnidad(int nUnidad,String tUnidad){
         inicializaFactoryController();
         Query query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad  LIKE '%' || :numeroUnidad || '%' AND u.tipounidad = :tipoUnidad",Unidades.class);
      
     query.setParameter("numeroUnidad", nUnidad);
    query.setParameter("tipoUnidad", tUnidad);
    
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }
      
      public static List<Object[]> filtrarUnidadesPorNumeroUnidadDisponibilidad(int nUnidad,boolean Estado){
            inicializaFactoryController();
      Query query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad  LIKE '%' || :numeroUnidad || '%' AND u.disponibilidad = :estado",Unidades.class);
    query.setParameter("tipoUnidad", nUnidad);
    query.setParameter("estado", Estado);
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }
      
       public static List<Object[]> filtrarUnidadesPorNumeroUnidad(int nUnidad){
           inicializaFactoryController();
      Query query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad : LIKE '%' || :numeroUnidad || '%' ",Unidades.class);
      
     query.setParameter("numeroUnidad", nUnidad);
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }
        public static List<Object[]> filtrarUnidadesPorTipoUnidad(String tUnidad){
      inicializaFactoryController();
         Query query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.tipounidad = :tipoUnidad ",Unidades.class);
    
         query.setParameter("tipoUnidad", tUnidad);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      
      }
        
         public static List<Object[]> filtrarUnidadesPorDisponibilidad(boolean Estado){
      inicializaFactoryController();
      Query query=em.createQuery
        ("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.disponibilidad = :estado",Unidades.class);
         
    query.setParameter("estado", Estado);
    
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      
      }
       
       
        public static List<Object[]> filtrarUnidadesPorTipoUnidadDisponibilidad(String tUnidad,boolean Estado){
            inicializaFactoryController();
      Query query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.tipounidad = :tipoUnidad AND u.disponibilidad = :estado",Unidades.class);
    query.setParameter("tipoUnidad", tUnidad);
    query.setParameter("estado", Estado);
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }

//-----------------------------------
//-----------------------------------
//-----------------------------------
        
        
        
//-----------------------------------
//METODOS FILTROS PARA LAS LLAMADAS(JPQL)
//------------------------------------
        
        public static List<Object[]> filtrarLlamadasPorNumeroTelefonoEstadoFecha(String numeroTelefono, int estadoId, String fecha) {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.numeroTelefono LIKE '%' || :numeroTelefono || '%' AND l.estadoId = :estadoId AND l.fecha LIKE '%' || :fecha || '%'",Llamadas.class);
    
    query.setParameter("numeroTelefono", numeroTelefono);
    query.setParameter("estadoId", estadoId);
    query.setParameter("fecha", fecha);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorNumeroTelefonoYEstado(String numeroTelefono, int estadoId) {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.numeroTelefono LIKE '%' || :numeroTelefono || '%' AND l.estadoId = :estadoId",
            Llamadas.class);
    
    query.setParameter("numeroTelefono", numeroTelefono);
    query.setParameter("estadoId", estadoId);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorNumeroTelefono(String numeroTelefono) {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.numeroTelefono LIKE '%' || :numeroTelefono || '%'", 
        Llamadas.class);
    
    query.setParameter("numeroTelefono", numeroTelefono);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorEstado(int estadoId) {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.estadoId = :estadoId", 
        Llamadas.class);
    
    query.setParameter("estadoId", estadoId);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorFecha(String fecha) {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.fecha LIKE '%' || :fecha || '%'", 
        Llamadas.class);
    
    query.setParameter("fecha", fecha);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}


//-----------------------------------
//-----------------------------------
//-----------------------------------


//----------------------------------------------------
//METODO PARA OBTENER LAS FK DE LAS TABLAS NECESARIAS
//----------------------------------------------------
        
public static List<Object[]> LeerDataTipoUnidad() {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT t.tipounidad FROM TIPOUNIDAD t", 
        Tipounidad.class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}


public static List<Object[]> LeerDataEstado() {
    inicializaFactoryController();
    Query query = em.createQuery(
        "SELECT e.tipoestado FROM ESTADO e", 
        Estado.class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}
//-----------------------------------
//-----------------------------------
//-----------------------------------




}