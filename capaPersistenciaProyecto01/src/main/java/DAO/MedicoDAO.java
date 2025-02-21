/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static com.mysql.cj.conf.PropertyKey.logger;
import conexion.IConexionBD;
import entidades.Medico;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class MedicoDAO implements IMedicoDAO {

    IConexionBD conexion;

    public MedicoDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    private static final Logger logger = Logger.getLogger(MedicoDAO.class.getName());

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

    @Override
    public List<Medico> obtenerMedicosPorEspecialidad(String especialidad) throws PersistenciaException {
        List<Medico> medicos = new ArrayList<>();

        String consultaSql = "SELECT M.*, U.id_usuario, U.nombre_usuario "
                + "FROM MEDICOS M "
                + "JOIN USUARIOS U ON M.id_usuario = U.id_usuario "
                + "WHERE M.especialidad = ? AND M.estado = 'Activo'";

        //String consultaSql = "SELECT * FROM MEDICOS M WHERE M.especialidad = ? AND M.estado = 'Activo'";
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

                /**
                 * Medico medico = new Medico();
                 * medico.setNombre(rs.getString("nombre"));
                 * medico.setApellido_paterno(rs.getString("apellido_paterno"));
                 * medico.setApellido_materno(rs.getString("apellido_materno"));
                 * medico.setEstado(rs.getString("estado"));
                 * medico.setEspecialidad(rs.getString("especialidad"));
                 * medico.setCedula(rs.getString("cedula"));
                 * medicos.add(medico);
                 *
                 */
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener médicos: " + e.getMessage());
            throw new PersistenciaException("Error al obtener médicos", e);
        }
        return medicos;
    }

    @Override
    public Medico obtenerMedicoPorId(int id_medico) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM Medicos WHERE id_usuario = ?";
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

                return new Medico(nombre, apellidoPaterno, apellidoMaterno, estado, especialidad, cedula, null, null);
            }
        } catch (SQLException e) {
            logger.severe("Error al obtener médicos: " + e.getMessage());
            throw new PersistenciaException("Error al obtener médicos", e);
        }
        return null;
    }
}
