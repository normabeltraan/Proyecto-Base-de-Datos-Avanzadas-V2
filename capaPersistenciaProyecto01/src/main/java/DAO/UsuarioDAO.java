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
        String storedProcedure = "{CALL REGISTRAR_USUARIO_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection con = conexion.crearConexion(); CallableStatement cb = con.prepareCall(storedProcedure)) {

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
        String query = "SELECT COUNT(*) FROM USUARIOS WHERE nombre_usuario = ? AND contrasenia = ?";

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getContrasenia());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; 
            }

            return false; 

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al iniciar sesión", e);
            throw new PersistenciaException("Error al iniciar sesión", e);
        }
    }
}
