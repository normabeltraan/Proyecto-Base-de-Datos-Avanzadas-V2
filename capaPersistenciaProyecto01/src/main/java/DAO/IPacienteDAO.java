/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Consulta;
import entidades.Direccion;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IPacienteDAO {

    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;

    public boolean eliminarPaciente() throws PersistenciaException;

    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException;

    public Paciente obtenerPacientePorIdUsuario(int idUsuario) throws PersistenciaException;
    
    public List<Consulta> obtenerHistorialConsultas(int id) throws PersistenciaException;
    
    public int insertarDireccion(Direccion direccion) throws PersistenciaException;
    
    public Paciente obtenerPacientePorNombreUsuario(String nombreUsuario) throws PersistenciaException;

}
