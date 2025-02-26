/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad Dirección.
 * Se utiliza para transportar información de las direcciones entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class DireccionDTO 
{
    private int id_direccion;
    private String colonia;
    private String ciudad;
    private String calle;

    /**
     * El constructor por defecto de la clase.
     */
    public DireccionDTO() {
    }

    /**
     * El constructor que inicializa los atributos de la dirección incluyendo el ID.
     * @param id_direccion El identificador único de la dirección.
     * @param colonia La colonia donde vive el paciente.
     * @param ciudad La ciudad donde vive el paciente.
     * @param calle La calle donde vive el paciente.
     */
    public DireccionDTO(int id_direccion, String colonia, String ciudad, String calle) {
        this.id_direccion = id_direccion;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    /**
     * El constructor que inicializa los atributos exceptuando el ID.
     * @param colonia La colonia donde vive el paciente.
     * @param ciudad La ciudad donde vive el paciente.
     * @param calle La calle donde vive el paciente.
     */
    public DireccionDTO(String colonia, String ciudad, String calle) {
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    /**
     * Se obtiene el identificador único de la dirección.
     * @return Regresa el identificador único de la dirección.
     */
    public int getId_direccion() {
        return id_direccion;
    }

    /**
     * Se establece el identificador único de la dirección.
     * @param id_direccion El identificador único de la dirección.
     */
    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    /**
     * Se obtiene la colonia donde vive el paciente.
     * @return Se regresa la colonia donde vive el paciente.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Se establece la colonia donde vive el paciente.
     * @param colonia La colonia donde vive el paciente.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Se obtiene la ciudad donde vive el paciente.
     * @return Se regresa la colonia donde vive el paciente.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Se establece la ciudad donde vive el paciente.
     * @param ciudad La ciudad donde vive el paciente.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Se obtiene la calle donde vive el paciente.
     * @return Se regresa la calle donde vive el paciente.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Se establece la calle donde vive el paciente.
     * @param calle La calle donde vive el paciente.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Representa la información de la dirección en cadena.
     * @return Regresa la cadena de texto con la información de la dirección DTO.
     */
    @Override
    public String toString() {
        return "DireccionDTO{" + "id_direccion=" + id_direccion + ", colonia=" + colonia + ", ciudad=" + ciudad + ", calle=" + calle + '}';
    }
}
