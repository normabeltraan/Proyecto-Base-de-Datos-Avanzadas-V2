/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import excepciones.PersistenciaException;
import java.sql.Connection;

/**
 *
 * @author norma
 */
public interface IConexionBD {

    /**
     * Crea la conexión con la base de datos
     *
     * @return la conexión
     * @throws PersistenciaException excepción que lanzará si existe un error
     */
    public Connection crearConexion() throws PersistenciaException;
}
