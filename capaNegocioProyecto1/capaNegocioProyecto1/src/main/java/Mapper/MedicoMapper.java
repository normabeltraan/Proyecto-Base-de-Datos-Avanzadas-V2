/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.MedicoDTO;
import DTO.UsuarioDTO;
import entidades.Medico;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir entre entidades Medico y objetos de transferencia de datos MedicoDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class MedicoMapper {
    
    private UsuarioMapper usuarioMapper;
    
    /**
     * Constructor que inicializa el UsuarioMapper.
     */
    public MedicoMapper() {
        this.usuarioMapper = new UsuarioMapper();  
    }
    
    /**
     * Este método convierte un objeto de tipo MedicoDTO a una entidad Medico.
     * 
     * @param medicoDTO El objeto MedicoDTO que se desea convertir a entidad.
     * @return Regresa una instancia de Medico con los mismos datos que el DTO o null si la entrada es null.
     */
    public Medico toEntity(MedicoDTO medicoDTO) {
        if (medicoDTO == null) {
            return null;
        }

        // Convertimos el DTO a la entidad Medico
        Usuario usuario = usuarioMapper.toEntity(medicoDTO.getUsuario());

        return new Medico(
                usuario, // Usuario
                medicoDTO.getNombre(), // Nombre
                medicoDTO.getApellido_paterno(), // Apellido paterno
                medicoDTO.getApellido_materno(), // Apellido materno
                medicoDTO.getEstado(), // Estado
                medicoDTO.getEspecialidad(), // Especialidad
                medicoDTO.getCedula(), // Cedula
                null, // Atributo adicional si no aplica (por ejemplo, Citas)
                null // Otro atributo si aplica (por ejemplo, Dirección)
        );
    }

    /**
     * Este método convierte una entidad Medico a un objeto de transferencia de datos MedicoDTO.
     * 
     * @param medico La entidad Medico que se desea convertir a DTO.
     * @return Regresa una instancia de MedicoDTO con los mismos datos que la entidad o null si la entrada es null.
     */
    public MedicoDTO toDTO(Medico medico) {
        if (medico == null) {
            return null;
        }

        // Convertimos la entidad Medico a su DTO correspondiente
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(medico.getUsuario());

        return new MedicoDTO(
                usuarioDTO, // UsuarioDTO
                medico.getNombre(), // Nombre
                medico.getApellido_paterno(), // Apellido paterno
                medico.getApellido_materno(), // Apellido materno
                medico.getEstado(), // Estado
                medico.getEspecialidad(), // Especialidad
                medico.getCedula(), // Cedula
                null, // Atributo adicional si no aplica
                null // Otro atributo si aplica
        );
    }

    /**
     * Este método convierte una lista de entidades Medico en una lista de objetos MedicoDTO.
     * 
     * @param medicos Lista de entidades Medico a convertir.
     * @return Regresa una lista de MedicoDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */
    public List<MedicoDTO> toDTOList(List<Medico> medicos) {
        if (medicos == null || medicos.isEmpty()) {
            return new ArrayList<>();
        }
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        for (Medico medico : medicos) {
            medicosDTO.add(toDTO(medico));
        }
        return medicosDTO;
    }

    /**
     * Este método convierte una lista de objetos CitaDTO en una lista de entidades Cita.
     * 
     * @param medicosDTO Lista de objetos MedicoDTO a convertir.
     * @return Regresa una lista de entidades Medico con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */
    public List<Medico> toEntityList(List<MedicoDTO> medicosDTO) {
        if (medicosDTO == null || medicosDTO.isEmpty()) {
            return new ArrayList<>();
        }
        List<Medico> medicos = new ArrayList<>();
        for (MedicoDTO medicoDTO : medicosDTO) {
            medicos.add(toEntity(medicoDTO));
        }
        return medicos;
    }
}
