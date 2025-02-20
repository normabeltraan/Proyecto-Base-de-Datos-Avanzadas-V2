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
 *
 * @author norma
 */
public interface ICitaDAO {

    public boolean agendarCita(Cita cita) throws PersistenciaException;
    
    public boolean cancelarCita(Cita cita) throws PersistenciaException;

    public List<HorarioDisponible> obtenerHorariosDisponibles(Date fecha, int id_usuario_medico) throws PersistenciaException;
}
