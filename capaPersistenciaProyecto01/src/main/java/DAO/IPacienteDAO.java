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
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * Esta interfaz define las operaciones que se realizan con los pacientes del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface IPacienteDAO {

    /**
     * Este método actualiza los datos personales de un paciente en la base de datos.
     * @param idUsuario El identificador único del usuario a actualizar.
     * @param nombre El nuevo nombre del paciente.
     * @param apellidoPaterno El nuevo apellido paterno del paciente.
     * @param apellidoMaterno El nuevo apellido materno del paciente.
     * @param telefono El nuevo número de teléfono del paciente.
     * @param fechaNacimiento La nueva fecha de nacimiento del paciente.
     * @param correoElectronico El nuevo correo electrónico del paciente.
     * @return Regresa verdadero si la actualización fue exitosa, falso en caso contrario.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al ejecutar la actualización en la base de datos.
     */
    public boolean actualizarDatosPaciente(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono,
            LocalDate fechaNacimiento, String correoElectronico) throws PersistenciaException;

    /**
     * Este método obtiene un paciente a partir del identificador único del usuario.
     * @param idUsuario El odentificador único del usuario.
     * @return Regresa el objeto paciente si el usuario existe, null si no se encuentra.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al consultar la base de datos.
     */
    public Paciente obtenerPacientePorIdUsuario(int idUsuario) throws PersistenciaException;

    /**
     * Este método obtiene el historial de consultas de un paciente atendido por un médico específico.
     * @param nombrePaciente El nombre completo del paciente.
     * @param nombreMedico El nombre completo del médico.
     * @return Regresa la lista con las consultas del paciente con el médico indicado.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta a la base de datos.
     */
    public List<Consulta> obtenerHistorialConsultasDelPacientePorMedico(String nombrePaciente, String nombreMedico) throws PersistenciaException;

    /**
     * Este método inserta una nueva dirección en la base de datos.
     * @param direccion El objeto con los datos de la dirección a registrar.
     * @return El identificador único de la dirección insertada.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al insertar la dirección en la base de datos.
     */
    public int insertarDireccion(Direccion direccion) throws PersistenciaException;

    /**
     * Este método actualiza la dirección de un usuario en la base de datos.
     * @param direccion El objeto con los nuevos datos de la dirección.
     * @param idUsuario El identificador único del usuario cuya dirección será actualizada.
     * @return Regresa verdadero si la actualización fue exitosa, falso en caso contrario.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la actualización o si el usuario tiene restricciones.
     */
    public boolean actualizarDireccionPorUsuario(Direccion direccion, Integer idUsuario) throws PersistenciaException;

    /**
     * Este método obtiene el identificador único de la dirección de un usuario en la base de datos.
     * @param idUsuario El identificador único del usuario.
     * @return Regresa el identificador único de la dirección si existe, null si el usuario no tiene una dirección asociada.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta.
     */
    public Integer obtenerIdDireccionPorUsuario(Integer idUsuario) throws PersistenciaException;

    /**
     * Este método obtiene un paciente a partir del nombre de usuario del sistema.
     * @param nombreUsuario El nombre de usuario del paciente en el sistema.
     * @return Regresa el objeto con los datos encontrados del paciente.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta o si el usuario no existe.
     */
    public Paciente obtenerPacientePorNombreUsuario(String nombreUsuario) throws PersistenciaException;

    /**
     * Este método verifica si un paciente con un nombre completo determinado existe en la base de datos.
     * @param nombrePaciente El nombre completo del paciente (incluyendo nombre y apellidos).
     * @return Regresa verdadero si el paciente existe, falso en caso contrario.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta.
     */
    public boolean existePaciente(String nombrePaciente) throws PersistenciaException;

    /**
     * Este método obtiene el identificador único de un usuario en la base de datos a partir de su correo electrónico.
     * @param correoElectronico El correo electrónico del usuario.
     * @return El identificador único del usuario si se encuentra.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta.
     */
    public int obtenerIdUsuarioPorCorreo(String correoElectronico) throws PersistenciaException;

    /**
     * Este método obtiene una lista de citas programadas para un paciente específico.
     * @param paciente El objeto que representa al paciente cuyo historial de citas se busca.
     * @return Regresa la lista de con las citas programadas del paciente.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta a la base de datos.
     */
    public List<Cita> obtenerCitasProgramadas(Paciente paciente) throws PersistenciaException;

    /**
     * Este método obtiene el identificador único de un paciente en la base de datos a partir de su nombre y apellidos.
     * @param nombre El nombre del paciente.
     * @param apellidoPaterno El apellido paterno del paciente.
     * @param apellidoMaterno El apellido materno del paciente.
     * @return Regresa el identificador único del usuario si se encuentra en la base de datos.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta.
     */
    public int obtenerIdPacientePorNombre(String nombre, String apellidoPaterno, String apellidoMaterno) throws PersistenciaException;


}
