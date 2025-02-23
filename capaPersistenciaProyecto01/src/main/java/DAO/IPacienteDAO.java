/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Cita;
import entidades.Consulta;
import entidades.Direccion;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IPacienteDAO {

    public boolean actualizarDatosPaciente(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono,
            LocalDate fechaNacimiento, String correoElectronico) throws PersistenciaException;

    public Paciente obtenerPacientePorIdUsuario(int idUsuario) throws PersistenciaException;

    public List<Consulta> obtenerHistorialConsultasDelPaciente(String nombrePaciente) throws PersistenciaException;
    
    public int insertarDireccion(Direccion direccion) throws PersistenciaException;
    
    public boolean actualizarDireccionPorUsuario(Direccion direccion, Integer idUsuario) throws PersistenciaException;
    
    public Integer obtenerIdDireccionPorUsuario(Integer idUsuario) throws PersistenciaException;
    
     public Paciente obtenerPacientePorNombreUsuario(String nombreUsuario) throws PersistenciaException;
     
     public boolean existePaciente(String nombrePaciente) throws PersistenciaException;
     
     public int obtenerIdUsuarioPorCorreo(String correoElectronico) throws PersistenciaException;    

    public List<Cita> obtenerCitasProgramadas(Paciente paciente) throws PersistenciaException;

}
