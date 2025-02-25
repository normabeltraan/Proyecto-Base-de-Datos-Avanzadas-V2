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
 * La clase ConexionBD es la encargada de gestionar la conexión a la base de datos de mysql,
 * en este caso de nuestro proyecto. Esta clase maneja las credenciales de acceso que son el usuario que tienes en tu base de datos,
 * tu contraseña y finalmente se cuenta con la url mejor dicho la cadena de conexión con nuestra base de datos.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class ConexionBD implements IConexionBD {

    final String USUARIO = "root";
    final String PASS = "17062Bel";
    final String CADENA_CONEXION = "jdbc:mysql://localhost:3306/sistemaconsultas";

    /**
     * En este método se crea la conexión con la base de datos, 
     * utilizando el drive manager para obtener una conexion con los parámetros establecidos y la url de la conexión.
     * 
     * @return La conexión de la base de datos.
     * @throws PersistenciaException excepción que lanzará si existe un error al intentar establecer la conexión.
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
