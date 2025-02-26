/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;

/**
 * Esta clase implementa la interfaz IHorarioDAO que se encarga de gestionar los horarios de las consultas y/o operaciones,
 * que la involucren.
 * 
 * @author Norma Alicia Beltrán Martín - 00000252102
 * @author Maximiliano Reyna Aguilar - 00000244877
 * @author Katia Ximena Návarez Espinoza - 00000252855
 */
public class HorarioDAO implements IHorarioDAO {
    IConexionBD conexion;

    /**
     * Constructor que recibe la conexión de la base de datos.
     * @param conexion La conexión de la base de datos.
     */
    public HorarioDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }
    
}
