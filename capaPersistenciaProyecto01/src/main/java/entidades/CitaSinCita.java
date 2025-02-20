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
    private String folioEmergencia;

    public CitaSinCita() {
    }

    public CitaSinCita(Cita cita, String folioEmergencia) {
        this.cita = cita;
        this.folioEmergencia = folioEmergencia;
    }

    public CitaSinCita(String folioEmergencia) {
        this.folioEmergencia = folioEmergencia;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public String getFolioEmergencia() {
        return folioEmergencia;
    }

    public void setFolioEmergencia(String folioEmergencia) {
        this.folioEmergencia = folioEmergencia;
    }

    @Override
    public String toString() {
        return "CitaSinCita{" + "cita=" + cita + ", folioEmergencia=" + folioEmergencia + '}';
    }

}
