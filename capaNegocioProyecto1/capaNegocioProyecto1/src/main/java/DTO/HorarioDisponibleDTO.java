/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;
import java.sql.Time;

/**
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad Horario Disponible.
 * Se utiliza para transportar información de los horarios disponibles entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class HorarioDisponibleDTO {

    private Date fecha;
    private Time hora_disponible;

    /**
     * El constructor que inicializa los atributos del horario disponible.
     * @param fecha La fecha en la cual están disponibles los horarios.
     * @param hora_disponible La hora del consultorio que está disponible.
     */
    public HorarioDisponibleDTO(Date fecha, Time hora_disponible) {
        this.fecha = fecha;
        this.hora_disponible = hora_disponible;
    }

    /**
     * Se obtiene la fecha del horario disponible.
     * @return Se regresa la fecha del horario disponible.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Se obtiene la hora disponible de la consulta.
     * @return Se regresa la hora disponible de la consulta.
     */
    public Time getHora_disponible() {
        return hora_disponible;
    }

    /**
     * Representación en cadena de la información de el horario disponible.
     * @return Regresa una cadena de texto con la información del horario disponible.
     */
    @Override
    public String toString() {
        return "HorarioDisponible{" + "fecha=" + fecha + ", hora_disponible=" + hora_disponible + '}';
    }
}
