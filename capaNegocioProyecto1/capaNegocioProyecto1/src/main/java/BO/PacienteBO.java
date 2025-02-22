/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IPacienteDAO;
import DAO.IUsuarioDAO;
import DAO.PacienteDAO;
import DAO.UsuarioDAO;
import DTO.DireccionDTO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;
import Exception.NegocioException;
import Mapper.DireccionMapper;
import Mapper.PacienteMapper;
import Mapper.UsuarioMapper;
import conexion.IConexionBD;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano
 */
public class PacienteBO {

    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());
    private final IPacienteDAO pacienteDAO;
    private final IUsuarioDAO usuarioDAO;
    private final PacienteMapper mapper_paciente = new PacienteMapper();
    private final UsuarioMapper mapper_usuario = new UsuarioMapper();
    private final DireccionMapper mapper_direccion = new DireccionMapper();

    public PacienteBO(IConexionBD conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
        this.usuarioDAO = new UsuarioDAO(conexion);
    }

    public PacienteDTO obtenerPacientePorNombreUsuario(String nombreUsuario) throws PersistenciaException, NegocioException {

        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new NegocioException("El nombre de usuario no es válido.");
        }

        Paciente paciente = pacienteDAO.obtenerPacientePorNombreUsuario(nombreUsuario);

        return mapper_paciente.toDTO(paciente);
    }

    public boolean actualizarDatosPaciente(UsuarioDTO usuarioDTO, PacienteDTO pacienteDTO, DireccionDTO direccionDTO) throws NegocioException, PersistenciaException {
        if (usuarioDTO == null || pacienteDTO == null) {
            throw new NegocioException("El usuario y el paciente son obligatorios.");
        }

        Integer idUsuario = usuarioDTO.getId_usuario();
        if (idUsuario == null) {
            throw new NegocioException("El ID del usuario es obligatorio.");
        }

        if (pacienteDTO.getNombre() == null || pacienteDTO.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del paciente no puede estar vacío.");
        }
        if (pacienteDTO.getApellido_paterno() == null || pacienteDTO.getApellido_paterno().isEmpty()) {
            throw new NegocioException("El apellido paterno es obligatorio.");
        }

        String telefono = pacienteDTO.getTelefono();
        if (telefono == null || !telefono.matches("\\d{1,10}")) {
            throw new NegocioException("El teléfono debe contener solo números y tener entre 1 y 10 dígitos.");
        }
        if (pacienteDTO.getFecha_nacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
        if (pacienteDTO.getCorreo_electronico() == null || !pacienteDTO.getCorreo_electronico().contains("@")) {
            throw new NegocioException("El correo electrónico tiene que tener @.");
        }
        if (direccionDTO == null || direccionDTO.getCalle() == null || direccionDTO.getCalle().isEmpty()) {
            throw new NegocioException("La dirección debe contener una calle válida.");
        }
        if (direccionDTO.getCiudad() == null || direccionDTO.getCiudad().isEmpty()) {
            throw new NegocioException("El número de la dirección es obligatorio.");
        }
        if (direccionDTO.getColonia() == null || direccionDTO.getColonia().isEmpty()) {
            throw new NegocioException("La colonia es obligatoria.");
        }
        
        System.out.println(direccionDTO);
        
        pacienteDAO.actualizarDireccionPorUsuario(mapper_direccion.toEntity(direccionDTO), idUsuario);

        return pacienteDAO.actualizarDatosPaciente(
                idUsuario,
                pacienteDTO.getNombre(),
                pacienteDTO.getApellido_paterno(),
                pacienteDTO.getApellido_materno(),
                pacienteDTO.getTelefono(),
                pacienteDTO.getFecha_nacimiento(),
                pacienteDTO.getCorreo_electronico()
        );
    }

}
