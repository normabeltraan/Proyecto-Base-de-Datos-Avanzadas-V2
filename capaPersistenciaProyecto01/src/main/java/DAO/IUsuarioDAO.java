/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface IUsuarioDAO {

    public boolean registrarUsuarioPaciente(Usuario usuario, Paciente paciente) throws PersistenciaException;

    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException;
    
    public boolean comprobarExistenciaNombreUsuario(String nombreUsuario) throws PersistenciaException;
    
    public boolean comprobarExistenciaCorreoElectronico(String correoElectronico) throws PersistenciaException;
    
    public String obtenerTipoUsuario(String nombreUsuario) throws PersistenciaException;
}
