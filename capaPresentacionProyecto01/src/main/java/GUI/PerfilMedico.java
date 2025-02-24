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
 *
 * @author norma
 */
public class PerfilMedico extends javax.swing.JFrame {

    private MedicoDTO medico;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();

    /**
     * Creates new form PerfilMedico
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

    private void btnConsultarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarAgendaActionPerformed
        new ConsultarAgenda(medico).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnConsultarAgendaActionPerformed

    private void btnHistorialConsultasPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialConsultasPacientesActionPerformed
        new HistorialConsultasPacientesPorMedico(medico).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnHistorialConsultasPacientesActionPerformed

    private void btnVerPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerPerfilActionPerformed
        try {
            new VerPerfilMedico(medico).setVisible(true);
        } catch (NegocioException ex) {
            Logger.getLogger(PerfilMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
    }//GEN-LAST:event_btnVerPerfilActionPerformed

    private void btnActivarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarCuentaActionPerformed
        activarCuenta();
    }//GEN-LAST:event_btnActivarCuentaActionPerformed

    private void btnDarDeBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDarDeBajaActionPerformed
        darDeBaja();
    }//GEN-LAST:event_btnDarDeBajaActionPerformed

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

    private void activarCuenta() {
        try {
            String resultado = medicoBO.cambiarEstadoMedico(medico, "Activo");
            JOptionPane.showMessageDialog(this, resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al activar la cuenta: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void darDeBaja() {
        try {
            String resultado = medicoBO.cambiarEstadoMedico(medico, "Inactivo");
            JOptionPane.showMessageDialog(this, resultado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja temporalmente: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
