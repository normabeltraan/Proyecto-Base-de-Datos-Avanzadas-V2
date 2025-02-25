/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.IMedicoDAO;
import DAO.MedicoDAO;
import DTO.CitaDTO;
import DTO.MedicoDTO;
import DTO.UsuarioDTO;
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
 * Clase de la capa de negocio que gestiona la información y operaciones
 * relacionadas con los médicos dentro del sistema de gestión de consultas
 * médicas.
 * 
 * Esta clase proporciona métodos para obtener información de médicos,
 * gestionar su disponibilidad, cambiar su estado y consultar su agenda de
 * citas.
 * 
 * Se encarga de la validación de datos antes de interactuar con la capa de
 * persistencia a través de IMedicoDAO.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class MedicoBO {

    private static final Logger logger = Logger.getLogger(MedicoBO.class.getName());
    private final IMedicoDAO medicoDAO;
    private final MedicoMapper mapper = new MedicoMapper();
    private final CitaMapper citamapper = new CitaMapper();

    /**
     * Constructor que inicializa el DAO de médicos con una conexión a la base de datos.
     * @param conexion El objeto de conexión a la base de datos.
     */
    public MedicoBO(IConexionBD conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
    }

    /**
     * Este método obtiene la lista de especialidades médicas disponibles en el sistema.
     * @return Regresa la lista de especialidades médicas disponibles o null si ocurre un error.
     */
    public List<String> obtenerEspecialidades() {
        try {
            return medicoDAO.obtenerEspecialidades();
        } catch (Exception e) {
            logger.severe("Error al obtener especialidades: " + e.getMessage());
            return null;
        }
    }

    /**
     * Este método obtiene la lista de médicos que pertenecen a una especialidad específica.
     * @param especialidad El nombre de la especialidad a buscar.
     * @return Regresa la lista de objetos MedicoDTO con la información de los médicos encontrados.
     */
    public List<MedicoDTO> obtenerMedicosPorEspecialidad(String especialidad) {
        try {
            List<Medico> medicos = medicoDAO.obtenerMedicosPorEspecialidad(especialidad);

            return mapper.toDTOList(medicos);
        } catch (Exception e) {
            logger.severe("Error al obtener médicos por especialidad: " + e.getMessage());
            return null;
        }
    }

    /**
     * Este método obtiene un médico por su identificador único.
     * @param id_medico El identificador único del médico.
     * @return Regresa el objeto MedicoDTO con la información del médico.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la capa de persistencia.
     * @throws NegocioException Lanza una excepción si el ID es inválido.
     */
    public MedicoDTO obtenerMedicoPorId(int id_medico) throws PersistenciaException, NegocioException {
        if (id_medico <= 0) {
            throw new NegocioException("El ID del médico debe ser mayor que cero.");
        }
        Medico medico = medicoDAO.obtenerMedicoPorId(id_medico);

        return mapper.toDTO(medico);
    }

    /**
     * Este método otiene un médico a partir de su nombre de usuario.
     * @param nombreUsuario El nombre de usuario del médico.
     * @return Regresa el objeto MedicoDTO con la información del médico.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la capa de persistencia.
     * @throws NegocioException Lanza una excepción si el nombre de usuario es inválido.
     */
    public MedicoDTO obtenerMedicoPorNombreUsuario(String nombreUsuario) throws PersistenciaException, NegocioException {
        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
            throw new NegocioException("El nombre de usuario no es válido.");
        }

        Medico medico = medicoDAO.obtenerMedicoPorNombreUsuario(nombreUsuario);

        return mapper.toDTO(medico);
    }

    /**
     * Este métodos cambia el estado de un médico a activo o inactivo.
     * @param medico El objeto MedicoDTO con la información del médico.
     * @param nuevo_estado El nuevo estado del médico.
     * @return Regresa un mensaje indicando el resultado de la operación.
     * @throws NegocioException Lanza una excepción si ocurre un error en la validación o actualización del estado.
     * @throws SQLException Lanza una excepción si ocurre un error en la base de datos.
     * @throws PersistenciaException Lanza una excepción si ocurre un error en la capa de persistencia.
     */
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

    /**
     * Este método obtiene el perfil de un médico basado en la información de su usuario.
     * @param usuarioDTO El objeto UsuarioDTO con la información del usuario.
     * @return Regresa el objeto MedicoDTO con la información del médico.
     * @throws NegocioException Lanza una excepción si el usuario es inválido.
     */
    public MedicoDTO perfilMedico(UsuarioDTO usuarioDTO) throws NegocioException {
        if (usuarioDTO == null) {
            throw new NegocioException("El usuario no puede ser nulo.");
        }

        Integer idMedico = usuarioDTO.getId_usuario();
        if (idMedico == null || idMedico <= 0) {
            throw new NegocioException("El ID del médico debe ser mayor que cero.");
        }

        try {
            Medico medico = medicoDAO.obtenerPerfilMedico(idMedico);

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

    /**
     * Este método obtiene la agenda de citas de un médico.
     * @param usuarioDTO El objeto UsuarioDTO con la información del usuario.
     * @return Regresa la lista de objetos CitaDTO con las citas programadas.
     * @throws NegocioException Lanza una excepción si el usuario es inválido o no se encuentra información.
     */
    public List<CitaDTO> obtenerAgendaMedico(UsuarioDTO usuarioDTO) throws NegocioException {
        if (usuarioDTO == null) {
            throw new NegocioException("El usuario no puede ser nulo.");
        }
        Integer idMedico = usuarioDTO.getId_usuario();
        if (idMedico == null || idMedico <= 0) {
            throw new NegocioException("El ID del médico debe ser mayor que cero.");
        }

        try {
            MedicoDTO medico = obtenerMedicoPorId(idMedico);
            if (medico == null) {
                throw new NegocioException("No se encontro un medico con ese id.");
            }

            List<Cita> citas = medicoDAO.consultarAgendaMedico(idMedico);
            return citamapper.toDTOList(citas);

        } catch (PersistenciaException e) {
            throw new NegocioException("No se pudo obtener la agenda del medico.");
        }
    }
}
