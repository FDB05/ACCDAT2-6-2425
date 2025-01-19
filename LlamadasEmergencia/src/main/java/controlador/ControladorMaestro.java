package controlador;

import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;
import modelo.ModeloMaestro;
import jakarta.persistence.EntityManagerFactory;
import modelo.Llamadas;
import modelo.Unidades;

import java.sql.Date;
import java.util.List;
import static modelo.ModeloMaestro.LeerDataEstado;
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


    public void actionPerformed(ActionEvent evento) {
        
     
            
        }

    @Override
    public void processAction(ActionEvent ae) throws AbortProcessingException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
    
    
   
    }

    
    
