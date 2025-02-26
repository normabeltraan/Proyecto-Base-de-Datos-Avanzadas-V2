/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import excepciones.PersistenciaException;
import java.sql.Connection;

/**
 * Esta interfaz define la gestión de la base de datos, además establece el método para obtener la conexión con la base de datos de mysql,
 * encapsulando el proceso de la conexión y del manejo de excepciones.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public interface IConexionBD {

    /**
     * Este método crea y devuelve la conexión activa con la base de datos de mysql, este método se puede implementar en cualquier clase donde,
     * se manejen conexiones.
     *
     * @return La conexión activa con la base de datos.
     * @throws PersistenciaException excepción que lanzará si existe un error al establecer la conexión con la base de datos.
     */
    public Connection crearConexion() throws PersistenciaException;
}
