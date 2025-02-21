/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

/**
 *
 * @author norma
 */
public class CitaSinCita {

    private Cita cita;
    private String folio_emergencia;

    public CitaSinCita() {
    }

    public CitaSinCita(Cita cita, String folio_emergencia) {
        this.cita = cita;
        this.folio_emergencia = folio_emergencia;
    }

    public CitaSinCita(String folio_emergencia) {
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

    public void setFolio_emergencia(String folioEmergencia) {
        this.folio_emergencia = folioEmergencia;
    }

    @Override
    public String toString() {
        return "CitaSinCita{" + "cita=" + cita + ", folioEmergencia=" + folio_emergencia + '}';
    }

}
