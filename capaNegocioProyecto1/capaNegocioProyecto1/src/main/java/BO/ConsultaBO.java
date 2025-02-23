/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ConsultaDAO;
import DAO.IConsultaDAO;
import DTO.ConsultaDTO;
import DTO.CitaDTO;
import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaSinCitaDTO;
import DAO.CitaSinCitaDAO;
import DAO.ICitaSinCitaDAO;
import DTO.MedicoDTO;
import DAO.MedicoDAO;
import DAO.IMedicoDAO;
import Exception.NegocioException;
import Mapper.ConsultaMapper;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Consulta;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano
 */
public class ConsultaBO {

    private static final Logger logger = Logger.getLogger(ConsultaBO.class.getName());
    private final IConsultaDAO consultaDAO;
    private final ConsultaMapper mapper = new ConsultaMapper();

    public ConsultaBO(IConexionBD conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
    }

    public List<ConsultaDTO> obtenerHistorialConsultas(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException {
        if (nombrePaciente == null || fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("El nombre del paciente y las fechas de inicio y fin son obligatorios.");
        }
        
        List<Consulta> consultas = consultaDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente, especialidad, fechaInicio, fechaFin);
        return mapper.toDTOList(consultas);
    }
    
    public boolean atenderCitaProgramada(ConsultaDTO consultaDTO) throws NegocioException {
    if (consultaDTO == null || consultaDTO.getCita() == null) {
        throw new NegocioException("La consulta o la cita asociada no pueden ser nulas.");
    }

    try {
        // Convertimos la entidad Cita a DTO
        CitaDTO citaDTO = mapper.toDTO(consultaDTO.getCita());

        // Obtenemos el médico desde la cita
        MedicoDTO medicoDTO = citaDTO.getMedico();
        if (medicoDTO == null || medicoDTO.getUsuario() == null) {
            throw new NegocioException("No se pudo determinar el médico.");
        }

        // Llamamos al DAO con los datos extraídos desde los DTOs
        boolean resultado = consultaDAO.atenderCitaProgramada(
            citaDTO.getId_cita(),
            medicoDTO.getUsuario().getId_usuario()
        );

        return resultado;
    } catch (PersistenciaException e) {
        throw new NegocioException("Error al atender cita programada.", e);
    }
}


     public boolean atenderCitaEmergencia(ConsultaDTO consultaDTO) throws NegocioException {
    if (consultaDTO == null || consultaDTO.getCita() == null) {
        throw new NegocioException("La consulta o la cita asociada no pueden ser nulas.");
    }

    try {
        // Obtener los datos desde los DTOs sin usar IDs directamente
        CitaDTO citaDTO = mapper.toDTO(consultaDTO.getCita()); // Convertir la entidad Cita a DTO
        MedicoDTO medicoDTO = citaDTO.getMedico(); // Suponiendo que CitaDTO tiene un MedicoDTO
        
        if (medicoDTO == null || medicoDTO.getUsuario() == null) {
            throw new NegocioException("No se pudo determinar el médico.");
        }

        // Extraer datos necesarios
        String folioEmergencia = citaDTO.getFolioEmergencia(); // Si existe este dato
        boolean resultado = consultaDAO.atenderCitaEmergencia(
            citaDTO.getId_cita(), 
            medicoDTO.getUsuario().getId_usuario(), 
            folioEmergencia
        );

        return resultado;
    } catch (PersistenciaException e) {
        throw new NegocioException("Error al atender cita de emergencia.", e);
    }
}
}
