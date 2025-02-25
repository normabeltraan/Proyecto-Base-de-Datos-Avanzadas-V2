/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.UsuarioDTO;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir entre entidades Usuario y objetos de transferencia de datos UsuarioDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class UsuarioMapper {

    /**
     * Este método convierte un objeto de tipo UsuarioDTO a una entidad Usuario.
     * 
     * @param usuarioDTO El objeto UsuarioDTO que se desea convertir a entidad.
     * @return Regresa una instancia de Usuario con los mismos datos que el DTO o null si la entrada es null.
     */
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }

        return new Usuario(
                usuarioDTO.getId_usuario(),
                usuarioDTO.getNombre_usuario(),
                usuarioDTO.getContrasenia()
        );
    }

    /**
     * Este método convierte una entidad Usuario a un objeto de transferencia de datos UsuarioDTO.
     * 
     * @param usuario La entidad Usuario que se desea convertir a DTO.
     * @return Regresa una instancia de UsuarioDTO con los mismos datos que la entidad o null si la entrada es null.
     */
    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return new UsuarioDTO(
                usuario.getId_usuario(),
                usuario.getNombre_usuario(),
                usuario.getContrasenia()
        );
    }

    /**
     * Este método convierte una lista de objetos UsuarioDTO en una lista de entidades Usuario.
     * 
     * @param usuarioDTOs Lista de objetos UsuarioDTO a convertir.
     * @return Regresa una lista de entidades Usuario con los datos de los DTOs o una lista vacía si la entrada es null o está vacía.
     */
    public List<Usuario> toEntityList(List<UsuarioDTO> usuarioDTOs) {
        if (usuarioDTOs == null || usuarioDTOs.isEmpty()) {
            return new ArrayList<>();
        }

        List<Usuario> usuarios = new ArrayList<>();
        for (UsuarioDTO usuarioDTO : usuarioDTOs) {
            usuarios.add(toEntity(usuarioDTO));
        }

        return usuarios;
    }

    /**
     * Este método convierte una lista de entidades Usuario en una lista de objetos UsuarioDTO.
     * 
     * @param usuarios Lista de entidades Usuario a convertir.
     * @return Regresa una lista de UsuarioDTO con los datos de las entidades o una lista vacía si la entrada es null o está vacía.
     */

    public List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        if (usuarios == null || usuarios.isEmpty()) {
            return new ArrayList<>();
        }

        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDTOs.add(toDTO(usuario));
        }

        return usuarioDTOs;
    }
}
