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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
  @Override
  public Medico obtenerPerfilMedico(int idMedico) throws PersistenciaException 
  {
    // Consulta para obtener los datos del médico y su usuario relacionado
    String consultaSQL = "SELECT m.id_usuario, m.nombre, m.apellido_paterno, m.apellido_materno, "
            + "m.estado, m.especialidad, m.cedula, u.nombre_usuario, u.contrasenia "
            + "FROM MEDICOS m "
            + "JOIN USUARIOS u ON m.id_usuario = u.id_usuario "
            + "WHERE m.id_medico = ?";

    try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

        ps.setInt(1, idMedico);  // Establecemos el ID del médico

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                // Recuperamos los datos del médico
                String nombre = rs.getString("nombre");
                String apellidoPaterno = rs.getString("apellido_paterno");
                String apellidoMaterno = rs.getString("apellido_materno");
                String estado = rs.getString("estado");
                String especialidad = rs.getString("especialidad");
                String cedula = rs.getString("cedula");

                // Recuperamos los datos del usuario relacionado
                Usuario usuario = new Usuario();
                usuario.setId_usuario(rs.getInt("id_usuario"));
                usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));

                // Creamos y retornamos el objeto Medico con los datos recuperados
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

    return null; // Si no se encuentra el médico, retornamos null
  }
  
@Override
public List<Cita> consultarAgendaMedico(int idMedico, Date fecha) throws PersistenciaException {
    List<Cita> citas = new ArrayList<>();

    String consultaSQL = "SELECT c.id_cita, DATE(c.fecha_hora) as fecha, TIME(c.fecha_hora) as hora, "
            + "c.tipo, c.estado, "
            + "CONCAT(p.nombre, ' ', p.apellido_paterno, IFNULL(CONCAT(' ', p.apellido_materno), '')) AS nombre_completo "
            + "FROM CITAS c "
            + "JOIN PACIENTES p ON c.id_usuario_paciente = p.id_usuario "
            + "WHERE c.id_usuario_medico = ? AND DATE(c.fecha_hora) = ?";

    try (Connection con = this.conexion.crearConexion();
         PreparedStatement ps = con.prepareStatement(consultaSQL)) {

        ps.setInt(1, idMedico);
        ps.setDate(2, fecha);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cita cita = new Cita();
                cita.setId_cita(rs.getInt("id_cita"));
                cita.setFecha_hora(rs.getTimestamp("fecha"));
                cita.setFecha_hora(rs.getTimestamp("hora"));
                cita.setTipo(rs.getString("tipo"));
                cita.setEstado(rs.getString("estado"));

                Paciente paciente = new Paciente();
                paciente.setNombre(rs.getString("nombre_completo"));

                cita.setPaciente(paciente);
                citas.add(cita);
            }
        }
    } catch (SQLException e) {
        logger.severe("Error al consultar la agenda del médico: " + e.getMessage());
        throw new PersistenciaException("Error al consultar la agenda del médico", e);
    }

    return citas;
}

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
}
