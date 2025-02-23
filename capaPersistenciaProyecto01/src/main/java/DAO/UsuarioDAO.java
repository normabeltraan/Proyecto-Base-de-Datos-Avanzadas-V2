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
 *
 * @author norma
 */
public class UsuarioDAO implements IUsuarioDAO {

    IConexionBD conexion;
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

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

    @Override
    public boolean iniciarSesion(Usuario usuario) throws PersistenciaException {
        String consultaSQL = "SELECT contrasenia FROM USUARIOS WHERE nombre_usuario = ?";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, usuario.getNombre_usuario());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String contraseniaEncriptada = rs.getString("contrasenia");

                if (BCrypt.checkpw(usuario.getContrasenia(), contraseniaEncriptada)) {
                    return true;
                }
            }

            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al iniciar sesión", e);
            throw new PersistenciaException("Error al iniciar sesión", e);
        }
    }

    @Override
    public boolean comprobarExistenciaNombreUsuario(String nombreUsuario) throws PersistenciaException {
        String consultaSQL = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar el nombre de usuario", e);
        }
        return false;
    }

    @Override
    public boolean comprobarExistenciaCorreoElectronico(String correoElectronico) throws PersistenciaException {
        String consultaSQL = "SELECT COUNT(*) FROM pacientes WHERE correo_electronico = ?";
        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setString(1, correoElectronico);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al verificar el correo electrónico", e);
        }
        return false;
    }

    @Override
    public String obtenerTipoUsuario(String nombreUsuario) throws PersistenciaException {
        String tipoUsuario = "";

        String consultaSQL = "SELECT "
                + "CASE "
                + "   WHEN u.id_usuario = p.id_usuario THEN 'paciente' "
                + "   WHEN u.id_usuario = m.id_usuario THEN 'medico' "
                + "END AS tipo_usuario "
                + "FROM USUARIOS u "
                + "LEFT JOIN PACIENTES p ON u.id_usuario = p.id_usuario "
                + "LEFT JOIN MEDICOS m ON u.id_usuario = m.id_usuario "
                + "WHERE u.nombre_usuario = ?";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipoUsuario = rs.getString("tipo_usuario");
                }
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Error al obtener el tipo de usuario", e);
        }

        return tipoUsuario;
    }

}
