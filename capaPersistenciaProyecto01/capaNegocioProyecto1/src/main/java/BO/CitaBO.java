/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaDTO;
import DTO.HorarioDisponibleDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.HorarioDisponibleMapper;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.HorarioDisponible;
import excepciones.PersistenciaException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano
 */
public class CitaBO {

    private static final Logger logger = Logger.getLogger(CitaBO.class.getName());
    private final ICitaDAO citaDAO;
    private final CitaMapper mapper = new CitaMapper();

    public CitaBO(IConexionBD conexion) {
        this.citaDAO = new CitaDAO(conexion);
    }

    public boolean agendarCita(CitaDTO citaNuevo) throws NegocioException {

        Timestamp fechaHora = citaNuevo.getFecha_hora();
        Timestamp hoy = new Timestamp(System.currentTimeMillis());
        if (fechaHora.before(hoy)) {
            throw new NegocioException("Error. No se puede agendar una cita en el pasado.");
        }

        Cita cita = mapper.toEntity(citaNuevo);
        try {
            return citaDAO.agendarCita(cita);

        } catch (PersistenciaException e) {
            logger.severe("Error al agendar la cita: " + e.getMessage());
            throw new NegocioException("Error al agendar la cita: " + e.getMessage(), e);
        }
    }

    public List<HorarioDisponibleDTO> obtenerHorariosDisponibles(int idMedico) throws NegocioException {
        try {
            List<HorarioDisponible> horarios = citaDAO.obtenerHorariosDisponibles(idMedico);

            HorarioDisponibleMapper mapper = new HorarioDisponibleMapper();
            return mapper.toDTOList(horarios);
        } catch (PersistenciaException e) {
            logger.severe("Error al obtener los horarios disponibles: " + e.getMessage());
            throw new NegocioException("Error al obtener los horarios disponibles", e);
        }
    }

}
