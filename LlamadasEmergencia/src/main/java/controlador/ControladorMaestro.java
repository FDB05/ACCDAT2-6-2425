package controlador;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import modelo.ModeloMaestro;
import java.util.List;
import vista.InterfazVista;

public class ControladorMaestro implements ActionListener {

   
    private final InterfazVista vista;
    private final ModeloMaestro modelo;
    
    public ControladorMaestro(InterfazVista vista, ModeloMaestro modelo) {
        
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    
    
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
      switch (e.getActionCommand()) {
          
        case InterfazVista.FILTRARLLAMADAS->{
            List<Object[]> query = null;
            String estado = vista.getCBestado();
           
            String fechaN = vista.getFecha();
            Date fecha=modelo.convertirFecha(fechaN);

            if ( estado.isBlank()) {
                query = modelo.filtrarLlamadasPorFecha(fechaN);
            } else if (fechaN.isBlank()) {
                query = modelo.filtrarLlamadasPorEstado(estado);
            } else if (!fechaN.isBlank() && !estado.isBlank()) {
                query = modelo.filtrarLlamadasPorEstadoFecha(estado, fechaN);
            }
            vista.cargarTablaLLamadas(query);
            break;
    }
        
       case InterfazVista.FILTRARUNIDADES -> {
            List<Object[]> query = null;
           
            String tUnidad = vista.getBtipoUnidad(); 
            boolean estado = Boolean.parseBoolean(vista.getBdisponibilidad());
            String estadoVerifica=vista.getBdisponibilidad();
           if (tUnidad != null && !tUnidad.isBlank()) {
                query = modelo.filtrarUnidadesPorTipo(tUnidad);
            } else if (estado) {
                query = modelo.filtrarUnidadesPorDisponibilidad(estado);
           } else if (tUnidad != null && !tUnidad.isBlank() && estadoVerifica.isBlank()) {
                query = modelo.filtrarUnidadesPorTipoUnidadDisponibilidad(tUnidad, estado);
            } 

            vista.cargarTablaUnidades(query); 
            break;
        }
       case InterfazVista.INSERTARUNIDAD->{
            int nUnidad = vista.getNunidad1(); 
            String tUnidad = vista.getBtipoUnidad1(); 
            boolean estado = Boolean.parseBoolean(vista.getBdisponibilidad1()); 
            
           boolean existe= modelo.insertaUnidad(nUnidad, estado, tUnidad);
            vista.soltarPopApp(existe);
       }
       case InterfazVista.INSERTARLLAMADAS->{
       String estado = vista.getCBestado1();
            int numT = vista.getNtelefono1();
            String fecha = vista.getFecha1();
            String descripcion=vista.getDescripcion();
            String ubicacion=vista.getUbicacion();
            
           boolean existe= modelo.insertaLlamada(numT, fecha, ubicacion, descripcion, estado);
                        vista.soltarPopApp(existe);

       }
       case InterfazVista.ELIMINARUNALLAMADA->{
           int numtef=vista.getNtelefono1();
           modelo.eliminarLlamada(numtef);
       }
       case InterfazVista.ELIMINARUNAUNIDAD->{
       int numUnida=vista.getNtelefono1();
       modelo.eliminarUnidad(numUnida);
       }
       case InterfazVista.MODIFICARUNALLAMADA->{
       String estado = vista.getCBestado1();
            int numT = vista.getNtelefono1();
            String fecha = vista.getFecha1();
            String descripcion=vista.getDescripcion();
            String ubicacion=vista.getUbicacion();
            
            modelo.modificarLlamada(numT, fecha, ubicacion, descripcion, estado);
       }
       
       case InterfazVista.MODIFICARUNAUNIDAD->{
            int nUnidad = vista.getNunidad1(); 
            String tUnidad = vista.getBtipoUnidad1(); 
            
            boolean estado = Boolean.parseBoolean(vista.getBdisponibilidad1()); 
            
            modelo.modificarUnidad(nUnidad, estado, tUnidad);
       }
       
       
          
          }
    
    }
}