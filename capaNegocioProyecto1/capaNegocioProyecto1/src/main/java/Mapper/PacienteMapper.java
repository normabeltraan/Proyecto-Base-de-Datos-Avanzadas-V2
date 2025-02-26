/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.DireccionDTO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;
import entidades.Direccion;
import entidades.Paciente;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir entre entidades cita y objetos de transferencia de datos CitaDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class PacienteMapper {

    private UsuarioMapper usuarioMapper;
    private DireccionMapper direccionMapper;

    /**
     * Constructor que inicializa el UsuarioMapper y la Direccion Mapper.
     */
    public PacienteMapper() {
        this.usuarioMapper = new UsuarioMapper();
        this.direccionMapper = new DireccionMapper();
    }

    /**
     * Este método convierte un objeto de tipo PacienteDTO a una entidad Paciente.
     * 
     * @param pacienteDTO El objeto PacienteDTO que se desea convertir a entidad.
     * @return Regresa una instancia de Paciente con los mismos datos que el DTO o null si la entrada es null.
     */
    public Paciente toEntity(PacienteDTO pacienteDTO) {
        if (pacienteDTO == null) {
            return null;
        }

        Usuario usuario = this.usuarioMapper.toEntity(pacienteDTO.getUsuario());
        Direccion direccion = this.direccionMapper.toEntity(pacienteDTO.getDireccion());

        if (usuario == null){
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        return new Paciente(
                usuario,
                pacienteDTO.getNombre(),
                pacienteDTO.getApellido_paterno(),
                pacienteDTO.getApellido_materno(),
                pacienteDTO.getTelefono(),
                pacienteDTO.getFecha_nacimiento(),
                pacienteDTO.getCorreo_electronico(),
                direccion,
                pacienteDTO.getCitas() != null ? pacienteDTO.getCitas() : new ArrayList<>()
        );
    }

    /**
     * Este método convierte una entidad Paciente a un objeto de transferencia de datos PacienteDTO.
     * 
     * @param paciente La entidad Paciente que se desea convertir a DTO.
     * @return Regresa una instancia de PacienteDTO con los mismos datos que la entidad o null si la entrada es null.
     */
    public PacienteDTO toDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        DireccionDTO direccionDTO = DireccionMapper.toDTO(paciente.getDireccion());

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(paciente.getUsuario());

        return new PacienteDTO(
                usuarioDTO, 
                paciente.getNombre(),
                paciente.getApellido_paterno(),
                paciente.getApellido_materno(),
                paciente.getTelefono(),
                paciente.getFecha_nacimiento(),
                paciente.getCorreo_electronico(),
                direccionDTO,
                paciente.getCitas() != null ? paciente.getCitas() : new ArrayList<>()
        );
    }

    /**
     * Este método convierte una lista de entidades Paciente en una lista de objetos PacienteDTO.
     * 
     * @param pacientes Lista de entidades Paciente a convertir.
     * @return Regresa una lista de PacienteDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */
    public List<PacienteDTO> toDTOList(List<Paciente> pacientes) {
        if (pacientes == null || pacientes.isEmpty()) {
            return new ArrayList<>();
        }
        List<PacienteDTO> pacientesDTO = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            pacientesDTO.add(toDTO(paciente));
        }
        return pacientesDTO;
    }

    /**
     * Este método convierte una lista de objetos PacienteDTO en una lista de entidades Paciente.
     * 
     * @param pacientesDTO Lista de objetos PacienteDTO a convertir.
     * @return Regresa una lista de entidades Paciente con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */

    public List<Paciente> toEntityList(List<PacienteDTO> pacientesDTO) {
        if (pacientesDTO == null || pacientesDTO.isEmpty()) {
            return new ArrayList<>();
        }
        List<Paciente> pacientes = new ArrayList<>();
        for (PacienteDTO pacienteDTO : pacientesDTO) {
            pacientes.add(toEntity(pacienteDTO));
        }
        return pacientes;
    }
}
