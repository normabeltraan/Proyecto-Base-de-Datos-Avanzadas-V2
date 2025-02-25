package DTO;

import entidades.Cita;
import entidades.Horario;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;


/**
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad Medico.
 * Se utiliza para transportar información de medicos entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class MedicoDTO {

    private UsuarioDTO usuarioDTO; 
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String estado;
    private String especialidad;
    private String cedula;
    private List<Cita> citas;
    private List<Horario> horarios;

    /**
     * El constructor que inicializa las listas de los horarios y las citas.
     */
    public MedicoDTO() {
        this.citas = new ArrayList<>();  // Iniciar las listas de citas
        this.horarios = new ArrayList<>(); // Iniciar las listas de horarios
    }

    /**
     * Constructor que inicializa los atributos del médico incluyendo el usuario.
     * @param usuarioDTO El usuario del médico.
     * @param nombre El nombre del médico.
     * @param apellido_paterno El apellido paterno del médico.
     * @param apellido_materno El apellido materno del médico.
     * @param estado El estado del médico si esta activo o no.
     * @param especialidad La especialidad del médico.
     * @param cedula La cédula profesional del médico.
     * @param citas Las citas que atiende el médico.
     * @param horarios Los horarios que atiende las consultas el médico.
     */
    public MedicoDTO(UsuarioDTO usuarioDTO, String nombre, String apellido_paterno, String apellido_materno, String estado, String especialidad, String cedula, List<Cita> citas, List<Horario> horarios) {
        this.usuarioDTO = usuarioDTO;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estado = estado;
        this.especialidad = especialidad;
        this.cedula = cedula;
        this.citas = citas != null ? citas : new ArrayList<>();
        this.horarios = horarios != null ? horarios : new ArrayList<>();
    }

    /**
     * Constructor que inicializa los atributos del médico exceptuando el usuario.
     * @param nombre El nombre del médico.
     * @param apellido_paterno El apellido paterno del médico.
     * @param apellido_materno El apellido materno del médico.
     * @param estado El estado del médico si esta activo o no.
     * @param especialidad La especialidad del médico.
     * @param cedula La cédula profesional del médico.
     * @param citas Las citas que atiende el médico.
     * @param horarios Los horarios que atiende las consultas el médico.
     */
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
    
    /**
     * Se obtiene el usuario del médico.
     * @return Se regresa el usuario del médico.
     */
    public UsuarioDTO getUsuario() {
        return usuarioDTO;
    }

    /**
     * Se establece el usuario del médico.
     * @param usuario El usuario del médico.
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuarioDTO = usuario;
    }

    /**
     * Se obtiene el nombre del médico.
     * @return Se regresa el nombre del médico.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Se establece el nombre del médico.
     * @param nombre El nombre del médico.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Se obtiene el apellido paterno del médico.
     * @return Se regresa el apellido paterno del médico.
     */
    public String getApellido_paterno() {
        return apellido_paterno;
    }

    /**
     * Se establece el apellido paterno del médico.
     * @param apellido_paterno El apellido paterno del médico.
     */
    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    /**
     * Se obtiene el apellido materno del médico.
     * @return Se regresa el apellido materno del médico.
     */
    public String getApellido_materno() {
        return apellido_materno;
    }

    /**
     * Se establece el apellido materno del médico.
     * @param apellido_materno El apellido materno del médico.
     */
    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    /**
     * Se obtiene el estado del médico.
     * @return Se regresa el estado del médico.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Se establece el estado del médico.
     * @param estado El estado del médico.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Se obtiene la especialidad del médico.
     * @return Se regresa la especialidad del médico.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Se establece la especialidad del médico.
     * @param especialidad La especialidad del médico.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Se obtiene la cédula profesional del médico.
     * @return Se regresa la cédula profesional del médico.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Se establece la cédula profesional del médico.
     * @param cedula La cédula profesional del médico.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Se obtiene la lista de citas que el médico atiende.
     * @return Se regresa la lista de citas que el médico atiende.
     */
    public List<Cita> getCitas() {
        return citas;
    }

    /**
     * Se establece la lista de citas que el médico atiende.
     * @param citas La lista de citas que el médico atiende.
     */
    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    /**
     * Se obtiene la lista de los horarios de los médicos.
     * @return Se regresa la lista de los horarios de los médicos.
     */
    public List<Horario> getHorarios() {
        return horarios;
    }

    /**
     * Se establece la lista de los horarios de los médicos.
     * @param horarios Se regresa la lista de los horarios de los médicos.
     */
    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    /**
     * Representación en cadena de la información del médico.
     * @return Regresa una cadena de texto con la información del médico DTO.
     */
    @Override
    public String toString() {
        return "MedicoDTO{" + "usuario=" + usuarioDTO + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno=" + apellido_materno + ", estado=" + estado + ", especialidad=" + especialidad + ", cedula=" + cedula + ", citas=" + citas + ", horarios=" + horarios + '}';
    }
}
