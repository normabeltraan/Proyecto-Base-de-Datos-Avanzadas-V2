/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.MedicoDTO;
import entidades.Medico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class MedicoMapper {

    public Medico toEntity(MedicoDTO medicoDTO) {
        if (medicoDTO == null) {
            return null;
        }
        return new Medico(
                medicoDTO.getUsuario(),
                medicoDTO.getNombre(),
                medicoDTO.getApellido_paterno(),
                medicoDTO.getApellido_materno(),
                medicoDTO.getEstado(),
                medicoDTO.getEspecialidad(),
                medicoDTO.getCedula(),
                null,
                null);
    }

    public MedicoDTO toDTO(Medico medico) {
        if (medico == null) {
            return null;
        }
        return new MedicoDTO(
                medico.getUsuario(),
                medico.getNombre(),
                medico.getApellido_paterno(),
                medico.getApellido_materno(),
                medico.getEstado(),
                medico.getEspecialidad(),
                medico.getCedula(),
                null,
                null);
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
