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
    public List<Consulta> obtenerHistorialConsultas(int id) throws PersistenciaException {

        List<Consulta> consultasP = new ArrayList<>();

        String consultaSQL
                = "SELECT * FROM CONSULTAS CONS "
                + "JOIN CITAS CI ON CI.ID_CITA = CONS.ID_CITA "
                + "JOIN USUARIOS U_PACIENTE ON CI.ID_USUARIO_PACIENTE = U_PACIENTE.ID_USUARIO "
                + "JOIN USUARIOS U_MEDICO ON CI.ID_USUARIO_MEDICO = U_MEDICO.ID_USUARIO "
                + "WHERE CI.ID_USUARIO_PACIENTE = ?";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Usuario usuarioPaciente = new Usuario();
                    usuarioPaciente.setId_usuario(rs.getInt("id_usuario_paciente"));

                    Usuario usuarioMedico = new Usuario();
                    usuarioMedico.setId_usuario(rs.getInt("id_usuario_medico"));

                    Medico medico = new Medico();
                    medico.setUsuario(usuarioMedico);

                    Paciente paciente = new Paciente();
                    paciente.setUsuario(usuarioPaciente);

                    Cita cita = new Cita();
                    cita.setEstado(rs.getString("estado"));
                    cita.setFecha_hora(rs.getTimestamp("fecha_hora"));
                    cita.setId_cita(rs.getInt("id_cita"));
                    cita.setTipo(rs.getString("tipo"));
                    cita.setMedico(medico);
                    cita.setPaciente(paciente);

                    Consulta consulta = new Consulta();
                    consulta.setCita(cita);
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setObservaciones(rs.getString("observaciones"));
                    consulta.setTratamiento(rs.getString("tratamiento"));
                    consulta.setId_consulta(rs.getInt("id_consulta"));

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
    public int insertarDireccion(Direccion direccion) throws PersistenciaException {
        String consultaSQL = "INSERT INTO direcciones (calle, colonia, ciudad) VALUES (?, ?, ?)";

        try (Connection con = this.conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getColonia());
            ps.setString(3, direccion.getCiudad());

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
