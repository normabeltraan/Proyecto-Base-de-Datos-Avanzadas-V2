/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import DTO.DireccionDTO;
import entidades.Direccion;

/**
 * Clase encargada de convertir entre entidades Direccion y objetos de transferencia de datos DireccionDTO.
 * Facilita la conversión bidireccional entre los modelos de negocio y los DTOs para su uso en la capa de negocio o presentación.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */

public class DireccionMapper {

    /**
     * Este método convierte un objeto de tipo DireccionDTO a una entidad Direccion.
     * @param direccionDTO El objeto DireccionDTO que se desea convertir a entidad.
     * @return Regresa una instancia de Direccion con los mismos datos que el DTO o null si la entrada es null.
     */
    public Direccion toEntity(DireccionDTO direccionDTO) {
        if (direccionDTO == null) {
            return null;
        }

        return new Direccion(
                direccionDTO.getColonia(),
                direccionDTO.getCiudad(),
                direccionDTO.getCalle()
        );
    }

    /**
     * Este método convierte una entidad Direccion a un objeto de transferencia de datos DireccionDTO.
     * @param direccion La entidad Direccion que se desea convertir a DTO.
     * @return Regresa una instancia de DireccionDTO con los mismos datos que la entidad o null si la entrada es null.
     */
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
