/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author norma
 */
public class ConexionBD implements IConexionBD {

    final String USUARIO = "root";
    final String PASS = "itson";
    final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/sistemaconsultas";

    /**
     * Crea la conexión con la base de datos
     *
     * @return la conexión
     * @throws PersistenciaException excepción que lanzará si existe un error
     */
    @Override
    public Connection crearConexion() throws PersistenciaException {

        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, PASS);
            return conexion;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al conectar a la base de datos", ex);

        }

    }
}
