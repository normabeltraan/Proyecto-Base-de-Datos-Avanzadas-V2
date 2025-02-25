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
 * Esta interfaz define las operaciones que se realizan en las consultas médicas.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface IConsultaDAO {

    /**
     * En este método se obtiene el historial de consultas del paciente.
     * @param nombrePaciente Nombre completo del paciente.
     * @param especialidad La especialidad del médico.
     * @param fechaInicio La fecha de inicio de la consulta.
     * @param fechaFin La fecha de fin de la consulta.
     * @return Regresa la lista del historial de consultas del paciente.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public List<Consulta> obtenerHistorialConsultasDelPaciente(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException;
    
    /**
     * Este método atiende una cita programada registrando la consulta correspondiente.
     * @param idCita El identificador único de la cita.
     * @param idUsuarioMedico El identificador único del médico que atiende la cita.
     * @param consulta El objeto que contiene la información de la consulta con los detalles de la consulta realizada.
     * @return Se regresa verdadero si la consulta fue registrada correctamente, falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean atenderCitaProgramada(int idCita, int idUsuarioMedico, Consulta consulta) throws PersistenciaException;

    /**
     * Este método atiende una cita de emergencia registrando la consulta correspondiente.
     * @param idCita El identificador único de la cita.
     * @param idUsuarioMedico El identificador único del médico que atiende la cita.
     * @param consulta El objeto que contiene la información de la consulta con los detalles de la consulta realizada.
     * @return Se regresa verdadero si la consulta fue registrada correctamente, falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean atenderCitaEmergencia(int idCita, int idUsuarioMedico, String folioEmergencia, Consulta consulta) throws PersistenciaException;
    
    /**
     * Este método valida si un folio de emergencia existe y corresponde a una cita dada.
     * @param idCita El identificador único de la cita.
     * @param folio El folio de emergencia de la cita de emergencia.
     * @return Regresa verdadero si el folio es válido, falso si es lo contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean validarFolio(int idCita, String folio) throws PersistenciaException;

}
