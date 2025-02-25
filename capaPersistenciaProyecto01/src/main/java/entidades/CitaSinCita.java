/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 * Esta clase representa una cita médica de emergencia que se genera sin cita previa.
 * Más que nada se utiliza para casos en los que el paciente requiera una atención inmediata.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class CitaSinCita {

    private Cita cita;
    private String folio_emergencia;

    /**
     * El constructor por defecto de la clase.
     */
    public CitaSinCita() {
    }

    /**
     * Este es el contructor que inicializa una cita de emergencia con una cita y un folio de emergencia.
     * @param cita La cita de emergencia en cuestión.
     * @param folio_emergencia El folio de emergencia necesario para poder atender la cita.
     */
    public CitaSinCita(Cita cita, String folio_emergencia) {
        this.cita = cita;
        this.folio_emergencia = folio_emergencia;
    }

    /**
     * Constructor que solamente inicializa el folio de emergencia sin asociar a una cita.
     * @param folio_emergencia El folio de emergencia de la cita de emergencia.
     */
    public CitaSinCita(String folio_emergencia) {
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
     * @return Regresa una cadena de texto con la información de la cita y el folio de emergencia.
     */
    @Override
    public String toString() {
        return "CitaSinCita{" + "cita=" + cita + ", folioEmergencia=" + folio_emergencia + '}';
    }

}
