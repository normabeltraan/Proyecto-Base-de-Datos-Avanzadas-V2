/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Encripar contraseñas
import org.mindrot.jbcrypt.BCrypt;

/**
 * Esta clase implementa la interfaz IUsuarioDAO se encarga de gestionar a los
 * usuarios ya sean paciente o médico.
 *
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class UsuarioDAO implements IUsuarioDAO {

    IConexionBD conexion;
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    /**
     * Constructor que recibe la conexión con la base de datos.
     *
     * @param conexion El objeto que gestiona la conexión con la base de datos.
     */
    public UsuarioDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    /**
     * Este método registra un nuevo usuario junto con sus datos de paciente en
     * la base de datos. La contraseña del usuario es encriptada antes de
     * almacenarse.
     *
     * @param usuario El objeto usuario con los datos de autenticación.
     * @param paciente El objeto paciente con la información personal del
     * paciente.
     * @return Regresa verdadero si el registro fue exitoso, falso en caso
     * contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la operación.
     */
    @Override
    public boolean registrarUsuarioPaciente(Usuario usuario, Paciente paciente) throws PersistenciaException {

        String contraseniaEncriptada = BCrypt.hashpw(usuario.getContrasenia(), BCrypt.gensalt());
        usuario.setContrasenia(contraseniaEncriptada);

        String consultaSQL = "{CALL REGISTRAR_USUARIO_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {
            cb.setString(1, usuario.getNombre_usuario());
            cb.setString(2, usuario.getContrasenia());
            cb.setString(3, paciente.getNombre());
            cb.setString(4, paciente.getApellido_paterno());
            cb.setString(5, paciente.getApellido_materno());
            cb.setString(6, paciente.getTelefono());
            cb.setDate(7, java.sql.Date.valueOf(paciente.getFecha_nacimiento()));
            cb.setString(8, paciente.getCorreo_electronico());
            cb.setInt(9, paciente.getDireccion().getId_direccion());

            cb.executeUpdate();
            logger.info("Usuario y paciente registrados con éxito.");
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar un usuario y paciente", e);
            throw new PersistenciaException("Error al registrar un usuario y paciente", e);
        }
    }

    /**
     * Este método verifica si las credenciales del usuario son correctas para
     * iniciar sesión. Se compara la contraseña ingresada con la almacenada en
     * la base de datos.
     *
     * @param usuario El objeto usuario con las credenciales de inicio de
     * sesión.
     * @return Regresa verdadero si la autenticación es exitosa, falso si las
     * credenciales no coinciden.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * furante la consulta a la base de datos.
     */
    @Override
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException {
        String consultaSQL = "SELECT contrasenia FROM USUARIOS WHERE nombre_usuario = ?";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, usuario.getNombre_usuario());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String contraseniaAlmacenada = rs.getString("contrasenia");

                if (contraseniaAlmacenada.startsWith("$2a$")) {
                    return BCrypt.checkpw(usuario.getContrasenia(), contraseniaAlmacenada);
                } else {
                    return contraseniaAlmacenada.equals(usuario.getContrasenia());
                }
            }

            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al iniciar sesión", e);
            throw new PersistenciaException("Error al iniciar sesión", e);
        }
    }

    /**
     * Este método comprueba si un nombre de usuario ya existe en la base de
     * datos.
     *
     * @param nombreUsuario El nombre de usuario a verificar.
     * @return Regresa verdadero si el nombre de usuario ya está registrado,
     * falso si está disponible.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la consulta.
     */
    @Override
    public boolean comprobarExistenciaNombreUsuario(String nombreUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar el nombre de usuario", e);
        }
    }

    /**
     * Este método comprueba si un correo electrónico ya está registrado en la
     * base de datos.
     *
     * @param correoElectronico El correo electrónico a verificar.
     * @return Regresa verdadero si el correo ya está registrado, falso si está
     * disponible.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error
     * durante la consulta.
     */
    @Override
    public boolean comprobarExistenciaCorreoElectronico(String correoElectronico) throws PersistenciaException {
        String consultaSQL = "SELECT COUNT(*) FROM pacientes WHERE correo_electronico = ?";
        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, correoElectronico);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar el correo electrónico", e);
        }
    }

    /**
     * Este método obtiene el tipo de usuario según su nombre de usuario. Puede
     * ser paciente o médico.
     *
     * @param nombreUsuario El nombre del usuario a consultar.
     * @return Regresa la cadena que indica el tipo de usuario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error en
     * la consulta.
     */
    @Override
    public String obtenerTipoUsuario(String nombreUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT CASE WHEN u.id_usuario = p.id_usuario THEN 'paciente' "
                + "WHEN u.id_usuario = m.id_usuario THEN 'medico' END AS tipo_usuario "
                + "FROM USUARIOS u LEFT JOIN PACIENTES p ON u.id_usuario = p.id_usuario "
                + "LEFT JOIN MEDICOS m ON u.id_usuario = m.id_usuario WHERE u.nombre_usuario = ?";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getString("tipo_usuario") : "";
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el tipo de usuario", e);
        }
    }

}
