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
 *
 * @author Maximiliano
 */
public class PacienteMapper {

    private UsuarioMapper usuarioMapper;
    private DireccionMapper direccionMapper;

    public PacienteMapper() {
        this.usuarioMapper = new UsuarioMapper();
        this.direccionMapper = new DireccionMapper();
    }

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
