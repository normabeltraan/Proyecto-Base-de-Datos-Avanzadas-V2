/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Cita;
import entidades.CitaSinCita;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

/**
 * Esta clase implementa las operaciones relacionadas a las citas de emergencia.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaSinCitaDAO implements ICitaSinCitaDAO {

    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión con la base de datos.
     * @param conexion El objeto que gestiona la conexión.
     */
    public CitaSinCitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * En este método se agenda una cita de emergencia para un paciente según la especialidad al igual que su folio.
     * @param especialidad La especialidad del médico.
     * @param id_paciente El identificador único del paciente.
     * @return Regresa un objeto con la información de una cita de emergencia.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    @Override
    public CitaSinCita agendarCitaEmergencia(String especialidad, int id_paciente) throws PersistenciaException {
        CitaSinCita citaSinCita = null;
        String consultaSQL = "{CALL AGENDAR_CITA_EMERGENCIA(?, ?)}";

        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setString(1, especialidad);
            cb.setInt(2, id_paciente);

            boolean hasResultSet = cb.execute();

            if (hasResultSet) {
                try (ResultSet rs = cb.getResultSet()) {
                    if (rs.next()) {
                        String folioEmergencia = rs.getString("folio_emergencia");
                        Timestamp fechaHora = rs.getTimestamp("fecha_hora");
                        int idMedico = rs.getInt("id_usuario_medico");
                        String especialidad_medico = rs.getString("especialidad");

                        MedicoDAO medicoDAO = new MedicoDAO(this.conexion);
                        Medico medico = medicoDAO.obtenerMedicoPorId(idMedico);

                        Cita cita = new Cita(fechaHora, "Activa", "Cita Emergencia", null, medico);

                        citaSinCita = new CitaSinCita(cita, folioEmergencia);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al generar la cita de emergencia, el paciente ya tiene cita con le médico en la fecha de la fecha más disponible", e);
        }

        return citaSinCita;
    }

    /**
     * Este método se encarga de obtener el folio de emergencia de las citas sin cita previa.
     * @param id_cita El identificador único de la cita.
     * @return Regresa el folio de emergencia asociado a la cita, o null si no existe.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public String obtenerFolioEmergencia(int id_cita) throws PersistenciaException {

        String consultaSQL = "SELECT folio_emergencia FROM CITAS_SINCITA WHERE id_cita = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, id_cita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("folio_emergencia");
            }
            return null; // Si no hay folio, retorna null
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el folio de emergencia.", e);
        }
    }

}
