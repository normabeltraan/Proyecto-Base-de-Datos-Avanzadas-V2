/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import entidades.Cita;

/**
 * Esta clase que representa un objeto de transferencia de datos (DTO) para la entidad Consulta.
 * Se utiliza para transportar información de las consultas entre las distintas capas del sistema.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class ConsultaDTO 
{
    private int id_consulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private Cita cita;

    /**
     * El constructor por defecto de la clase.
     */
    public ConsultaDTO() {
    }

    /**
     * Constructor que inicializa los atributos de la clase incluyendo el ID.
     * @param id_consulta El identificador único de la clase.
     * @param diagnostico El diagnóstico realizado por el médico.
     * @param tratamiento El tratamiento establecido para el paciente.
     * @param observaciones Las observaciones adicionales del médico.
     * @param cita La cita asociada a la consulta.
     */
    public ConsultaDTO(int id_consulta, String diagnostico, String tratamiento, String observaciones, Cita cita) {
        this.id_consulta = id_consulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.cita = cita;
    }

    /**
     * Constructor que inicializa los atributos de la clase exceptuando el ID.
     * @param diagnostico El diagnóstico realizado por el médico.
     * @param tratamiento El tratamiento establecido para el paciente.
     * @param observaciones Las observaciones adicionales del médico.
     * @param cita La cita asociada a la consulta.
     */
    public ConsultaDTO(String diagnostico, String tratamiento, String observaciones, Cita cita) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.cita = cita;
    }
    
    /**
     * Se establece el identificador único de la consulta médica.
     * @param id_consulta El identificador único de la consulta médica.
     */
    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    /**
     * Se establece el diagnóstico realizado por el médico.
     * @param diagnostico El diagnóstico realizado por el médico.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Se establece el tratamiento establecido para el paciente.
     * @param tratamiento El tratamiento establecido para el paciente.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Se establece las observaciones adicionales del médico.
     * @param observaciones Las observaciones adicionales del médico.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Se establece la cita asociada a la consulta.
     * @param cita La cita asociada a la consulta.
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Se obtiene el identificador único de la consulta médica.
     * @return Se regresa el identificador único de la consulta médica.
     */
    public int getId_consulta() {
        return id_consulta;
    }

    /**
     * Se obtiene el diagnóstico realizado por el médico.
     * @return Se regresa el diagnóstico realizado por el médico.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Se obtiene el tratamiento establecido para el paciente.
     * @return Se regresa el tratamiento establecido para el paciente.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Se obtiene las observaciones adicionales del médico.
     * @return Se regresa las observaciones adicionales del médico.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Se obtiene la cita asociada con la consulta médica.
     * @return Se regresa la cita asociada con la consulta médica.
     */
    public Cita getCita() {
        return cita;
    }
    
    /**
     * Representación en cadena de la información de la consulta médica.
     * @return Regresa una cadena de texto con la información de la consulta DTO médica.
     */
    @Override
    public String toString() {
        return "ConsultaDTO{" + "id_consulta=" + id_consulta + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", observaciones=" + observaciones + ", cita=" + cita + '}';
    }
}
