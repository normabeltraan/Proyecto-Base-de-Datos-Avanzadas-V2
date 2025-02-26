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
 * La clase cita DAO implementa la interfaz ICitaDAO, se encarga de gestionar todas las operaciones relacionadas,
 * a las citas en general más enfocada a las citas agendadas.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaDAO implements ICitaDAO {

    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión con la base de datos.
     * @param conexion La conexión de la base de datos.
     */
    public CitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(CitaDAO.class.getName());

    /**
     * Este método se usa para agendar una nueva cita en la base de datos.
     * @param cita El objeto que contiene toda la información de la cita.
     * @return Regresa verdadero si la cita se agendó correctamente, falso en el caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
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

    /**
     * Este método se usa para cancelar una cita ya existente en la base de datos.
     * @param cita Un objeto que almacena toda la información de la cita.
     * @return Regresa verdadero si la cita se canceló correctamente, falso en el caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
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

    /**
     * Este método se usa para obtener los horarios disponibles de un médico en una fecha en específico.
     * @param fecha Fecha para la cual se quiere obtener los horarios disponibles.
     * @param id_usuario_medico El identificador único del usuario del médico.
     * @return Regresa la lista con los horarios disponibles de los médicos.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
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
