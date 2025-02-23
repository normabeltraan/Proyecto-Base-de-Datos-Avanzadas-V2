/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import BO.CitaBO;
import BO.CitaSinCitaBO;
import BO.MedicoBO;
import BO.PacienteBO;
import BO.UsuarioBO;
import DAO.CitaDAO;
import DAO.ICitaDAO;
import DTO.CitaDTO;
import DTO.CitaSinCitaDTO;
import DTO.ConsultaDTO;
import DTO.HorarioDisponibleDTO;
import DTO.MedicoDTO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;
import Exception.NegocioException;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;
import Mapper.UsuarioMapper;
import static com.mysql.cj.conf.PropertyKey.logger;
import conexion.ConexionBD;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Direccion;
import entidades.HorarioDisponible;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
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

    public static void main(String[] args) throws NegocioException, PersistenciaException {
        //Prueba agendar cita
//        Scanner scanner = new Scanner(System.in);
        IConexionBD conexionBD = new ConexionBD();
//
        MedicoBO medicoBO = new MedicoBO(conexionBD);
        
        
        
//        CitaBO citaBO = new CitaBO(conexionBD);
//
//        try {
//            System.out.println("Selecciona una especialidad:");
//            List<String> especialidades = medicoBO.obtenerEspecialidades();
//            for (int i = 0; i < especialidades.size(); i++) {
//                System.out.println((i + 1) + ". " + especialidades.get(i));
//            }
//
//            int especialidadIndex = scanner.nextInt();
//            String especialidadSeleccionada = especialidades.get(especialidadIndex - 1);
//
//            List<MedicoDTO> medicos = medicoBO.obtenerMedicosPorEspecialidad(especialidadSeleccionada);
//            System.out.println("Médicos disponibles para la especialidad '" + especialidadSeleccionada + "':");
//            for (int i = 0; i < medicos.size(); i++) {
//                MedicoDTO medico = medicos.get(i);
//                System.out.println((i + 1) + ". " + medico.getNombre() + " " + medico.getApellido_paterno());
//            }
//
//            int medicoIndex = scanner.nextInt();
//            MedicoDTO medicoSeleccionado = medicos.get(medicoIndex - 1);
//
//            System.out.println("Introduce la fecha (YYYY-MM-DD):");
//            String fechaStr = scanner.next();
//            Date fechaConsultada = Date.valueOf(fechaStr);
//
//            List<HorarioDisponibleDTO> horarios = citaBO.obtenerHorariosDisponibles(fechaConsultada, medicoSeleccionado.getUsuario().getId_usuario());
//            System.out.println("Horarios disponibles para el día " + fechaStr + ":");
//            for (int i = 0; i < horarios.size(); i++) {
//                System.out.println((i + 1) + ". " + horarios.get(i).getHora_disponible());
//            }
//
//            int horarioIndex = scanner.nextInt();
//            HorarioDisponibleDTO horarioSeleccionado = horarios.get(horarioIndex - 1);
//
//            System.out.println("Agendando la cita...");
//
//            Time horaDisponible = horarioSeleccionado.getHora_disponible();
//            String horaStr = horaDisponible.toString();
//            Timestamp fechaHora = Timestamp.valueOf(fechaStr + " " + horaStr);
//
//            //Timestamp fechaHora = Timestamp.valueOf(fechaStr + " " + horarioSeleccionado.getHora_disponible() + ":00");
//            CitaDTO citaDTO = new CitaDTO();
//            citaDTO.setFecha_hora(fechaHora);
//            MedicoMapper medicoMapper = new MedicoMapper();
//            Medico medico = medicoMapper.toEntity(medicoSeleccionado);
//            citaDTO.setMedico(medico);
//
//            Direccion direccionPaciente = new Direccion("Centro", "Obregon", "Pitagoras");
//            Usuario usuarioPaciente = new Usuario(1, "karlacota", "karlita12");
//            Paciente paciente = new Paciente(usuarioPaciente, "Karla", "Cota", "Hernandez", "7715289543", LocalDate.of(1990, 12, 12), "karlacotaher@example.com", direccionPaciente, new ArrayList<>());
//            List<Cita> citasPaciente = new ArrayList<>();
//            PacienteDTO pacienteDTO = new PacienteDTO(
//                    usuarioPaciente,
//                    "Karla",
//                    "Cota",
//                    "Hernandez",
//                    "7715289543",
//                    LocalDate.of(1990, 12, 12),
//                    "karlacotaher@example.com",
//                    direccionPaciente,
//                    new ArrayList<>()
//            );
//            PacienteMapper pacienteMapper = new PacienteMapper();
//            Paciente pacienteCita = pacienteMapper.toEntity(pacienteDTO);
//            citaDTO.setPaciente(pacienteCita);
//            boolean citaAgendada = citaBO.agendarCita(citaDTO);
//
//            if (citaAgendada) {
//                System.out.println("Cita agendada con éxito para " + medicoSeleccionado.getNombre() + " el " + fechaStr + " a las " + horarioSeleccionado.getHora_disponible());
//            } else {
//                System.out.println("Error al agendar la cita.");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }

        //Prueba cancelar cita
//        Scanner scanner = new Scanner(System.in);
//        IConexionBD conexionBD = new ConexionBD();
//        CitaBO citaBO = new CitaBO(conexionBD);
//
//        try {
//            System.out.println("Introduce el ID de la cita a cancelar:");
//            int idCita = scanner.nextInt();
//
//            CitaDTO citaDTO = new CitaDTO();
//            citaDTO.setId_cita(idCita);
//            citaDTO.setEstado("Activa");
//            String fechaHoraStr = "2025-02-20 08:00:00";  
//
//            Timestamp timestamp = Timestamp.valueOf(fechaHoraStr);
//            citaDTO.setFecha_hora(timestamp);
//
//            System.out.println("Intentando cancelar la cita con ID: " + idCita);
//
//            boolean resultado = citaBO.cancelarCita(citaDTO);
//
//            if (resultado) {
//                System.out.println("La cita fue cancelada exitosamente.");
//            } else {
//                System.out.println("No se pudo cancelar la cita.");
//            }
//        } catch (NegocioException | PersistenciaException e) {
//            System.out.println("Ocurrió un error: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
        //Prueba registrar usuario
//        UsuarioBO usuarioBO = new UsuarioBO(conexionBD);
//        Direccion direccionPaciente = new Direccion("Real", "Hermosillo", "Rosa");
//
//        Usuario usuarioPaciente = new Usuario("daniela", "cano");
//
//        Paciente paciente = new Paciente(
//                usuarioPaciente,
//                "Daniela",
//                "Cano",
//                "Arias",
//                "248",
//                LocalDate.of(2004, 10, 29),
//                "danicano@gmail.com",
//                direccionPaciente,
//                new ArrayList<>()
//        );
//
//        UsuarioMapper usuarioMapper = new UsuarioMapper();
//        PacienteMapper pacienteMapper = new PacienteMapper();
//
//        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuarioPaciente);
//        PacienteDTO pacienteDTO = pacienteMapper.toDTO(paciente);
//
//        try {
//            boolean resultado = usuarioBO.registrarUsuarioPaciente(usuarioDTO, pacienteDTO);
//
//            if (resultado) {
//                System.out.println("Usuario y paciente registrados exitosamente.");
//            } else {
//                System.out.println("Error al registrar el usuario y paciente.");
//            }
//        } catch (NegocioException | PersistenciaException e) {
//            System.out.println("Ocurrió un error: " + e.getMessage());
//        }
        //Prueba iniciar sesion
//        UsuarioDTO usuarioIniciarSesion = new UsuarioDTO();
//        usuarioIniciarSesion.setNombre_usuario("josesanchez");
//        usuarioIniciarSesion.setContrasenia("josesan78");
//
//        boolean autenticado = usuarioBO.iniciarSesion(usuarioIniciarSesion);
//
//        if (autenticado) {
//            System.out.println("Inicio de sesión exitoso.");
//        } else {
//            System.out.println("Error al iniciar sesión.");
//        }

        //Prueba agendar cita de emergencia
//        CitaSinCitaBO citaSinCitaBO = new CitaSinCitaBO(conexionBD);
//        Scanner scanner = new Scanner(System.in);
//
//        try {
//            System.out.println("Introduce el ID del paciente para la cita de emergencia:");
//            int idPaciente = scanner.nextInt();
//
//
//            System.out.println("Selecciona una especialidad para la cita de emergencia:");
//            List<String> especialidades = medicoBO.obtenerEspecialidades();
//            for (int i = 0; i < especialidades.size(); i++) {
//                System.out.println((i + 1) + ". " + especialidades.get(i));
//            }
//
//            int especialidadIndex = scanner.nextInt();
//
//            String especialidadSeleccionada = null;
//
//            if (especialidadIndex > 0 && especialidadIndex <= especialidades.size()) {
//                especialidadSeleccionada = especialidades.get(especialidadIndex - 1);
//                System.out.println("Especialidad seleccionada: " + especialidadSeleccionada);
//            } else {
//                System.out.println("Selección no válida. Por favor, elige un número dentro del rango.");
//            }
//
//            if (especialidadSeleccionada != null) {
//                CitaSinCitaDTO cita = citaSinCitaBO.agendarCitaEmergencia(especialidadSeleccionada, idPaciente);
//
//                if (cita != null) {
//                    System.out.println("Cita de emergencia agendada con éxito para el paciente ID: " + idPaciente);
//                    System.out.println("Folio: " + cita.getFolio_emergencia());
//                    System.out.println("Hora: " + cita.getCita().getFecha_hora());
//                    System.out.println("Médico: " + cita.getCita().getMedico());
//                } else {
//                    System.out.println("Error al agendar la cita de emergencia.");
//                }
//            }
//
//        } catch (Exception e) {
//            System.out.println("Ocurrió un error: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
    }
}
