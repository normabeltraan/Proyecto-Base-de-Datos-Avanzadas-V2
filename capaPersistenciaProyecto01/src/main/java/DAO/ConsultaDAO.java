/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Cita;
import entidades.Consulta;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class ConsultaDAO implements IConsultaDAO {

    IConexionBD conexion;

    public ConsultaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    
    private static final Logger logger = Logger.getLogger(ConsultaDAO.class.getName());
    
    @Override
    public List<Consulta> obtenerHistorialConsultasDelPaciente(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException {

        List<Consulta> consultas = new ArrayList<>();

        String consultaSQL = "SELECT "
                + "CONCAT(P.NOMBRE, ' ', P.APELLIDO_PATERNO, ' ', IFNULL(P.APELLIDO_MATERNO, '')) AS nombre_completo_paciente, "
                + "M.ESPECIALIDAD, "
                + "CONCAT(M.NOMBRE, ' ', M.APELLIDO_PATERNO, ' ', IFNULL(M.APELLIDO_MATERNO, '')) AS nombre_completo_medico, "
                + "CI.FECHA_HORA, "
                + "CO.DIAGNOSTICO, "
                + "CO.TRATAMIENTO "
                + "FROM CONSULTAS CO "
                + "JOIN CITAS CI ON CI.ID_CITA = CO.ID_CITA "
                + "JOIN PACIENTES P ON CI.ID_USUARIO_PACIENTE = P.ID_USUARIO "
                + "JOIN MEDICOS M ON CI.ID_USUARIO_MEDICO = M.ID_USUARIO "
                + "WHERE CONCAT(P.NOMBRE, ' ', P.APELLIDO_PATERNO, ' ', IFNULL(P.APELLIDO_MATERNO, '')) = ? "
                + "AND (? IS NULL OR M.ESPECIALIDAD = ?) "
                + "AND CI.FECHA_HORA BETWEEN ? AND ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nombrePaciente);
            ps.setString(2, especialidad);
            ps.setString(3, especialidad);
            ps.setTimestamp(4, new Timestamp(fechaInicio.getTime()));
            ps.setTimestamp(5, new Timestamp(fechaFin.getTime()));

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Medico medico = new Medico();
                    medico.setNombre(rs.getString("nombre_completo_medico"));
                    medico.setEspecialidad(rs.getString("especialidad"));

                    Cita cita = new Cita();
                    cita.setFecha_hora(rs.getTimestamp("fecha_hora"));
                    cita.setMedico(medico);

                    Consulta consulta = new Consulta();
                    consulta.setCita(cita);
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setTratamiento(rs.getString("tratamiento"));

                    consultas.add(consulta);
                }
            }

        } catch (SQLException ex) {
            logger.severe("Error al obtener historial de consultas: " + ex.getMessage());
            throw new PersistenciaException("Error al obtener el historial de consultas", ex);
        }

        return consultas;
    }
    
         @Override
    public boolean atenderCitaProgramada(int idCita, int idUsuarioMedico) throws PersistenciaException {
        String query = "SELECT estado, id_usuario_medico FROM CITAS WHERE id_cita = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCita);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                int medicoId = rs.getInt("id_usuario_medico");

                // Validar que la cita esté activa y que el médico sea el correcto
                if (!estado.equals("Activa")) {
                    throw new PersistenciaException("La cita no está activa o ya ha sido atendida.");
                }
                if (medicoId != idUsuarioMedico) {
                    throw new PersistenciaException("El médico asignado no es el que está intentando atender la cita.");
                }

                // Actualizar el estado de la cita a 'Atendida'
                String updateQuery = "UPDATE CITAS SET estado = 'Atendida' WHERE id_cita = ?";
                try (Connection conNuevo = this.conexion.crearConexion(); PreparedStatement updatePS = conNuevo.prepareStatement(updateQuery)) {
                    updatePS.setInt(1, idCita);
                    int rowsAffected = updatePS.executeUpdate();
                    return rowsAffected > 0;
                }
            } else {
                throw new PersistenciaException("Cita no encontrada.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al atender la cita programada.", e);
        }
    }

    @Override
    public boolean atenderCitaEmergencia(int idCita, int idUsuarioMedico, String folioEmergencia) throws PersistenciaException {
        String query = "SELECT estado, id_usuario_medico FROM CITAS c " +
                       "JOIN CITAS_SINCITA cs ON c.id_cita = cs.id_cita " +
                       "WHERE c.id_cita = ? AND cs.folio_emergencia = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, idCita);
            ps.setString(2, folioEmergencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                int medicoId = rs.getInt("id_usuario_medico");

                // Validar que la cita de emergencia esté activa y que el médico sea el correcto
                if (!estado.equals("Activa")) {
                    throw new PersistenciaException("La cita de emergencia no está activa o ya ha sido atendida.");
                }
                if (medicoId != idUsuarioMedico) {
                    throw new PersistenciaException("El médico asignado no es el que está intentando atender la cita de emergencia.");
                }

                // Actualizar el estado de la cita a 'Atendida'
                String updateQuery = "UPDATE CITAS SET estado = 'Atendida' WHERE id_cita = ?";
                try (Connection conNuevo = this.conexion.crearConexion(); PreparedStatement updatePS = conNuevo.prepareStatement(updateQuery)) {
                    updatePS.setInt(1, idCita);
                    int rowsAffected = updatePS.executeUpdate();
                    return rowsAffected > 0;
                }
            } else {
                throw new PersistenciaException("Cita de emergencia no encontrada o el folio es incorrecto.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al atender la cita de emergencia.", e);
        }
    }
}
