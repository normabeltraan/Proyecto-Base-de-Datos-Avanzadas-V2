/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Medico;
import entidades.Paciente;
import java.sql.Timestamp;
        

/**
 *
 * @author Maximiliano
 */
public class CitaDTO 
{
    private int id_cita;
    private Timestamp  fecha_hora;
    private String estado;
    private String tipo;
    private Paciente paciente;
    private Medico medico;

    public CitaDTO() {
    }

    public CitaDTO(int id_cita, Timestamp  fecha_hora, String estado, String tipo, Paciente paciente, Medico medico) {
        this.id_cita = id_cita;
        this.fecha_hora = fecha_hora;
        this.estado = estado;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    public CitaDTO(Timestamp  fecha_hora, String estado, String tipo, Paciente paciente, Medico medico) {
        this.fecha_hora = fecha_hora;
        this.estado = estado;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public Timestamp  getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp  fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "CitaDTO{" + "id_cita=" + id_cita + ", fecha_hora=" + fecha_hora + ", estado=" + estado + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }
}
