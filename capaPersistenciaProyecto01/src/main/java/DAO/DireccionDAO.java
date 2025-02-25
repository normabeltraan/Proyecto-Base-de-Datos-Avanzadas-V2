/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;

/**
 * Esta clase implementa la interfaz IDireccionDAO se encarga de gestionar las direcciones del paciente y/o otras operaciones,
 * que involucren a la dirección.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class DireccionDAO implements IDireccionDAO {
    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión con la base de datos.
     * @param conexion La conexión de la base de datos.
     */
    public DireccionDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    
}
