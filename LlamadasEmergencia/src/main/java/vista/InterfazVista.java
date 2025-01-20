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

    public void arranca();

    public void setControlador(ControladorMaestro This);
    
    public int getNtelefono();
    
    public int getNunidad();
    
    public String getFecha();
    
    public String getEstado();
    
    public String getUbicacion();
    
    public String getDescripcion();
    
    
    public String getCBestado();
    
    public String getBtipoUnidad();
    
    public String getBdisponibilidad();
    
    public void cargarTablaLLamadas(List<Object[]> list);
    
     public void cargarTablaUnidades(List<Object[]> list);
    
    
    static final String FILTRARUNIDADES="Metodo para filtra el tipo de Unidades que quieres";
    static final String FILTRARLLAMADAS="Metodo para filtrar el tipo de unidades que equieres ";
}
