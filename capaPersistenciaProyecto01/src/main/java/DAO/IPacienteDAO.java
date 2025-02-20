/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Paciente;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface IPacienteDAO {

    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;

    public boolean eliminarPaciente() throws PersistenciaException;

    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException;

    public Paciente obtenerPacientePorUsuario(int idUsuario) throws PersistenciaException;

}
