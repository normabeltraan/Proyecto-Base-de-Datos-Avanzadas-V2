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
 *
 * @author Maximiliano
 */
public class CitaSinCitaMapper {

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
