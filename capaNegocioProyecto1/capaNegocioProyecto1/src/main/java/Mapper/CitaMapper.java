package Mapper;

import DTO.CitaDTO;
import entidades.Cita;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class CitaMapper {

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
