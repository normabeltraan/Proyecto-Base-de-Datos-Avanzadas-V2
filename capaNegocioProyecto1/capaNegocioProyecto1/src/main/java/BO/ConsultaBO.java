/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ConsultaDAO;
import DAO.IConsultaDAO;
import DTO.ConsultaDTO;
import DTO.CitaDTO;
import DAO.CitaSinCitaDAO;
import DAO.ICitaSinCitaDAO;
import DTO.MedicoDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.CitaSinCitaMapper;
import Mapper.ConsultaMapper;
import Mapper.MedicoMapper;
import conexion.IConexionBD;
import entidades.Consulta;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase de la capa de negocio que gestiona las consultas médicas.
 * 
 * Se encarga de coordinar la obtención del historial de consultas, 
 * así como el registro y validación de citas médicas programadas y de emergencia.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class ConsultaBO {

    private static final Logger logger = Logger.getLogger(ConsultaBO.class.getName());
    private final IConsultaDAO consultaDAO;
    private final ICitaSinCitaDAO citaSinCitaDAO;
    private final ConsultaMapper mapper = new ConsultaMapper();
    private final CitaMapper mapper_cita = new CitaMapper();
    private final CitaSinCitaMapper mapper_citaSinCita = new CitaSinCitaMapper();
    private final MedicoMapper mapper_medico = new MedicoMapper();

    /**
     * Constructor que inicializa los DAOs de consultas y citas de emergencia con una conexión a la base de datos.
     * @param conexion El objeto de conexión a la base de datos.
     */
    public ConsultaBO(IConexionBD conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
        this.citaSinCitaDAO = new CitaSinCitaDAO(conexion);
    }

    /**
     * Este método obtiene el historial de consultas de un paciente en un rango de fechas, con la opción de filtrar por especialidad.
     * @param nombrePaciente El nombre del paciente para el cual se quiere consultar el historial.
     * @param especialidad La especialidad médica (opcional) para filtrar las consultas.
     * @param fechaInicio La fecha de inicio del periodo de consulta.
     * @param fechaFin La fecha de fin del periodo de consulta.
     * @return Regresa la lista de objetos ConsultaDTO con la información de las consultas realizadas.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al recuperar la información de la base de datos.
     * @throws IllegalArgumentException Lanza una excepción si alguno de los parámetros obligatorios es nulo.
     */
    public List<ConsultaDTO> obtenerHistorialConsultas(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException {
        if (nombrePaciente == null || fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("El nombre del paciente y las fechas de inicio y fin son obligatorios.");
        }

        List<Consulta> consultas = consultaDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente, especialidad, fechaInicio, fechaFin);
        return mapper.toDTOList(consultas);
    }

    /**
     * Este método registra la atención de una cita médica programada.
     * Se valida que la cita exista y se registra la consulta en la base de datos. 
     * @param consultaDTO El objeto ConsultaDTO con la información de la consulta realizada.
     * @param medicoDTO El objeto MedicoDTO con la información del médico que atendió la cita.
     * @return Regresa verdadero si la consulta fue registrada correctamente, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si la consulta o la cita asociada son nulas o si ocurre un error en la persistencia.
     */
    public boolean atenderCitaProgramada(ConsultaDTO consultaDTO, MedicoDTO medicoDTO) throws NegocioException {
        if (consultaDTO == null || consultaDTO.getCita() == null) {
            throw new NegocioException("La consulta o la cita asociada no pueden ser nulas.");
        }

        try {
            CitaDTO citaDTO = mapper_cita.toDTO(consultaDTO.getCita());

            Consulta consulta = mapper.toEntity(consultaDTO);

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

    /**
     * Este método registra la atención de una cita médica de emergencia.
     * Se valida la cita y se registra la consulta, además de obtener el folio de emergencia correspondiente.
     * @param consultaDTO El objeto ConsultaDTO con la información de la consulta realizada.
     * @param medicoDTO El objeto MedicoDTO con la información del médico que atendió la cita.
     * @return Regresa verdadero si la consulta fue registrada correctamente, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si la consulta o la cita asociada son nulas o si ocurre un error en la persistencia.
     */
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

    /**
     * Este método valida si un folio de emergencia es correcto para una cita determinada.
     * @param folio_emergencia El código de folio de emergencia que se desea validar.
     * @param citaDTO El objeto CitaDTO que contiene la cita a validar.
     * @return Regresa verdadero si el folio es válido, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si ocurre un error en la validación del folio.
     */
    public boolean validarFolio(String folio_emergencia, CitaDTO citaDTO) throws NegocioException {
        try {
            return consultaDAO.validarFolio(citaDTO.getId_cita(), folio_emergencia);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Folio de emergencia no válido");
        }
    }

}
