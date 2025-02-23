/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.proyecto01.capapersistenciaproyecto01;

import DAO.CitaDAO;
import DAO.CitaSinCitaDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import conexion.ConexionBD;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.CitaSinCita;
import entidades.Consulta;
import entidades.Direccion;
import entidades.Medico;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class CapaPersistenciaProyecto01 {

    public static void main(String[] args) throws SQLException {

        IConexionBD conexionBD = new ConexionBD();

        try (Connection conexion = conexionBD.crearConexion();) {

            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (PersistenciaException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
        /**
        Direccion direccionPaciente = new Direccion("Centro", "Obregon", "Pitagoras");
        Usuario usuarioPaciente = new Usuario(1,"karlacota", "karlita12");
        Paciente paciente = new Paciente(usuarioPaciente, "Karla", "Cota", "Hernandez", "7715289543", LocalDate.of(1990, 12, 12), "karlacotaher@example.com", direccionPaciente, new ArrayList<>());
        Usuario usuarioMedico = new Usuario(3,"jose", "josesan78");
        Medico medico = new Medico(usuarioMedico, "José", "Sanchez", "Martinez", "Activo", "Cardiología", "6723984", new ArrayList<>(), new ArrayList<>());
        Cita cita = new Cita(Timestamp.valueOf("2025-02-24 10:00:00"), "Agendada", "Cita Agendada", paciente, medico);
        
        CitaDAO citaDAO = new CitaDAO(conexionBD);
        
        try {
            boolean result = citaDAO.agendarCita(cita);
            if (result) {
                System.out.println("La cita fue agendada con éxito.");
            } else {
                System.out.println("Problema al agendar la cita.");
            }
        } catch (PersistenciaException e) {
            System.out.println("Error al agendar la cita: " + e.getMessage());
        }
        **/
        
        
        /**
        // PROBANDO METODO OBTENER HISTORIAL DE CONSULTAS DE UN PACIENTE
        try {
            IConexionBD conexion = new ConexionBD(); 
            PacienteDAO pacienteDAO = new PacienteDAO(conexion);
            String nombrePaciente = "Karla Cota Hernandez"; 
            List<Consulta> historial = pacienteDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente);
            //historial.forEach(System.out::println);
            //**
            for (Consulta consulta : historial) {
                //System.out.println("Consulta ID: " + consulta.getId_consulta());
                //System.out.println("Especialidad: " + consulta.getCita().getMedico().getEspecialidad());
                //System.out.println("Medico: " + consulta.getCita().getMedico().getNombre() + " " + consulta.getCita().getMedico().getApellido_paterno());
                //System.out.println("Médico: " + consulta.getCita().getMedico().getUsuario().getNombre_usuario());
                System.out.println("Fecha y hora de la cita: " + consulta.getCita().getFecha_hora());
                System.out.println("Tipo: " + consulta.getCita().getTipo());
                System.out.println("Estado: " + consulta.getCita().getEstado());
                System.out.println("Diagnóstico: " + consulta.getDiagnostico());
                //System.out.println("Observaciones: " + consulta.getObservaciones());
                System.out.println("Tratamiento: " + consulta.getTratamiento());
                //System.out.println("Paciente: " + consulta.getCita().getPaciente().getUsuario().getNombre_usuario());
                System.out.println("-----------------------------------");
               
            }
            
        } catch (PersistenciaException e) {
            System.err.println("Error al obtener el historial: " + e.getMessage());
        }
        **/
        
        // PROBANDO MÉTODO PARA CITAS PROGRAMADAS
//        try {
//            IConexionBD conexion = new ConexionBD(); 
//            PacienteDAO pacienteDAO = new PacienteDAO(conexion);
//            
//            Usuario usuarioPac = new Usuario();
//            usuarioPac.setId_usuario(1);
//            
//            Paciente paciente = new Paciente();
//            paciente.setUsuario(usuarioPac);
//            
//            List<Cita> proximas = pacienteDAO.obtenerCitasProgramadas(paciente);
//            
//            for (Cita cita : proximas) {
//                System.out.println("Fecha y hora: " + cita.getFecha_hora());
//                System.out.println("Especialidad: " + cita.getMedico().getEspecialidad());
//                System.out.println("Médico: " + cita.getMedico().getNombre());
//                System.out.println("-----------------------------------");
//            }
//        } catch(PersistenciaException ex){
//            System.err.println("Error al obtener citas proximas: " + ex.getMessage());
//        }

                try {
                    IConexionBD conexion = new ConexionBD();

                    PacienteDAO pacienteDAO = new PacienteDAO(conexion);

                    String nombre = "Karla";
                    String apellidoPaterno = "Cota";
                    String apellidoMaterno = "Hernandez";

                    int idPaciente = pacienteDAO.obtenerIdPacientePorNombre(nombre, apellidoPaterno, apellidoMaterno);

                    if (idPaciente != -1) {
                        System.out.println("El ID del paciente es: " + idPaciente);
                    } else {
                        System.out.println("Paciente no encontrado.");
                    }
                } catch (PersistenciaException ex) {
                    System.err.println("Error: " + ex.getMessage());
                }
        
    }
        
}
