/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entidades.HorarioDisponible;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.CitaSinCita;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class CitaDAO implements ICitaDAO {

    IConexionBD conexion;

    public CitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(CitaDAO.class.getName());

    /**
     * private Timestamp convertirFechaHora(Date fecha, Time hora) { return
     * Timestamp.valueOf(fecha.toString() + " " + hora.toString()); }
     *
     */
    /**
     * private Timestamp convertirFechaHora(Date fecha, Time hora) { try {
     * String fechaHoraStr = fecha.toString() + " " + hora.toString(); return
     * Timestamp.valueOf(fechaHoraStr); } catch (IllegalArgumentException e) {
     * logger.log(Level.SEVERE, "Error en la conversión de fecha y hora", e);
     * return null; } }
     *
     */
    @Override
    public boolean agendarCita(Cita cita) throws PersistenciaException {
        String consultaSQL = "CALL AGENDAR_CITA(?, ?, ?)";
        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setTimestamp(1, cita.getFecha_hora());
            cb.setInt(2, cita.getPaciente().getUsuario().getId_usuario());
            cb.setInt(3, cita.getMedico().getUsuario().getId_usuario());

            cb.executeUpdate();

            logger.info("Cita agendada con éxito.");
            return true;

        } catch (SQLException e) {
            if (e.getMessage().contains("El paciente ya tiene una cita con este médico en esa fecha")) {
                logger.log(Level.SEVERE, "El paciente ya tiene una cita con este médico en esa fecha.");
                throw new PersistenciaException("El paciente ya tiene una cita con este médico en esa fecha", e);
            } else {
                logger.log(Level.SEVERE, "El paciente ya tiene una cita con este médico en esa fecha. " + e.getMessage());
                throw new PersistenciaException("El paciente ya tiene una cita con este médico en esa fecha. ", e);
            }
        }
    }

    @Override
    public boolean cancelarCita(Cita cita) throws PersistenciaException {
        
        String consultaSQL = "CALL CANCELAR_CITA(?)";

        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setInt(1, cita.getId_cita());

            cb.executeUpdate();

            logger.info("Cita cancelada con éxito.");
            return true;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al cancelar la cita", e);
            throw new PersistenciaException("Error al cancelar cita", e);
        }
    }

    @Override
    public List<HorarioDisponible> obtenerHorariosDisponibles(Date fecha, int id_usuario_medico) throws PersistenciaException {
        String consultaSQL = "SELECT fecha, hora_disponible "
                + "FROM vista_horarios_medicos "
                + "WHERE fecha = ? "
                + "AND id_usuario_medico = ? "
                + "AND NOT EXISTS ("
                + "    SELECT 1 FROM citas ci "
                + "    WHERE ci.id_usuario_medico = vista_horarios_medicos.id_usuario_medico "
                + "    AND ci.fecha_hora = CONCAT(vista_horarios_medicos.fecha, ' ', vista_horarios_medicos.hora_disponible) "
                + "    AND ci.estado != 'Cancelada'"
                + ")";
        List<HorarioDisponible> horarios = new ArrayList<>();

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setDate(1, fecha);
            ps.setInt(2, id_usuario_medico);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date dia = rs.getDate("fecha");
                Time hora_disponible = rs.getTime("hora_disponible");

                /**
                 * Timestamp fechaHoraCita = convertirFechaHora(dia,
                 * hora_disponible); if (fechaHoraCita == null) {
                 * logger.warning("No se convirtio fecha y hora,"); continue; }
                 *
                 */
                HorarioDisponible horario = new HorarioDisponible(dia, hora_disponible);
                horarios.add(horario);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener los horarios disponibles", e);
            throw new PersistenciaException("Error al obtener los horarios disponibles", e);
        }

        return horarios;
    }

}
