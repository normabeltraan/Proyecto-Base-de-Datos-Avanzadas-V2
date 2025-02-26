package Mapper;

import DTO.CitaDTO;
import entidades.Cita;
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

public class CitaMapper {

    /**
     * Este método convierte un objeto de tipo CitaDTO a una entidad Cita.
     * 
     * @param cita El objeto CitaDTO que se desea convertir a entidad.
     * @return Regresa una instancia de Cita con los mismos datos que el DTO o null si la entrada es null.
     */
    public Cita toEntity(CitaDTO cita) {
        if (cita == null) {
            return null;
        }
        return new Cita(
                cita.getId_cita(),
                cita.getFecha_hora(),
                cita.getEstado(),
                cita.getTipo(),
                cita.getPaciente(),
                cita.getMedico()
        );
    }

    /**
     * Este método convierte una entidad Cita a un objeto de transferencia de datos CitaDTO.
     * 
     * @param cita La entidad Cita que se desea convertir a DTO.
     * @return Regresa una instancia de CitaDTO con los mismos datos que la entidad o null si la entrada es null.
     */
    public CitaDTO toDTO(Cita cita) {
        if (cita == null) {
            return null;
        }
        return new CitaDTO(
                cita.getId_cita(),
                cita.getFecha_hora(),
                cita.getEstado(),
                cita.getTipo(),
                cita.getPaciente(),
                cita.getMedico()
        );
    }

    /**
     * Este método convierte una lista de entidades Cita en una lista de objetos CitaDTO.
     * 
     * @param citas Lista de entidades Cita a convertir.
     * @return Regresa una lista de CitaDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */
    public List<CitaDTO> toDTOList(List<Cita> citas) {
        if (citas == null || citas.isEmpty()) {
            return new ArrayList<>();
        }
        List<CitaDTO> citasDTO = new ArrayList<>();
        for (Cita cita : citas) {
            citasDTO.add(toDTO(cita));
        }
        return citasDTO;
    }

    /**
     * Este método convierte una lista de objetos CitaDTO en una lista de entidades Cita.
     * 
     * @param citasDTO Lista de objetos CitaDTO a convertir.
     * @return Regresa una lista de entidades Cita con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */
    public List<Cita> toEntityList(List<CitaDTO> citasDTO) {
        if (citasDTO == null || citasDTO.isEmpty()) {
            return new ArrayList<>();
        }
        List<Cita> citas = new ArrayList<>();
        for (CitaDTO citaDTO : citasDTO) {
            citas.add(toEntity(citaDTO));
        }
        return citas;
    }
}
