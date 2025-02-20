/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Direccion;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class PacienteDAO implements IPacienteDAO {

    IConexionBD conexion;

    public PacienteDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    @Override
    public Paciente obtenerPacientePorUsuario(int idUsuario) throws PersistenciaException {
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

    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean eliminarPaciente() throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
