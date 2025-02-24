/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.CitaSinCita;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface ICitaSinCitaDAO {

    public CitaSinCita agendarCitaEmergencia(String especialidad, int idPaciente) throws PersistenciaException;
    
    public String obtenerFolioEmergencia(int id_cita) throws PersistenciaException;
}
