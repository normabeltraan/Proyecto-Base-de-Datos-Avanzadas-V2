/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IMedicoDAO {

    public List<String> obtenerEspecialidades() throws SQLException, PersistenciaException;
    
    public List<Medico> obtenerMedicosPorEspecialidad(String especialidad) throws PersistenciaException;
    
    public Medico obtenerMedicoPorId(int idMedico) throws PersistenciaException;
}
