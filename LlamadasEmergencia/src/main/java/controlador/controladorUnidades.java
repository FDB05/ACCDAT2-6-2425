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
    
    public static void insertarUnidades(int NumeroUnidad , Tipounidad TipoUnidad, boolean Disponibilidad){
        
        //NO HAY JPACONTROLLER
        //UnidadesJpaController unidadesJpaController=new UnidadesJpaController  (emfactory);

        Unidades d=new Unidades();
        
        
        //SOLUCION BIG DECIMAL
        BigDecimal bigDecimalValue = BigDecimal.valueOf(NumeroUnidad);        
        unidades.setNumerounidad(bigDecimalValue);
        //----------------------------------------------------------------------
        unidades.setDisponibilidad(Disponibilidad);
        unidades.setTipounidad(TipoUnidad);
        //Falta insertar en movilizaciones

        try {
            unidadesJpaController.create(d);
        } catch (Exception ex) {
            Logger.getLogger(controladorUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void borrarUnidades(int NumeroUnidad ){
        unidades=entitymanager.find(Unidades.class,NumeroUnidad );
        
        entitymanager.getTransaction().begin();
        entitymanager.remove(unidades);
        entitymanager.getTransaction().commit();
    }
    
    public static void ModificarUnidades(int NumeroUnidad , Tipounidad TipoUnidad, boolean Disponibilidad){
        unidades=entitymanager.find(Unidades.class, NumeroUnidad );
    
    
    
    entitymanager.getTransaction().begin();
    //DUDA CON EL BIG DECIMAL
        BigDecimal bigDecimalValue = BigDecimal.valueOf(NumeroUnidad);        
        unidades.setNumerounidad(bigDecimalValue);
        
        unidades.setDisponibilidad(Disponibilidad);
        unidades.setTipounidad(TipoUnidad);
    entitymanager.getTransaction().commit();
    }
    
    public static void LeerRecursosUnidades(int NumeroUnidad ){
        
        unidades= entitymanager.find(Unidades.class, NumeroUnidad);
        
        //METERLO EN EL JFRAME
        if(unidades != null){
           //System.out.println("Dept NAME : "+ unidades.getDnombre());
        }else{
            System.out.println("NO existe el registro");
        }
}
}
