/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.Cita;
import entidades.CitaSinCita;
import entidades.Medico;
import excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

/**
 *
 * @author norma
 */
public class CitaSinCitaDAO implements ICitaSinCitaDAO {

    IConexionBD conexion;

    public CitaSinCitaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

    @Override
    public CitaSinCita agendarCitaEmergencia(String especialidad, int id_paciente) throws PersistenciaException {
        CitaSinCita citaSinCita = null;
        String consultaSQL = "{CALL AGREGAR_CITA_EMERGENCIA(?, ?)}";

        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {

            cb.setString(1, especialidad);
            cb.setInt(2, id_paciente);

            boolean hasResultSet = cb.execute();

            if (hasResultSet) {
                try (ResultSet rs = cb.getResultSet()) {
                    if (rs.next()) {
                        String folioEmergencia = rs.getString("folio_emergencia");
                        Timestamp fechaHora = rs.getTimestamp("fecha_hora");
                        int idMedico = rs.getInt("id_usuario_medico");
                        String especialidadMedico = rs.getString("especialidad");

                        MedicoDAO medicoDAO = new MedicoDAO(this.conexion);
                        Medico medico = medicoDAO.obtenerMedicoPorId(idMedico);

                        Cita cita = new Cita(fechaHora, "Activa", "Cita Emergencia", null, medico);

                        citaSinCita = new CitaSinCita(cita, folioEmergencia);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al agregar la cita de emergencia", e);
        }

        return citaSinCita;
    }

}
