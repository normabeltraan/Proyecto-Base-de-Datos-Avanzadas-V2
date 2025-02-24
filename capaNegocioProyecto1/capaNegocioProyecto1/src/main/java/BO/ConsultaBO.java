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
import Mapper.CitaMapper;
import Mapper.CitaSinCitaMapper;
import Mapper.ConsultaMapper;
import Mapper.MedicoMapper;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Consulta;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano
 */
public class ConsultaBO {

    private static final Logger logger = Logger.getLogger(ConsultaBO.class.getName());
    private final IConsultaDAO consultaDAO;
    private final ICitaSinCitaDAO citaSinCitaDAO;
    private final ConsultaMapper mapper = new ConsultaMapper();
    private final CitaMapper mapper_cita = new CitaMapper();
    private final CitaSinCitaMapper mapper_citaSinCita = new CitaSinCitaMapper();
    private final MedicoMapper mapper_medico = new MedicoMapper();

    public ConsultaBO(IConexionBD conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
        this.citaSinCitaDAO = new CitaSinCitaDAO(conexion);
    }

    public List<ConsultaDTO> obtenerHistorialConsultas(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException {
        if (nombrePaciente == null || fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("El nombre del paciente y las fechas de inicio y fin son obligatorios.");
        }

        List<Consulta> consultas = consultaDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente, especialidad, fechaInicio, fechaFin);
        return mapper.toDTOList(consultas);
    }

    public boolean atenderCitaProgramada(ConsultaDTO consultaDTO, MedicoDTO medicoDTO) throws NegocioException {
        if (consultaDTO == null || consultaDTO.getCita() == null) {
            throw new NegocioException("La consulta o la cita asociada no pueden ser nulas.");
        }

        try {
            CitaDTO citaDTO = mapper_cita.toDTO(consultaDTO.getCita());

            Consulta consulta = mapper.toEntity(consultaDTO);

            // Llamamos al DAO para atender la cita y registrar la consulta
            boolean resultado = consultaDAO.atenderCitaProgramada(
                    citaDTO.getId_cita(),
                    medicoDTO.getUsuario().getId_usuario(),
                    consulta
            );

            return resultado;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al atender cita programada.", e);
        }
    }

    public boolean atenderCitaEmergencia(ConsultaDTO consultaDTO, MedicoDTO medicoDTO) throws NegocioException {
        if (consultaDTO == null || consultaDTO.getCita() == null) {
            throw new NegocioException("La consulta o la cita asociada no pueden ser nulas.");
        }

        try {

            CitaDTO citaDTO = mapper_cita.toDTO(consultaDTO.getCita());

            Consulta consulta = mapper.toEntity(consultaDTO);

            String folioEmergencia = citaSinCitaDAO.obtenerFolioEmergencia(citaDTO.getId_cita());
            boolean resultado = consultaDAO.atenderCitaEmergencia(
                    citaDTO.getId_cita(),
                    medicoDTO.getUsuario().getId_usuario(),
                    folioEmergencia, consulta
            );

            return resultado;
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al atender cita de emergencia.", e);
        }
    }

    public boolean validarFolio(String folio_emergencia, CitaDTO citaDTO) throws NegocioException {
        try {
            return consultaDAO.validarFolio(citaDTO.getId_cita(), folio_emergencia);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Folio de emergencia no válido");
        }
    }

}
