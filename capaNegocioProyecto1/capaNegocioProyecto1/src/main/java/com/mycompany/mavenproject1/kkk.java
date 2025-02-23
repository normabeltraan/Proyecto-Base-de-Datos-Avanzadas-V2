/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.mavenproject1;

import BO.MedicoBO;
import BO.PacienteBO;
import DTO.ConsultaDTO;
import Exception.NegocioException;
import conexion.ConexionBD;
import conexion.IConexionBD;
import java.util.List;

/**
 *
 * @author katia
 */
public class kkk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NegocioException {
        
        /**
        IConexionBD conexionBD = new ConexionBD();
        PacienteBO pacienteBO = new PacienteBO(conexionBD);
        String nombrePaciente = "Karla Cota Hernandez";
        List<ConsultaDTO> historial = pacienteBO.obtenerHistorialConsultasDelPaciente(nombrePaciente);
        System.out.println("Historial consultas paciente");
        for (ConsultaDTO consulta : historial) {
            System.out.println("Fecha y hora de la cita: " + consulta.getCita().getFecha_hora());
            System.out.println("Especialidad: " + consulta.getCita().getMedico().getEspecialidad());
            System.out.println("Diagnóstico: " + consulta.getDiagnostico());
            System.out.println("Tratamiento: " + consulta.getTratamiento());
            System.out.println("-----------------------------------");
        
        /**
        System.out.println("Historial consultas paciente");
        for (ConsultaDTO consulta : historial){
            System.out.println(consulta);
        }
        
        }
        **/
    }
        
    
}
