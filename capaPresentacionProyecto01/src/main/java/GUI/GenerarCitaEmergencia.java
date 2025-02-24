/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BO.CitaSinCitaBO;
import BO.MedicoBO;
import Configuracion.DependencyInjector;
import DTO.CitaSinCitaDTO;
import DTO.PacienteDTO;
import Exception.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author norma
 */
public class GenerarCitaEmergencia extends javax.swing.JFrame {

    private PacienteDTO paciente;
    private MedicoBO medicoBO = DependencyInjector.crearMedicoBO();
    private CitaSinCitaBO citaBO = DependencyInjector.crearCitaSinCitaBO();
    private DefaultTableModel modeloTabla;

    /**
     * Creates new form GenerarCitaEmergencia
     */
    public GenerarCitaEmergencia(PacienteDTO pacienteDTO) {
        this.paciente = pacienteDTO;
        initComponents();
        cargarEspecialidades();
        modeloTabla = new DefaultTableModel(new Object[]{"Folio", "Médico", "Fecha y hora"}, 0);
        tablaHorario.setModel(modeloTabla);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEspecialidad = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHorario = new javax.swing.JTable();
        btnGenerarFolio = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Generar Cita de Emergencia");

        jLabel2.setText("Especialidad");

        txtEspecialidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txtEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEspecialidadActionPerformed(evt);
            }
        });

        tablaHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Folio", "Médico", "Fecha y hora"
            }
        ));
        jScrollPane1.setViewportView(tablaHorario);

        btnGenerarFolio.setText("Generar Folio");
        btnGenerarFolio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarFolioActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGenerarFolio)
                .addGap(182, 182, 182))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(btnGenerarFolio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarFolioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarFolioActionPerformed
        folio();
    }//GEN-LAST:event_btnGenerarFolioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new PerfilPaciente(paciente).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEspecialidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEspecialidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerarFolio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHorario;
    private javax.swing.JComboBox<String> txtEspecialidad;
    // End of variables declaration//GEN-END:variables

    private void folio() {
        String especialidadSeleccionada = (String) txtEspecialidad.getSelectedItem();
        System.out.println(especialidadSeleccionada);
        if (especialidadSeleccionada == null || especialidadSeleccionada.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una especialidad", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idPaciente = paciente.getUsuario().getId_usuario();

        try {
            CitaSinCitaDTO cita = citaBO.agendarCitaEmergencia(especialidadSeleccionada, idPaciente);
            actualizarTabla(cita);
            JOptionPane.showMessageDialog(this, "La cita de emergencia ha sido agendada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NegocioException | PersistenciaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al generar la cita", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEspecialidades() {
        List<String> especialidades = medicoBO.obtenerEspecialidades();
        if (especialidades != null) {
            txtEspecialidad.removeAllItems();
            for (String especialidad : especialidades) {
                txtEspecialidad.addItem(especialidad);
            }
        }
    }

    private void actualizarTabla(CitaSinCitaDTO cita) {
        Object[] fila = new Object[]{
            cita.getFolio_emergencia(),
            cita.getCita().getMedico().getNombre(),
            cita.getCita().getFecha_hora()
        };
        modeloTabla.addRow(fila);
    }

}
