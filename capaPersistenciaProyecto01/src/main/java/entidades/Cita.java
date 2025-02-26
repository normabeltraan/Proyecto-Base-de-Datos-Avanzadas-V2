/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;


import java.sql.Timestamp;

/**
 * Esta clase representa una cita médica normal las cuáles se agendan con antelación y se pueden cancelar.
 * Esta clase tiene información relevante como el paciente y médico involucrados, la fecha y hora programada de las citas,
 * Las citas pueden ser agendadas o de emergencia.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class Cita {

    private int id_cita;
    private Timestamp  fecha_hora;
    private String estado;
    private String tipo;
    private Paciente paciente;
    private Medico medico;

    /**
     * El constructor por defecto, crea la instancia vacía de la clase.
     */
    public Cita() {
    }
    
    /**
     * Constructor con todos los atributos de la clase Cita, incluyendo el ID.
     * @param id_cita El identificador de la cita.
     * @param fecha_hora La fecha y hora de la cita.
     * @param estado El estado actual de la cita.
     * @param tipo El tipo de cita si es programada o de emergencia.
     * @param paciente El paciente que será atendido en la cita.
     * @param medico El médico responsable de la consulta.
     */
    public Cita(int id_cita, Timestamp  fecha_hora, String estado, String tipo, Paciente paciente, Medico medico) {
        this.id_cita = id_cita;
        this.fecha_hora = fecha_hora;
        this.estado = estado;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }
    
    /**
     * Constructor con todos los atributos de la clase Cita pero sin el ID.
     * @param fecha_hora La fecha y hora de la cita.
     * @param estado El estado actual de la cita.
     * @param tipo El tipo de cita si es programada o de emergencia.
     * @param paciente El paciente que será atendido en la cita.
     * @param medico El médico responsable de la consulta.
     */
    public Cita(Timestamp  fecha_hora, String estado, String tipo, Paciente paciente, Medico medico) {
        this.fecha_hora = fecha_hora;
        this.estado = estado;
        this.tipo = tipo;
        this.paciente = paciente;
        this.medico = medico;
    }
    
    /**
     * Se obtiene el identificador único de la cita.
     * @return Regresa el identificador único de la cita.
     */
    public int getId_cita() {
        return id_cita;
    }
    
    /**
     * Se establece el identificador único de la cita.
     * @param id_cita El identificador único de la cita.
     */
    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }
    
    /**
     * Se obtiene la fecha y hora de la cita.
     * @return Regresa la fecha y hora de la cita.
     */
    public Timestamp  getFecha_hora() {
        return fecha_hora;
    }
    
    /**
     * Se establece la fecha y hora de la cita.
     * @param fecha_hora La fecha y hora de la cita.
     */
    public void setFecha_hora(Timestamp  fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
    
    /**
     * Se obtiene el estado actual de la cita.
     * @return Regresa el estado actual de la cita.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Se establece el estado actual de la cita.
     * @param estado El estado actual de la cita.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Se obtiene el tipo de cita (Programada o de emergencia).
     * @return Regresa el tipo de la cita (Programada o de emergencia.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Se establece el tipo de la cita (Programada o de emergencia).
     * @param tipo El tipo de la cita (Programada o de emergencia).
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Se obtiene el paciente que será atendido en la cita.
     * @return Regresa el paciente atendido de la cita.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Se establece el paciente atendido de la cita.
     * @param paciente El paciente atendido de la cita.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Se obtiene al médico encargado de la cita.
     * @return Regresa el médico encargado de la cita.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Se establece al médico encargado de la cita.
     * @param medico El médico encargado de la cita.
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Este método es la representación en cadena de la cita.
     * @return Regresa la cadena de texto con la información de la cita.
     */
    @Override
    public String toString() {
        return "Cita{" + "id_cita=" + id_cita + ", fecha_hora=" + fecha_hora + ", estado=" + estado + ", tipo=" + tipo + ", paciente=" + paciente + ", medico=" + medico + '}';
    }

}
