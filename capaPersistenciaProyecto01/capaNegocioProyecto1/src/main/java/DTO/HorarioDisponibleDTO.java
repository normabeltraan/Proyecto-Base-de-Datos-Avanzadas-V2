/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author norma
 */
public class HorarioDisponibleDTO {

    private Date fecha;
    private String hora_disponible;

    public HorarioDisponibleDTO(Date fecha, String hora_disponible) {
        this.fecha = fecha;
        this.hora_disponible = hora_disponible;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora_disponible() {
        return hora_disponible;
    }

    @Override
    public String toString() {
        return "HorarioDisponible{" + "fecha=" + fecha + ", hora_disponible=" + hora_disponible + '}';
    }
}
