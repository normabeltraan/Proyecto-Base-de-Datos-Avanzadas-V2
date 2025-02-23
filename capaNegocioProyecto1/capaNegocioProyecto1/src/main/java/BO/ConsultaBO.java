/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.ConsultaDAO;
import DAO.IConsultaDAO;
import DTO.ConsultaDTO;
import Mapper.ConsultaMapper;
import conexion.IConexionBD;
import entidades.Consulta;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano
 */
public class ConsultaBO {

    private static final Logger logger = Logger.getLogger(ConsultaBO.class.getName());
    private final IConsultaDAO consultaDAO;
    private final ConsultaMapper mapper = new ConsultaMapper();

    public ConsultaBO(IConexionBD conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
    }

    public List<ConsultaDTO> obtenerHistorialConsultas(String nombrePaciente, String especialidad, Date fechaInicio, Date fechaFin) throws PersistenciaException {
        if (nombrePaciente == null || fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("El nombre del paciente y las fechas de inicio y fin son obligatorios.");
        }
        
        List<Consulta> consultas = consultaDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente, especialidad, fechaInicio, fechaFin);
        return mapper.toDTOList(consultas);
    }

}
