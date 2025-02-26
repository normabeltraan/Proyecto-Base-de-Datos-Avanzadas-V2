/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.CitaSinCita;
import excepciones.PersistenciaException;

/**
 * Esta interfaz define los métodos para gestionar las citas de emergencia.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface ICitaSinCitaDAO {

    /**
     * Este método agenda una cita de emergencia en el sistema.
     * @param especialidad La especialidad del médico.
     * @param idPaciente La identificación única del paciente.
     * @return Regresa un objeto con los detalles de la cita de emergencia agendada.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public CitaSinCita agendarCitaEmergencia(String especialidad, int idPaciente) throws PersistenciaException;
    
    /**
     * En este método se obtiene el folio de emergencia de la cita de emergencia.
     * @param id_cita El identificador único de la cita.
     * @return Regresa una cadena con el folio de emergencia.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public String obtenerFolioEmergencia(int id_cita) throws PersistenciaException;
}
