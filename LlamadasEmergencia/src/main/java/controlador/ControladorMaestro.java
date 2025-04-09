package controlador;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import modelo.ModeloMaestro;
import java.util.List;
import modelo.Estado;
import modelo.Tipounidad;
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

          //JULIAN
          case InterfazVista.INSERTARTIPO -> {
              String tipo = vista.getTipoUnidad();
              String nombre = vista.getNombreUnidad();
              String telefono = vista.getNumeroTelefono(); // 
              String localidad = vista.getLocalidad();     // 
              boolean existe = modelo.insertaTipoUnidad(tipo, nombre, telefono, localidad);
              vista.soltarPopApp(existe);
              
          }

            case InterfazVista.ELIMINARTIPO -> {
                String tipoUnidad = vista.getTipoUnidad();

                // Llamar al modelo para eliminar la unidad
                modelo.eliminarTipoUnidad(tipoUnidad);
                vista.soltarPopApp(true);
            }

            case InterfazVista.MODIFICARTIPO -> {

                String tipoUnidad = vista.getTipoUnidad();
                String nombreUnidad = vista.getNombreUnidad();

                modelo.modificarTipoUnidad(tipoUnidad, nombreUnidad);
                vista.soltarPopApp(true);
            }

            case InterfazVista.CARGARTIPO -> {

                String telefono = vista.getUnidadTelefonoSeleccionado();
                String localidad = vista.getUnidadLocalidadSeleccionado();

                List<Tipounidad> resultados = modelo.buscarTipoUnidadFiltrado(telefono, localidad);
                vista.cargarTablaTipoUnidad(resultados); // Este método deberías tenerlo como ya tenías
            }

            case InterfazVista.INSERTARESTADO -> {
                String tipoEstado = vista.getTipoEstado();
                String nombreEstado = vista.getNombreEstado();
                boolean existeEstado = modelo.insertaEstado(tipoEstado, nombreEstado);
                vista.soltarPopApp(existeEstado);
            }

            case InterfazVista.MODIFICARESTADO -> {
                String tipoEstadoMod = vista.getTipoEstado();  // Obtener el tipo de estado desde la vista
                String nombreEstadoMod = vista.getNombreEstado();  // Obtener el nombre del estado desde la vista
                modelo.modificarEstado(tipoEstadoMod, nombreEstadoMod);  // Llamar al método de modificación
            }

            case InterfazVista.CARGARESTADO -> {
                String tipoSeleccionado = vista.getTipoEstado();
                String nombreBusqueda = vista.getNombreEstado();

                List<Estado> resultados = modelo.buscarEstadosFiltrados(tipoSeleccionado, nombreBusqueda);
              vista.cargarTablaEstado(resultados);
          }
          case InterfazVista.ACTUALIZATIPOUNIDAD -> {
              List<String> telefonos = modelo.obtenerTelefonosUnicos();
              List<String> localidades = modelo.obtenerLocalidadesUnicas();

              vista.setCBunidadTelefono(telefonos);
              vista.setCBunidadLocalidad(localidades);

          }

            case InterfazVista.ELIMINARESTADO -> {
                String tipoEstado = vista.getTipoEstado(); // Obtener el tipo de estado desde la vista

                // Llamar al modelo para eliminar el estado
                modelo.eliminarEstado(tipoEstado);
                vista.soltarPopApp(true); // Mostrar mensaje de éxito (true indica que la eliminación fue exitosa)
            }

        }

    }
}
