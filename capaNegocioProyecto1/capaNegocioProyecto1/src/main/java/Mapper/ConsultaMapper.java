/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaDTO;
import DTO.ConsultaDTO;
import DTO.MedicoDTO;
import DTO.PacienteDTO;
import entidades.Cita;
import entidades.Consulta;
import entidades.Medico;
import entidades.Paciente;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Maximiliano
 */
public class ConsultaMapper {
  
    public Consulta toEntity(ConsultaDTO consultaDTO) {
        if (consultaDTO == null) {
            return null;
        }
        return new Consulta(
                consultaDTO.getId_consulta(),
                consultaDTO.getDiagnostico(),
                consultaDTO.getTratamiento(),
                consultaDTO.getObservaciones(), 
                consultaDTO.getCita()
        );
    }

    public ConsultaDTO toDTO(Consulta consulta) {
        if (consulta == null) {
            return null;
        }
        return new ConsultaDTO(
                consulta.getId_consulta(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                consulta.getObservaciones(),
                consulta.getCita()
        );
    }

    public List<ConsultaDTO> toDTOList(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) {
            return new ArrayList<>();
        }
        return consultas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Consulta> toEntityList(List<ConsultaDTO> consultasDTO) {
        if (consultasDTO == null || consultasDTO.isEmpty()) {
            return new ArrayList<>();
        }
        return consultasDTO.stream().map(this::toEntity).collect(Collectors.toList());
    }
    
}
