/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Medico;
import java.sql.Time;

/**
 *
 * @author Maximiliano
 */
public class HorarioDTO 
{
    private int id_horario;
    private int dia;         
    private Time hora_entrada; 
    private Time hora_salida;  
    private Medico medico; 

    public HorarioDTO() {
    }

    public HorarioDTO(int id_horario, int dia, Time hora_entrada, Time hora_salida, Medico medico) {
        this.id_horario = id_horario;
        this.dia = dia;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.medico = medico;
    }

    public HorarioDTO(int dia, Time hora_entrada, Time hora_salida, Medico medico) {
        this.dia = dia;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.medico = medico;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Time getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "HorarioDTO{" + "id_horario=" + id_horario + ", dia=" + dia + ", hora_entrada=" + hora_entrada + ", hora_salida=" + hora_salida + ", medico=" + medico + '}';
    } 
}
