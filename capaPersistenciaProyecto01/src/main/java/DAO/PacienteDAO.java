/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Cita;
import entidades.Consulta;
import entidades.Direccion;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase implementa la interfaz IPacienteDAO se encarga de gestionar a los pacientes y otras operaciones.
 * que involucren a la dirección.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class PacienteDAO implements IPacienteDAO {

    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión con la base de datos.
     * @param conexion La conexión de la base de datos.
     */
    public PacienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    /**
     * Este método bbtiene un paciente asociado a un usuario a partir del ID de usuario proporcionado.
     * Realiza una consulta SQL a la base de datos para recuperar la información del paciente y su usuario correspondiente. 
     * Si se encuentra al paciente, se retorna como un objeto Paciente, con todos los datos de usuario y dirección. 
     * Si no se encuentra o ocurre algún error en la consulta, se maneja la excepción y se devuelve null.
     * @param idUsuario El identificador único del usuario asociado al paciente que se quiere obtener.
     * @return Regresa un objeto Paciente con todos los detalles del paciente, o null si no se encuentra.
     * @throws PersistenciaException Lanza una excepción si ocurre un error durante la operación.
     */
    @Override
    public Paciente obtenerPacientePorIdUsuario(int idUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT P.*, U.nombre_usuario "
                + "FROM PACIENTES P "
                + "JOIN USUARIOS U ON P.id_usuario = U.id_usuario "
                + "WHERE P.id_usuario = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre_usuario"),
                        ""
                );

                return new Paciente(
                        usuario,
                        rs.getString("nombre"),
                        rs.getString("apellido_paterno"),
                        rs.getString("apellido_materno"),
                        rs.getString("telefono"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("correo"),
                        new Direccion(
                                rs.getString("colonia"),
                                rs.getString("ciudad"),
                                rs.getString("calle")
                        ),
                        new ArrayList<>() // Lista para citas
                );
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el paciente por usuario", e);
        }

        return null;
    }

    /**
     * Este método obtiene el historial de consultas médicas de un paciente con un médico específico.
     * Realiza una consulta a la base de datos para recuperar todas las consultas realizadas entre un paciente y un médico en particular. 
     * La información obtenida incluye los detalles del paciente, médico, cita y la consulta médica, como diagnóstico y tratamiento.
     * @param nombrePaciente El nombre completo del paciente (nombre, apellido paterno y apellido materno opcional).
     * @param nombreMedico El nombre completo del médico (nombre, apellido paterno y apellido materno opcional).
     * @return Regresa una lista de consultas del historial de consultas del paciente con el médico.
     * Si no hay consultas registradas, la lista se devuelve vacía.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al realizar la consulta a la base de datos.
     */
    @Override
    public List<Consulta> obtenerHistorialConsultasDelPacientePorMedico(String nombrePaciente, String nombreMedico) throws PersistenciaException {

        List<Consulta> consultasP = new ArrayList<>();

        String consultaSQL
                = "SELECT "
                + "CONCAT(P.NOMBRE, ' ', P.APELLIDO_PATERNO, ' ', IFNULL(P.APELLIDO_MATERNO, '')) AS nombre_completo_paciente, "
                + "MED.ESPECIALIDAD, "
                + "MED.NOMBRE AS nombre_medico, "
                + "MED.APELLIDO_PATERNO AS apellido_paterno_medico, "
                + "IFNULL(MED.APELLIDO_MATERNO, '') AS apellido_materno_medico, "
                + "CI.FECHA_HORA, "
                + "CONS.DIAGNOSTICO, "
                + "CONS.TRATAMIENTO, "
                + "CI.ESTADO, "
                + "CI.TIPO "
                + "FROM CONSULTAS CONS "
                + "JOIN CITAS CI ON CI.ID_CITA = CONS.ID_CITA "
                + "JOIN PACIENTES P ON CI.ID_USUARIO_PACIENTE = P.ID_USUARIO "
                + "JOIN MEDICOS MED ON CI.ID_USUARIO_MEDICO = MED.ID_USUARIO "
                + "WHERE CONCAT(P.NOMBRE, ' ', P.APELLIDO_PATERNO, ' ', IFNULL(P.APELLIDO_MATERNO, '')) = ? "
                + "AND CONCAT(MED.NOMBRE, ' ', MED.APELLIDO_PATERNO, ' ', IFNULL(MED.APELLIDO_MATERNO, '')) = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nombrePaciente);
            ps.setString(2, nombreMedico);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Usuario usuarioPaciente = new Usuario();
                    Medico medico = new Medico();
                    medico.setNombre(rs.getString("nombre_medico"));
                    medico.setApellido_paterno(rs.getString("apellido_paterno_medico"));
                    medico.setEspecialidad(rs.getString("especialidad"));

                    Paciente paciente = new Paciente();
                    paciente.setUsuario(usuarioPaciente);

                    Cita cita = new Cita();
                    cita.setEstado(rs.getString("estado"));
                    cita.setFecha_hora(rs.getTimestamp("fecha_hora"));
                    cita.setTipo(rs.getString("tipo"));
                    cita.setMedico(medico);
                    cita.setPaciente(paciente);

                    Consulta consulta = new Consulta();
                    consulta.setCita(cita);
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setTratamiento(rs.getString("tratamiento"));

                    consultasP.add(consulta);
                }

            }

        } catch (SQLException ex) {
            logger.severe("Error al obtener historial consultas: " + ex.getMessage());
            throw new PersistenciaException("Error al obtener el historial de consultas", ex);
        }
        return consultasP;
    }

    /**
     * Este método obtiene la lista de citas programadas para un paciente específico.
     * Consulta la base de datos para recuperar todas las citas activas asociadas a un paciente, 
     * junto con la información del médico correspondiente.
     * Las citas se ordenan en orden ascendente según la fecha y hora de la cita.
     * @param paciente El objeto que representa al paciente del cual se desean obtener las citas programadas.
     * @return Regresa una lista de las citas activas del paciente. Si el paciente no tiene citas activas, la lista se devuelve vacía.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al acceder a la base de datos.
     */
    @Override
    public List<Cita> obtenerCitasProgramadas(Paciente paciente) throws PersistenciaException {
        List<Cita> citasProgramadas = new ArrayList<>();

        String consultaSQL
                = "SELECT ci.id_cita, ci.fecha_hora, ci.estado, me.especialidad, "
                + "CONCAT(me.nombre, ' ', me.apellido_paterno, ' ', IFNULL(me.apellido_materno, '')) as nombre_completo_medico "
                + "FROM CITAS ci "
                + "JOIN MEDICOS me on ci.id_usuario_medico = me.id_usuario "
                + "WHERE ci.id_usuario_paciente = ? "
                + "AND ci.estado = 'ACTIVA' "
                + "ORDER BY ci.fecha_hora asc";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, paciente.getUsuario().getId_usuario());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Medico medico = new Medico();
                    medico.setNombre(rs.getString("nombre_completo_medico"));
                    medico.setEspecialidad(rs.getString("especialidad"));

                    Cita cita = new Cita();
                    cita.setId_cita(rs.getInt("id_cita"));
                    cita.setFecha_hora(rs.getTimestamp("fecha_hora"));
                    cita.setEstado(rs.getString("estado"));
                    cita.setMedico(medico);
                    cita.setPaciente(paciente);

                    citasProgramadas.add(cita);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al obtener citas programadas.");
        }

        return citasProgramadas;
    }

    /**
     * Este método inserta una nueva dirección en la base de datos y devuelve el ID generado.
     * @param direccion El objeto que contiene la información de la dirección a insertar.
     * @return Regresa el identificador único de la dirección insertada si la operación fue exitosa, o -1 si no se generó el identificador.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al acceder a la base de datos.
     */
    @Override
    public int insertarDireccion(Direccion direccion) throws PersistenciaException {
        String consultaSQL = "INSERT INTO direcciones (colonia, ciudad, calle) VALUES (?, ?, ?)";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, direccion.getColonia());
            ps.setString(2, direccion.getCiudad());
            ps.setString(3, direccion.getCalle());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }

            return -1;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al insertar la dirección", e);
            throw new PersistenciaException("Error al insertar la dirección", e);
        }
    }

    /**
     * Este método actualiza la dirección de un usuario en la base de datos, siempre que no tenga citas activas.
     * @param direccion El objeto con los nuevos datos de la dirección.
     * @param idUsuario El identificador único del usuario cuya dirección se actualizará.
     * @return Regresa verdadero si la dirección se actualizó correctamente, falso en caso contrario.
     * @throws PersistenciaException Lanza una excepción si el usuario tiene citas activas, no se encuentra su dirección, o ocurre un error de base de datos.
     */
    @Override
    public boolean actualizarDireccionPorUsuario(Direccion direccion, Integer idUsuario) throws PersistenciaException {
        String consultacitasSQL = "SELECT COUNT(*) FROM CITAS WHERE id_usuario_paciente = ? AND estado = 'Activa'";
        try (Connection con = this.conexion.crearConexion(); PreparedStatement psCitas = con.prepareStatement(consultacitasSQL)) {

            psCitas.setInt(1, idUsuario);
            ResultSet rs = psCitas.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                throw new PersistenciaException("El paciente tiene citas activas y no puede actualizar sus datos.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al verificar citas activas", e);
            throw new PersistenciaException("Error al verificar citas activas", e);
        }

        Integer idDireccion = obtenerIdDireccionPorUsuario(idUsuario);
        if (idDireccion == null) {
            throw new PersistenciaException("No se encontró dirección asociada al usuario.");
        }

        String consultaSQL = "UPDATE direcciones SET colonia = ?, ciudad = ?, calle = ? WHERE id_direccion = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, direccion.getColonia());
            ps.setString(2, direccion.getCiudad());
            ps.setString(3, direccion.getCalle());
            ps.setInt(4, idDireccion);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al actualizar la dirección", e);
            throw new PersistenciaException("Error al actualizar la dirección", e);
        }
    }

    /**
     * Este método obtiene el ID de la dirección asociada a un usuario en la base de datos.
     * Consulta la tabla pacientes para recuperar el identificador de la dirección.
     * @param idUsuario El identificador único del usuario cuya dirección se desea obtener.
     * @return Regresa el identificador único de la dirección asociada al usuario o null si no se encuentra.
     * @throws PersistenciaException Lanza una excepción si ocurre un error al acceder a la base de datos.
     */
    @Override
    public Integer obtenerIdDireccionPorUsuario(Integer idUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT id_direccion FROM pacientes WHERE id_usuario = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setInt(1, idUsuario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_direccion");
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener el id_direccion por id_usuario", e);
            throw new PersistenciaException("Error al obtener el id_direccion", e);
        }
    }

    /**
     * Este método obtiene un paciente a partir del nombre de usuario.
     * @param nombreUsuario El nombre de usuario del paciente a buscar.
     * @return El objeto con la información del paciente recuperada.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta o si no se encuentra el paciente.
     */
    @Override
    public Paciente obtenerPacientePorNombreUsuario(String nombreUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT p.id_usuario, p.nombre, p.apellido_paterno, p.apellido_materno, p.telefono, "
                + "p.fecha_nacimiento, p.correo_electronico, p.id_direccion, "
                + "d.colonia, d.ciudad, d.calle "
                + "FROM PACIENTES p "
                + "JOIN USUARIOS u ON p.id_usuario = u.id_usuario "
                + "JOIN DIRECCIONES d ON p.id_direccion = d.id_direccion "
                + "WHERE u.nombre_usuario = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, nombreUsuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Paciente paciente = new Paciente();
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                paciente.setUsuario(usuario);

                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido_paterno(rs.getString("apellido_paterno"));
                paciente.setApellido_materno(rs.getString("apellido_materno"));
                paciente.setCorreo_electronico(rs.getString("correo_electronico"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());

                Direccion direccion = new Direccion();
                direccion.setId_direccion(rs.getInt("id_direccion"));
                direccion.setColonia(rs.getString("colonia"));
                direccion.setCiudad(rs.getString("ciudad"));
                direccion.setCalle(rs.getString("calle"));

                paciente.setDireccion(direccion);

                return paciente;
            } else {
                throw new PersistenciaException("Paciente no encontrado para el nombre de usuario: " + nombreUsuario);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el paciente: " + e.getMessage(), e);
        }
    }

    /**
     * Este método verifica si existe un paciente en la base de datos a partir de su nombre completo.
     * Realiza una consulta en la tabla pacientes para determinar si hay un paciente registrado con el nombre completo proporcionado.
     * @param nombrePaciente El nombre completo del paciente en formato "Nombre ApellidoPaterno ApellidoMaterno".
     * @return Regresa verdadero si el paciente existe en la base de datos, falso en caso contrario.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta a la base de datos.
     */
    @Override
    public boolean existePaciente(String nombrePaciente) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM PACIENTES WHERE "
                + "CONCAT(NOMBRE, ' ', APELLIDO_PATERNO, ' ', APELLIDO_MATERNO) = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nombrePaciente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al verificar la existencia del paciente.", ex);
        }

        return false;
    }

    /**
     * Este método obtiene el identificador único de un usuario a partir de su correo electrónico.
     * Consulta la tabla usuario para recuperar el identificador único del usuario.
     * correspondiente al correo electrónico proporcionado.
     * @param correoElectronico El correo electrónico del usuario a buscar.
     * @return Regresa el identificador del usuario si existe en la base de datos, y -1 si no se encuentra.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la consulta a la base de datos.
     */
    @Override
    public int obtenerIdUsuarioPorCorreo(String correoElectronico) throws PersistenciaException {
        String consultaSQL = "SELECT id_usuario FROM Usuario WHERE correo_electronico = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, correoElectronico);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_usuario");
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el id del usuario por correo electrónico.", e);
        }
        return -1;
    }

    /**
     * Este método actualiza los datos personales de un paciente en la base de datos.
     * Invoca el procedimiento almacenado {@code ACTUALIZAR_DATOS_PACIENTE}
     * para modificar la información de un paciente en la base de datos.
     * @param idUsuario El identificador único del usuario a actualizar.
     * @param nombre El nuevo nombre del paciente.
     * @param apellidoPaterno El nuevo apellido paterno del paciente.
     * @param apellidoMaterno El nuevo apellido materno del paciente.
     * @param telefono El nuevo número de teléfono del paciente.
     * @param fechaNacimiento La nueva fecha de nacimiento del paciente.
     * @param correoElectronico El nuevo correo electrónico del paciente.
     * @return Regresa verdadero si la actualización se realizó con éxito.
     * @throws PersistenciaException Lanza una excepción si el paciente tiene citas activas o si ocurre un error en la base de datos.
     */
    @Override
    public boolean actualizarDatosPaciente(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono,
            LocalDate fechaNacimiento, String correoElectronico) throws PersistenciaException {

        String consultaSQL = "{CALL ACTUALIZAR_DATOS_PACIENTE(?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setInt(1, idUsuario);
            cb.setString(2, nombre);
            cb.setString(3, apellidoPaterno);
            cb.setString(4, apellidoMaterno);
            cb.setString(5, telefono);
            cb.setDate(6, Date.valueOf(fechaNacimiento));
            cb.setString(7, correoElectronico);

            cb.execute();
            return true;

        } catch (SQLException e) {
            if ("45000".equals(e.getSQLState())) {
                throw new PersistenciaException("El paciente tiene citas activas y no puede actualizar sus datos.", e);
            }
            throw new PersistenciaException("Error al ejecutar el procedimiento almacenado.", e);
        }
    }

    /**
     * Este método obtiene el identificador único de un paciente en la base de datos a partir de su nombre, apellido paterno y apellido materno.
     * Busca en la tabla pacientes el identificador del usuario cuyo nombre y apellidos coincidan con los proporcionados.
     * @param nombre El nombre del paciente a buscar.
     * @param apellidoPaterno El apellido paterno del paciente.
     * @param apellidoMaterno El apellido materno del paciente.
     * @return Regresa el identificador único del usuario si se encuentra un paciente con los datos proporcionados, 
     * @throws PersistenciaException Lanza una excepción si ocurre un error al ejecutar la consulta SQL.
     */
    @Override
    public int obtenerIdPacientePorNombre(String nombre, String apellidoPaterno, String apellidoMaterno) throws PersistenciaException {
        String consultaSQL = "SELECT ID_USUARIO FROM PACIENTES WHERE NOMBRE = ? AND APELLIDO_PATERNO = ? AND APELLIDO_MATERNO = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nombre);
            ps.setString(2, apellidoPaterno);
            ps.setString(3, apellidoMaterno);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("ID_USUARIO");
                } else {
                    return -1;
                }
            }
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al obtener el ID del paciente", ex);
        }
    }

}
