/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GUI;

/**
 * Es la clase principal, para comenzar a mostrar todo.
 * Se crea una instancia de la clase IniciarSesion y se hace visible la
 * ventana de inicio de sesión, para que el usuario ingrese sus datos.
 * @author norma
 */
public class Inicio {

    /**
     * Método para ejecutar el sistema.
     * @param args 
     */
    public static void main(String[] args) {
        IniciarSesion pantallaIniciarSesion = new IniciarSesion();
        pantallaIniciarSesion.setVisible(true);
    }

}
 