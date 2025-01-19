/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import controlador.ControladorMaestro;

/**
 *
 * @author Fernando A
 */
public interface InterfazVista {

    public void arranca();

    public void setControlador(ControladorMaestro This);
    
    public int getNtelefono();
    
    public String getFecha();
    
    public void setCBestado();
    
    public void setCBtipoUnidad();
    
    public void setCBdisponibilidad();
    
}
