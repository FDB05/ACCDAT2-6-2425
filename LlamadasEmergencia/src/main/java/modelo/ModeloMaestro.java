package modelo;

import controlador.LlamadasJpaController;
import controlador.UnidadesJpaController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import modelo.Llamadas;
import modelo.Unidades;

import java.sql.Date;
import java.util.List;

public class ModeloMaestro {

    static EntityManagerFactory emf;
    static EntityManager em;
    
    public static void inicializaFactoryController()
{
emf=Persistence.createEntityManagerFactory("com.mycompany_LlamadasEmergencia_jar_1.0-SNAPSHOTPU");

}

public static void cierraFactoryController(){
emf.close();

}
//-----------------------------------
//METODOS FILTROS PARA LAS UNIDADES(JPQL)
//--------------------------------------
     public static List<Object[]> filtrarUnidadesPorNumeroUnidadTipoUnidadDisponibilidad(int nUnidad,String tUnidad,boolean Estado){
      inicializaFactoryController();
         TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad =  LIKE '%' || :numeroUnidad || '%'  AND u.tipounidad = :tipoUnidad AND u.disponibilidad = :estado",Object[].class);
      
     query.setParameter("numeroUnidad", nUnidad);
    query.setParameter("tipoUnidad", tUnidad);
    query.setParameter("estado", Estado);
    
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      
      }
     
     
      public static List<Object[]> filtrarUnidadesPorNumeroUnidadTipoUnidad(int nUnidad,String tUnidad){
         inicializaFactoryController();
         TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad  LIKE '%' || :numeroUnidad || '%' AND u.tipounidad = :tipoUnidad",Object[].class);
      
     query.setParameter("numeroUnidad", nUnidad);
    query.setParameter("tipoUnidad", tUnidad);
    
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }
      
      public static List<Object[]> filtrarUnidadesPorNumeroUnidadDisponibilidad(int nUnidad,boolean Estado){
            inicializaFactoryController();
      TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad  LIKE '%' || :numeroUnidad || '%' AND u.disponibilidad = :estado",Object[].class);
    query.setParameter("tipoUnidad", nUnidad);
    query.setParameter("estado", Estado);
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }
      
       public static List<Object[]> filtrarUnidadesPorNumeroUnidad(int nUnidad){
           inicializaFactoryController();
      TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.numerounidad : LIKE '%' || :numeroUnidad || '%' ",Object[].class);
      
     query.setParameter("numeroUnidad", nUnidad);
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      }
        public static List<Object[]> filtrarUnidadesPorTipoUnidad(String tUnidad){
      inicializaFactoryController();
         TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.tipounidad = :tipoUnidad ",Object[].class);
    
         query.setParameter("tipoUnidad", tUnidad);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      
      }
        
         public static List<Object[]> filtrarUnidadesPorDisponibilidad(boolean Estado){
      inicializaFactoryController();
         TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.disponibilidad = :estado",Object[].class);
         
    query.setParameter("estado", Estado);
    
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
      
      }
       
       
        public static List<Object[]> filtrarUnidadesPorTipoUnidadDisponibilidad(String tUnidad,boolean Estado){
            inicializaFactoryController();
      TypedQuery<Object[]> query=em.createQuery("SELECT u.numerounidad,u.tipounidad,u.disponibilidad FROM UNIDADES u WHERE u.tipounidad = :tipoUnidad AND u.disponibilidad = :estado",Object[].class);
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
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.numeroTelefono LIKE '%' || :numeroTelefono || '%' AND l.estadoId = :estadoId AND l.fecha LIKE '%' || :fecha || '%'", 
        Object[].class);
    
    query.setParameter("numeroTelefono", numeroTelefono);
    query.setParameter("estadoId", estadoId);
    query.setParameter("fecha", fecha);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorNumeroTelefonoYEstado(String numeroTelefono, int estadoId) {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.numeroTelefono LIKE '%' || :numeroTelefono || '%' AND l.estadoId = :estadoId",
        Object[].class);
    
    query.setParameter("numeroTelefono", numeroTelefono);
    query.setParameter("estadoId", estadoId);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorNumeroTelefono(String numeroTelefono) {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.numeroTelefono LIKE '%' || :numeroTelefono || '%'", 
        Object[].class);
    
    query.setParameter("numeroTelefono", numeroTelefono);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorEstado(int estadoId) {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.estadoId = :estadoId", 
        Object[].class);
    
    query.setParameter("estadoId", estadoId);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}

public static List<Object[]> filtrarLlamadasPorFecha(String fecha) {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT l.numeroTelefono, l.estadoId, l.fecha FROM Llamadas l WHERE l.fecha LIKE '%' || :fecha || '%'", 
        Object[].class);
    
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
        
public static List<Object[]> LeerDataTipoUnidad(String TUnidad) {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT t.tipounidad FROM TipoUnidad t", 
        Object[].class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}


public static List<Object[]> LeerDataEstado(String TEstado) {
    inicializaFactoryController();
    TypedQuery<Object[]> query = em.createQuery(
        "SELECT e.tipoestado FROM Estado e", 
        Object[].class);
    
    List<Object[]> list = query.getResultList();
    cierraFactoryController();
    return list;
}
//-----------------------------------
//-----------------------------------
//-----------------------------------
}