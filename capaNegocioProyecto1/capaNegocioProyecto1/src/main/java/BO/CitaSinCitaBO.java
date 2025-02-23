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
 *
 * @author Maximiliano
 */
public class CitaSinCitaBO {

    private static final Logger logger = Logger.getLogger(CitaSinCitaBO.class.getName());
    private final ICitaSinCitaDAO citaSinCitaDAO;
    private final CitaSinCitaMapper mapper = new CitaSinCitaMapper();

    public CitaSinCitaBO(IConexionBD conexion) {
        this.citaSinCitaDAO = new CitaSinCitaDAO(conexion);
    }

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
