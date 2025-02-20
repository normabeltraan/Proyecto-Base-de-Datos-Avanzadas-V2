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
public class ConsultaDTO 
{
    private int id_consulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private Cita cita;

    public ConsultaDTO() {
    }

    public ConsultaDTO(int id_consulta, String diagnostico, String tratamiento, String observaciones, Cita cita) {
        this.id_consulta = id_consulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.cita = cita;
    }

    public ConsultaDTO(String diagnostico, String tratamiento, String observaciones, Cita cita) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.cita = cita;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    @Override
    public String toString() {
        return "ConsultaDTO{" + "id_consulta=" + id_consulta + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", observaciones=" + observaciones + ", cita=" + cita + '}';
    }
}
