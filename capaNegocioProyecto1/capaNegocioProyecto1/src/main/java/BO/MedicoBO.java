/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.MedicoDTO;
import Mapper.MedicoMapper;
import conexion.IConexionBD;
import entidades.Medico;
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
}
