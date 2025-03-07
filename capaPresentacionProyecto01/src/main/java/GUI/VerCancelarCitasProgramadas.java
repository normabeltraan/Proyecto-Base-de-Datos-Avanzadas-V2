package GUI;

import BO.CitaBO;
import BO.PacienteBO;
import Configuracion.DependencyInjector;
import DTO.CitaDTO;
import DTO.PacienteDTO;
import Exception.NegocioException;
import GUI.PerfilPaciente;
import excepciones.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase utilizada para permitir que el paciente observe y/o cancele si lo desea
 * las citas que previamente agendó.
 * @author norma
 */
public class VerCancelarCitasProgramadas extends javax.swing.JFrame {

    private PacienteDTO paciente;
    private CitaBO citaBO = DependencyInjector.crearCitaBO();
    private PacienteBO pacienteBO = DependencyInjector.crearPacienteBO();
    private DefaultTableModel tabla;
    List<CitaDTO> citas;

    /**
     * Constructor de la clase.
     * Inicializa la ventana con los datos del paciente y carga sus citas.
     * @param pacienteDTO Objeto de tipo PacienteDTO que representa al paciente
     * que verá sus citas.
     */
    public VerCancelarCitasProgramadas(PacienteDTO pacienteDTO) {
        this.paciente = pacienteDTO;
        initComponents();
        tabla = new DefaultTableModel(new Object[]{"Fecha y hora", "Especialidad", "Médico"}, 0);
        tablaCitas.setModel(tabla);
        citas();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCitas = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnCancelarCita = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Citas Programadas");

        tablaCitas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Fecha y hora", "Especialidad", "Médico"
            }
        ));
        jScrollPane1.setViewportView(tablaCitas);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCancelarCita.setText("Cancelar Cita");
        btnCancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCitaActionPerformed(evt);
            }
        });

        jLabel2.setText("(Si quiere cancelar una cita seleccione la cita a cancelar y luego el botón \"Cancelar cita)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarCita)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCancelarCita))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que se invoca cuando el usuario hace click en "Cancelar".
     * Cierra la ventana en la que se encuentra, y lo regresa a la ventana
     * del perfil del paciente donde están las opciones.
     * @param evt Evento de acción generado al presionar el botón
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        new PerfilPaciente(paciente).setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Método ejecutado cuando se presiona el botón "Cancelar Cita".
     * Intenta cancelar la cita que seleccionó el usuario, y maneja
     * excepciones para los casos de error.
     * @param evt Evento de acción por presionar el botón
     */
    private void btnCancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCitaActionPerformed
        try {
            cancelarCita();
        } catch (PersistenciaException ex) {
            Logger.getLogger(VerCancelarCitasProgramadas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCancelarCitaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarCita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCitas;
    // End of variables declaration//GEN-END:variables

    /**
     * Método que se encarga de obtener y mostrar las citas que el paciente tiene
     * programadas.
     * Hace un llamado a PacienteBO para conseguir la lista de citas y mostrarlas
     * en una tabla.
     */
    private void citas() {
        try {
            citas = pacienteBO.obtenerCitasProgramadas(paciente);
            tabla.setRowCount(0);

            for (CitaDTO cita : citas) {
                tabla.addRow(new Object[]{
                    cita.getFecha_hora(),
                    cita.getMedico().getEspecialidad(),
                    cita.getMedico().getNombre()
                });
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener las citas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método utilizado para cancelar la cita previamente seleccionada.
     * Verifica si sí se seleccionó la cita, la elimina usando CitaBO y posteriormente
     * actualiza la tabla
     * @throws PersistenciaException Por si ocurre un error en la capa de persistencia.
     */
    private void cancelarCita() throws PersistenciaException {
        int filaSeleccionada = tablaCitas.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una cita para cancelar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            CitaDTO citaSeleccionada = citas.get(filaSeleccionada);
          
            citaBO.cancelarCita(citaSeleccionada);
            citas(); 
            JOptionPane.showMessageDialog(this, "Cita cancelada exitosamente.");
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cancelar la cita: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


