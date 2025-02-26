/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.CitaSinCitaDAO;
import DAO.ICitaSinCitaDAO;
import DTO.CitaSinCitaDTO;
import Exception.NegocioException;
import Mapper.CitaSinCitaMapper;
import conexion.IConexionBD;
import entidades.CitaSinCita;
import excepciones.PersistenciaException;
import java.util.logging.Logger;

/**
 * Clase de la capa de negocio que gestiona las citas de emergencia ("sin cita previa").
 * 
 * Se encarga de coordinar la asignación de médicos disponibles para atender a pacientes sin cita,
 * asegurando que los datos sean válidos antes de delegar la operación a la capa de persistencia.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaSinCitaBO {

    private static final Logger logger = Logger.getLogger(CitaSinCitaBO.class.getName());
    private final ICitaSinCitaDAO citaSinCitaDAO;
    private final CitaSinCitaMapper mapper = new CitaSinCitaMapper();

    /**
     * Constructor que inicializa el acceso a datos de citas de emergencia con una conexión a la base de datos.
     * @param conexion El objeto de conexión a la base de datos.
     */
    public CitaSinCitaBO(IConexionBD conexion) {
        this.citaSinCitaDAO = new CitaSinCitaDAO(conexion);
    }

    /**
     * Este método agenda una cita de emergencia asignando un médico disponible de la especialidad solicitada.
     * Se validan los datos de entrada y, si hay disponibilidad, se genera una cita de emergencia.
     * 
     * @param especialidad La especialidad médica requerida para la cita de emergencia.
     * @param id_paciente El identificador del paciente que solicita la cita de emergencia.
     * @return El objeto CitaSinCitaDTO con la información de la cita generada.
     * @throws NegocioException Lanza una excepción si ocurre un problema relacionado con las reglas de negocio.
     * @throws PersistenciaException Lanza una excepción si no se puede generar la cita por problemas en la capa de persistencia.
     * @throws IllegalArgumentException Lanza una excepción si los datos de entrada son inválidos.
     */
    public CitaSinCitaDTO agendarCitaEmergencia(String especialidad, int id_paciente) throws NegocioException, PersistenciaException {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía");
        }
        if (id_paciente <= 0) {
            throw new IllegalArgumentException("El ID del paciente no es válido");
        }

        CitaSinCita citaSinCita = citaSinCitaDAO.agendarCitaEmergencia(especialidad, id_paciente);

        if (citaSinCita == null) {
            throw new PersistenciaException("No se pudo generar la cita de emergencia");
        }

        CitaSinCitaDTO citaSinCitaDTO = new CitaSinCitaMapper().toDTO(citaSinCita);

        return citaSinCitaDTO;
    }
}
