/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import BO.CitaBO;
import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaDTO;
import DTO.HorarioDisponibleDTO;
import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import entidades.Direccion;
import entidades.HorarioDisponible;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class capaNegocioPrueba {

    public static void main(String[] args) {
        try {
            IConexionBD conexion = new ConexionBD();
            CitaBO citaBO = new CitaBO(conexion);

            int idMedico = 5;
            List<HorarioDisponibleDTO> horariosDisponibles = citaBO.obtenerHorariosDisponibles(idMedico);

            System.out.println("Horarios Disponibles:");
            for (HorarioDisponibleDTO horario : horariosDisponibles) {
                System.out.println(horario);
            }

            CitaDTO nuevaCita = new CitaDTO();
            nuevaCita.setFecha_hora(Timestamp.valueOf("2025-02-24 10:00:00"));
            Direccion direccionPaciente = new Direccion("Centro", "Obregon", "Pitagoras");

            Usuario usuarioPaciente = new Usuario(1, "karlacota", "karlita12");

            Paciente paciente = new Paciente(usuarioPaciente, "Karla", "Cota", "Hernandez", "7715289543", LocalDate.of(1990, 12, 12), "karlacotaher@example.com", direccionPaciente, new ArrayList<>());

            nuevaCita.setPaciente(paciente);

            Usuario usuarioMedico = new Usuario(5, "norma", "norma123");

            Medico medico = new Medico(usuarioMedico, "Alicia", "Cano", "Arias", "Activo", "Cardiología", "123456", new ArrayList<>(), new ArrayList<>());
            nuevaCita.setMedico(medico);

            boolean citaAgendada = citaBO.agendarCita(nuevaCita);
            if (citaAgendada) {
                System.out.println("Cita agendada exitosamente.");
            } else {
                System.out.println("No se pudo agendar la cita.");
            }

            System.out.println("\nHorarios Disponibles después de agendar la cita:");
            horariosDisponibles = citaBO.obtenerHorariosDisponibles(idMedico);  
            
            for (HorarioDisponibleDTO horario : horariosDisponibles) {
                System.out.println(horario);
            }

        } catch (NegocioException e) {
            System.out.println("Error en la operación: " + e.getMessage());
        }

    }
}
