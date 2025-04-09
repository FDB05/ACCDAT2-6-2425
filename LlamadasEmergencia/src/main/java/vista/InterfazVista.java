/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.ControladorMaestro;
import java.util.List;
import modelo.Estado;

/**
 *
 * @author Fernando A
 */
public interface InterfazVista {

    public void soltarPopApp(boolean exito);
    
    public void arranca();

    public void setControlador(ControladorMaestro This);
    
    //  ya existentes
    public int getNtelefono1();
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
    
    // metodos nuevos para TipoUnidad
    public String getTipoUnidad(); 
    public String getNombreUnidad(); 
    public String getLocalidad();
    public String getNumeroTelefono();
    public String getTipoUnidadMod(); 
    public String getNombreUnidadMod(); 
    public void cargarTablaTipoUnidad(List<Object[]> list); 

    // metodos nuevos para Estado
    public String getTipoEstado(); 
    public String getNombreEstado(); 
    public String getTipoEstadoMod(); 
    public String getNombreEstadoMod(); 
    public void cargarTablaEstado(List<Estado> list);
    
    // Comandos para las operaciones
    static final String FILTRARUNIDADES = "Metodo para filtrar el tipo de Unidades que quieres";
    static final String FILTRARLLAMADAS = "Metodo para filtrar el tipo de llamadas que quieres";
    static final String INSERTARUNIDAD = "Metodo para INSERTAR el tipo de unidades que quieres";
    static final String INSERTARLLAMADAS = "Metodo para INSERTAR el tipo de llamadas que quieres";
    static final String ELIMINARUNAUNIDAD = "Metodo para eliminar una unidad";
    static final String ELIMINARUNALLAMADA = "Metodo para eliminar una llamada";
    static final String MODIFICARUNAUNIDAD = "Metodo para modificar una unidad";
    static final String MODIFICARUNALLAMADA = "Metodo para modificar una llamada";
    static final String CONSEGUIRUNIDAD = "Metodo para conseguir un número de unidad para modificar";
    
    // Nuevos comandos para TipoUnidad y Estado
    static final String INSERTARTIPO = "Metodo para insertar un tipo de unidad";
    static final String ELIMINARTIPO = "Metodo para eliminar un tipo de unidad";
    static final String MODIFICARTIPO = "Metodo para modificar un tipo de unidad";
    static final String CARGARTIPO = "Metodo para cargar todos los tipos de unidades";
    
    
    static final String INSERTARESTADO = "Metodo para insertar un estado";
    static final String MODIFICARESTADO = "Metodo para modificar un estado";
    static final String CARGARESTADO = "Metodo para cargar todos los estados";
    static final String ELIMINARESTADO ="Metodo para eliminar un estado";
}
