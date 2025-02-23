/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IPacienteDAO;
import DAO.IUsuarioDAO;
import DAO.PacienteDAO;
import DAO.UsuarioDAO;
import DTO.CitaDTO;
import DTO.UsuarioDTO;
import DTO.DireccionDTO;
import DTO.PacienteDTO;
import Mapper.DireccionMapper;
import DTO.ConsultaDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.ConsultaMapper;
import Mapper.PacienteMapper;
import Mapper.UsuarioMapper;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Consulta;
import entidades.Paciente;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Maximiliano
 */
public class PacienteBO {

    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());
    private final IPacienteDAO pacienteDAO;
    private final IUsuarioDAO usuarioDAO;
    private final ConsultaMapper mapper_consulta = new ConsultaMapper();
    private final PacienteMapper mapper_paciente = new PacienteMapper();
    private final UsuarioMapper mapper_usuario = new UsuarioMapper();
    private final DireccionMapper mapper_direccion = new DireccionMapper();
    private final CitaMapper mapper_cita = new CitaMapper();

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

    public List<ConsultaDTO> obtenerHistorialConsultasDelPaciente(String nombrePaciente) throws NegocioException {
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty()) {
            throw new NegocioException("El nombre del paciente no puede ser nulo.");
        }

        try {

            if (!pacienteDAO.existePaciente(nombrePaciente)) {
                throw new NegocioException("El paciente no existe.");
            }
            List<Consulta> consultasP = pacienteDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente);

            if (consultasP.isEmpty()) {
                throw new NegocioException("El paciente no tiene consultas registradas.");
            }
            return consultasP.stream()
                    .map(mapper_consulta::toDTO)
                    .collect(Collectors.toList());

        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al obtener el historial de consultas.", ex);
            throw new NegocioException("Error al obtener el historial de consultas.", ex);
        }
    }
    
    public List<CitaDTO> obtenerCitasProgramadas(PacienteDTO pacienteDTO) throws NegocioException{
        try{
            
            if(pacienteDTO == null){
                throw new NegocioException("El paciente no puede ser nulo.");
            }
            
            if (pacienteDTO.getUsuario() == null){
                throw new NegocioException("El paciente no tiene usuario valido");
            }
            Paciente paciente = mapper_paciente.toEntity(pacienteDTO);
            
            int idPaciente = pacienteDAO.obtenerIdPacientePorNombre(
                    paciente.getNombre(),
                    paciente.getApellido_paterno(),
                    paciente.getApellido_materno()
            );
            
            if (idPaciente == -1){
                throw new NegocioException("No se encontro el paciente.");
            }
            if (paciente.getUsuario() == null){
                throw new NegocioException("El paciente no tiene usuario");
            }
            
            paciente.getUsuario().setId_usuario(idPaciente);
            List<Cita> citas = pacienteDAO.obtenerCitasProgramadas(paciente);
            List<CitaDTO> citasDTO = mapper_cita.toDTOList(citas);

        
        return citasDTO;
            
        }
        catch (PersistenciaException ex){
            throw new NegocioException("Error al obtener citas programadas.");
        }
        
    }

}
