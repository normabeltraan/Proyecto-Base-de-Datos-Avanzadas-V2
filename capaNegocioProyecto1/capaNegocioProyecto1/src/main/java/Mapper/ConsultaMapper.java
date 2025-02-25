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
 * Clase encargada de convertir entre entidades consulta y objetos de transferencia de datos ConsultaDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class ConsultaMapper {
  
    /**
     * Este método convierte un objeto de tipo ConsultaDTO a una entidad Consulta.
     * @param consultaDTO El objeto ConsultaDTO que se desea convertir a entidad.
     * @return Regresa una instancia de la Consulta con los mismos datos que el DTO o null si la entrada es null.
     */
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

    /**
     * Este método convierte una entidad Cita a un objeto de transferencia de datos ConsultaDTO.
     * @param consulta La entidad  que se desea convertir a STO.
     * @return Regresa una instancia de CitaDTO con los mismos datos que la entidad o null si la entrada es null.
     */
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

    /**
     * Este método convierte una lista de entidades Consulta en una lista de objetos ConsultaDTO.
     * @param consultas Lista de entidades Consulta a convertir.
     * @return Regresa una lista de ConsultaDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */
    public List<ConsultaDTO> toDTOList(List<Consulta> consultas) {
        if (consultas == null || consultas.isEmpty()) {
            return new ArrayList<>();
        }
        return consultas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Este método convierte una lista de objetos ConsultaDTO en una lista de entidades Consulta.
     * @param consultasDTO Lista de objetos ConsultasDTO a convertir.
     * @return Regresa una lista de entidades ConsultasDTO con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */
    public List<Consulta> toEntityList(List<ConsultaDTO> consultasDTO) {
        if (consultasDTO == null || consultasDTO.isEmpty()) {
            return new ArrayList<>();
        }
        return consultasDTO.stream().map(this::toEntity).collect(Collectors.toList());
    }
    
}
