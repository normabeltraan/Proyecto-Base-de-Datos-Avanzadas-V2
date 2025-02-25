/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Cita;
import entidades.CitaSinCita;
import entidades.HorarioDisponible;
import excepciones.PersistenciaException;
import java.sql.Date;
import java.util.List;

/**
 * Esta interfaz define las operaciones que se realizan en las citas agendadas.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface ICitaDAO {

    /**
     * Este método agenda una nueva cita en el sistema.
     * @param cita El objeto que contiene la información de la cita a agendar.
     * @return Regresa verdadero si la cita fue agendada correctamente, y falso si es lo contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean agendarCita(Cita cita) throws PersistenciaException;
    
    /**
     * Este método cancela una cita previamente agendada.
     * @param cita El objeto que contiene la información de la cita a cancelar.
     * @return Regresa verdadero si la cita fue cancelada exitosamente, y falso si fue lo contrario.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public boolean cancelarCita(Cita cita) throws PersistenciaException;

    /**
     * Este método obtiene los horarios disponibles para un médico en alguna fecha específica.
     * @param fecha Fecha en la que se buscan los horarios disponibles.
     * @param id_usuario_medico El identificador único del usuario médico.
     * @return Regresa la lista de horarios disponibles de un médico.
     * @throws PersistenciaException Lanzará una excepción si ocurre un error durante la operación.
     */
    public List<HorarioDisponible> obtenerHorariosDisponibles(Date fecha, int id_usuario_medico) throws PersistenciaException;
}
