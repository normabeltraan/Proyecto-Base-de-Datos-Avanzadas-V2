/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuracion;

import BO.PacienteBO;
import BO.CitaBO;
import BO.CitaSinCitaBO;
import BO.ConsultaBO;
import BO.DireccionBO;
import BO.HorarioBO;
import BO.MedicoBO;
import BO.UsuarioBO;
import conexion.ConexionBD;
import conexion.IConexionBD;

/**
 *
 * @author Maximiliano
 */
public class DependencyInjector 
{
//    /**
//     * 
//     * @return 
//     */
//    public static PacienteBO crearPacienteBO() {
//        IConexionBD conexion = new ConexionBD();
//        PacienteBO pacienteBO = new PacienteBO(conexion);
//
//        return pacienteBO;
//    }
//    
//    /**
//     * 
//     * @return 
//     */
//    public static MedicoBO crearMedicoBO() {
//        IConexionBD conexion = new ConexionBD();
//        MedicoBO medicoBO = new MedicoBO(conexion);
//
//        return medicoBO;
//    }
//    
//    /**
//     * 
//     * @return 
//     */
//    public static UsuarioBO crearUsuarioBO() {
//        IConexionBD conexion = new ConexionBD();
//        UsuarioBO usuarioBO = new UsuarioBO(conexion);
//
//        return usuarioBO;
//    }
//    
    /**
     * 
     * @return 
     */
    public static CitaBO crearCitaBO() {
        IConexionBD conexion = new ConexionBD();
        CitaBO citaBO = new CitaBO(conexion);

        return citaBO;
    }
    
//    /**
//     * 
//     * @return 
//     */
//    public static CitaSinCitaBO crearCitaSinCitaBO() {
//        IConexionBD conexion = new ConexionBD();
//        CitaSinCitaBO citaSinCitaBO = new CitaSinCitaBO(conexion);
//
//        return citaSinCitaBO;
//    }
//    
//    /**
//     * 
//     * @return 
//     */
//    public static ConsultaBO crearConsultaBO() {
//        IConexionBD conexion = new ConexionBD();
//        ConsultaBO consultaBO = new ConsultaBO(conexion);
//
//        return consultaBO;
//    }
//    
//    /**
//     * 
//     * @return 
//     */
//    public static DireccionBO crearDireccionBO() {
//        IConexionBD conexion = new ConexionBD();
//        DireccionBO direccionBO = new DireccionBO(conexion);
//
//        return direccionBO;
//    }
//    
//    /**
//     * 
//     * @return 
//     */
//    public static HorarioBO crearHorarioBO() {
//        IConexionBD conexion = new ConexionBD();
//        HorarioBO horarioBO = new HorarioBO(conexion);
//
//        return horarioBO;
//    }
}
