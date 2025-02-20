/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.HorarioDisponibleDTO;
import entidades.HorarioDisponible;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author norma
 */
public class HorarioDisponibleMapper {

    public HorarioDisponibleDTO toDTO(HorarioDisponible horario) {
        if (horario == null) {
            return null;
        }
        return new HorarioDisponibleDTO(
                horario.getFecha(),
                horario.getHora_disponible()
        );
    }

    public HorarioDisponible toEntity(HorarioDisponibleDTO horarioDTO) {
        if (horarioDTO == null) {
            return null;
        }
        return new HorarioDisponible(
                horarioDTO.getFecha(),
                horarioDTO.getHora_disponible()
        );
    }

    public List<HorarioDisponibleDTO> toDTOList(List<HorarioDisponible> horarios) {
        if (horarios == null || horarios.isEmpty()) {
            return new ArrayList<>();
        }
        List<HorarioDisponibleDTO> horariosDTO = new ArrayList<>();
        for (HorarioDisponible horario : horarios) {
            horariosDTO.add(toDTO(horario));
        }
        return horariosDTO;
    }

    public List<HorarioDisponible> toEntityList(List<HorarioDisponibleDTO> horariosDTO) {
        if (horariosDTO == null || horariosDTO.isEmpty()) {
            return new ArrayList<>();
        }
        List<HorarioDisponible> horarios = new ArrayList<>();
        for (HorarioDisponibleDTO horarioDTO : horariosDTO) {
            horarios.add(toEntity(horarioDTO));
        }
        return horarios;
    }
}
