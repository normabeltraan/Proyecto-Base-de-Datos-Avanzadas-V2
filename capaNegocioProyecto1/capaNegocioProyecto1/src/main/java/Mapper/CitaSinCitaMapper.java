/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.CitaSinCitaDTO;
import entidades.Cita;
import entidades.CitaSinCita;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir entre entidades citaSinCita y objetos de transferencia de datos CitaSinCitaDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaSinCitaMapper {

    /**
     * Este método convierte un objeto de tipo CitaSinCitaDTO a una entidad CitaSinCita.
     * @param citaSinCitaDTO El objeto ConsultaDTO que se desea convertir a entidad.
     * @return Regresa una instancia de la CitaSinCita con los mismos datos que el DTO o null si la entrada es null.
     */
    public CitaSinCita toEntity(CitaSinCitaDTO citaSinCitaDTO) {
        if (citaSinCitaDTO == null) {
            return null;
        }
        Cita cita = new Cita(
                citaSinCitaDTO.getCita().getFecha_hora(),
                citaSinCitaDTO.getCita().getEstado(),
                citaSinCitaDTO.getCita().getTipo(),
                citaSinCitaDTO.getCita().getPaciente(),
                citaSinCitaDTO.getCita().getMedico()
        );
        return new CitaSinCita(cita, citaSinCitaDTO.getFolio_emergencia());
    }

    /**
     * Este método convierte una entidad CitaSinCita a un objeto de transferencia de datos CitaSinCitaDTO.
     * @param citaSinCita La entidad que se desea convertir a DTO.
     * @return Regresa una instancia de CitaSinCitaDTO con los mismos datos que la entidad o null si la entrada es null.
     */
    public CitaSinCitaDTO toDTO(CitaSinCita citaSinCita) {
        if (citaSinCita == null) {
            return null;
        }
        Cita cita = citaSinCita.getCita(); 
        return new CitaSinCitaDTO(
                cita, 
                citaSinCita.getFolio_emergencia()
        );
    }

    /**
     * Este método convierte una lista de entidades CitaSinCita en una lista de objetos CitaSinCitaDTO.
     * @param citasSinCita Lista de entidades CitaSinCita a convertir.
     * @return Regresa una lista de CitaSinCitaDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */
    public List<CitaSinCitaDTO> toDTOList(List<CitaSinCita> citasSinCita) {
        if (citasSinCita == null || citasSinCita.isEmpty()) {
            return new ArrayList<>();
        }
        List<CitaSinCitaDTO> citasSinCitaDTOs = new ArrayList<>();
        for (CitaSinCita citaSinCita : citasSinCita) {
            citasSinCitaDTOs.add(toDTO(citaSinCita));
        }
        return citasSinCitaDTOs;
    }

    /**
     * Este método convierte una lista de objetos CitaSinCitaDTO en una lista de entidades Consulta.
     * @param citasSinCitaDTOs Lista de objetos CitaSinCitaDTO a convertir.
     * @return Regresa una lista de entidades CitaSinCita con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */
    public List<CitaSinCita> toEntityList(List<CitaSinCitaDTO> citasSinCitaDTOs) {
        if (citasSinCitaDTOs == null || citasSinCitaDTOs.isEmpty()) {
            return new ArrayList<>();
        }
        List<CitaSinCita> citasSinCita = new ArrayList<>();
        for (CitaSinCitaDTO citaSinCitaDTO : citasSinCitaDTOs) {
            citasSinCita.add(toEntity(citaSinCitaDTO));
        }
        return citasSinCita;
    }
}
