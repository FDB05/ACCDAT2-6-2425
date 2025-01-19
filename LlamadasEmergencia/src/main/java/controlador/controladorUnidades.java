/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Tipounidad;
import modelo.Unidades;

/**
 *
 * @author Fernando A
 */
public class controladorUnidades {
    static EntityManagerFactory emfactory;
    static EntityManager entitymanager;
    static Unidades unidades;
    static UnidadesJpaController unidadesJpaController;
    
    //--------------------------------------------------------------------------
    //METODOS INSERTAR, BORRAR MODIFICAR Y LEER(Nico)
    //--------------------------------------------------------------------------
    
    public static void insertarUnidades(String NumeroUnidad , Tipounidad TipoUnidad, String Disponibilidad){
        
       
        UnidadesJpaController unidadesJpaController=new UnidadesJpaController  (emfactory);

        Unidades d=new Unidades();
        
        
        //SOLUCION BIG DECIMAL
        BigDecimal bigDecimalValue =new BigDecimal(NumeroUnidad);        
        unidades.setNumerounidad(bigDecimalValue);
        //----------------------------------------------------------------------
        boolean disponibilidadR=false;
        if("true".equals(Disponibilidad)){
            disponibilidadR= true;
        }
                
        unidades.setDisponibilidad(disponibilidadR);
        unidades.setTipounidad(TipoUnidad);
        //Falta insertar en movilizaciones

        try {
            unidadesJpaController.create(d);
        } catch (Exception ex) {
            Logger.getLogger(controladorUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void borrarUnidades(String NumeroUnidad ){
        unidades=entitymanager.find(Unidades.class,NumeroUnidad );
        
        entitymanager.getTransaction().begin();
        entitymanager.remove(unidades);
        entitymanager.getTransaction().commit();
    }
    
    public static void ModificarUnidades(String NumeroUnidad , Tipounidad TipoUnidad, String Disponibilidad){
        unidades=entitymanager.find(Unidades.class, NumeroUnidad );
        BigDecimal bigDecimal = new BigDecimal(NumeroUnidad);
        boolean disponibilidadR=false;
        if("true".equals(Disponibilidad)){
            disponibilidadR= true;
        }
    
    
    entitymanager.getTransaction().begin();
    
    
              
        unidades.setNumerounidad(bigDecimal);
        
        unidades.setDisponibilidad(disponibilidadR);
        unidades.setTipounidad(TipoUnidad);
    entitymanager.getTransaction().commit();
    }
    
    public static void LeerRecursosUnidades(String NumeroUnidad ){
        
        unidades= entitymanager.find(Unidades.class, NumeroUnidad);
        
        //METERLO EN EL JFRAME
        if(unidades != null){
           //System.out.println("Dept NAME : "+ unidades.getDnombre());
        }else{
            System.out.println("NO existe el registro");
        }
}
}
