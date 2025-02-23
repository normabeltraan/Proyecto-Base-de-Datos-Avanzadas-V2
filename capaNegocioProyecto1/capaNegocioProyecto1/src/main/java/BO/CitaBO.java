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
 *
 * @author Maximiliano
 */
public class CitaBO {

    private static final Logger logger = Logger.getLogger(CitaBO.class.getName());
    private final ICitaDAO citaDAO;
    private final CitaMapper mapper = new CitaMapper();
    private final CitaSinCitaMapper mapper_citaSinCita = new CitaSinCitaMapper();
    private final HorarioDisponibleMapper mapper_horario = new HorarioDisponibleMapper();

    public CitaBO(IConexionBD conexion) {
        this.citaDAO = new CitaDAO(conexion);
    }

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

    public List<HorarioDisponibleDTO> obtenerHorariosDisponibles(Date fecha, int id_usuario_medico) throws PersistenciaException {
        List<HorarioDisponible> horarios = citaDAO.obtenerHorariosDisponibles(fecha, id_usuario_medico);

        return mapper_horario.toDTOList(horarios);
    }

}
