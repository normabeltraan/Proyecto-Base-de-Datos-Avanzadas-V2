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
 *
 * @author Maximiliano
 */
public class UsuarioMapper {

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
