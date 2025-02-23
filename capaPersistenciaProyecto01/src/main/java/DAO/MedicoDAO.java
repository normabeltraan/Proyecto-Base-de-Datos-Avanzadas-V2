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
import entidades.Horario;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Level;

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
    public Medico obtenerPerfilMedico(int idMedico) throws PersistenciaException {
        // Consulta para obtener los datos del médico y su usuario relacionado
        String consultaSQL = "SELECT m.id_usuario, m.nombre, m.apellido_paterno, m.apellido_materno, "
                + "m.estado, m.especialidad, m.cedula, u.nombre_usuario, u.contrasenia "
                + "FROM MEDICOS m "
                + "JOIN USUARIOS u ON m.id_usuario = u.id_usuario "
                + "WHERE m.id_medico = ?";

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


    //@Override
    //public List<Cita> consultarAgendaMedico(int idMedico) throws PersistenciaException {
//        List<Cita> citas = new ArrayList<>();
//
//        java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());
//
//        String consultaSQL = "SELECT c.id_cita, c.fecha_cita, c.hora_cita, c.tipo_cita, c.estado, "
//                + "p.nombre, p.apellido_paterno, p.apellido_materno "
//                + "FROM CITAS c "
//                + "JOIN PACIENTES p ON c.id_paciente = p.id_paciente "
//                + "WHERE c.id_medico = ? AND c.fecha_cita = ?";
//
//        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
//
//            ps.setInt(1, idMedico);
//            ps.setDate(2, fechaActual);  
//
//            try (ResultSet rs = ps.executeQuery()) {
//
//                while (rs.next()) {
//                    Cita cita = new Cita();
//                    cita.setId_cita(rs.getInt("id_cita"));
//                    cita.setFecha_cita(rs.getDate("fecha_cita"));
//                    cita.setHora_cita(rs.getTime("hora_cita"));
//                    cita.setTipo_cita(rs.getString("tipo_cita"));
//                    cita.setEstado(rs.getString("estado"));
//
//                    Paciente paciente = new Paciente();
//                    paciente.setNombre(rs.getString("nombre"));
//                    paciente.setApellido_paterno(rs.getString("apellido_paterno"));
//                    paciente.setApellido_materno(rs.getString("apellido_materno"));
//
//                    cita.setPaciente(paciente); // Asignamos el paciente a la cita
//
//                    citas.add(cita); // Añadimos la cita a la lista
//                }
//            } catch (SQLException e) {
//                logger.severe("Error al consultar la agenda del médico: " + e.getMessage());
//                throw new PersistenciaException("Error al consultar la agenda del médico", e);
//            }
//
//        } catch (SQLException e) {
//            logger.severe("Error al consultar la agenda del médico: " + e.getMessage());
//            throw new PersistenciaException("Error al consultar la agenda del médico", e);
//        }
//
//        return citas; 
        //return null;
    //}
    
    @Override
    public List <Cita> consultarAgendaMedico(int id_medico) throws PersistenciaException {
        List<Cita> citasDia = new ArrayList<>();
        String consultaSQL = "SELECT DATE_FORMAT(c.fecha_hora, '%H:%i') AS hora,  "
            + "CONCAT(p.nombre, ' ', p.apellido_paterno, IFNULL(CONCAT(' ', p.apellido_materno),'')) AS paciente " 
            + "FROM CITAS c JOIN PACIENTES p ON c.id_usuario_paciente = p.id_usuario " 
            + "WHERE c.id_usuario_medico = ? AND DATE(c.fecha_hora) = CURDATE() " 
            + "ORDER BY c.fecha_hora";
        
        try(Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)){
            
            ps.setInt(1, id_medico);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Cita cita = new Cita();
                    String horaStr = rs.getString("hora");
                    Timestamp horaTimestamp = Timestamp.valueOf("1970-01-01 " + horaStr + ":00");
                    
                    cita.setFecha_hora(horaTimestamp);
                    Paciente paciente = new Paciente();
                    paciente.setNombre(rs.getString("paciente"));
                    cita.setPaciente(paciente);
                    
                    citasDia.add(cita);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error al consultar la agenda del médico", ex);
            throw new PersistenciaException("Error al consultar la agenda del médico.", ex);
        }
        return citasDia;
    }

//    @Override
//    public List<Cita> consultarAgendaMedico(int idMedico) throws PersistenciaException {
//    String consultaSQL = "SELECT " +
//            "CONCAT(p.nombre, ' ', p.apellido_paterno, ' ', p.apellido_materno) AS nombre_paciente, " +
//            "CURDATE() AS fecha_actual, " +
//            "c.fecha_hora AS hora_atencion " +
//            "FROM citas c " +
//            "JOIN pacientes p ON c.id_usuario_paciente = p.id_usuario " +
//            "WHERE c.id_usuario_medico = ? " +
//            "AND DATE(c.fecha_hora) = CURDATE() " +
//            "AND (c.tipo = 'Agendada' OR c.tipo = 'Emergencia') " +
//            "ORDER BY c.fecha_hora";
//
//    List<Cita> citas = new ArrayList<>();
//
//    try (Connection con = this.conexion.crearConexion();
//         PreparedStatement ps = con.prepareStatement(consultaSQL)) {
//
//        ps.setInt(1, idMedico);
//        ResultSet rs = ps.executeQuery();
//
//        while (rs.next()) {
//            String nombrePaciente = rs.getString("nombre_paciente");
//            Timestamp horaAtencion = rs.getTimestamp("hora_atencion");
//
//            Cita cita = new Cita();
//            cita.setNombrePaciente(nombrePaciente);
//            cita.setFecha_hora(horaAtencion);
//
//            citas.add(cita);
//        }
//
//    } catch (SQLException e) {
//        throw new PersistenciaException("Error al obtener la agenda del médico", e);
//    }
//
//    return citas;
//}
}
