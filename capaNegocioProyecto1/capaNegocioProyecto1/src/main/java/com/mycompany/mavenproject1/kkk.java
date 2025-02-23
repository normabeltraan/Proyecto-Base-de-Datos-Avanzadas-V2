/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mavenproject1;

import BO.MedicoBO;
import BO.PacienteBO;
import DAO.CitaDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DTO.CitaDTO;
import DTO.ConsultaDTO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.PacienteMapper;
import conexion.ConexionBD;
import conexion.IConexionBD;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author katia
 */
//public class kkk {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws NegocioException {
//        
//        IConexionBD conexionBD = new ConexionBD();
//        
//        
//        PacienteBO pacienteBO = new PacienteBO(conexionBD);
//        String nombrePaciente = "Karla Cota Hernandez";
//        
//        
//        List<ConsultaDTO> historial = pacienteBO.obtenerHistorialConsultasDelPaciente(nombrePaciente);
//        
//        System.out.println("Historial consultas paciente");
//        for (ConsultaDTO consulta : historial) {
//            System.out.println("Fecha y hora de la cita: " + consulta.getCita().getFecha_hora());
//            System.out.println("Especialidad: " + consulta.getCita().getMedico().getEspecialidad());
//            System.out.println("Diagnóstico: " + consulta.getDiagnostico());
//            System.out.println("Tratamiento: " + consulta.getTratamiento());
//            System.out.println("-----------------------------------");
//        
//        /**
//        System.out.println("Historial consultas paciente");
//        for (ConsultaDTO consulta : historial){
//            System.out.println(consulta);
//        }
//        **/
//        }
//    }
//        
//    
//}
public class kkk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegocioException {
        
        try{
            IConexionBD conexion = new ConexionBD();
            MedicoBO medicoBO = new MedicoBO(conexion);
            int idMedico = 3; 
            
            List<CitaDTO> citas = medicoBO.obtenerAgendaMedico(idMedico);

            System.out.println("Agenda del Médico con ID " + idMedico + ":");
            for (CitaDTO cita : citas) {
                LocalDateTime fechaHora = cita.getFecha_hora().toLocalDateTime();
                String hora = fechaHora.format(DateTimeFormatter.ofPattern("HH:mm"));
                System.out.println("Hora: " + hora + ", Paciente: " + cita.getPaciente().getNombre());
            }
        } catch (NegocioException e) {
            System.err.println("Error: " + e.getMessage());
        }
    

//        try {
//            IConexionBD conexion = new ConexionBD();
//
//            PacienteBO pacienteBO = new PacienteBO(conexion);
//            UsuarioDTO usuarioDTO = new UsuarioDTO();
//
//            PacienteDTO pacienteDTO = new PacienteDTO();
//            pacienteDTO.setUsuario(usuarioDTO);
//            pacienteDTO.setNombre("Karla");
//            pacienteDTO.setApellido_paterno("Cota");
//            pacienteDTO.setApellido_materno("Hernandez");
//
//            List<CitaDTO> citas = pacienteBO.obtenerCitasProgramadas(pacienteDTO);
//
//            System.out.println("Citas programadas:");
//            for (CitaDTO cita : citas) {
//                System.out.println("Fecha y hora: " + cita.getFecha_hora());
//                System.out.println("Especialidad: " + cita.getMedico().getEspecialidad());
//                System.out.println("Médico: " + cita.getMedico().getNombre());
//                System.out.println("-----------------------------------");
//            }
//
//        } catch (NegocioException e) {
//            System.err.println("Error al obtener citas programadas: " + e.getMessage());
//        }

        /**
         * IConexionBD conexionBD = new ConexionBD(); PacienteBO pacienteBO =
         * new PacienteBO(conexionBD); String nombrePaciente = "Karla Cota
         * Hernandez"; List<ConsultaDTO> historial =
         * pacienteBO.obtenerHistorialConsultasDelPaciente(nombrePaciente);
         * System.out.println("Historial consultas paciente"); for (ConsultaDTO
         * consulta : historial) { System.out.println("Fecha y hora de la cita:
         * " + consulta.getCita().getFecha_hora());
         * System.out.println("Especialidad: " +
         * consulta.getCita().getMedico().getEspecialidad());
         * System.out.println("Diagnóstico: " + consulta.getDiagnostico());
         * System.out.println("Tratamiento: " + consulta.getTratamiento());
         * System.out.println("-----------------------------------");
         *
         * /**
         * System.out.println("Historial consultas paciente"); for (ConsultaDTO
         * consulta : historial){ System.out.println(consulta); }
         *
         * }
         *
         */
    }

}
