/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Consulta;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IConsultaDAO {

    public List<Consulta> obtenerHistorialConsultasDelPaciente(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException;
    
    /**
     * Atender una cita programada.
     *
     * @param idCita Identificador de la cita
     * @param idUsuarioMedico Identificador del médico
     * @throws PersistenciaException Si ocurre un error en la base de datos
     */
    public boolean atenderCitaProgramada(int idCita, int idUsuarioMedico) throws PersistenciaException;

    /**
     * Atender una cita de emergencia.
     *
     * @param idCita Identificador de la cita
     * @param idUsuarioMedico Identificador del médico
     * @param folioEmergencia Folio de la cita de emergencia
     * @throws PersistenciaException Si ocurre un error en la base de datos
     */
    public boolean atenderCitaEmergencia(int idCita, int idUsuarioMedico, String folioEmergencia) throws PersistenciaException;

}
