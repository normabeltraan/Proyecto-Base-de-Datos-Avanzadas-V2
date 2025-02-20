/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Maximiliano
 */
public class DireccionDTO 
{
    private int id_direccion;
    private String colonia;
    private String ciudad;
    private String calle;

    public DireccionDTO() {
    }

    public DireccionDTO(int id_direccion, String colonia, String ciudad, String calle) {
        this.id_direccion = id_direccion;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    public DireccionDTO(String colonia, String ciudad, String calle) {
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.calle = calle;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public String toString() {
        return "DireccionDTO{" + "id_direccion=" + id_direccion + ", colonia=" + colonia + ", ciudad=" + ciudad + ", calle=" + calle + '}';
    }
}
