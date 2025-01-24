/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.llamadasemergencia;

import controlador.ControladorMaestro;
import modelo.ModeloMaestro;
import vista.InterfazVista;
import vista.Ventana1;

/**
 *
 * @author Fernando A
 */
public class LlamadasEmergencia {

    public static void main(String[] args) {
       InterfazVista vista = (InterfazVista) new Ventana1();
        ModeloMaestro modelo = new ModeloMaestro();
        modelo.inicializarDatos();
        ControladorMaestro controlador = new ControladorMaestro(vista,modelo);
        
        
    }
}
