/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;

/**
 * Esta interfaz define las operaciones que se realizan con los usaurios del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface IUsuarioDAO {

    /**
     * Registra un nuevo usuario paciente en el sistema.
     * @param usuario El objeto que contiene toda la información del usuario a registrar.
     * @param paciente El objeto que contiene toda la información del paciente asociado al usuario.
     * @return Se regresa verdadero si el registro fue exitoso, falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean registrarUsuarioPaciente(Usuario usuario, Paciente paciente) throws PersistenciaException;

    /**
     * Verifica las credenciales de un usuario para permitir su acceso al sistema.
     * @param usuario El objeto usuario con los datos de autenticación.
     * @return Se regresa verdadero si las credenciales son válidas, falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException;
    
    /**
     * Este método comprueba si un nombre de usuario ya está registrado en el sistema.
     * 
     * @param nombreUsuario El nombre de usuario a verificar.
     * @return Regresa verdadero si el nombre de usuario ya existe, falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la consulta.
     */
    public boolean comprobarExistenciaNombreUsuario(String nombreUsuario) throws PersistenciaException;
    
    /**
     * Este método comprueba si un correo electrónico ya está registrado en el sistema.
     * 
     * @param correoElectronico El correo electrónico a verificar.
     * @return Regresa verdadero si el correo ya está registrado, falso en caso contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la consulta.
     */
    public boolean comprobarExistenciaCorreoElectronico(String correoElectronico) throws PersistenciaException;
    
     /**
     * Este método obtiene el tipo de usuario (paciente o médico) a partir de su nombre de usuario.
     * 
     * @param nombreUsuario El nombre de usuario del cual se quiere obtener el tipo.
     * @return La cadena que representa el tipo de usuario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la consulta.
     */
    public String obtenerTipoUsuario(String nombreUsuario) throws PersistenciaException;
    
    
}
