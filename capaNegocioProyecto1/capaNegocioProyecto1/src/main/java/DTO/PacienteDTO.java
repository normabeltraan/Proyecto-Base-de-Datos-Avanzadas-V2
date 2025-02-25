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
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad Paciente.
 * Se utiliza para transportar información de pacientes entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
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

    /**
     * Constructor que inicializa la lista de citas de los pacientes y un objeto de la DirecciónDTO.
     */
    public PacienteDTO() {
        this.citas = new ArrayList<>();
        this.direccionDTO = new DireccionDTO();
    }

    /**
     * Constructor que inicializa los atributos del paciente incluyendo el usuario.
     * @param usuario El usuario vinculado con el paciente y su identificador.
     * @param nombre El nombre del paciente.
     * @param apellido_paterno El apellido paterno del paciente.
     * @param apellido_materno El apellido materno del paciente.
     * @param telefono El teléfono del paciente.
     * @param fecha_nacimiento La fecha de nacimiento del paciente.
     * @param correo_electronico El correo electrónico del paciente.
     * @param direccion La dirección donde vive el paciente.
     * @param citas Las citas que debe atender el paciente.
     */
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

    /**
     * Constructor que inicializa los atributos del paciente exceptuando al usuario.
     * @param nombre El nombre del paciente.
     * @param apellido_paterno El apellido paterno del paciente.
     * @param apellido_materno El apellido materno del paciente.
     * @param telefono El teléfono del paciente.
     * @param fecha_nacimiento La fecha de nacimiento del paciente.
     * @param correo_electronico El correo electrónico del paciente.
     * @param direccion La dirección donde vive el paciente.
     * @param citas Las citas que debe atender el paciente.
     */
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

    /**
     * Se obtiene el usuario DTO.
     * @return Se regresa el usuario DTO
     */
    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    /**
     * Se obtiene la dirección DTO.
     * @return Se regresa la dirección DTO
     */
    public DireccionDTO getDireccionDTO() {
        return direccionDTO;
    }

    /**
     * Se establece el usuario DTO.
     * @param usuarioDTO El usuario DTO.
     */
    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

    /**
     * Se establece la dirección DTO.
     * @param direccionDTO La dirección DTO.
     */
    public void setDireccionDTO(DireccionDTO direccionDTO) {
        this.direccionDTO = direccionDTO;
    }
    
    /**
     * Se obtiene el usuario del paciente.
     * @return Se regresa el usuario del paciente.
     */
    public UsuarioDTO getUsuario() {
        return usuarioDTO;
    }

    /**
     * Se establece el usuario del paciente.
     * @param usuario El usuario del paciente.
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuarioDTO = usuario;
    }

    /**
     * Se obtiene el nombre del paciente.
     * @return Se regresa el nombre del paciente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Se establece el nombre del paciente.
     * @param nombre El nombre del paciente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Se obtiene el apellido paterno del paciente.
     * @return Se regresa el apellido paterno del paciente.
     */
    public String getApellido_paterno() {
        return apellido_paterno;
    }

    /**
     * Se establece el apellido paterno del paciente.
     * @param apellido_paterno El apellido paterno del paciente. 
     */
    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    /**
     * Se obtiene el apellido materno del paciente.
     * @return Se regresa el apellido materno del paciente.
     */
    public String getApellido_materno() {
        return apellido_materno;
    }

    /**
     * Se establece el apellido materno del paciente
     * @param apellido_materno El apellido materno del paciente.
     */
    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    /**
     * Se obtiene el teléfono del paciente.
     * @return Se regresa el teléfono del paciente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Se establece el teléfono del paciente.
     * @param telefono El teléfono del paciente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Se obtiene la fecha de nacimiento del paciente.
     * @return Se regresa la fecha de nacimiento del paciente.
     */
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Se establece la fecha de nacimiento del paciente.
     * @param fecha_nacimiento La fecha de nacimiento del paciente.
     */
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Se obtiene el correo electrónico del paciente.
     * @return Se regresa el correo elctrónico del paciente.
     */
    public String getCorreo_electronico() {
        return correo_electronico;
    }

    /**
     * Se establece el correo electrónico del paciente.
     * @param correo_electronico El correo electrónico del paciente.
     */
    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    /**
     * Se obtiene la dirección donde vive el paciente.
     * @return Se regresa la dirección donde vive el paciente.
     */
    public DireccionDTO getDireccion() {
        return direccionDTO;
    }

    /**
     * Se establece la dirección donde vive el paciente.
     * @param direccion La dirección donde vive el paciente.
     */
    public void setDireccion(DireccionDTO direccion) {
        this.direccionDTO = direccion;
    }

    /**
     * Se obtienen la lista de citas del paciente.
     * @return Se regresa la lista de citas del paciente.
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Se establece la lista de citas del paciente.
     * @param citas La lista de citas del paciente.
     */
    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    /**
     * Representación en cadena de la información del paciente.
     * @return Regresa una cadena de texto con la información del paciente DTO.
     */
    @Override
    public String toString() {
        return "PacienteDTO{" + "usuario=" + usuarioDTO + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento + ", correo_electronico=" + correo_electronico + ", direccion=" + direccionDTO + ", citas=" + citas + '}';
    }
}
