/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.PacienteDTO;
import entidades.Direccion;
import entidades.Paciente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class PacienteMapper {

    public Paciente toEntity(PacienteDTO pacienteDTO) {
        if (pacienteDTO == null) {
            return null;
        }

        return new Paciente(
                pacienteDTO.getUsuario(),
                pacienteDTO.getNombre(),
                pacienteDTO.getApellido_paterno(),
                pacienteDTO.getApellido_materno(),
                pacienteDTO.getTelefono(),
                pacienteDTO.getFecha_nacimiento(),
                pacienteDTO.getCorreo_electronico(),
                pacienteDTO.getDireccion() != null ? pacienteDTO.getDireccion() : new Direccion(), 
                pacienteDTO.getCitas() != null ? pacienteDTO.getCitas() : new ArrayList<>()
        );
    }

    public PacienteDTO toDTO(Paciente paciente) {
        if (paciente == null) {
            return null;
        }

        return new PacienteDTO(
                paciente.getNombre(),
                paciente.getApellido_paterno(),
                paciente.getApellido_materno(),
                paciente.getTelefono(),
                paciente.getFecha_nacimiento(),
                paciente.getCorreo_electronico(),
                paciente.getDireccion() != null ? paciente.getDireccion() : new Direccion(), 
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
