/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Cita;

/**
 *
 * @author Maximiliano
 */
public class CitaSinCitaDTO 
{
    private Cita cita;
    private String folio_emergencia;

    public CitaSinCitaDTO() {
    }

    public CitaSinCitaDTO(Cita cita, String folio_emergencia) {
        this.cita = cita;
        this.folio_emergencia = folio_emergencia;
    }

    public CitaSinCitaDTO(String folio_emergencia) {
        this.folio_emergencia = folio_emergencia;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String getFolio_emergencia() {
        return folio_emergencia;
    }

    public void setFolio_emergencia(String folio_emergencia) {
        this.folio_emergencia = folio_emergencia;
    }

    @Override
    public String toString() {
        return "CitaSinCitaDTO{" + "cita=" + cita + ", folioEmergencia=" + folio_emergencia + '}';
    } 
}
