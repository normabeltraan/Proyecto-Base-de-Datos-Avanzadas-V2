/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Medico;
import entidades.Cita;
import entidades.Horario;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IMedicoDAO {

    public List<String> obtenerEspecialidades() throws SQLException, PersistenciaException;

    public List<Medico> obtenerMedicosPorEspecialidad(String especialidad) throws PersistenciaException;

    public Medico obtenerMedicoPorId(int idMedico) throws PersistenciaException;
    
    public Medico obtenerPerfilMedico(int idMedico) throws PersistenciaException;
    
    //public List<Cita> consultarAgendaMedico(int idMedico) throws PersistenciaException ;
    
    public Medico obtenerMedicoPorNombreUsuario(String nombreUsuario) throws PersistenciaException;

    public boolean medicoCitasActivas(int idMedico) throws SQLException, PersistenciaException;

    public boolean actualizarEstadoMedico(int idMedico, String nuevoEstado) throws SQLException, PersistenciaException;
    
    //public List<Cita> consultarAgendaMedico(int idMedico) throws PersistenciaException;
    
    public Horario consultarAgendaMedico(int id_medico, Date fecha) throws PersistenciaException;

}
