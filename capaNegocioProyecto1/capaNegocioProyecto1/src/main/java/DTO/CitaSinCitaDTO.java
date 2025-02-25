/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Cita;

/**
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad CitaSinCita.
 * Se utiliza para transportar información de las citas de emergencia entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaSinCitaDTO 
{
    private Cita cita;
    private String folio_emergencia;

    /**
     * El constructor por defecto de la clase.
     */
    public CitaSinCitaDTO() {
    }

    /**
     * Este es el contructor que inicializa una cita de emergencia con una cita y un folio de emergencia.
     * @param cita La cita de emergencia en cuestión.
     * @param folio_emergencia El folio de emergencia necesario para poder atender la cita.
     */
    public CitaSinCitaDTO(Cita cita, String folio_emergencia) {
        this.cita = cita;
        this.folio_emergencia = folio_emergencia;
    }

    /**
     * Constructor que solamente inicializa el folio de emergencia sin asociar a una cita.
     * @param folio_emergencia El folio de emergencia de la cita de emergencia.
     */
    public CitaSinCitaDTO(String folio_emergencia) {
        this.folio_emergencia = folio_emergencia;
    }

    /**
     * Se obtiene la cita asociada a la consulta de emergencia.
     * @return Regresa la cita con la información médica de la cita de emergencia.
     */
    public Cita getCita() {
        return cita;
    }

    /**
     * Se establece la cita médica asociada a la consulta de emergencia.
     * @param cita La cita asignada para la consulta de emergencia.
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Se obtiene el folio único de emergencia de la cita de emergencia. 
     * @return Regresa el folio de emergencia de la cita de emergencia.
     */
    public String getFolio_emergencia() {
        return folio_emergencia;
    }

    /**
     * Se establece el folio único de emergencia de la cita de emergencia
     * @param folioEmergencia El folio de emergencia de la cita de emergencia.
     */
    public void setFolio_emergencia(String folioEmergencia) {
        this.folio_emergencia = folioEmergencia;
    }

    /**
     * Representación en cadena de la información de la cita de emergencia.
     * @return Regresa una cadena de texto con la información de la cita de emergencia DTO y el folio de emergencia.
     */
    @Override
    public String toString() {
        return "CitaSinCitaDTO{" + "cita=" + cita + ", folioEmergencia=" + folio_emergencia + '}';
    } 
}
