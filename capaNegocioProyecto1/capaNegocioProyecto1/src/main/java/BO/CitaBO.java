/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaDTO;
import DTO.CitaSinCitaDTO;
import DTO.HorarioDisponibleDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.CitaSinCitaMapper;
import Mapper.HorarioDisponibleMapper;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.CitaSinCita;
import entidades.HorarioDisponible;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase de la capa de negocio que gestiona las operaciones relacionadas con las citas médicas.
 * Se encarga de realizar validaciones y coordinar la interacción con la capa de persistencia para
 * la programación y cancelación de citas, así como la consulta de horarios disponibles.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaBO {

    private static final Logger logger = Logger.getLogger(CitaBO.class.getName());
    private final ICitaDAO citaDAO;
    private final CitaMapper mapper = new CitaMapper();
    private final CitaSinCitaMapper mapper_citaSinCita = new CitaSinCitaMapper();
    private final HorarioDisponibleMapper mapper_horario = new HorarioDisponibleMapper();

    /**
     * Constructor que inicializa el acceso a datos de citas con una conexión a la base de datos.
     * @param conexion El objeto de conexión a la base de datos.
     */
    public CitaBO(IConexionBD conexion) {
        this.citaDAO = new CitaDAO(conexion);
    }

    /**
     * Este método agenda una nueva cita en el sistema, validando que la fecha sea válida y que el paciente
     * no tenga una cita con el mismo médico en el mismo horario.
     * @param citaNuevo El objeto CitaDTO con la información de la cita a agendar.
     * @return Regresa verdadero si la cita fue agendada correctamente, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si se intenta agendar una cita en el pasado o si el paciente ya tiene una cita con el médico en ese horario.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la capa de persistencia.
     */
    public boolean agendarCita(CitaDTO citaNuevo) throws NegocioException, PersistenciaException {

        Timestamp fechaHora = citaNuevo.getFecha_hora();
        Timestamp hoy = new Timestamp(System.currentTimeMillis());
        if (fechaHora == null) {
            throw new PersistenciaException("La fecha y hora no pueden ser nulas.");
        }
        if (fechaHora.before(hoy)) {
            throw new NegocioException("Error. No se puede agendar una cita en el pasado.");
        }

        Cita cita = mapper.toEntity(citaNuevo);
        try {
            return citaDAO.agendarCita(cita);

        } catch (PersistenciaException e) {
            if (e.getMessage().contains("El paciente ya tiene una cita con este médico en esa fecha")) {
                logger.severe("El paciente ya tiene una cita con este médico en esa fecha.");
                throw new NegocioException("El paciente ya tiene una cita con este médico en esa fecha.");
            } else {
                logger.severe("El paciente ya tiene una cita con este médico en esa fecha. " + e.getMessage());
                throw new NegocioException("El paciente ya tiene una cita con este médico en esa fecha. " + e.getMessage());
            }
        }
    }

    /**
     * Este método cancela una cita existente en el sistema, validando que la cita esté activa y que se cancele con al menos 24 horas de anticipación.
     * @param citaDTO El objeto CitaDTO con la información de la cita a cancelar.
     * @return Regresa verdadero si la cita fue cancelada correctamente, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si la cita no está activa o si la cancelación se intenta realizar con menos de 24 horas de anticipación.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la capa de persistencia al intentar cancelar la cita.
     */
    public boolean cancelarCita(CitaDTO citaDTO) throws NegocioException, PersistenciaException {
        
        System.out.println(citaDTO);
        Cita cita = mapper.toEntity(citaDTO);
        
        System.out.println(cita);
        
        if (!"Activa".equals(cita.getEstado())) {
            logger.log(Level.SEVERE, "Error: Solo se pueden cancelar citas activas.");
            throw new NegocioException("Solo se pueden cancelar citas activas.");
        }

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime fechaCita = cita.getFecha_hora().toLocalDateTime();

        if (fechaCita.isBefore(ahora.plusHours(24))) {
            logger.log(Level.SEVERE, "Error: La cita solo puede cancelarse con 24 horas de anticipación.");
            throw new NegocioException("La cita solo puede cancelarse con 24 horas de anticipación.");
        }

        try {
            return citaDAO.cancelarCita(cita);
        } catch (PersistenciaException e) {
            logger.severe("Error al cancelar la cita: " + e.getMessage());
            throw new NegocioException("Error al cancelar la cita: " + e.getMessage(), e);
        }
    }

    /**
     * Este método obtiene los horarios disponibles para agendar una cita con un médico en una fecha específica.
     * @param fecha La fecha en la que se desean consultar los horarios disponibles.
     * @param id_usuario_medico El identificador del médico cuyos horarios se desean consultar.
     * @return La lista de objetos HorarioDisponibleDTO con los horarios disponibles.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la capa de persistencia.
     */
    public List<HorarioDisponibleDTO> obtenerHorariosDisponibles(Date fecha, int id_usuario_medico) throws PersistenciaException {
        List<HorarioDisponible> horarios = citaDAO.obtenerHorariosDisponibles(fecha, id_usuario_medico);

        return mapper_horario.toDTOList(horarios);
    }

}
