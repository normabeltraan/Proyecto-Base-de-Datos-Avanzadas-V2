/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Cita;
import entidades.Direccion;
import entidades.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class PacienteDTO {

    private UsuarioDTO usuarioDTO;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;
    private LocalDate fecha_nacimiento;
    private String correo_electronico;
    private DireccionDTO direccionDTO;
    private List<Cita> citas;

    public PacienteDTO() {
        this.citas = new ArrayList<>();
        this.direccionDTO = new DireccionDTO();
    }

    public PacienteDTO(UsuarioDTO usuario, String nombre, String apellido_paterno, String apellido_materno, String telefono, LocalDate fecha_nacimiento, String correo_electronico, DireccionDTO direccion, List<Cita> citas) {
        this.usuarioDTO = usuario;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_electronico = correo_electronico;
        this.direccionDTO = direccion != null ? direccion : new DireccionDTO(); 
        this.citas = citas != null ? citas : new ArrayList<>();
    }

    public PacienteDTO(String nombre, String apellido_paterno, String apellido_materno, String telefono, LocalDate fecha_nacimiento, String correo_electronico, DireccionDTO direccion, List<Cita> citas) {
        this.usuarioDTO = new UsuarioDTO();
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_electronico = correo_electronico;
        this.direccionDTO = direccion != null ? direccion : new DireccionDTO(); 
        this.citas = citas != null ? citas : new ArrayList<>();
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public DireccionDTO getDireccionDTO() {
        return direccionDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    public void setDireccionDTO(DireccionDTO direccionDTO) {
        this.direccionDTO = direccionDTO;
    }
    

    public UsuarioDTO getUsuario() {
        return usuarioDTO;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuarioDTO = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public DireccionDTO getDireccion() {
        return direccionDTO;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccionDTO = direccion;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "PacienteDTO{" + "usuario=" + usuarioDTO + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento + ", correo_electronico=" + correo_electronico + ", direccion=" + direccionDTO + ", citas=" + citas + '}';
    }
}
