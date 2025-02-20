/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import BO.CitaBO;
import BO.MedicoBO;
import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaDTO;
import DTO.HorarioDisponibleDTO;
import DTO.MedicoDTO;
import DTO.PacienteDTO;
import Exception.NegocioException;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;
import conexion.ConexionBD;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Direccion;
import entidades.HorarioDisponible;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Maximiliano
 */
public class capaNegocioPrueba {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IConexionBD conexionBD = new ConexionBD();

        MedicoBO medicoBO = new MedicoBO(conexionBD);
        CitaBO citaBO = new CitaBO(conexionBD);

        try {
            System.out.println("Selecciona una especialidad:");
            List<String> especialidades = medicoBO.obtenerEspecialidades();
            for (int i = 0; i < especialidades.size(); i++) {
                System.out.println((i + 1) + ". " + especialidades.get(i));
            }

            int especialidadIndex = scanner.nextInt();
            String especialidadSeleccionada = especialidades.get(especialidadIndex - 1);

            List<MedicoDTO> medicos = medicoBO.obtenerMedicosPorEspecialidad(especialidadSeleccionada);
            System.out.println("Médicos disponibles para la especialidad '" + especialidadSeleccionada + "':");
            for (int i = 0; i < medicos.size(); i++) {
                MedicoDTO medico = medicos.get(i);
                System.out.println((i + 1) + ". " + medico.getNombre() + " " + medico.getApellido_paterno());
            }

            int medicoIndex = scanner.nextInt();
            MedicoDTO medicoSeleccionado = medicos.get(medicoIndex - 1);

            System.out.println("Introduce la fecha (YYYY-MM-DD):");
            String fechaStr = scanner.next();
            Date fechaConsultada = Date.valueOf(fechaStr);

            List<HorarioDisponibleDTO> horarios = citaBO.obtenerHorariosDisponibles(fechaConsultada, medicoSeleccionado.getUsuario().getId_usuario());
            System.out.println("Horarios disponibles para el día " + fechaStr + ":");
            for (int i = 0; i < horarios.size(); i++) {
                System.out.println((i + 1) + ". " + horarios.get(i).getHora_disponible());
            }

            int horarioIndex = scanner.nextInt();
            HorarioDisponibleDTO horarioSeleccionado = horarios.get(horarioIndex - 1);

            System.out.println("Agendando la cita...");

            Time horaDisponible = horarioSeleccionado.getHora_disponible();
            String horaStr = horaDisponible.toString();
            Timestamp fechaHora = Timestamp.valueOf(fechaStr + " " + horaStr);

            //Timestamp fechaHora = Timestamp.valueOf(fechaStr + " " + horarioSeleccionado.getHora_disponible() + ":00");
            CitaDTO citaDTO = new CitaDTO();
            citaDTO.setFecha_hora(fechaHora);
            MedicoMapper medicoMapper = new MedicoMapper();
            Medico medico = medicoMapper.toEntity(medicoSeleccionado);
            citaDTO.setMedico(medico);

            Direccion direccionPaciente = new Direccion("Centro", "Obregon", "Pitagoras");
            Usuario usuarioPaciente = new Usuario(1, "karlacota", "karlita12");
            Paciente paciente = new Paciente(usuarioPaciente, "Karla", "Cota", "Hernandez", "7715289543", LocalDate.of(1990, 12, 12), "karlacotaher@example.com", direccionPaciente, new ArrayList<>());
            List<Cita> citasPaciente = new ArrayList<>();
            PacienteDTO pacienteDTO = new PacienteDTO(
                    usuarioPaciente,
                    "Karla",
                    "Cota",
                    "Hernandez",
                    "7715289543",
                    LocalDate.of(1990, 12, 12),
                    "karlacotaher@example.com",
                    direccionPaciente,
                    new ArrayList<>()
            );
            PacienteMapper pacienteMapper = new PacienteMapper();
            Paciente pacienteCita = pacienteMapper.toEntity(pacienteDTO);
            citaDTO.setPaciente(pacienteCita);
            boolean citaAgendada = citaBO.agendarCita(citaDTO);

            if (citaAgendada) {
                System.out.println("Cita agendada con éxito para " + medicoSeleccionado.getNombre() + " el " + fechaStr + " a las " + horarioSeleccionado.getHora_disponible());
            } else {
                System.out.println("Error al agendar la cita.");
            }

        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
