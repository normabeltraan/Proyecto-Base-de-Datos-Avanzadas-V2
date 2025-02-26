/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa al paciente que se atiende en las citas y esta vinculado con el usuario.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class Paciente {

    private Usuario usuario;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;
    private LocalDate fecha_nacimiento;
    private String correo_electronico;
    private Direccion direccion;
    private List<Cita> citas;

    /**
     * Constructor que inicializa la lista de citas de los pacientes.
     */
    public Paciente() {
        this.citas = new ArrayList<>();  
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
    public Paciente(Usuario usuario, String nombre, String apellido_paterno, String apellido_materno, String telefono, LocalDate fecha_nacimiento, String correo_electronico, Direccion direccion, List<Cita> citas) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_electronico = correo_electronico;
        this.direccion = direccion;
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
    public Paciente(String nombre, String apellido_paterno, String apellido_materno, String telefono, LocalDate fecha_nacimiento, String correo_electronico, Direccion direccion, List<Cita> citas) {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo_electronico = correo_electronico;
        this.direccion = direccion;
        this.citas = citas != null ? citas : new ArrayList<>();
    }

    /**
     * Se obtiene el usuario del paciente.
     * @return Se regresa el usuario del paciente.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Se establece el usuario del paciente.
     * @param usuario El usuario del paciente.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Se establece la dirección donde vive el paciente.
     * @param direccion La dirección donde vive el paciente.
     */
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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
     * @return Regresa una cadena de texto con la información del paciente.
     */
    @Override
    public String toString() {
        return "Paciente{" + "usuario=" + usuario + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", telefono=" + telefono + ", fecha_nacimiento=" + fecha_nacimiento + ", correo_electronico=" + correo_electronico + ", direccion=" + direccion + ", citas=" + citas + '}';
    }

    

}
