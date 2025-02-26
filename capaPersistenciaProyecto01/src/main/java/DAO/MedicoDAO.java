/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Medico;
import entidades.Paciente;
import entidades.Cita;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Timestamp;
import java.util.logging.Level;

/**
 * Esta clase implementa la interfaz IMedicoDAO se encarga de gestionar a los
 * médicos y/o otras operaciones relacionados al médico
 *
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class MedicoDAO implements IMedicoDAO {

    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión de la base de datos
     *
     * @param conexion La conexión con la base de datos.
     */
    public MedicoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(MedicoDAO.class.getName());

    /**
     * Este método se encarga de obtener la lista de especialidades del médico
     * disponibles en la base de datos.
     *
     * @return Regresa la lista de especialidades médicas activas.
     * @throws SQLException Lanzará una excepción si ocurre un error durante la
     * consulta de sql.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public List<String> obtenerEspecialidades() throws SQLException, PersistenciaException {
        List<String> especialidades = new ArrayList<>();
        String consultaSql = "SELECT DISTINCT especialidad FROM MEDICOS WHERE estado = 'Activo'";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                especialidades.add(rs.getString("especialidad"));
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener especialidades: " + e.getMessage());
            throw new PersistenciaException("Error al obtener especialidades", e);
        }
        return especialidades;
    }

    /**
     * Este método obtiene la lista de médicos según sus especialidades médicas
     * disponibles.
     *
     * @param especialidad La especialidad del médico.
     * @return Regresa la lista de médicos según sus especialidades.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public List<Medico> obtenerMedicosPorEspecialidad(String especialidad) throws PersistenciaException {
        List<Medico> medicos = new ArrayList<>();

        String consultaSql = "SELECT M.*, U.id_usuario, U.nombre_usuario "
                + "FROM MEDICOS M "
                + "JOIN USUARIOS U ON M.id_usuario = U.id_usuario "
                + "WHERE M.especialidad = ? AND M.estado = 'Activo'";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSql)) {

            ps.setString(1, especialidad);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));

                    Medico medico = new Medico();
                    medico.setUsuario(usuario);
                    medico.setNombre(rs.getString("nombre"));
                    medico.setApellido_paterno(rs.getString("apellido_paterno"));
                    medico.setApellido_materno(rs.getString("apellido_materno"));
                    medico.setEstado(rs.getString("estado"));
                    medico.setEspecialidad(rs.getString("especialidad"));
                    medico.setCedula(rs.getString("cedula"));

                    medicos.add(medico);
                }

            }
        } catch (SQLException e) {
            logger.severe("Error al obtener médicos: " + e.getMessage());
            throw new PersistenciaException("Error al obtener médicos", e);
        }
        return medicos;
    }

    /**
     * Este método se encarga de encontrar a un médico por medio de su
     * identificación. Y poder brindar todos sus datos al ser accedido.
     *
     * @param id_medico El identificador único del médico.
     * @return Regresa los datos del médico para ser utilizado en alguna
     * operación, o consulta necesaria.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public Medico obtenerMedicoPorId(int id_medico) throws PersistenciaException {
        String consultaSQL = "SELECT m.id_usuario, m.nombre, m.apellido_paterno, m.apellido_materno, "
                + "m.estado, m.especialidad, m.cedula, u.nombre_usuario, u.contrasenia "
                + "FROM MEDICOS m "
                + "JOIN USUARIOS u ON m.id_usuario = u.id_usuario "
                + "WHERE m.id_usuario = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, id_medico);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellido_paterno");
                String apellidoMaterno = rs.getString("apellido_materno");
                String estado = rs.getString("estado");
                String especialidad = rs.getString("especialidad");
                String cedula = rs.getString("cedula");

                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));

                return new Medico(usuario, nombre, apellidoPaterno, apellidoMaterno, estado, especialidad, cedula, null, null);
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener médicos: " + e.getMessage());
            throw new PersistenciaException("Error al obtener médicos", e);
        }

        return null;
    }

    /**
     * Este método obtiene el perfil de un médico con ayuda de su ID, para poder
     * mostrar todos sus datos principales.
     *
     * @param idMedico El identificador único del médico.
     * @return Regresa si es verdadero el perfil completo del médico desde su
     * nombre completo, especialidad, etc.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public Medico obtenerPerfilMedico(int idMedico) throws PersistenciaException {
        String consultaSQL = "SELECT m.id_usuario, m.nombre, m.apellido_paterno, m.apellido_materno, "
                + "m.estado, m.especialidad, m.cedula, u.nombre_usuario, u.contrasenia "
                + "FROM MEDICOS m "
                + "JOIN USUARIOS u ON m.id_usuario = u.id_usuario "
                + "WHERE m.id_usuario = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idMedico);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String apellidoPaterno = rs.getString("apellido_paterno");
                    String apellidoMaterno = rs.getString("apellido_materno");
                    String estado = rs.getString("estado");
                    String especialidad = rs.getString("especialidad");
                    String cedula = rs.getString("cedula");

                    Usuario usuario = new Usuario();
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setContrasenia(rs.getString("contrasenia"));

                    return new Medico(usuario, nombre, apellidoPaterno, apellidoMaterno, estado, especialidad, cedula, null, null);
                }

            } catch (SQLException e) {
                logger.severe("Error al obtener el perfil del médico: " + e.getMessage());
                throw new PersistenciaException("Error al obtener el perfil del médico", e);
            }

        } catch (SQLException e) {
            logger.severe("Error al obtener el perfil del médico: " + e.getMessage());
            throw new PersistenciaException("Error al obtener el perfil del médico", e);
        }

        return null;
    }

    /**
     * Este método se utiliza para obtener el médico buscando por su nombre de
     * usuario.
     *
     * @param nombreUsuario El nombre de usuario del médico.
     * @return Regresa un médico si encontró el nombre de usuario, sino entonces
     * lanzará un mensaje.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public Medico obtenerMedicoPorNombreUsuario(String nombreUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT m.id_usuario, m.nombre, m.apellido_paterno, m.apellido_materno, m.estado, "
                + "m.especialidad, m.cedula "
                + "FROM MEDICOS m "
                + "JOIN USUARIOS u ON m.id_usuario = u.id_usuario "
                + "WHERE u.nombre_usuario = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, nombreUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Medico medico = new Medico();
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                medico.setUsuario(usuario);

                medico.setNombre(rs.getString("nombre"));
                medico.setApellido_paterno(rs.getString("apellido_paterno"));
                medico.setApellido_materno(rs.getString("apellido_materno"));
                medico.setEstado(rs.getString("estado"));
                medico.setEspecialidad(rs.getString("especialidad"));
                medico.setCedula(rs.getString("cedula"));

                return medico;
            } else {
                throw new PersistenciaException("Médico no encontrado para el nombre de usuario: " + nombreUsuario);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el médico: " + e.getMessage(), e);
        }
    }

    /**
     * Este método verifica si un médico tiene citas activas en el sistema. en
     * estado "Activa" asociadas a un médico en particular. Se utiliza una
     * consulta SQL para contar el número de citas activas del médico con el
     * identificador proporcionado.
     *
     * @param idMedico El identificador único del médico cuya agenda se desea
     * verificar.
     * @return Regresamos verdadero si el médico tiene al menos una cita activa,
     * falso en caso contrario.
     * @throws SQLException Lanza una excepción si ocurre un error en la
     * ejecución de la consulta SQL.
     * @throws PersistenciaException Lanza un error si se produce un error al
     * acceder a la capa de persistencia.
     */
    @Override
    public boolean medicoCitasActivas(int idMedico) throws SQLException, PersistenciaException {
        String consultaSQL = "SELECT COUNT(*) FROM CITAS WHERE id_usuario_medico = ? AND estado = 'Activa'";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setInt(1, idMedico);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar citas activas", e);
        }
        return false;
    }

    /**
     * En este método se actualiza el estado de un médico en la base de datos.
     * Permite modificar el estado de un médico identificado por su ID en la
     * tabla de médicos. Se utiliza para reflejar cambios en la disponibilidad o
     * situación del médico dentro del sistema.
     *
     * @param idMedico El identificador único del médico cuyo estado se
     * actualizará.
     * @param nuevoEstado El nuevo estado que se asignará al médico.
     * @return Regresamos verdadero si la actualización fue exitosa, falso en
     * caso contrario.
     * @throws SQLException Lanza una excepción si ocurre un error durante la
     * ejecución de la consulta SQL.
     * @throws PersistenciaException Lanza una excepción si se produce un error
     * al acceder a la capa de persistencia.
     */
    @Override
    public boolean actualizarEstadoMedico(int idMedico, String nuevoEstado) throws SQLException, PersistenciaException {
        String consultaSQL = "UPDATE MEDICOS SET estado = ? WHERE id_usuario = ?";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idMedico);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new PersistenciaException("Error al actualizar el estado del medico", e);
        }
    }

    /**
     * Este método consulta la agenda diaria de un médico, obteniendo las citas
     * activas del día. Recupera todas las citas programadas para el día actual
     * de un médico en particular. Devuelve una lista con información detallada
     * de cada cita.
     *
     * @param id_medico El identificador único del médico cuya agenda se
     * consultará.
     * @return La lista de las citas activas del día.
     * @throws PersistenciaException Lanza una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public List<Cita> consultarAgendaMedico(int id_medico) throws PersistenciaException {
        List<Cita> citasDia = new ArrayList<>();
        String consultaSQL = "SELECT c.id_cita, DATE_FORMAT(c.fecha_hora, '%H:%i') AS hora,  "
                + "CONCAT(p.nombre, ' ', p.apellido_paterno, IFNULL(CONCAT(' ', p.apellido_materno),'')) AS paciente, "
                + "c.tipo "
                + "FROM CITAS c JOIN PACIENTES p ON c.id_usuario_paciente = p.id_usuario "
                + "WHERE c.id_usuario_medico = ? AND DATE(c.fecha_hora) = CURDATE() "
                + "AND c.estado = 'Activa' "
                + "ORDER BY c.fecha_hora";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, id_medico);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cita cita = new Cita();

                    cita.setId_cita(rs.getInt("id_cita"));

                    String horaStr = rs.getString("hora");
                    Timestamp horaTimestamp = Timestamp.valueOf("2025-02-23 " + horaStr + ":00");

                    cita.setFecha_hora(horaTimestamp);

                    Paciente paciente = new Paciente();
                    paciente.setNombre(rs.getString("paciente"));
                    cita.setPaciente(paciente);

                    String tipoCita = rs.getString("tipo");
                    cita.setTipo(tipoCita);

                    citasDia.add(cita);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al consultar la agenda del médico", ex);
            throw new PersistenciaException("Error al consultar la agenda del médico.", ex);
        }
        return citasDia;
    }

}
