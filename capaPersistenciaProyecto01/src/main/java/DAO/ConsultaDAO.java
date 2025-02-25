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
 * Esta clase implementa consultas, de tal manera que se realizane las consultas médicas de ambos casos de cita.
 * De emergencia y agendadas.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class ConsultaDAO implements IConsultaDAO {

    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión con la base de datos.
     * @param conexion El objeto que gestiona la conexión con la base de datos.
     */
    public ConsultaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(ConsultaDAO.class.getName());

    /**
     * Este método obtiene el historial de consultas de los pacientes filtrados por especialidad y un rango de fechas.
     * @param nombrePaciente El nombre completo del paciente.
     * @param especialidad La especialidad del médico.
     * @param fechaInicio La fecha de inicio de la consulta.
     * @param fechaFin La fecha de fin de la consulta.
     * @return Regresa la lista de consultas del paciente.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
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

    /**
     * Este método se encarga de que las citas programadas sean atendidas por los médicos para que lleven a cabo su consulta.
     * @param idCita El identificador único de la cita programada.
     * @param idUsuarioMedico El identificador único del usuario del médico.
     * @param consulta El objeto que almacena toda la información de la consulta realizada.
     * @return Se regresa verdadero si la cita fue atendida exitosamente, y falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    @Override
    public boolean atenderCitaProgramada(int idCita, int idUsuarioMedico, Consulta consulta) throws PersistenciaException {
        String consultaSQL = "SELECT estado, id_usuario_medico FROM CITAS WHERE id_cita = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
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
                    int filasAfectadas = updatePS.executeUpdate();

                    if (filasAfectadas > 0) {
                        String consultaSQL2 = "INSERT INTO CONSULTAS (diagnostico, tratamiento, observaciones, id_cita) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement insertPS = conNuevo.prepareStatement(consultaSQL2)) {
                            insertPS.setString(1, consulta.getDiagnostico());
                            insertPS.setString(2, consulta.getTratamiento());
                            insertPS.setString(3, consulta.getObservaciones());
                            insertPS.setInt(4, idCita);
                            insertPS.executeUpdate();
                        }
                        return true;
                    }
                }
            } else {
                throw new PersistenciaException("Cita no encontrada.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al atender la cita programada.", e);
        }
        return false;
    }

    /**
     * En este método se encarga de que las citas de emergencia sean atendidos por los médicos que llevan a cabo la consulta.
     * @param idCita El identificador único de la cita.
     * @param idUsuarioMedico El identificador único del usuario médico.
     * @param folioEmergencia El folio de emergencia necesario para poder atender la cita.
     * @param consulta El objeto que guarda toda la información de la consulta realizada.
     * @return Regresa verdadero si la cita fue atendida exitosamente, y falso si es el caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error en la operación.
     */
    @Override
    public boolean atenderCitaEmergencia(int idCita, int idUsuarioMedico, String folioEmergencia, Consulta consulta) throws PersistenciaException {
        String consultaSQL = "SELECT estado, id_usuario_medico FROM CITAS c "
                + "JOIN CITAS_SINCITA cs ON c.id_cita = cs.id_cita "
                + "WHERE c.id_cita = ? AND cs.folio_emergencia = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setInt(1, idCita);
            ps.setString(2, folioEmergencia);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String estado = rs.getString("estado");
                int medicoId = rs.getInt("id_usuario_medico");

                if (!estado.equals("Activa")) {
                    throw new PersistenciaException("La cita de emergencia no está activa o ya ha sido atendida.");
                }
                if (medicoId != idUsuarioMedico) {
                    throw new PersistenciaException("El médico asignado no es el que está intentando atender la cita de emergencia.");
                }

                String updateQuery = "UPDATE CITAS SET estado = 'Atendida' WHERE id_cita = ?";
                try (PreparedStatement updatePS = con.prepareStatement(updateQuery)) {
                    updatePS.setInt(1, idCita);
                    int filasAfectadas = updatePS.executeUpdate();

                    if (filasAfectadas > 0) {
  
                        String consultaSQL2 = "INSERT INTO CONSULTAS (diagnostico, tratamiento, observaciones, id_cita) "
                                + "VALUES (?, ?, ?, ?)";
                        try (PreparedStatement insertPS = con.prepareStatement(consultaSQL2)) {
                            insertPS.setString(1, consulta.getDiagnostico());
                            insertPS.setString(2, consulta.getTratamiento());
                            insertPS.setString(3, consulta.getObservaciones());
                            insertPS.setInt(4, idCita);

                            int insertRows = insertPS.executeUpdate();
                            if (insertRows > 0) {
                                return true;
                            } else {
                                throw new PersistenciaException("No se pudo registrar la consulta para la cita de emergencia.");
                            }
                        }
                    } else {
                        throw new PersistenciaException("No se pudo actualizar el estado de la cita de emergencia.");
                    }
                }
            } else {
                throw new PersistenciaException("Cita de emergencia no encontrada o el folio es incorrecto.");
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al atender la cita de emergencia.", e);
        }
    }

    /**
     * En este método se encarga de validar el folio de emergencia de la cita de emergencia.
     * @param idCita El identificador único de la cita.
     * @param folio El folio de la cita de emergencia.
     * @return Regresa el verdadero si fue validado con éxito y sea el correcto, y falso si es lo contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    @Override
    public boolean validarFolio(int idCita, String folio) throws PersistenciaException {
        String consultaSQL = "SELECT folio_emergencia FROM CITAS_SINCITA WHERE id_cita = ? AND folio_emergencia = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idCita);
            ps.setString(2, folio);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
