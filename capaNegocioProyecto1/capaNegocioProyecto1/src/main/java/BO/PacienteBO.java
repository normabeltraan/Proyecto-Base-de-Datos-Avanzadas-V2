/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IPacienteDAO;
import DAO.PacienteDAO;
import DTO.ConsultaDTO;
import DTO.PacienteDTO;
import DTO.UsuarioDTO;
import Exception.NegocioException;
import Mapper.ConsultaMapper;
import Mapper.PacienteMapper;
import Mapper.UsuarioMapper;
import conexion.IConexionBD;
import entidades.Consulta;
import entidades.Paciente;
import entidades.Usuario;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Maximiliano
 */
public class PacienteBO {

    private static final Logger logger = Logger.getLogger(PacienteBO.class.getName());
    private final IPacienteDAO pacienteDAO;
    private final ConsultaMapper mapper_consulta = new ConsultaMapper();
    private final PacienteMapper mapper_paciente = new PacienteMapper();
    private final UsuarioMapper mapper_usuario = new UsuarioMapper();

    
    public PacienteBO(IConexionBD conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
    }

    public PacienteDTO obtenerPacientePorNombreUsuario(String nombreUsuario) throws PersistenciaException, NegocioException {

        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new NegocioException("El nombre de usuario no es válido.");
        }

        Paciente paciente = pacienteDAO.obtenerPacientePorNombreUsuario(nombreUsuario);

        return mapper_paciente.toDTO(paciente);
    }
    
    
    public List<ConsultaDTO> obtenerHistorialConsultasDelPaciente(String nombrePaciente) throws NegocioException{
        if (nombrePaciente == null || nombrePaciente.trim().isEmpty()){
            throw new NegocioException("El nombre del paciente no puede ser nulo.");
        }
        
        try{
            
            if (!pacienteDAO.existePaciente(nombrePaciente)){
                throw new NegocioException("El paciente no existe.");
            }
            List<Consulta> consultasP = pacienteDAO.obtenerHistorialConsultasDelPaciente(nombrePaciente);            
            
            if (consultasP.isEmpty()) {
                throw new NegocioException("El paciente no tiene consultas registradas.");
        }
            return consultasP.stream()
                    .map(mapper_consulta::toDTO)
                    .collect(Collectors.toList());
            
    }   catch (PersistenciaException ex) {
        logger.log(Level.SEVERE, "Error al obtener el historial de consultas.", ex);
        throw new NegocioException("Error al obtener el historial de consultas.", ex);
    }
        }
}
