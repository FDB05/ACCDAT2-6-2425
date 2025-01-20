package controlador;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import java.awt.event.ActionListener;


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
            List<Object[]> query;
            String estado = vista.getCBestado();
            int numT = vista.getNtelefono();
            String fecha = vista.getFecha();
            modelo.convertirFecha(fecha);

            if (fecha.isBlank() && estado.isBlank()) {
                query = modelo.filtrarLlamadasPorNumeroTelefono(numT);
            } else if ((numT == -1) && estado.isBlank()) {
                query = modelo.filtrarLlamadasPorFecha(fecha);
            } else if ((numT == -1) && fecha.isBlank()) {
                query = modelo.filtrarLlamadasPorEstado(estado);
            } else if (numT == -1) {
                query = modelo.filtrarLlamadasPorEstadoFecha(estado, fecha);
            } else if (estado.isBlank()) {
                query = modelo.filtrarLlamadasPorNumeroTelefonoFecha(numT, fecha);
            } else if (fecha.isBlank()) {
                query = modelo.filtrarLlamadasPorNumeroTelefonoYEstado(numT, estado);
            } else {
                query = modelo.filtrarLlamadasPorNumeroTelefonoEstadoFecha(numT, estado, fecha);
            }
            

            vista.cargarTablaLLamadas(query); // Asegúrate de que este método esté bien implementado
            break;
    }
        
       case InterfazVista.FILTRARUNIDADES -> {
            List<Object[]> query = null;
            int nUnidad = vista.getNunidad(); 
            String tUnidad = vista.getBtipoUnidad(); 
            boolean estado = Boolean.parseBoolean(vista.getBdisponibilidad()); 
            // Lógica para filtrar unidades
            if (nUnidad != -1 && tUnidad != null && !tUnidad.isBlank() && estado) {
                query = modelo.filtrarUnidadesPorNumeroUnidadTipoUnidadDisponibilidad(nUnidad, tUnidad, estado);
            } else if (nUnidad != -1 && tUnidad != null && !tUnidad.isBlank()) {
                query = modelo.filtrarUnidadesPorNumeroUnidadTipoUnidad(nUnidad, tUnidad);
            } else if (nUnidad != -1 && estado) {
                query = modelo.filtrarUnidadesPorNumeroUnidadDisponibilidad(nUnidad, estado);
            } else if (nUnidad != -1) {
                query = modelo.filtrarUnidadesPorNumeroUnidad(nUnidad);
            } else if (tUnidad != null && !tUnidad.isBlank()) {
                query = modelo.filtrarUnidadesPorTipoUnidad(tUnidad);
            } else if (estado) {
                query = modelo.filtrarUnidadesPorDisponibilidad(estado);
           } else if (tUnidad != null && !tUnidad.isBlank() && estado) {
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
       
       }
       
       
       
          
          }
    
    }
}