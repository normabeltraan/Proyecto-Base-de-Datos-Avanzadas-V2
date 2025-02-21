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
 *
 * @author Maximiliano
 */
public class UsuarioBO {

    private static final Logger logger = Logger.getLogger(UsuarioBO.class.getName());
    private final IUsuarioDAO usuarioDAO;
    private PacienteDAO pacienteDAO;
    private final UsuarioMapper mapper_usuario = new UsuarioMapper();
    private final PacienteMapper mapper_paciente = new PacienteMapper();

    public UsuarioBO(IConexionBD conexion) {
        this.usuarioDAO = new UsuarioDAO(conexion);
        this.pacienteDAO = new PacienteDAO(conexion);
    }

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
        if (paciente.getTelefono() == null || paciente.getTelefono().length() > 10) {
            throw new NegocioException("El teléfono debe tener máximo 10 dígitos.");
        }
        if (paciente.getFecha_nacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
        if (paciente.getCorreo_electronico() == null || !paciente.getCorreo_electronico().contains("@")) {
            throw new NegocioException("El correo electrónico no es válido. Tiene que tener '@' ");
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

}
