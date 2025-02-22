/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.MedicoDTO;
import DTO.UsuarioDTO;
import entidades.Medico;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class MedicoMapper {
    
    private UsuarioMapper usuarioMapper;
    
    public MedicoMapper() {
        this.usuarioMapper = new UsuarioMapper();  
    }
    
    public Medico toEntity(MedicoDTO medicoDTO) {
        if (medicoDTO == null) {
            return null;
        }

        // Convertimos el DTO a la entidad Medico
        Usuario usuario = usuarioMapper.toEntity(medicoDTO.getUsuario());

        return new Medico(
                usuario, // Usuario
                medicoDTO.getNombre(), // Nombre
                medicoDTO.getApellido_paterno(), // Apellido paterno
                medicoDTO.getApellido_materno(), // Apellido materno
                medicoDTO.getEstado(), // Estado
                medicoDTO.getEspecialidad(), // Especialidad
                medicoDTO.getCedula(), // Cedula
                null, // Atributo adicional si no aplica (por ejemplo, Citas)
                null // Otro atributo si aplica (por ejemplo, Dirección)
        );
    }

    public MedicoDTO toDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

        // Convertimos la entidad Medico a su DTO correspondiente
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(medico.getUsuario());

        return new MedicoDTO(
                usuarioDTO, // UsuarioDTO
                medico.getNombre(), // Nombre
                medico.getApellido_paterno(), // Apellido paterno
                medico.getApellido_materno(), // Apellido materno
                medico.getEstado(), // Estado
                medico.getEspecialidad(), // Especialidad
                medico.getCedula(), // Cedula
                null, // Atributo adicional si no aplica
                null // Otro atributo si aplica
        );
    }

    public List<MedicoDTO> toDTOList(List<Medico> medicos) {
        if (medicos == null || medicos.isEmpty()) {
            return new ArrayList<>();
        }
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        for (Medico medico : medicos) {
            medicosDTO.add(toDTO(medico));
        }
        return medicosDTO;
    }

    public List<Medico> toEntityList(List<MedicoDTO> medicosDTO) {
        if (medicosDTO == null || medicosDTO.isEmpty()) {
            return new ArrayList<>();
        }
        List<Medico> medicos = new ArrayList<>();
        for (MedicoDTO medicoDTO : medicosDTO) {
            medicos.add(toEntity(medicoDTO));
        }
        return medicos;
    }
}
