/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.MedicoBO;
import Configuracion.DependencyInjector;
import DTO.MedicoDTO;
import Exception.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Clase que representa la interfaz gráfica del perfil de un médico.
 * Muestra las opciones disponibles, ya que haya iniciado sesión.
 * @author norma
 */
public class PerfilMedico extends javax.swing.JFrame {

    private MedicoDTO medico;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();

    /**
     * Constructor de la clase que inicializa el perfil del médico
     * con los datos proporcionados.
     * @param medicoDTO Objeto que contiene los datos del médico.
     */
    public PerfilMedico(MedicoDTO medicoDTO) {
        this.medico = medicoDTO;
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnConsultarAgenda = new javax.swing.JButton();
        btnHistorialConsultasPacientes = new javax.swing.JButton();
        btnVerPerfil = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnActivarCuenta = new javax.swing.JButton();
        btnDarDeBaja = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Perfil Médico");

        btnConsultarAgenda.setText("Consultar Agenda");
        btnConsultarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarAgendaActionPerformed(evt);
            }
        });

        btnHistorialConsultasPacientes.setText("Historial de Consultas Pacientes");
        btnHistorialConsultasPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialConsultasPacientesActionPerformed(evt);
            }
        });

        btnVerPerfil.setText("Ver Perfil");
        btnVerPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerPerfilActionPerformed(evt);
            }
        });

        jLabel2.setText("Cambiar Estado");

        btnActivarCuenta.setText("Activar cuenta ");
        btnActivarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarCuentaActionPerformed(evt);
            }
        });

        btnDarDeBaja.setText("Dar de baja temporal");
        btnDarDeBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDarDeBajaActionPerformed(evt);
            }
        });

        btnCerrarSesion.setText("Cerrar Sesión");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConsultarAgenda)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVerPerfil)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(btnHistorialConsultasPacientes))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnCerrarSesion)
                        .addGap(45, 45, 45)
                        .addComponent(btnActivarCuenta)
                        .addGap(29, 29, 29)
                        .addComponent(btnDarDeBaja)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addComponent(btnConsultarAgenda)
                .addGap(29, 29, 29)
                .addComponent(btnHistorialConsultasPacientes)
                .addGap(39, 39, 39)
                .addComponent(btnVerPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActivarCuenta)
                    .addComponent(btnDarDeBaja)
                    .addComponent(btnCerrarSesion))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que se ejecuta cuando el botón "Consultar Agenda" es presionado.
     * Abre la ventana para observar la agenda del médico del día actual.
     * @param evt Evento de acción generado por el botón.
     */
    private void btnConsultarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarAgendaActionPerformed
        new ConsultarAgenda(medico).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnConsultarAgendaActionPerformed

    /**
     * Método invocado cuando el botón "Historial de Consultas Pacientes" es clickeado.
     * Abre la ventana que muestra el historial de consultas de un paciente (el paciente
     * al que se escribió su nombre completo).
     * @param evt  Evento de acción 
     */
    private void btnHistorialConsultasPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialConsultasPacientesActionPerformed
        new HistorialConsultasPacientesPorMedico(medico).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHistorialConsultasPacientesActionPerformed

    /**
     * Método que se ejecuta cuando se hace click en el botón "Ver perfil".
     * Abre la ventana que permite al médico ver su información registrada
     * en el sistema.
     * @param evt Evento de acción generado por el botón ver pefil
     */
    private void btnVerPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPerfilActionPerformed
        try {
            new VerPerfilMedico(medico).setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(PerfilMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnVerPerfilActionPerformed

    /**
     * Método invocado cuando el botón "Activar cuenta" es presionado.
     * Cambia el estado de la cuenta del médico de "Inactivo" a "Activo".
     * @param evt Evento de acción generado por el botón.
     */
    private void btnActivarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarCuentaActionPerformed
        activarCuenta();
    }//GEN-LAST:event_btnActivarCuentaActionPerformed

    /**
     * Método que se invoca cuando el botón "Dar de baja temporal" es presionado.
     * Cambia el estado de la cuenta del médico de "Activo" a "Inactivo".
     * @param evt Evento de acción
     */
    private void btnDarDeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarDeBajaActionPerformed
        darDeBaja();
    }//GEN-LAST:event_btnDarDeBajaActionPerformed

    /**
     * Método que se ejecuta cuando el botón "Cerrar sesión" es presionado.
     * Solicita una confirmación para saber si realmente se quiere cerrar
     * la sesión del médico.
     * @param evt Evento de acción
     */
    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que quiere cerrar sesión?",
                "Confirmar cierre de sesión", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivarCuenta;
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnConsultarAgenda;
    private javax.swing.JButton btnDarDeBaja;
    private javax.swing.JButton btnHistorialConsultasPacientes;
    private javax.swing.JButton btnVerPerfil;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    /**
     * Cambia el estado de la cuenta del médico a "Activo".
     */
    private void activarCuenta() {
        try {
            String resultado = medicoBO.cambiarEstadoMedico(medico, "Activo");
            JOptionPane.showMessageDialog(this, resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al activar la cuenta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cambia el estado de la cuenta del médico a "Inactivo".
     * Solo es una baja temporal, no definitiva.
     */
    private void darDeBaja() {
        try {
            String resultado = medicoBO.cambiarEstadoMedico(medico, "Inactivo");
            JOptionPane.showMessageDialog(this, resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja temporalmente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
