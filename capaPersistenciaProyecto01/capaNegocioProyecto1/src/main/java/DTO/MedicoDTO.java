/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Cita;
import entidades.Horario;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maximiliano
 */
public class MedicoDTO 
{
    private Usuario usuario;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String estado;
    private String especialidad;
    private String cedula;
    private List<Cita> citas;
    private List<Horario> horarios;

    // Constructor vacío
    public MedicoDTO() {
        this.citas = new ArrayList<>();  // Iniciar las listas de citas
        this.horarios = new ArrayList<>(); // Iniciar las listas de horarios
    }

    // Constructor con todos los parámetros
    public MedicoDTO(Usuario usuario, String nombre, String apellido_paterno, String apellido_materno, String estado, String especialidad, String cedula, List<Cita> citas, List<Horario> horarios) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estado = estado;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.citas = citas != null ? citas : new ArrayList<>();  
        this.horarios = horarios != null ? horarios : new ArrayList<>();  
    }

    // Constructor sin usuario
    public MedicoDTO(String nombre, String apellido_paterno, String apellido_materno, String estado, String especialidad, String cedula, List<Cita> citas, List<Horario> horarios) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estado = estado;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.citas = citas != null ? citas : new ArrayList<>();
        this.horarios = horarios != null ? horarios : new ArrayList<>();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "MedicoDTO{" + "usuario=" + usuario + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", estado=" + estado + ", especialidad=" + especialidad + ", cedula=" + cedula + ", citas=" + citas + ", horarios=" + horarios + '}';
    }
}
