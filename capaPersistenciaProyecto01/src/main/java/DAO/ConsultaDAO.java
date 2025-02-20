/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.IConexionBD;

/**
 *
 * @author norma
 */
public class ConsultaDAO implements IConsultaDAO {

    IConexionBD conexion;

    public ConsultaDAO(IConexionBD conexion) {
        this.conexion = conexion;
    }

}
