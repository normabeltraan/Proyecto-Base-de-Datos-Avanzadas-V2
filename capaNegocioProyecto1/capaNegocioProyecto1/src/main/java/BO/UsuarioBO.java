/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IUsuarioDAO;
import DAO.PacienteDAO;
import DAO.UsuarioDAO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;
import Exception.NegocioException;
import Mapper.PacienteMapper;
import Mapper.UsuarioMapper;
import conexion.IConexionBD;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Clase de negocio para gestionar usuarios y pacientes.
 * Proporciona métodos para registrar usuarios-pacientes, iniciar sesión
 * y obtener el tipo de usuario.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class UsuarioBO {

    private static final Logger logger = Logger.getLogger(UsuarioBO.class.getName());
    private final IUsuarioDAO usuarioDAO;
    private PacienteDAO pacienteDAO;
    private final UsuarioMapper mapper_usuario = new UsuarioMapper();
    private final PacienteMapper mapper_paciente = new PacienteMapper();

    /**
     * Constructor que inicializa los DAOs con la conexión a la base de datos.
     * @param conexion El objeto de conexión a la base de datos.
     */
    public UsuarioBO(IConexionBD conexion) {
        this.usuarioDAO = new UsuarioDAO(conexion);
        this.pacienteDAO = new PacienteDAO(conexion);
    }

    /**
     * Este método registra un usuario y un paciente en la base de datos.
     * @param usuarioDTO El objeto DTO que contiene los datos del usuario.
     * @param pacienteDTO El objeto DTO que contiene los datos del paciente.
     * @return Regresa verdadero si el registro es exitoso, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si hay un error de validación.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la persistencia.
     */
    public boolean registrarUsuarioPaciente(UsuarioDTO usuarioDTO, PacienteDTO pacienteDTO) throws NegocioException, PersistenciaException {
        if (usuarioDTO == null || pacienteDTO == null) {
            throw new NegocioException("El usuario y el paciente son obligatorios.");
        }

        Usuario usuario = mapper_usuario.toEntity(usuarioDTO);
        Paciente paciente = mapper_paciente.toEntity(pacienteDTO);

        if (usuario.getNombre_usuario() == null || usuario.getNombre_usuario().isEmpty()) {
            throw new NegocioException("El nombre de usuario no puede estar vacío.");
        }

        if (usuarioDAO.comprobarExistenciaNombreUsuario(usuario.getNombre_usuario())) {
            throw new NegocioException("El nombre de usuario ya está registrado.");
        }
        if (usuario.getContrasenia() == null || usuario.getContrasenia().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía.");
        }
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del paciente es obligatorio.");
        }
        if (paciente.getApellido_paterno() == null || paciente.getApellido_paterno().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }
        String telefono = pacienteDTO.getTelefono();
        if (telefono == null || !telefono.matches("\\d{1,10}")) {
            throw new NegocioException("El teléfono debe contener solo números y tener entre 1 y 10 dígitos.");
        }
        if (paciente.getFecha_nacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
        if (pacienteDTO.getCorreo_electronico() == null || !pacienteDTO.getCorreo_electronico().contains("@")) {
            throw new NegocioException("El correo electrónico tiene que tener @.");
        }
        if (usuarioDAO.comprobarExistenciaCorreoElectronico(paciente.getCorreo_electronico())) {
            throw new NegocioException("El correo electrónico ya está registrado.");
        }
        if (paciente.getDireccion() == null) {
            throw new NegocioException("La dirección no es válida.");
        }

        int direccionId = pacienteDAO.insertarDireccion(paciente.getDireccion());
        if (direccionId <= 0) {
            throw new NegocioException("La dirección no se pudo registrar correctamente.");
        }

        paciente.getDireccion().setId_direccion(direccionId);

        try {
            return usuarioDAO.registrarUsuarioPaciente(usuario, paciente);
        } catch (PersistenciaException e) {
            logger.severe("Error al registrar el usuario y paciente: " + e.getMessage());
            throw new NegocioException("Error al registrar el usuario y paciente: " + e.getMessage(), e);
        }

    }

    /**
     * Este método verifica las credenciales de un usuario para iniciar sesión.
     * @param usuarioDTO El objeto DTO con los datos del usuario.
     * @return Regresa verdadero si la autenticación es correcta, falso en caso contrario.
     * @throws NegocioException Lanza una excepción si hay errores de validación.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la persistencia.
     */
    public boolean iniciarSesion(UsuarioDTO usuarioDTO) throws NegocioException, PersistenciaException {
        if (usuarioDTO.getNombre_usuario() == null || usuarioDTO.getNombre_usuario().trim().isEmpty()) {
            throw new NegocioException("El nombre de usuario no puede estar vacío.");
        }
        if (usuarioDTO.getContrasenia() == null || usuarioDTO.getContrasenia().trim().isEmpty()) {
            throw new NegocioException("La contraseña no puede estar vacía.");
        }

        Usuario usuario = mapper_usuario.toEntity(usuarioDTO);

        try {
            return usuarioDAO.iniciarSesion(usuario);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al autenticar el usuario: " + e.getMessage(), e);
        }
    }

    /**
     * Este método obtiene el tipo de usuario según su nombre de usuario.
     * @param nombreUsuario El nombre del usuario en el sistema.
     * @return Regresa una cadena con el tipo de usuario.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la persistencia.
     */
    public String obtenerTipoUsuario(String nombreUsuario) throws PersistenciaException {
        return usuarioDAO.obtenerTipoUsuario(nombreUsuario);
    }

}
