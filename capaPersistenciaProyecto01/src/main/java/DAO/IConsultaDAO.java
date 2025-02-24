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
    
     public boolean atenderCitaProgramada(int idCita, int idUsuarioMedico, Consulta consulta) throws PersistenciaException;

     public boolean atenderCitaEmergencia(int idCita, int idUsuarioMedico, String folioEmergencia, Consulta consulta) throws PersistenciaException;
    
    public boolean validarFolio(int idCita, String folio) throws PersistenciaException;

}
