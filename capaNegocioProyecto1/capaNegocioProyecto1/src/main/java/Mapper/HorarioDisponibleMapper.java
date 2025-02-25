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
 * Clase encargada de convertir entre entidades HorarioDisponible y objetos de transferencia de datos HorarioDisponibleDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */

public class HorarioDisponibleMapper {

    /**
     * Este método convierte una entidad HorarioDisponible a un objeto de transferencia de datos HorarioDisponibleDTO.
     * 
     * @param horario La entidad HorarioDisponible que se desea convertir a DTO.
     * @return Regresa una instancia de HorarioDisponibleDTO con los mismos datos que la entidad o null si la entrada es null.
     */
    public HorarioDisponibleDTO toDTO(HorarioDisponible horario) {
        if (horario == null) {
            return null;
        }
        return new HorarioDisponibleDTO(
                horario.getFecha(),
                horario.getHora_disponible()
        );
    }

    /**
     * Este método convierte un objeto de tipo HorarioDisponibleDTO a una entidad HorarioDisponible.
     * 
     * @param horarioDTO El objeto HorarioDisponibleDTO que se desea convertir a entidad.
     * @return Regresa una instancia de HorarioDisponible con los mismos datos que el DTO o null si la entrada es null.
     */
    public HorarioDisponible toEntity(HorarioDisponibleDTO horarioDTO) {
        if (horarioDTO == null) {
            return null;
        }
        return new HorarioDisponible(
                horarioDTO.getFecha(),
                horarioDTO.getHora_disponible()
        );
    }

    /**
     * Este método convierte una lista de entidades Cita en una lista de objetos CitaDTO.
     * 
     * @param horarios Lista de entidades Cita a convertir.
     * @return Regresa una lista de HorarioDisponibleDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */
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

    /**
     * Este método convierte una lista de objetos HorarioDisponibleDTO en una lista de entidades HorarioDisponible.
     * 
     * @param horariosDTO Lista de objetos HorarioDisponibleDTO a convertir.
     * @return Regresa una lista de entidades HorarioDisponible con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */
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
