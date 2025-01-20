/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.ControladorMaestro;
import java.util.List;

/**
 *
 * @author Fernando A
 */
public interface InterfazVista {

    public void soltarPopApp(boolean exito);
    
    public void arranca();

    public void setControlador(ControladorMaestro This);
    
    public int getNtelefono();
    
    public int getNtelefono1();
    
    public int getNunidad();
    
    public String getFecha();
    
    public String getFecha1();
    
    public String getUbicacion();
    
    public String getDescripcion();
    
    public String getCBestado1();
    
    public String getBtipoUnidad1();
    
    public int getNunidad1();
    
    public String getCBestado();
    
    public String getBtipoUnidad();
    
    public String getBdisponibilidad();
    
    public String getBdisponibilidad1();
    
    

    
    public void cargarTablaLLamadas(List<Object[]> list);
    
     public void cargarTablaUnidades(List<Object[]> list);
    
    
    static final String FILTRARUNIDADES="Metodo para filtra el tipo de Unidades que quieres";
    static final String FILTRARLLAMADAS="Metodo para filtrar el tipo de unidades que equieres ";
    static final String INSERTARUNIDAD="Metodo para INSERTAR el tipo de unidades que equieres ";
    static final String INSERTARLLAMADAS="Metodo para INSERTAR el tipo de llamadas que equieres ";
    static final String ELIMINARUNAUNIDAD="Metodo para eliminar una llamada";
    static final String ELIMINARUNALLAMADA="Metodo para eliminar una llamdada";
    static final  String MODIFICARUNAUNIDAD="Metodo para modificar una unidad";
    static final  String MODIFICARUNALLAMADA="Metodo para modificar una unidad";

            


}
