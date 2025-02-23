/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.CitaDTO;
import DTO.MedicoDTO;
import Exception.NegocioException;
import Mapper.CitaMapper;
import Mapper.MedicoMapper;
import conexion.IConexionBD;
import entidades.Cita;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano
 */
public class MedicoBO {

    private static final Logger logger = Logger.getLogger(MedicoBO.class.getName());
    private final IMedicoDAO medicoDAO;
    private final MedicoMapper mapper = new MedicoMapper();
    private final CitaMapper citamapper = new CitaMapper();

    public MedicoBO(IConexionBD conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
    }

    public List<String> obtenerEspecialidades() {
        try {
            return medicoDAO.obtenerEspecialidades();
        } catch (Exception e) {
            logger.severe("Error al obtener especialidades: " + e.getMessage());
            return null;
        }
    }

    public List<MedicoDTO> obtenerMedicosPorEspecialidad(String especialidad) {
        try {
            List<Medico> medicos = medicoDAO.obtenerMedicosPorEspecialidad(especialidad);

            return mapper.toDTOList(medicos);
        } catch (Exception e) {
            logger.severe("Error al obtener médicos por especialidad: " + e.getMessage());
            return null;
        }
    }

    public MedicoDTO obtenerMedicoPorId(int id_medico) throws PersistenciaException, NegocioException {
        if (id_medico <= 0) {
            throw new NegocioException("El ID del médico debe ser mayor que cero.");
        }
        Medico medico = medicoDAO.obtenerMedicoPorId(id_medico);

        return mapper.toDTO(medico);
    }

    public MedicoDTO obtenerMedicoPorNombreUsuario(String nombreUsuario) throws PersistenciaException, NegocioException {
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new NegocioException("El nombre de usuario no es válido.");
        }

        Medico medico = medicoDAO.obtenerMedicoPorNombreUsuario(nombreUsuario);

        return mapper.toDTO(medico);
    }

    public String cambiarEstadoMedico(MedicoDTO medico, String nuevo_estado) throws NegocioException, SQLException, PersistenciaException {
        try {
            if ("Inactivo".equals(nuevo_estado) && medicoDAO.medicoCitasActivas(medico.getUsuario().getId_usuario())) {
                return "No puedes darte de baja porque tienes citas activas.";
            }
            boolean actualizado = medicoDAO.actualizarEstadoMedico(medico.getUsuario().getId_usuario(), nuevo_estado);
            if (actualizado) {
                return "El estado se ha actualizado exitosamente a: " + nuevo_estado;
            } else {
                return "Hubo un error al intentar actualizar el estado.";
            }
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al cambiar el estado del medico", e);
        }
    }
    
    public MedicoDTO perfilMedico(int id_medico) throws NegocioException {
    if (id_medico <= 0) {
        throw new NegocioException("El ID del médico debe ser mayor que cero.");
    }

    try {
        Medico medico = medicoDAO.obtenerPerfilMedico(id_medico);

        if (medico == null) {
            throw new NegocioException("No se encontró un médico con el ID proporcionado.");
        }

        return mapper.toDTO(medico);
    } catch (PersistenciaException e) {
        logger.severe("Error en la capa de persistencia al obtener el perfil del médico: " + e.getMessage());
        throw new NegocioException("Error al obtener el perfil del médico", e);
    } catch (Exception e) {
        logger.severe("Error inesperado al obtener el perfil del médico: " + e.getMessage());
        throw new NegocioException("Ocurrió un error inesperado al obtener el perfil del médico", e);
    }
  }


    public List<CitaDTO> obtenerAgendaMedico(int idMedico)throws NegocioException{
        if (idMedico <= 0){
            throw new NegocioException("El ID debe ser mayor a 0.");
        }
        
        try{
            MedicoDTO medico = obtenerMedicoPorId(idMedico);
            if (medico==null){
                throw new NegocioException("No se encontro un medico con ese id.");
            }
            
            List<Cita> citas = medicoDAO.consultarAgendaMedico(idMedico);
            return citamapper.toDTOList(citas);
            
        } catch(PersistenciaException e){
            throw new NegocioException("No se pudo obtener la agenda del medico.");
        }
    }
}
