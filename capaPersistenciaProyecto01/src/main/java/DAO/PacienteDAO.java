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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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

    @Override
    public List<Consulta> obtenerHistorialConsultasDelPaciente(String nombrePaciente) throws PersistenciaException {

        List<Consulta> consultasP = new ArrayList<>();

        String consultaSQL
                = "SELECT " +
                "CONCAT(P.NOMBRE, ' ', P.APELLIDO_PATERNO, ' ', IFNULL(P.APELLIDO_MATERNO, '')) AS nombre_completo_paciente, " +
                "MED.ESPECIALIDAD, " +
                "MED.NOMBRE AS nombre_medico, " +
                "MED.APELLIDO_PATERNO AS apellido_paterno_medico, " +
                "IFNULL(MED.APELLIDO_MATERNO, '') AS apellido_materno_medico, " +
                "CI.FECHA_HORA, " +
                "CONS.DIAGNOSTICO, " +
                "CONS.TRATAMIENTO, " +
                "CI.ESTADO, " +
                "CI.TIPO " +
                "FROM CONSULTAS CONS " +
                "JOIN CITAS CI ON CI.ID_CITA = CONS.ID_CITA " +
                "JOIN PACIENTES P ON CI.ID_USUARIO_PACIENTE = P.ID_USUARIO " +
                "JOIN MEDICOS MED ON CI.ID_USUARIO_MEDICO = MED.ID_USUARIO " +
                "WHERE CONCAT(P.NOMBRE, ' ', P.APELLIDO_PATERNO, ' ', IFNULL(P.APELLIDO_MATERNO, '')) = ?";
        
        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setString(1, nombrePaciente);

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
    
    
    
    @Override
    public List<Cita> obtenerCitasProgramadas(Paciente paciente) throws PersistenciaException {
        List<Cita> citasProgramadas = new ArrayList<>();
        
        String consultaSQL = 
                "SELECT CI.FECHA_HORA, MED.ESPECIALIDAD, " +
                "CONCAT(MED.NOMBRE, ' ', MED.APELLIDO_PATERNO, ' ', IFNULL(MED.APELLIDO_MATERNO, '')) AS NOMBRE_COMPLETO_MEDICO " +
                "FROM CITAS CI " +
                "JOIN MEDICOS MED ON CI.ID_USUARIO_MEDICO = MED.ID_USUARIO " +
                "WHERE CI.ID_USUARIO_PACIENTE = ? " +
                "AND CI.ESTADO = 'ACTIVA' "
                + "ORDER BY CI.FECHA_HORA ASC";
        
        try (Connection con = this.conexion.crearConexion(); 
                PreparedStatement ps = con.prepareStatement(consultaSQL)){
            
            ps.setInt(1, paciente.getUsuario().getId_usuario());
            
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Medico medico = new Medico();
                    medico.setNombre(rs.getString("NOMBRE_COMPLETO_MEDICO"));
                    medico.setEspecialidad(rs.getString("ESPECIALIDAD"));
                    
                    Cita cita = new Cita();
                    cita.setFecha_hora(rs.getTimestamp("FECHA_HORA"));
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

    @Override
    public boolean actualizarDireccionPorUsuario(Direccion direccion, Integer idUsuario) throws PersistenciaException {
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
    
    @Override
    public boolean existePaciente(String nombrePaciente) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM PACIENTES WHERE "
                + "CONCAT(NOMBRE, ' ', APELLIDO_PATERNO, ' ', APELLIDO_MATERNO) = ?";
        
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            
            ps.setString(1, nombrePaciente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }  catch (SQLException ex) {
            throw new PersistenciaException("Error al verificar la existencia del paciente.", ex);
        }
        
        return false;
    }

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
    
    public int obtenerIdPacientePorNombre(String nombre, String apellidoPaterno, String apellidoMaterno) throws PersistenciaException {
        String consultaSQL = "SELECT ID_USUARIO FROM PACIENTES WHERE NOMBRE = ? AND APELLIDO_PATERNO = ? AND APELLIDO_MATERNO = ?";

        try (Connection con = this.conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(consultaSQL)) {

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
