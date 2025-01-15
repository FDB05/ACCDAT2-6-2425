/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.fadb.proyectopav3;

import com.fadb.proyectopav3.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fernando-Antonio-Domingeuz barranco 
 */
public class ProyectoPAv3 {
    
    static EntityManagerFactory emfactory;
    static EntityManager entitymanger;
    static Departamentos departamentos;
    static DepartamentosJpaController DepartamentosJpaController;
    static Empleados empleados;
    static EmpleadosJpaController empleadosJpaController;

    public static void main(String[] args) {
              try {
//Ejemplo creando datos con el factory y usando las clases                   
//        emfactory=Persistence.createEntityManagerFactory("com.FADB_proyectoPAv3_jar_1.0-SNAPSHOTPU");
//        
//        DepartamentosJpaController=new DepartamentosJpaController(emfactory);
//        DepartamentosJpaController.getEntityManager();
//        Departamentos departamentos=new Departamentos();
//        departamentos.setDeptNo((short)99);
//        departamentos.setDnombre("Pruebas");
//        departamentos.setLoc("Madrid"); 
//            DepartamentosJpaController.create(departamentos);

//inicializafactory();




//
//cierraFactory();

inicializafactory();

//Empleados empleado=new Empleados();
//Collection<Empleados> empleadosCollection=new ArrayList<Empleados>();
//empleado.setEmpNo((short)7777);
//empleado.setApellido("ROBLES");
//empleado.setSalario(BigDecimal.valueOf(2000));
//empleado.setOficio("ANALISTA");
//empleado.setDir((short) 7839);
//
//empleadosCollection.add(empleado);
//departamento.setEmpleadosCollection(empleadosCollection);
ConsultaSimples();

cierraFactory();

        } catch (Exception ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    
// pruab d electuar utilizando consultas JPQL con CriteranQuery    
    public static void consultaConCriteriaQuery(){
    CriteriaBuilder cb =entitymanger.getCriteriaBuilder();
        CriteriaQuery<Departamentos> query=cb.createQuery(Departamentos.class);
        
        Root<Departamentos> c=query.from(Departamentos.class);//Especificamops el from
                         query.select(c);// los campos a seleccionar
        List<Departamentos> list=entitymanger.createQuery(query).getResultList();
        
        for(Departamentos e:list){
            System.out.println("Nombre del departamento"+ e.getDnombre());
        }
        
    }
    
    public static void consultaConCriteriaQueryVariosCampos(){
    CriteriaBuilder cb =entitymanger.getCriteriaBuilder();
    
    CriteriaQuery<Object[]> query=cb.createQuery(Object[].class);
    
    Root<Departamentos> c=query.from(Departamentos.class);
    
    
    }
    
    public static void modificarDAtosConJPQL(){
    Query query=entitymanger.createQuery("UPDATE DEPARTAMENTOS d SET d.dnombre=:valorNuevo"+""
            + "WHERE d.deptNo=:deptNov");
    
    query.setParameter("valorNuevo", "PRUEBAS");
    query.setParameter("deptNov", (short)77);
    
    entitymanger.getTransaction().begin();
    int updateCount=query.executeUpdate();
    entitymanger.getTransaction().commit();
    
    }
    
    public static void borrarDatosConJPQL(){
    Query query=entitymanger.createQuery("DELETE from departamentos d WHERE d.dept_no=:deptNov");
    
    
    query.setParameter("deptNov", (short)77);
    
    entitymanger.getTransaction().begin();
    int deleteCoount=query.executeUpdate();
    entitymanger.getTransaction().commit();
    
    }
    
    
    //EJEMPLOS QUES USAN FACTORY
public static void inicializaFactoryController()
{
emfactory=Persistence.createEntityManagerFactory("com.FADB_proyectoPAv3_jar_1.0-SNAPSHOTPU");

}

public static void cierraFactoryController(){
emfactory.close();

}
public static void insertarDepartamento(){
DepartamentosJpaController departamentosJpaController=new DepartamentosJpaController  (emfactory);

Departamentos d=new Departamentos();

d.setDeptNo((short)99);
d.setDnombre("BIG DATA");
d.setLoc("TOLETUM");

        try {
            departamentosJpaController.create(d);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public static void insertaDepartamentoConEmpleado(){
DepartamentosJpaController departamentosJpaController=new DepartamentosJpaController  (emfactory);

Departamentos d=new Departamentos();

d.setDeptNo((short)99);
d.setDnombre("BIG DATA");
d.setLoc("TOLETUM");

empleados=new Empleados((short)7521);

Collection<Empleados> empleadoses=new ArrayList<Empleados>();

empleadoses.add(empleados);

d.setEmpleadosCollection(empleadoses);

        try {
            departamentosJpaController.create(d);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public static void eliminarDepartamento(){
        try {
            DepartamentosJpaController controller=new DepartamentosJpaController(emfactory);
            
            controller.destroy((short)99);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }

}

public static void listarDepartamentos(){
DepartamentosJpaController controller=new DepartamentosJpaController(emfactory);

List<Departamentos>depoartList;

depoartList= controller.findDepartamentosEntities();
        
        for(Departamentos d:depoartList){
            System.out.println("Nombre dtpo "+ d.getDnombre());
           
        }
}

public static void listarDepartamentosPorTramos(){
DepartamentosJpaController controller=new DepartamentosJpaController(emfactory);

List<Departamentos> departamentoses;
    System.out.println("TODOS LOS DEPARTAMENTOS");
    listarDepartamentos();
    
    System.out.println("Trae 3 regsitros empezando por la posicion 0");
    
    departamentoses=controller.findDepartamentosEntities(3,0);
    
    for(Departamentos d:departamentoses){
        System.out.println("DeptNo"+d.getDnombre());
    }
    
      System.out.println("Trae 3 regsitros empezando por la posicion 1");
      departamentoses=controller.findDepartamentosEntities(3,1);
    
    for(Departamentos d:departamentoses){
        System.out.println("DeptNo"+d.getDnombre());
    }
    
}

public static void contarNumeroDepartamentos(){
DepartamentosJpaController controller=new DepartamentosJpaController(emfactory);
int nElemento=controller.getDepartamentosCount();

    System.out.println("Nº de departamento "+nElemento);
}

public static void listarUndepartameto(){
DepartamentosJpaController controller=new DepartamentosJpaController(emfactory);

Departamentos  d=controller.findDepartamentos((short)10);

    System.out.println(d.getDnombre());
}

public static void modificarDepartamentro(int id){
DepartamentosJpaController controller=new DepartamentosJpaController(emfactory);

departamentos=controller.findDepartamentos((short)id);

departamentos=new Departamentos();

departamentos.setDeptNo((short)id);

departamentos.setDnombre("Contabilidad");

departamentos.setLoc("Madrd");
        try {
            controller.edit(departamentos);
        } catch (Exception ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }

}
 //EJEMPLO QUE NO UTILIZAN LOS JPACONTROLLER    
    public static void inicializafactory(){
    emfactory=Persistence.createEntityManagerFactory("com.FADB_proyectoPAv3_jar_1.0-SNAPSHOTPU");
    entitymanger=emfactory.createEntityManager();
    } 
    
    public static void cierraFactory(){
    entitymanger.close();
    emfactory.close();
    }
    
    public static void leerUnRegistro(){
    departamentos=entitymanger.find(Departamentos.class,(short)10);
        if (departamentos!=null) {System.out.println("Dept NAME:"+departamentos.getDnombre());}
        else{System.out.println("NO EXISTE EL DEPARTAMENTO");}
    }
    
    public static void esperar(){
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    
        System.out.println("Pulsa entere para continuar..");
        
        try {
            String sTexto=br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void leerUnRegistroBloqueado(){
    entitymanger.getTransaction().begin();
    
    departamentos=entitymanger.find(Departamentos.class, (short) 5,LockModeType.PESSIMISTIC_READ);
        if (departamentos!=null) {System.out.println("Dept NAME:"+departamentos.getDnombre());}
        else{System.out.println("NO EXISTE EL DEPARTAMENTO");}
        entitymanger.getTransaction().commit();
    } 
    
    public static void recargardesdeBbdd(){
    entitymanger.getTransaction().begin();
     entitymanger.refresh(departamentos);
     entitymanger.getTransaction().commit();
    }
    
    public static void leerUnRegistroRelacionado(short dep_no){
    
    
    departamentos=entitymanger.find(Departamentos.class, dep_no);
    
        if (departamentos!=null) {
            System.out.println("Depet_No:"+departamentos.getDnombre());
            Collection<Empleados> list=departamentos.getEmpleadosCollection();
//            Iterator<Empleados> it=list.iterator();
            

        //Se puede usar tambien el while con un (it.hasNext)
           for(Empleados empleados:list) {
//                emple= it.next();
                System.out.println("EMPLE NAME:"+  empleados.getApellido());                
            }
        }
        else{System.out.println("NO EXISTE EL DEPARTAMENTO");}
    }
    
    public static void insertarDatos(){
    Departamentos d;
    d=new Departamentos();
    d.setDeptNo((short)5);
    d.setDnombre("PRUEBA");
    d.setLoc("LocPrueba");
    entitymanger.getTransaction().begin();
    entitymanger.persist(d);
    entitymanger.getTransaction().commit();

    }
    
    public static void modificarDatos(){
    departamentos=entitymanger.find(Departamentos.class, (short)5);
    
    entitymanger.getTransaction().begin();
    departamentos.setLoc("Daimiel");
    entitymanger.getTransaction().commit();
    }
    
    public static void borrarDatos(short id) {
        departamentos=entitymanger.find(Departamentos.class,id);
        
        entitymanger.getTransaction().begin();
        entitymanger.remove(departamentos);
        entitymanger.getTransaction().commit();
    }
    
    public static void leerUnRegistroEleccion(short id){
        
    departamentos=entitymanger.find(Departamentos.class, id);
    if(departamentos!=null){
        System.out.println("Dept NAME:"+departamentos.getDnombre()+" Dept No:"+departamentos.getDeptNo()+" Dept LOC:"+departamentos.getLoc());
    }
    else{System.out.println("NO EXISTE EL DEPARTAMENTO");}
    }
    
    public static void borrarDatosElegir(short id){
    departamentos=entitymanger.find(Departamentos.class, id);
    
    entitymanger.getTransaction().begin();
    entitymanger.remove(id);
    entitymanger.getTransaction().commit();
    }
    
    public static void modificarSalarioYDepartEmple(BigDecimal newS,short newDepar){
    empleados=entitymanger.find(Empleados.class,(short)5);
    entitymanger.getTransaction().begin();
    empleados.setSalario(newS);
     departamentos=entitymanger.find(Departamentos.class,newDepar);
        if (departamentos!=null) {empleados.setDeptNo(departamentos);}
        else{throw new IllegalArgumentException("NO EXISTE EL DEPARTAMENTO");}    
    entitymanger.getTransaction().commit();
    }
    
     public static void subirSalario(){
        
        Empleados emple;
        try {
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            
            System.out.print("Indica el numero del departamento: ");
            short nDep = Short.parseShort( br.readLine() );
            
            System.out.print("Indica la subida en euros: ");
            String subida = br.readLine();
            
            //Muestro los empleados y el salario de cada uno de un departamento antes de la subida
            leerUnRegistroRelacionado(nDep);
            esperar();
            
            //Hacemos el cambio
            departamentos = entitymanger.find(Departamentos.class, nDep);
            
            if (departamentos != null){ //Si hay departamentos...
            
            //Coleccion que contiene todos los empleados de ese departamento
            Collection<Empleados> listaEmpleados = departamentos.getEmpleadosCollection();
            
            //Creamos un iterator para recorrer la coleccion
            Iterator<Empleados> it = listaEmpleados.iterator();
            entitymanger.getTransaction().begin();
            
            while( it.hasNext() ){ //Si el iterator tiene next, es decir, si tiene un empleado
                
                //Leemos el empleado
                emple = it.next();
                emple.setSalario( emple.getSalario().add( new BigDecimal (subida) ) );
            }
             entitymanger.getTransaction().commit();
            } else {
                System.out.println("No existe el registro");
            }
            
            //Muestro los empleados y el salario de cada uno de un departamento despues de la subida
            leerUnRegistroRelacionado(nDep);
            esperar();

            
        } catch (IOException ex) {
            
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    //POR HACER 
    public static void leerDatosDeptYEmple(short id){}
    
     public static void insertarEmpleados(){
        
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Indica el numero del empleado: ");
            short nEmple = Short.parseShort( br.readLine() );
            
            System.out.print("Indica el apellido del empleado: ");
            String apellido = br.readLine();
            
            System.out.print("Indica el oficio del empleado: ");
            String oficio = br.readLine();
            
            System.out.print("Indica el director del empleado: ");
            short dir = Short.parseShort( br.readLine() );
            
            System.out.print("Indica la fecha alta del empleado en formato (DD/MM/YYYY): ");
            Date fechaAlta = convertirFecha( br.readLine() );
            
            System.out.print("Indica el salario del empleado: ");
            String salario = br.readLine();
            
            System.out.print("Indica la comision del empleado: ");
            String comision = br.readLine();
            
            System.out.print("Indica el numero del departamento al que pertenece: ");
            String nDep = br.readLine();
            departamentos = new Departamentos();
            departamentos.setDeptNo( Short.parseShort(nDep));
            
      
            empleados = new Empleados();
            empleados.setEmpNo(nEmple);
            empleados.setApellido(apellido);
            empleados.setOficio(oficio);
            empleados.setDir(dir);
            empleados.setFechaAlt(fechaAlta);
            empleados.setSalario( new BigDecimal(salario) );
            empleados.setComision( new BigDecimal(comision) );
            empleados.setDeptNo(departamentos );
            
            
            entitymanger.getTransaction().begin();
            entitymanger.persist(empleados);
            entitymanger.getTransaction().commit();
           
            
            
        } catch (IOException ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    private static Date convertirFecha(String fecha){
        java.util.Date fechaUtil = null;
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");    
            fechaUtil = s.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(ProyectoPAv3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(fechaUtil.getTime());
    }

    
    //C)Ejemplos utilizan JPQL 
    
    //https//www.onjkect.com/java/jpa/squery
    public static void ConsultaSimples(){
    Query quey =entitymanger.createQuery("SELECT UPPER(d.dnombre) FROM Departamentos d");
      List<String> list= quey.getResultList();
      
      for(String e:list){
          System.out.println("Nombre dep " + e);
      }
    }
      
      public static void ConsultavariosCampos(){
      TypedQuery<Object[]> query=entitymanger.createQuery("SELECT d.dnombre,d.loc FROM Deaprtamentos d",Object[].class);
      
      List<Object[]>list=query.getResultList();
      
      for(Object[] e:list)
      {
          System.out.println("Nombre departamento "+e[0]);
          System.out.println("Nombre loc "+e[1]);
      }
      }
      
      public static void ConsultaAlmacenada(){
          Query quey =entitymanger.createNamedQuery("Departamentos.findAll");
      List<Departamentos> list= quey.getResultList();
      
      for(Departamentos e:list){
          System.out.println("Nombre dep " + e.getDnombre());
      }
      }
      
      public static void consultaAlmacendaConParametro(int depNoP) {
      Query quey =entitymanger.createQuery("Departamentos.findByDeptNo");
      quey.setParameter("deptNo", depNoP);
      
      List<String> list= quey.getResultList();
      
      for(String e:list){
          System.out.println("Nombre dep " + e);
      }
      }
      
      
    
    
}
