/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Medico;
import java.sql.Time;

/**
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad Horario.
 * Se utiliza para transportar información de horarios entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class HorarioDTO 
{
    private int id_horario;
    private int dia;         
    private Time hora_entrada; 
    private Time hora_salida;  
    private Medico medico; 

    /**
     * El constructor por defecto de la clase.
     */
    public HorarioDTO() {
    }

    /**
     * El constructor que inicializa los atributos del horario incluyendo su ID.
     * @param id_horario El identificador único del horario.
     * @param dia El día en el que el médico atiende.
     * @param hora_entrada La hora del médico en la cual empieza a atender.
     * @param hora_salida La hora del médico en la cual deja de atender.
     * @param medico El médico en cuestión a cargo de las consultas.
     */
    public HorarioDTO(int id_horario, int dia, Time hora_entrada, Time hora_salida, Medico medico) {
        this.id_horario = id_horario;
        this.dia = dia;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.medico = medico;
    }

    /**
     * Constructor que inicializa los atributos exceptuando el ID del horario.
     * @param dia El día que el doctor atiende en el consultorio.
     * @param hora_entrada La hora en la que el doctor empieza a atender a los pacientes.
     * @param hora_salida La hora en la que el doctor deja de atender pacientes.
     * @param medico El médico encargado de las consultas.
     */
    public HorarioDTO(int dia, Time hora_entrada, Time hora_salida, Medico medico) {
        this.dia = dia;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.medico = medico;
    }

    /**
     * Se obtiene el identificador único del horario.
     * @return Se regresa el identificador único del horario.
     */
    public int getId_horario() {
        return id_horario;
    }

    /**
     * Se establece el identificador único del horario.
     * @param id_horario El identificador único del horario.
     */
    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    /**
     * Se obtiene el día en el que el médico atiende.
     * @return Se regresa el día en el que el médico atiende.
     */
    public int getDia() {
        return dia;
    }

    /**
     * Se establece el día en el que el médico atiende.
     * @param dia El día en el que el médico atiende.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Se obtiene la hora en el que el médico empieza a atender en el consultorio.
     * @return Se regresa la hora en el que el médico empieza a atender en el consultorio.
     */
    public Time getHora_entrada() {
        return hora_entrada;
    }

    /**
     * Se establece la hora en el que el médico empieza a atender en el consultorio.
     * @param hora_entrada La hora de entrada en el que el médico empieza a atender en el consultorio. 
     */
    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    /**
     * Se obtiene la hora en el que el médico deja de atender en el consultorio.
     * @return Se regresa la hora en el que el médico deja de atender en el consultorio.
     */
    public Time getHora_salida() {
        return hora_salida;
    }

    /**
     * Se establece la hora en el que el médico deja de atender en el consultorio.
     * @param hora_salida La hora en el que el médico deja de atender en el consultorio.
     */
    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    /**
     * Se obtiene el médico que atiende el consultorio. 
     * @return Se regresa el médico que atiende el consultorio.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Se establece el médico que atiende el consultorio.
     * @param medico El médico que atiende el consultorio.
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Representación en cadena de la información de los horarios del médico.
     * @return Regresa una cadena de texto con la información de los horarios DTO del médico.
     */
    @Override
    public String toString() {
        return "HorarioDTO{" + "id_horario=" + id_horario + ", dia=" + dia + ", hora_entrada=" + hora_entrada + ", hora_salida=" + hora_salida + ", medico=" + medico + '}';
    } 
}
