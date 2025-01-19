/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import javax.swing.table.DefaultTableModel;
import modelo.Tipounidad;

/**
 *
 * @author Fernando A
 */
public class Ventana1 extends javax.swing.JFrame {
 
    DefaultTableModel model;
    /**
     * Creates new form Ventana1
     */
    public Ventana1() {
        initComponents();
        Tllamadas.setVisible(false);
        Tunidad.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JFCllamadas = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tllamadas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        CBestado = new javax.swing.JComboBox<>();
        TFnumTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        BTNbuscar2 = new javax.swing.JButton();
        BTNback2 = new javax.swing.JButton();
        TFfecha = new javax.swing.JTextField();
        JFCunidades = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tunidad = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BTNbuscar = new javax.swing.JButton();
        CBtipounidad = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        TFnumUnidad = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        BTNback1 = new javax.swing.JButton();
        BotonInsertar = new javax.swing.JButton();
        BotonModificar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MTCunidades = new javax.swing.JMenuItem();
        MTCllamadas = new javax.swing.JMenuItem();
        MTCmovilizaciones = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MTIunidades = new javax.swing.JMenuItem();
        MTIllamadas = new javax.swing.JMenuItem();
        MTImovilizaciones = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MTBunidades = new javax.swing.JMenuItem();
        MTBllamadas = new javax.swing.JMenuItem();
        MTBmovilizaciones = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        MTsalir = new javax.swing.JMenu();

        Tllamadas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Tllamadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NºTelefono", "Fecha", "Ubicacion", "Estado", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Tllamadas);

        jLabel4.setText("NºTelefono:");

        CBestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CBestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBestadoActionPerformed(evt);
            }
        });

        TFnumTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFnumTelefonoActionPerformed(evt);
            }
        });

        jLabel5.setText("Estado:");

        jLabel6.setText("Fecha:");

        BTNbuscar2.setText("Filtrar");
        BTNbuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNbuscar2ActionPerformed(evt);
            }
        });

        BTNback2.setText("Volver");
        BTNback2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNback2ActionPerformed(evt);
            }
        });

        TFfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFfechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFCllamadasLayout = new javax.swing.GroupLayout(JFCllamadas.getContentPane());
        JFCllamadas.getContentPane().setLayout(JFCllamadasLayout);
        JFCllamadasLayout.setHorizontalGroup(
            JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFCllamadasLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFCllamadasLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BTNback2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BTNbuscar2)))
                    .addGroup(JFCllamadasLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TFnumTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFCllamadasLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JFCllamadasLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(TFfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        JFCllamadasLayout.setVerticalGroup(
            JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFCllamadasLayout.createSequentialGroup()
                .addGroup(JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFCllamadasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JFCllamadasLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(TFnumTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(CBestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(JFCllamadasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TFfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(BTNbuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(BTNback2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Tunidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NºUnidad", "TipoUnidad", "Disponibilidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Tunidad);

        jLabel1.setText("TipoUnidad:");

        BTNbuscar.setText("Filtrar");
        BTNbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNbuscarActionPerformed(evt);
            }
        });

        CBtipounidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("NºUnidad:");

        TFnumUnidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TFnumUnidadActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Disponibilidad:");

        BTNback1.setText("Volver");
        BTNback1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNback1ActionPerformed(evt);
            }
        });

        BotonInsertar.setText("Insertar");
        BotonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonInsertarActionPerformed(evt);
            }
        });

        BotonModificar.setText("Modificar");
        BotonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonModificarActionPerformed(evt);
            }
        });

        BotonEliminar.setText("Eliminar");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JFCunidadesLayout = new javax.swing.GroupLayout(JFCunidades.getContentPane());
        JFCunidades.getContentPane().setLayout(JFCunidadesLayout);
        JFCunidadesLayout.setHorizontalGroup(
            JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFCunidadesLayout.createSequentialGroup()
                .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JFCunidadesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(CBtipounidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TFnumUnidad)))
                    .addGroup(JFCunidadesLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BotonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(BotonInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTNback1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BTNbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JFCunidadesLayout.setVerticalGroup(
            JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JFCunidadesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(JFCunidadesLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CBtipounidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TFnumUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(JFCunidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(BTNbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonInsertar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BTNback1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("CARGAR");

        MTCunidades.setText("Unidades");
        MTCunidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MTCunidadesActionPerformed(evt);
            }
        });
        jMenu1.add(MTCunidades);

        MTCllamadas.setText("Llamadas");
        MTCllamadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MTCllamadasActionPerformed(evt);
            }
        });
        jMenu1.add(MTCllamadas);

        MTCmovilizaciones.setText("Movilizaciones");
        jMenu1.add(MTCmovilizaciones);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("INSERTAR");

        MTIunidades.setText("Unidades");
        MTIunidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MTIunidadesActionPerformed(evt);
            }
        });
        jMenu2.add(MTIunidades);

        MTIllamadas.setText("Llamadas");
        jMenu2.add(MTIllamadas);

        MTImovilizaciones.setText("Movilizaciones");
        jMenu2.add(MTImovilizaciones);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("BORRAR");

        MTBunidades.setText("Unidades");
        MTBunidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MTBunidadesActionPerformed(evt);
            }
        });
        jMenu3.add(MTBunidades);

        MTBllamadas.setText("Llamadas");
        MTBllamadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MTBllamadasActionPerformed(evt);
            }
        });
        jMenu3.add(MTBllamadas);

        MTBmovilizaciones.setText("Movilizaciones");
        jMenu3.add(MTBmovilizaciones);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("MODIFICAR");

        jMenuItem16.setText("Unidades");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem17.setText("Llamadas");
        jMenu4.add(jMenuItem17);

        jMenuItem18.setText("TipoUnidad");
        jMenu4.add(jMenuItem18);

        jMenuItem19.setText("Movilizaciones");
        jMenu4.add(jMenuItem19);

        jMenuItem20.setText("TipoEstado");
        jMenu4.add(jMenuItem20);

        jMenuBar1.add(jMenu4);

        MTsalir.setText("SALIR");
        jMenuBar1.add(MTsalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 922, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void MTCunidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MTCunidadesActionPerformed
                
    }//GEN-LAST:event_MTCunidadesActionPerformed

    private void MTIunidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MTIunidadesActionPerformed
         
    }//GEN-LAST:event_MTIunidadesActionPerformed

    private void MTBunidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MTBunidadesActionPerformed
    }//GEN-LAST:event_MTBunidadesActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
         
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void MTCllamadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MTCllamadasActionPerformed
          
    }//GEN-LAST:event_MTCllamadasActionPerformed

    private void MTBllamadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MTBllamadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MTBllamadasActionPerformed

    private void BTNbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNbuscarActionPerformed

    private void TFnumUnidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFnumUnidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFnumUnidadActionPerformed

    private void BTNback1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNback1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNback1ActionPerformed

    private void TFnumTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFnumTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFnumTelefonoActionPerformed

    private void BTNbuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNbuscar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNbuscar2ActionPerformed

    private void BTNback2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNback2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTNback2ActionPerformed

    private void TFfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TFfechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TFfechaActionPerformed

    private void CBestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBestadoActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        String NumeroUnidad= TFnumUnidad.getText();
        controladorUnidades.borrarUnidades(NumeroUnidad);
        
        //Insertar metodo eliminarUnidad
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void BotonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonInsertarActionPerformed
        String NumeroUnidad = TFnumUnidad.getText();
        Tipounidad TipoUnidad=(Tipounidad) CBtipounidad.getSelectedItem();
        boolean Disponibilidad= (boolean) jComboBox1.getSelectedItem();
        
        controladorUnidades.insertarUnidades(NumeroUnidad,TipoUnidad,Disponibilidad);
        
        //Insertar metodo insertarUnidades
    }//GEN-LAST:event_BotonInsertarActionPerformed

    private void BotonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonModificarActionPerformed
        String NumeroUnidad = TFnumUnidad.getText();
        String TipoUnidad=(String) CBtipounidad.getSelectedItem();
        boolean Disponibilidad= (boolean) jComboBox1.getSelectedItem();
        
        //Insertar metodo modificarUnidades
        controladorUnidades.ModificarUnidades(NumeroUnidad,TipoUnidad,Disponibilidad);
    }//GEN-LAST:event_BotonModificarActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana1().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNback1;
    private javax.swing.JButton BTNback2;
    private javax.swing.JButton BTNbuscar;
    private javax.swing.JButton BTNbuscar2;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonInsertar;
    private javax.swing.JButton BotonModificar;
    private javax.swing.JComboBox<String> CBestado;
    private javax.swing.JComboBox<String> CBtipounidad;
    private javax.swing.JFrame JFCllamadas;
    private javax.swing.JFrame JFCunidades;
    private javax.swing.JMenuItem MTBllamadas;
    private javax.swing.JMenuItem MTBmovilizaciones;
    private javax.swing.JMenuItem MTBunidades;
    private javax.swing.JMenuItem MTCllamadas;
    private javax.swing.JMenuItem MTCmovilizaciones;
    private javax.swing.JMenuItem MTCunidades;
    private javax.swing.JMenuItem MTIllamadas;
    private javax.swing.JMenuItem MTImovilizaciones;
    private javax.swing.JMenuItem MTIunidades;
    private javax.swing.JMenu MTsalir;
    private javax.swing.JTextField TFfecha;
    private javax.swing.JTextField TFnumTelefono;
    private javax.swing.JTextField TFnumUnidad;
    private javax.swing.JTable Tllamadas;
    private javax.swing.JTable Tunidad;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
