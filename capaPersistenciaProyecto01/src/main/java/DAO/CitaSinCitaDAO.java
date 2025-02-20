/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;
import entidades.CitaSinCita;
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
    public String agendarCitaEmergencia(String especialidad, int idPaciente) throws PersistenciaException {
        String folioEmergencia = null;

        String consultaSQL = "{CALL AGREGAR_CITA_EMERGENCIA(?, ?)}";
        try (Connection con = this.conexion.crearConexion(); CallableStatement cb = con.prepareCall(consultaSQL)) {
            // Establecer los parámetros de entrada
            cb.setString(1, especialidad);
            cb.setInt(2, idPaciente);

            // Ejecutar el procedimiento
            ResultSet rs = cb.executeQuery();

            // Si hay resultados, obtener el folio
            if (rs.next()) {
                folioEmergencia = rs.getString("Folio");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenciaException("Error al agregar la cita de emergencia", e);
        }

        return folioEmergencia;
    }

}
