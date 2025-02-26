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
 * Esta interfaz define los métodos que se realizan en la clase de MédicoDAO.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface IMedicoDAO {

    /**
     * El método obtiene la lista de especialidades médicas registradas en el sistema.
     * Consulta la base de datos para recuperar todas las especialidades disponibles en la clínica. 
     * Se utiliza para ofrecer opciones en la selección de médicos por especialidad.
     * @return Regresa la lista de nombres de especialidades médicas disponibles.
     * @throws SQLException Lanza una excepción si ocurre un error al ejecutar la consulta SQL.
     * @throws PersistenciaException Lanza una excepción si se produce un error en la capa de persistencia.
     */
    public List<String> obtenerEspecialidades() throws SQLException, PersistenciaException;

    /**
     * Este método obtiene la lista de médicos que pertenecen a una especialidad específica.
     * Permite consultar los médicos registrados en el sistema que pertenecen a la especialidad indicada.
     * @param especialidad El nombre de la especialidad médica por la que se desea filtrar.
     * @return La lista de médicos que pertenecen a la especialidad especificada.
     * @throws PersistenciaException Lanza una excepción si ocurre un error durante la operación.
     */
    public List<Medico> obtenerMedicosPorEspecialidad(String especialidad) throws PersistenciaException;

    /**
     * Este método obtiene la información de un médico a partir de su identificador único.
     * Recupera todos los datos asociados a un médico específico registrado en el sistema.
     * @param idMedico El identificador único del médico a consultar.
     * @return Regresa un objeto con la información del médico encontrado.
     * @throws PersistenciaException Lanza una excepcióm si el médico no existe o hay un error en la consulta.
     */
    public Medico obtenerMedicoPorId(int idMedico) throws PersistenciaException;
    
    /**
     * Este método obtiene el perfil detallado de un médico a partir de su identificador.
     * Recupera información completa sobre un médico, 
     * incluyendo su especialidad, datos personales y estado actual en el sistema.
     * @param idMedico El identificador único del médico a consultar.
     * @return Regresa el objeto con la información detallada del médico.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta a la base de datos.
     */
    public Medico obtenerPerfilMedico(int idMedico) throws PersistenciaException;
    
    /**
     * Este método obtiene la información de un médico a partir de su nombre de usuario.
     * Permite consultar los datos de un médico mediante su nombre de usuario en el sistema,
     * facilitando la autenticación y recuperación de información.
     * @param nombreUsuario El nombre de usuario del médico registrado en el sistema.
     * @return Regresa el objeto con la información del médico encontrado.
     * @throws PersistenciaException Lanza una excepción si el usuario no existe o hay un error en la consulta.
     */
    public Medico obtenerMedicoPorNombreUsuario(String nombreUsuario) throws PersistenciaException;

    /**
     * Este método verifica si un médico tiene citas activas programadas en el sistema.
     * Consulta la base de datos para determinar si el médico tiene al menos una cita con estado 'Activa'.
     * @param idMedico El identificador único del médico a consultar.
     * @return Regresa verdadero si el médico tiene citas activas, falso en caso contrario.
     * @throws SQLException Lanza una excepción si ocurre un error en la ejecución de la consulta SQL.
     * @throws PersistenciaException Lanza una excepción si hay un problema con la capa de persistencia.
     */
    public boolean medicoCitasActivas(int idMedico) throws SQLException, PersistenciaException;

    /**
     * Este método actualiza el estado de un médico en la base de datos.
     * Permite cambiar el estado de un médico en el sistema, reflejando cambios en su disponibilidad o situación laboral.
     * @param idMedico El identificador único del médico cuyo estado se actualizará.
     * @param nuevoEstado El nuevo estado que se asignará al médico.
     * @return Regresa verdadero si la actualización fue exitosa, falso en caso contrario.
     * @throws SQLException Lanza una excepción si ocurre un error al ejecutar la consulta SQL.
     * @throws PersistenciaException Lanza una excepción si hay un problema al actualizar la base de datos.
     */
    public boolean actualizarEstadoMedico(int idMedico, String nuevoEstado) throws SQLException, PersistenciaException;
    
    /**
     * Este método consulta la agenda diaria de un médico, obteniendo las citas activas del día.
     * Recupera todas las citas programadas para el día actual del médico especificado.
     * @param idMedico El identificador único del médico cuya agenda se consultará.
     * @return Regresa la lista con las citas activas del día.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta a la base de datos.
     */
    public List<Cita> consultarAgendaMedico(int idMedico) throws PersistenciaException;

}
