/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.DireccionDTO;
import entidades.Direccion;

/**
 *
 * @author Maximiliano
 */
public class DireccionMapper {

    public static Direccion toEntity(DireccionDTO direccionDTO) {
        if (direccionDTO == null) {
            return null;
        }

        return new Direccion(
                direccionDTO.getColonia(),
                direccionDTO.getCiudad(),
                direccionDTO.getCalle()
        );
    }

    public static DireccionDTO toDTO(Direccion direccion) {
        if (direccion == null) {
            return null;
        }

        return new DireccionDTO(
                direccion.getId_direccion(),
                direccion.getColonia(),
                direccion.getCiudad(),
                direccion.getCalle()
        );
    }
}
